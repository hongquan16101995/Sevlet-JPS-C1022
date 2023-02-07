package com.example.first_project.DAO;

import com.example.first_project.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private final Connection connection;

    private final String SELECT_ALL_CATEGORY = "select * from category where status = true";
    private final String SELECT_CATEGORY_BY_ID = "select * from category where id = ? and status = true";
    private final String INSERT_CATEGORY = "insert into category(name, status) value (?,?)";
    private final String UPDATE_CATEGORY = "update category set name = ? where id = ?";
    private final String DELETE_CATEGORY = "update category set status = false where id = ?";

    public CategoryDAO() {
        connection = MyConnection.getConnection();
    }

    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                categories.add(new Category(resultSet.getLong(1),
                        resultSet.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public Category findById(Long id) {
        Category category = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                category = new Category(resultSet.getLong(1),
                        resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    public void create(Category category) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATEGORY)) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.setBoolean(2, category.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Category category) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORY)) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CATEGORY)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
