package com.devesh.gsco.shipment;

import com.devesh.gsco.carrier.Carrier;
import com.devesh.gsco.carrier.CarrierRepository;
import com.devesh.gsco.inventory.Inventory;
import com.devesh.gsco.inventory.InventoryRepository;
import com.devesh.gsco.order.Order;
import com.devesh.gsco.order.OrderRepository;
import com.devesh.gsco.trackinglog.TrackingLog;
import com.devesh.gsco.trackinglog.TrackingLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final OrderRepository orderRepository;
    private final CarrierRepository carrierRepository;
    private final TrackingLogRepository trackingLogRepository;
    private final InventoryRepository inventoryRepository;

    public ShipmentDto reroute(Integer shipmentId, String newDestination) {

        Shipment shipment = shipmentRepository.findById(shipmentId).orElse(null);

        if (shipment != null) {
            shipment.setDestination(newDestination);
            return convertToShipmentDto(shipmentRepository.save(shipment));
        }

        return null;
    }

    public ShipmentDto updateCurrentLocation(Integer shipmentId, double latitude, double longitude) {

        Shipment shipment = shipmentRepository.findById(shipmentId).orElse(null);

        if (shipment != null) {
            shipment.setCurrentLatitude(latitude);
            shipment.setCurrentLongitude(longitude);
            return convertToShipmentDto(shipmentRepository.save(shipment));
        }

        return null;
    }

    public long calculateDelay(Integer shipmentId, Date actualArrival) {

        Shipment shipment = shipmentRepository.findById(shipmentId).orElse(null);

        if (shipment != null && shipment.getEstimatedArrival() != null) {

            long diff = actualArrival.getTime() - shipment.getEstimatedArrival().getTime();

            return diff / (1000 * 60); // delay in minutes
        }

        return 0;
    }

    public Shipment createShipment(int orderId, int carrierId, Shipment shipment) {

        Order order = orderRepository.findById(orderId).orElse(null);
        Carrier carrier = carrierRepository.findById(carrierId).orElse(null);

        if (order != null) {

            order.setStatus("shipping");

            shipment.setOrder(order);
            shipment.setCarrier(carrier);

            orderRepository.save(order);

            TrackingLog log = TrackingLog.builder()
                    .timestamp(new Date())
                    .latitude(0.0)
                    .longitude(0.0)
                    .event("Shipment created")
                    .shipment(shipment)
                    .build();

            trackingLogRepository.save(log);

            return shipmentRepository.save(shipment);
        }

        return null;
    }

    public ShipmentDto dispatchOrder(
            Integer orderId,
            Integer carrierId,
            DispatchRequest request
    ) {

        Inventory inventory =
                inventoryRepository
                        .findByProduct_ProductIdAndWarehouse_WarehouseId(
                                request.productId(),
                                request.warehouseId()
                        );

        if (inventory == null || inventory.getQuantity() < request.quantity()) {
            return null;
        }

        inventory.setQuantity(inventory.getQuantity() - request.quantity());
        inventoryRepository.save(inventory);

        Order order = orderRepository.findById(orderId).orElse(null);
        Carrier carrier = carrierRepository.findById(carrierId).orElse(null);

        if (order == null || carrier == null || !carrier.isOnDuty()) {
            return null;
        }

        order.setStatus("shipping");
        orderRepository.save(order);

        Shipment shipment = Shipment.builder()
                .trackingCode(request.trackingCode())
                .transportMode(request.transportMode())
                .destination(request.destination())
                .estimatedArrival(request.estimatedArrival())
                .currentStatus("dispatched")
                .currentLatitude(request.latitude())
                .currentLongitude(request.longitude())
                .carrier(carrier)
                .order(order)
                .build();

        shipment = shipmentRepository.save(shipment);

        TrackingLog log = TrackingLog.builder()
                .timestamp(new Date())
                .latitude(request.latitude())
                .longitude(request.longitude())
                .event("Shipment dispatched")
                .shipment(shipment)
                .build();

        trackingLogRepository.save(log);

        return convertToShipmentDto(shipment);
    }

    private ShipmentDto convertToShipmentDto(Shipment shipment) {
        return new ShipmentDto(
                shipment.getShipmentId(),
                shipment.getTrackingCode(),
                shipment.getCurrentStatus(),
                shipment.getEstimatedArrival(),
                shipment.getTransportMode(),
                shipment.getDestination(),
                shipment.getCurrentLatitude(),
                shipment.getCurrentLongitude(),
                shipment.getOrder().getOrderId(),
                shipment.getCarrier().getCarrierId()
        );
    }

}
