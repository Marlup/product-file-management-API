package com.marlo.productFileManagement.service;

import com.marlo.productFileManagement.entities.ProductEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface Service {

    byte[] getProductImage(Long id);
    void addProductImage(Long id, MultipartFile file) throws IOException;
}
