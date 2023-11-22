package com.example.lab9.controller;

import com.example.lab9.dto.ProductDto;
import com.example.lab9.model.Product;
import com.example.lab9.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = { "", "/" })
    public List<Product> getAllProducts() {
        return this.productService.getAllProducts();
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody ProductDto productDto) {
        Product product = Product.builder()
                .product_name(productDto.getProduct_name())
                .price(productDto.getPrice())
                .brand(productDto.getBrand())
                .color(productDto.getColor())
                .build();
        productService.addProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
    @GetMapping(value = {"/{id}"})
    public Optional<Product> getProduct(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody ProductDto productDto) throws Exception {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            Product product1 = product.get();
            if (productDto.getProduct_name() != null)
                product1.setProduct_name(productDto.getProduct_name());
            if (productDto.getPrice() > 0)
                product1.setPrice(productDto.getPrice());
            if (productDto.getBrand() != null)
                product1.setBrand(productDto.getBrand());
            if (productDto.getColor() != null)
                product1.setColor(productDto.getColor());
            productService.updateProduct(product1.getProduct_id(), product1);
            return ResponseEntity.ok(product1);
        } else {
            throw new Exception("Product not found with id: " + id);
        }
    }

    @PatchMapping(value = {"/{id}"})
    public ResponseEntity<Product> modifyProduct(@PathVariable Integer id, @RequestBody ProductDto productDto) throws Exception {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            Product product1 = product.get();
            if (productDto.getProduct_name() != null)
                product1.setProduct_name(productDto.getProduct_name());
            if (productDto.getPrice() > 0)
                product1.setPrice(productDto.getPrice());
            if (productDto.getBrand() != null)
                product1.setBrand(productDto.getBrand());
            if (productDto.getColor() != null)
                product1.setColor(productDto.getColor());
            productService.updateProduct(product1.getProduct_id(),product1);
            return ResponseEntity.ok(product1);
        }else{
            throw new Exception("Product not found with id: " + id);
        }
    }

    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
