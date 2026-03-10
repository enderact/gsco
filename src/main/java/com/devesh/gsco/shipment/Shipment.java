package com.devesh.gsco.shipment;

import com.devesh.gsco.carrier.Carrier;
import com.devesh.gsco.order.Order;
import com.devesh.gsco.trackinglog.TrackingLog;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipment_seq")
    @SequenceGenerator(
            name = "shipment_seq",
            sequenceName = "shipment_seq",
            allocationSize = 1
    )
    private Integer shipmentId;
    private String trackingCode;
    private String currentStatus;
    private Date estimatedArrival;
    private String transportMode;
    private String destination;

    private Double currentLatitude;
    private Double currentLongitude;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "carrier_id")
    private Carrier carrier;

    @OneToMany(mappedBy = "shipment")
    private List<TrackingLog> trackingLogs;

}
