package com.marlo.productFileManagement.repository;

import com.marlo.productFileManagement.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<ProductEntity, Long> {
}
