package com.example.first_project.DAO;

import com.example.first_project.model.Category;
import com.example.first_project.model.Product;
import com.example.first_project.service.CategoryService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private final Connection connection;

    private final CategoryService categoryService;

    //các câu lệnh truy vấn tương ứng
    // ? : tham số cho các câu lệnh truy vấn, có vị trí tính từ 1 -> n
    private final String SELECT_ALL_PRODUCT = "select * from product join category " +
            "on category.id = product.category_id where category.status = true";
    private final String SELECT_PRODUCT_BY_ID = "select * from product where id = ?";
    private final String INSERT_PRODUCT = "insert into product(name, price, quantity, category_id) value (?,?,?,?)";
    private final String UPDATE_PRODUCT = "update product set name = ?, price = ?, quantity = ? where id = ?";
    private final String DELETE_PRODUCT = "delete from product where id = ?";

    public ProductDAO() {
        connection = MyConnection.getConnection();
        categoryService = new CategoryService();
    }

    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        //tạo đối tượng Statement: hỗ trợ truy vấn câu truy vấn tĩnh, không có tham số
        try (Statement statement = connection.createStatement();
             //tập hợp kết quả trả về được hứng bởi ResultSet
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_PRODUCT)) {
            while (resultSet.next()) {
                Category category = categoryService.findById(resultSet.getLong(5));
                products.add(new Product(resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getInt(4),
                        category));
            }
            //bắt lỗi SQL vs SQLException
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Product findById(Long id) {
        Product product = null;
        //tạo đối tượng PreparedStatement: hỗ trợ thao tác với câu lệnh truy vấn động, có tham số
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID)) {
            //thêm tham số cho câu truy vấn với các hàm set giá trị tương ứng
            preparedStatement.setLong(1, id);
            //executeQuery: truy vấn đọc giữ liệu
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category category = categoryService.findById(resultSet.getLong(5));
                product = new Product(resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getInt(4),
                        category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public void create(Product product) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setLong(4, product.getCategory().getId());
            //executeUpdate: truy vấn thay đổi dữ liệu. kết quả là số bản ghi được thay đổi
            int row1 = preparedStatement.executeUpdate();
            System.out.println("Create:" + row1);
//            preparedStatement.setString(1, "HelloWorld");
//            preparedStatement.setDouble(2, product.getPrice());
//            preparedStatement.setInt(3, product.getQuantity());
//            int row2 = preparedStatement.executeUpdate();
//            System.out.println("Create:" + row2);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void update(Product product) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setLong(4, product.getId());
            int row = preparedStatement.executeUpdate();
            System.out.println("Update:" + row);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT)) {
            preparedStatement.setLong(1, id);
            int row = preparedStatement.executeUpdate();
            System.out.println("Delete:" + row);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
