package com.devesh.gsco.trackinglog;

import java.util.Date;

public record TrackingLogDto(
        String logId,
        Date timestamp,
        Double latitude,
        Double longitude,
        String event,
        Integer shipmentId
) {
}
