package com.example.first_project.service;

import com.example.first_project.DAO.ProductDAO;
import com.example.first_project.model.Product;

import java.util.List;

public class ProductService {

    //chuyển sang dùng DAO thay vì list cứng
    private final ProductDAO productDAO;

    public ProductService() {
        //tạo list ảo chứa đối tượng tương tác, sau sẽ thêm Db xử lý
        //id được xử lý tăng tự động khi tạo mới, tương đương với Db sau này
        productDAO = new ProductDAO();
    }

    public List<Product> findAll() {
        return productDAO.findAll();
    }

    public Product findById(Long id) {
        return productDAO.findById(id);
    }

    public void createProduct(Product product) {
        productDAO.create(product);
    }

    public void updateProduct(Product product) {
        productDAO.update(product);
    }

    public void deleteProduct(Long id) {
        productDAO.delete(id);
    }
}
