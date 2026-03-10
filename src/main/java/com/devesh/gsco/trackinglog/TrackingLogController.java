package com.devesh.gsco.trackinglog;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tracking")
@RequiredArgsConstructor
public class TrackingLogController {
    private final TrackingLogService trackingService;

    @GetMapping("/shipment/{shipment-id}/latest")
    public TrackingLogDto getLatestTracking(
            @PathVariable("shipment-id") Integer shipmentId) {

        return trackingService.getLatestTracking(shipmentId);
    }
}
