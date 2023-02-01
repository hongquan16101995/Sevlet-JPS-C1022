package com.example.first_project;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DemoServlet", value = "/demo")
public class DemoServlet extends HttpServlet {

    private final List<Product> products = new ArrayList<>();

    @Override
    public void init() {
        products.add(new Product(1L, "A", 10D, 10));
        products.add(new Product(2L, "B", 10D, 10));
        products.add(new Product(3L, "C", 20D, 12));
        products.add(new Product(4L, "D", 20D, 13));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("demo.jsp");
        request.setAttribute("products", products);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        Double price = Double.parseDouble(request.getParameter("price"));
        Integer quantity = Integer.parseInt(request.getParameter("quantity"));
        products.add(new Product(id, name, price, quantity));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("demo.jsp");
        request.setAttribute("products", products);
        requestDispatcher.forward(request, response);
    }
}
