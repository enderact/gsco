package com.devesh.gsco.carrier;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarrierRepository extends JpaRepository<Carrier, Integer> {
    List<Carrier> findByOnDutyTrue();
}
