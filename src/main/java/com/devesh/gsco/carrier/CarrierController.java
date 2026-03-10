package com.devesh.gsco.carrier;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carriers")
@RequiredArgsConstructor
public class CarrierController {
    private final CarrierService carrierService;

    @GetMapping("/{carrier-id}/rate")
    public double getShippingRate(
            @PathVariable("carrier-id") int carrierId,
            @RequestParam double weight) {

        return carrierService.getShippingRate(carrierId, weight);
    }

    @GetMapping("/available")
    public List<Carrier> getAvailableCarriers() {
        return carrierService.getCarrierOnDuty();
    }

    @PostMapping("/create")
    public Carrier createCarrier(@RequestBody Carrier carrier) {
        return  carrierService.createCarrier(carrier);
    }
}
