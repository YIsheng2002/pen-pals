package com.penpals.controller;

import java.sql.DriverManager;
import java.sql.SQLException;
import com.penpals.model.ProductCategory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryController extends Controller {
    public void connectToDatabase()
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/penpalsoop", "root", "");
        } catch (ClassNotFoundException | SQLException err)
        {
            System.out.println(err.getMessage());
        }
    }

    // get product category by id
    public ProductCategory getProductCategoryDetailbyId(int id)
    {
        ProductCategory productCategory = new ProductCategory();
        try
        {
            String sql = "SELECT * FROM product_category WHERE ID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                productCategory.setProductCategoryId(rs.getInt("id"));
                productCategory.setProductCategoryName(rs.getString("name"));
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }

        return productCategory;
    }

    // Get all product category
    public List<ProductCategory> getAllProductCategory()
    {
        List<ProductCategory> productCategories = new ArrayList<>();
        try
        {
            String sql = "SELECT * FROM product_category";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                ProductCategory productCategory = new ProductCategory();
                productCategory.setProductCategoryId(rs.getInt("id"));
                productCategory.setProductCategoryName(rs.getString("name"));
                productCategories.add(productCategory);
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }

        return productCategories;
    }
}
