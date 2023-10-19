package com.hieu.dao;

import com.hieu.repository.IProduct;
import com.hieu.model.Product;

import java.util.List;

public class ProductDAO extends AbstractDAO<Product> implements IProduct {
    public ProductDAO(Class<Product> clazz) {
        super(clazz);
    }

    @Override
    public List<Product> getAll() {
        String hql = "FROM Product";
        return this.query(hql);
    }

}
