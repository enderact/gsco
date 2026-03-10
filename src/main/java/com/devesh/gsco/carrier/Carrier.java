package com.devesh.gsco.carrier;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Carrier {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carrier_seq")
    @SequenceGenerator(
            name = "carrier_seq",
            sequenceName = "carrier_seq",
            allocationSize = 1
    )
    private Integer carrierId;
    private String providerName;
    private String apiUrl;
    private int rating;
    private boolean onDuty;
}
