package com.devesh.gsco.trackinglog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrackingLogService {
    private final TrackingLogRepository trackingLogRepository;

    public TrackingLogDto getLatestTracking(Integer shipmentId) {

        return convertToTrackingLogDto(trackingLogRepository
                .findTopByShipmentShipmentIdOrderByTimestampDesc(shipmentId));
    }

    private TrackingLogDto convertToTrackingLogDto(TrackingLog trackingLog) {
        if (trackingLog == null) {
            return null;
        }

        return new TrackingLogDto(
                trackingLog.getLogId(),
                trackingLog.getTimestamp(),
                trackingLog.getLatitude(),
                trackingLog.getLongitude(),
                trackingLog.getEvent(),
                trackingLog.getShipment().getShipmentId()
        );
    }
}
