package com.devesh.gsco.carrier;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarrierService {

    private final CarrierRepository carrierRepository;

    public double getShippingRate(Integer carrierId, double weight) {

        Carrier carrier = carrierRepository.findById(carrierId).orElse(null);

        if (carrier != null) {
            double baseRate = 50;

            return baseRate + (weight * (5 - carrier.getRating()));
        }

        return 0;
    }
    public List<Carrier> getCarrierOnDuty() {
        return carrierRepository.findByOnDutyTrue();
    }

    public Carrier createCarrier(Carrier carrier) {
        return carrierRepository.save(carrier);
    }
}
