package com.marlo.productFileManagement.controller;

import com.marlo.productFileManagement.service.impl.ServiceImpl;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

//@Controller
@Slf4j
@RestController
@RequiredArgsConstructor
public class Controller {

    @NonNull
    private final ServiceImpl service;
    
    @PostMapping(value = "/productImage/{id}/add")
    public ResponseEntity<String> addProductImage(@PathVariable long id, @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        try {
            // Only saves PNG or JPEG images
            String originalName = imageFile.getOriginalFilename();
            Boolean isPngOrJpg = originalName.contains(".png") || originalName.contains(".jpg");

            if (originalName == null || !isPngOrJpg) {
                //return ResponseEntity.badRequest().build();
                //return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file type. Only PNG and JPG images are allowed.");
            }

            service.addProductImage(id, imageFile);
            return ResponseEntity.ok("The image was successfully saved.");
            
        } catch (IOException e) {
            // Handle I/O errors
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing the image. Please try again.");

        } catch (Exception e) {
            // Handle general errors
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("An unexpected error occurred. Please check the details and try again.");
        }
    }

    @GetMapping(value = "/productImage/{id}")
    public ResponseEntity<byte[]> getProductImage(@PathVariable Long id) {
        try {
            byte[] imageBytes = service.getProductImage(id);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);

            if (imageBytes.length == 0)
                return new ResponseEntity<>(imageBytes, headers, HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
