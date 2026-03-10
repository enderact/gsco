package com.devesh.gsco.shipment;

import java.util.Date;

public record DispatchRequest(
        Integer productId,
        Integer warehouseId,
        Integer quantity,
        String trackingCode,
        String transportMode,
        String destination,
        double latitude,
        double longitude,
        Date estimatedArrival
) {}
