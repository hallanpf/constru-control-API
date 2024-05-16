package com.construcontrol.construcontrol.repositories;

import com.construcontrol.construcontrol.model.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
