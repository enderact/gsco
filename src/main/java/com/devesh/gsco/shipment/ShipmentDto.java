package com.devesh.gsco.shipment;

import java.util.Date;

public record ShipmentDto(
        Integer shipmentId,
        String trackingCode,
        String currentStatus,
        Date estimatedArrival,
        String transportMode,
        String destination,
        Double currentLatitude,
        Double currentLongitude,
        Integer orderId,
        Integer carrierId
) {
}
