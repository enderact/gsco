package com.devesh.gsco.trackinglog;

import com.devesh.gsco.shipment.Shipment;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class TrackingLog {
    @Id
    @UuidGenerator
    private String logId;
    private Date timestamp;
    private Double latitude;
    private Double longitude;
    private String event;

    @ManyToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;
}
