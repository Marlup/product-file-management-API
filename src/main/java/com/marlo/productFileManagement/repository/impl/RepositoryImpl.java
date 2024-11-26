package com.marlo.productFileManagement.repository.impl;

import com.marlo.productFileManagement.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryImpl extends JpaRepository<ProductEntity, Long> {
}
