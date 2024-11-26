package com.marlo.productFileManagement.service.impl;

import com.marlo.productFileManagement.entities.ProductEntity;
import com.marlo.productFileManagement.repository.impl.RepositoryImpl;
import com.marlo.productFileManagement.service.Service;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
@Slf4j
public class ServiceImpl implements Service {

    @NonNull
    private final RepositoryImpl productRepository;

    private final Path uploadDir = Paths.get("uploads");

    @Override
    public void addProductImage(Long id, MultipartFile file) throws IOException {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(RuntimeException::new);

        log.info("Saving product image...");
        productEntity.setImage(Base64.getEncoder().encode(file.getBytes()));
        productRepository.save(productEntity);
    }

    @Override
    public byte[] getProductImage(Long id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        byte[] image = productEntity.getImage();

        if (image == null || image.length == 0)
            return new byte[] {};

        return Base64.getDecoder().decode(image);
    }
}