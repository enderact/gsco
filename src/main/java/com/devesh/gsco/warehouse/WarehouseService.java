package com.devesh.gsco.warehouse;

import com.devesh.gsco.carrier.Carrier;
import com.devesh.gsco.carrier.CarrierRepository;
import com.devesh.gsco.order.Order;
import com.devesh.gsco.order.OrderRepository;
import com.devesh.gsco.shipment.Shipment;
import com.devesh.gsco.shipment.ShipmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final CarrierRepository carrierRepository;
    private final OrderRepository orderRepository;
    private final ShipmentRepository shipmentRepository;

    public Integer getCapacity(Integer warehouseId) {
        return warehouseRepository.findCapacityById(warehouseId);
    }

    public String getWarehouseLocation(Integer id) {
        return warehouseRepository.findLocationById(id);
    }

    public Shipment createShipment(int orderId, int carrierId, Shipment shipment) {

        Order order = orderRepository.findById(orderId).orElse(null);
        Carrier carrier = carrierRepository.findById(carrierId).orElse(null);

        if (order != null && carrier != null && carrier.isOnDuty()) {

            shipment.setCarrier(carrier);
            shipment.setOrder(order);

            order.setStatus("shipping");
            orderRepository.save(order);

            return shipmentRepository.save(shipment);
        }

        return null;
    }
}
