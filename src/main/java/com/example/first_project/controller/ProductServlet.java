package com.example.first_project.controller;

import com.example.first_project.model.Product;
import com.example.first_project.service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//annotation @WebServlet: dùng để đánh dấu đây là 1 Servlet, được quản lý bới ServletContainer
//name: đặt tên cho Servlet để quản lý
//value hoặc urlPattern: dùng để định danh đường dẫn cho Servlet
@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet {

    //tạo đối tượng Service để thao tác với cấu trúc dữ liệu lưu đối tượng
    private final ProductService productService;

    public ProductServlet() {
        productService = new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request parameter Action: nhằm quy định về hành động đang đang yêu cầu của client
        String action = request.getParameter("action");
        //tránh lỗi khi không có parameter Action đi kèm
        if (action == null) {
            action = "";
        }

        //dùng câu lệnh điều kiện để điều hướng Action tương ứng về với phương thức tương ứng
        switch (action) {
            case "delete":
                deleteProduct(request, response);
                break;
            case "detail":
                detailProduct(request, response);
                break;
            case "update":
                updateForm(request, response);
                break;
            default:
                displayProductList(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //tương tự ở doGet, có thể tách phương thức đoạn code này để tái sử dụng
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                create(request, response);
                break;
            case "update":
                update(request, response);
                break;
            default:
                displayProductList(request, response);
        }
    }

    //hiển thị tất cả sản phẩm
    private void displayProductList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
        request.setAttribute("products", productService.findAll());
        rd.forward(request, response);
    }

    //hiển thị chi tiết 1 sản phẩm
    private void detailProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        RequestDispatcher rd = request.getRequestDispatcher("detail.jsp");
        request.setAttribute("product", productService.findById(id));
        rd.forward(request, response);
    }

    //mở form update với giá trị thuộc tính của sản phẩm tương ứng
    private void updateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        RequestDispatcher rd = request.getRequestDispatcher("update.jsp");
        request.setAttribute("product", productService.findById(id));
        rd.forward(request, response);
    }

    //nhận dữ liệu của tạo mới
    private void create(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        Double price = Double.parseDouble(request.getParameter("price"));
        Integer quantity = Integer.parseInt(request.getParameter("quantity"));
        productService.createProduct(new Product(name, price, quantity));
        //xử lý lỗi duplicate dữ liệu trong khi tạo hoặc sửa: điều hướng với Servlet tương ứng
        response.sendRedirect("/products");
    }

    //nhận dữ liệu của chỉnh sửa thông tin sản phẩm theo id
    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        Double price = Double.parseDouble(request.getParameter("price"));
        Integer quantity = Integer.parseInt(request.getParameter("quantity"));
        productService.updateProduct(new Product(id, name, price, quantity));
        response.sendRedirect("/products");
    }

    //xóa sản phẩm theo id
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        productService.deleteProduct(id);
        response.sendRedirect("/products");
    }
}
