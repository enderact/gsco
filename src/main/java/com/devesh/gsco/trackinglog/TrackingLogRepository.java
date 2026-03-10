package com.devesh.gsco.trackinglog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackingLogRepository extends JpaRepository<TrackingLog, Integer> {
    TrackingLog findTopByShipmentShipmentIdOrderByTimestampDesc(Integer shipmentId);
}
