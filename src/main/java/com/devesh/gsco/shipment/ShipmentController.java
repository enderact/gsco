package com.devesh.gsco.shipment;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/shipments")
@RequiredArgsConstructor
public class ShipmentController {

    private final ShipmentService shipmentService;

    @PatchMapping("/{shipment-id}/reroute")
    public ShipmentDto reroute(
            @PathVariable("shipment-id") Integer shipmentId,
            @RequestBody Map<String, String> body) {

        return shipmentService.reroute(shipmentId, body.get("destination"));
    }

    @PatchMapping("/{shipment-id}/location")
    public ShipmentDto updateLocation(
            @PathVariable("shipment-id") Integer shipmentId,
            @RequestBody Map<String, Double> body) {

        return shipmentService.updateCurrentLocation(
                shipmentId,
                body.get("latitude"),
                body.get("longitude")
        );
    }

    @GetMapping("/{shipment-id}/delay")
    public long calculateDelay(
            @PathVariable("shipment-id") Integer shipmentId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date actualArrival) {

        return shipmentService.calculateDelay(shipmentId, actualArrival);
    }


    @PostMapping("/orders/{order-id}/carrier/{carrier-id}/create")
    public Shipment createShipment(
            @PathVariable("order-id") int orderId,
            @PathVariable("carrier-id") int carrierId,
            @RequestBody Shipment shipment) {

        return shipmentService.createShipment(orderId, carrierId, shipment);
    }

    @PostMapping("/orders/{order-id}/carrier/{carrier-id}/dispatch")
    @Transactional
    public ShipmentDto dispatchOrder(
            @PathVariable("order-id") Integer orderId,
            @PathVariable("carrier-id") Integer carrierId,
            @RequestBody DispatchRequest request
    ) {

        return shipmentService.dispatchOrder(orderId, carrierId, request);
    }

}
