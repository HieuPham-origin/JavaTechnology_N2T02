package com.example.lab9.service;

import com.example.lab9.model.Product;
import com.example.lab9.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    @Override
    public Product addProduct(Product product){
        return productRepository.save(product);
    }
    @Override
    public Optional<Product> getProductById(int id){
        return productRepository.findById(id);
    }
    @Override
    public void deleteProduct(int id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            productRepository.deleteById(id);
        }
        else{
            return;
        }
    }
    @Override
    public Product updateProduct(int id, Product product){
        Optional<Product> update = productRepository.findById(id);
        if(update.isPresent()){
            product.setProduct_id(id);
            return productRepository.save(product);
        }else{
            return null;
        }
    }
}
