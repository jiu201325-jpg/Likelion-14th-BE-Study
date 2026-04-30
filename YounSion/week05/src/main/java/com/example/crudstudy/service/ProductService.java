package com.example.crudstudy.service;

import com.example.crudstudy.dto.ProductDTO;
import com.example.crudstudy.entity.Product;
import com.example.crudstudy.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    //Read - 전체조회
    public List<Product> findAll(){
        return productRepository.findAll();
    }
    //Read - 상세 조회
    //id에 해당하는 상품 하나를 가져옴
    public Product findById(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    //Create - 상품 생성
    public Product create(ProductDTO productDTO){
        Product product = productDTO.toEntity();
        return productRepository.save(product);
    }
}
