package com.penpals.controller;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import javax.swing.JOptionPane;
import com.penpals.model.Product;

public class ProductController extends Controller{
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

    // GET ONE PRODUCT BY ID
    public Product getProductDetailbyId(int id)
    {
        Product product = new Product();
        try
        {
            String sql = "SELECT * FROM product WHERE ID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                product.setProductId(rs.getInt("id"));
                product.setProductName(rs.getString("name"));
                product.setProductPrice(rs.getDouble("price"));
                product.setProductStockQuantity(rs.getInt("stock"));
                product.setProductDescription(rs.getString("description"));
                product.setProductImageURL(rs.getString("image_url"));
                product.setProductCategory(new ProductCategoryController().getProductCategoryDetailbyId(rs.getInt("category_id_fk")));
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
        return product;
    }

    // GET ALL PRODUCT
    public List<Product> getAllProduct()
    {
        List<Product> products = new ArrayList<>();
        try
        {
            String sql = "SELECT * FROM product";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Product product = new Product();
                product.setProductId(rs.getInt("id"));
                product.setProductName(rs.getString("name"));
                product.setProductPrice(rs.getDouble("price"));
                product.setProductStockQuantity(rs.getInt("stock"));
                product.setProductDescription(rs.getString("description"));
                product.setProductImageURL(rs.getString("image_url"));
                product.setProductCategory(new ProductCategoryController().getProductCategoryDetailbyId(rs.getInt("category_id_fk")));
                if (rs.getInt("promotion_id_fk") != 0 || rs.getInt("promotion_id_fk") > 0)
                {
                    product.setProductHasPromotion(true);
                }
                products.add(product);
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
        return products;
    }

    // GET ALL PRODUCT BY RATING
    // THIS FUNCT WILL RUN EVERYTIME FILTER PRESS (WHEN NOTHING IS SELECTED)
    public List<Product> getAllProductbyRating(int ratings)
    {
        List<Product> products = new ArrayList<>();
        try
        {
            String sql;
            if (ratings != 0 ){
                // SELECT WHEN RATING > 0
                sql = "SELECT p.id, p.name, p.description, p.price, p.stock, p.image_url, p.category_id_fk, p.promotion_id_fk FROM product p LEFT JOIN feedback f ON (p.id = f.product_id_fk) GROUP BY p.id HAVING AVG(f.rating) >= ?;";
            } else {
                // SELECT WHEN RATING = 0 (SELECT ALL NULL RATING ALSO)
                sql = "SELECT p.id, p.name, p.description, p.price, p.stock, p.image_url, p.category_id_fk, p.promotion_id_fk FROM product p LEFT JOIN feedback f ON (p.id = f.product_id_fk) GROUP BY p.id HAVING (AVG(f.rating) >= ? OR AVG(f.rating) IS NULL);";
            }
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ratings);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Product product = new Product();
                product.setProductId(rs.getInt("id"));
                product.setProductName(rs.getString("name"));
                product.setProductPrice(rs.getDouble("price"));
                product.setProductStockQuantity(rs.getInt("stock"));
                product.setProductDescription(rs.getString("description"));
                product.setProductImageURL(rs.getString("image_url"));
                product.setProductCategory(new ProductCategoryController().getProductCategoryDetailbyId(rs.getInt("category_id_fk")));
                if (rs.getInt("promotion_id_fk") != 0 || rs.getInt("promotion_id_fk") > 0)
                {
                    product.setProductHasPromotion(true);
                }
                products.add(product);
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
        return products;
    }

    // GET ALL PRODUCT BY RATING AND CATEGORY
    public List<Product> getAllProductByCategory(String categoryName, int ratings)
    {
        List<Product> products = new ArrayList<>();
        try
        {
            String sql;
            if (ratings != 0 ){
                // SELECT WHEN RATING > 0
                sql = "SELECT p.id, p.name, p.description, p.price, p.stock, p.image_url, p.category_id_fk, p.promotion_id_fk FROM product p LEFT JOIN feedback f ON (p.id = f.product_id_fk) JOIN product_category pc ON (p.category_id_fk = pc.id) WHERE pc.name = ? GROUP BY p.id HAVING AVG(f.rating) >= ?;";
            } else {
                // SELECT WHEN RATING = 0 (SELECT ALL NULL RATING ALSO)
                sql = "SELECT p.id, p.name, p.description, p.price, p.stock, p.image_url, p.category_id_fk, p.promotion_id_fk FROM product p LEFT JOIN feedback f ON (p.id = f.product_id_fk) JOIN product_category pc ON (p.category_id_fk = pc.id) WHERE pc.name = ? GROUP BY p.id HAVING (AVG(f.rating) >= ? OR AVG(f.rating) IS NULL);";
            }
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, categoryName);
            ps.setInt(2, ratings);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Product product = new Product();
                product.setProductId(rs.getInt("p.id"));
                product.setProductName(rs.getString("p.name"));
                product.setProductPrice(rs.getDouble("p.price"));
                product.setProductStockQuantity(rs.getInt("p.stock"));
                product.setProductDescription(rs.getString("p.description"));
                product.setProductImageURL(rs.getString("p.image_url"));
                product.setProductCategory(new ProductCategoryController().getProductCategoryDetailbyId(rs.getInt("p.category_id_fk")));
                if (rs.getInt("promotion_id_fk") != 0 || rs.getInt("promotion_id_fk") > 0)
                {
                    product.setProductHasPromotion(true);
                }
                products.add(product);
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
        return products;
    }

    // GET ALL PROMOTION PRODUCT PRODUCT BY RATING
    public List<Product> getAllPromotionProduct(int ratings)
    {
        List<Product> products = new ArrayList<>();
        try
        {
            String sql;
            if (ratings != 0 ){
                // SELECT WHEN RATING > 0
                sql = "SELECT p.id, p.name, p.description, p.price, p.stock, p.image_url, p.category_id_fk, p.promotion_id_fk FROM product p LEFT JOIN feedback f ON (p.id = f.product_id_fk) WHERE p.promotion_id_fk IS NOT NULL GROUP BY p.id HAVING AVG(f.rating) >= ?;";
            } else {
                // SELECT WHEN RATING = 0 (SELECT ALL NULL RATING ALSO)
                sql = "SELECT p.id, p.name, p.description, p.price, p.stock, p.image_url, p.category_id_fk, p.promotion_id_fk FROM product p LEFT JOIN feedback f ON (p.id = f.product_id_fk) WHERE p.promotion_id_fk IS NOT NULL GROUP BY p.id HAVING (AVG(f.rating) >= ? OR AVG(f.rating) IS NULL);";
            }
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ratings);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Product product = new Product();
                product.setProductId(rs.getInt("p.id"));
                product.setProductName(rs.getString("p.name"));
                product.setProductPrice(rs.getDouble("p.price"));
                product.setProductStockQuantity(rs.getInt("p.stock"));
                product.setProductDescription(rs.getString("p.description"));
                product.setProductImageURL(rs.getString("p.image_url"));
                product.setProductCategory(new ProductCategoryController().getProductCategoryDetailbyId(rs.getInt("p.category_id_fk")));
                if (rs.getInt("promotion_id_fk") != 0 || rs.getInt("promotion_id_fk") > 0)
                {
                    product.setProductHasPromotion(true);
                }
                products.add(product);
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
        return products;
    }

    // GET ALL PROMOTION PRODUCT BY RATING AND CATEGORY
    public List<Product> getAllPromotionProductByCategory(String categoryName, int ratings)
    {
        List<Product> products = new ArrayList<>();
        try
        {
            String sql;
            if (ratings != 0 ){
                // SELECT WHEN RATING > 0
                sql = "SELECT p.id, p.name, p.description, p.price, p.stock, p.image_url, p.category_id_fk, p.promotion_id_fk FROM product p LEFT JOIN feedback f ON (p.id = f.product_id_fk) JOIN product_category pc ON (p.category_id_fk = pc.id) WHERE p.promotion_id_fk IS NOT NULL AND pc.name = ? GROUP BY p.id HAVING AVG(f.rating) >= ?;";
            } else {
                // SELECT WHEN RATING = 0 (SELECT ALL NULL RATING ALSO)
                sql = "SELECT p.id, p.name, p.description, p.price, p.stock, p.image_url, p.category_id_fk, p.promotion_id_fk FROM product p LEFT JOIN feedback f ON (p.id = f.product_id_fk) JOIN product_category pc ON (p.category_id_fk = pc.id) WHERE p.promotion_id_fk IS NOT NULL AND pc.name = ? GROUP BY p.id HAVING (AVG(f.rating) >= ? OR AVG(f.rating) IS NULL);";
            }
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, categoryName);
            ps.setInt(2, ratings);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Product product = new Product();
                product.setProductId(rs.getInt("p.id"));
                product.setProductName(rs.getString("p.name"));
                product.setProductPrice(rs.getDouble("p.price"));
                product.setProductStockQuantity(rs.getInt("p.stock"));
                product.setProductDescription(rs.getString("p.description"));
                product.setProductImageURL(rs.getString("p.image_url"));
                product.setProductCategory(new ProductCategoryController().getProductCategoryDetailbyId(rs.getInt("p.category_id_fk")));
                if (rs.getInt("promotion_id_fk") != 0 || rs.getInt("promotion_id_fk") > 0)
                {
                    product.setProductHasPromotion(true);
                }
                products.add(product);
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
        return products;
    } 
    
    // GET ALL PRODUCT BY RATING
    // THIS FUNCT WILL RUN EVERYTIME FILTER PRESS (WHEN NOTHING IS SELECTED)
    public List<Product> getAllProductbyRatingAndPrice(int ratings, String minPrice, String maxPrice)
    {
        List<Product> products = new ArrayList<>();
        try
        {
            String sql;
            if (ratings != 0 ){
                // SELECT WHEN RATING > 0
                sql = "SELECT p.id, p.name, p.description, p.price, p.stock, p.image_url, p.category_id_fk, p.promotion_id_fk FROM product p LEFT JOIN feedback f ON (p.id = f.product_id_fk) WHERE (p.price >= ? AND p.price <= ?) GROUP BY p.id HAVING AVG(f.rating) >= ?;";
            } else {
                // SELECT WHEN RATING = 0 (SELECT ALL NULL RATING ALSO)
                sql = "SELECT p.id, p.name, p.description, p.price, p.stock, p.image_url, p.category_id_fk, p.promotion_id_fk FROM product p LEFT JOIN feedback f ON (p.id = f.product_id_fk) WHERE (p.price >= ? AND p.price <= ?) GROUP BY p.id HAVING (AVG(f.rating) >= ? OR AVG(f.rating) IS NULL);";
            }
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, minPrice);
            ps.setString(2, maxPrice);
            ps.setInt(3, ratings);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Product product = new Product();
                product.setProductId(rs.getInt("id"));
                product.setProductName(rs.getString("name"));
                product.setProductPrice(rs.getDouble("price"));
                product.setProductStockQuantity(rs.getInt("stock"));
                product.setProductDescription(rs.getString("description"));
                product.setProductImageURL(rs.getString("image_url"));
                product.setProductCategory(new ProductCategoryController().getProductCategoryDetailbyId(rs.getInt("category_id_fk")));
                if (rs.getInt("promotion_id_fk") != 0 || rs.getInt("promotion_id_fk") > 0)
                {
                    product.setProductHasPromotion(true);
                }
                products.add(product);
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
        return products;
    }

    // GET ALL PRODUCT BY RATING AND CATEGORY
    public List<Product> getAllProductByCategoryAndPrice(String categoryName, int ratings, String minPrice, String maxPrice)
    {
        List<Product> products = new ArrayList<>();
        try
        {
            String sql;
            if (ratings != 0 ){
                // SELECT WHEN RATING > 0
                sql = "SELECT p.id, p.name, p.description, p.price, p.stock, p.image_url, p.category_id_fk, p.promotion_id_fk FROM product p LEFT JOIN feedback f ON (p.id = f.product_id_fk) JOIN product_category pc ON (p.category_id_fk = pc.id) WHERE (pc.name = ? AND p.price >= ? AND p.price <= ?) GROUP BY p.id HAVING AVG(f.rating) >= ?;";
            } else {
                // SELECT WHEN RATING = 0 (SELECT ALL NULL RATING ALSO)
                sql = "SELECT p.id, p.name, p.description, p.price, p.stock, p.image_url, p.category_id_fk, p.promotion_id_fk FROM product p LEFT JOIN feedback f ON (p.id = f.product_id_fk) JOIN product_category pc ON (p.category_id_fk = pc.id) WHERE (pc.name = ? AND p.price >= ? AND p.price <= ?) GROUP BY p.id HAVING (AVG(f.rating) >= ? OR AVG(f.rating) IS NULL);";
            }
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, categoryName);
            ps.setString(2, minPrice);
            ps.setString(3, maxPrice);
            ps.setInt(4, ratings);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Product product = new Product();
                product.setProductId(rs.getInt("p.id"));
                product.setProductName(rs.getString("p.name"));
                product.setProductPrice(rs.getDouble("p.price"));
                product.setProductStockQuantity(rs.getInt("p.stock"));
                product.setProductDescription(rs.getString("p.description"));
                product.setProductImageURL(rs.getString("p.image_url"));
                product.setProductCategory(new ProductCategoryController().getProductCategoryDetailbyId(rs.getInt("p.category_id_fk")));
                if (rs.getInt("promotion_id_fk") != 0 || rs.getInt("promotion_id_fk") > 0)
                {
                    product.setProductHasPromotion(true);
                }
                products.add(product);
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
        return products;
    }

    // GET ALL PROMOTION PRODUCT PRODUCT BY RATING
    public List<Product> getAllPromotionProductbyPrice(int ratings, String minPrice, String maxPrice)
    {
        List<Product> products = new ArrayList<>();
        try
        {
            String sql;
            if (ratings != 0 ){
                // SELECT WHEN RATING > 0
                sql = "SELECT p.id, p.name, p.description, p.price, p.stock, p.image_url, p.category_id_fk, p.promotion_id_fk FROM product p LEFT JOIN feedback f ON (p.id = f.product_id_fk) WHERE (p.promotion_id_fk IS NOT NULL AND p.price >= ? AND p.price <= ?) GROUP BY p.id HAVING AVG(f.rating) >= ?;";
            } else {
                // SELECT WHEN RATING = 0 (SELECT ALL NULL RATING ALSO)
                sql = "SELECT p.id, p.name, p.description, p.price, p.stock, p.image_url, p.category_id_fk, p.promotion_id_fk FROM product p LEFT JOIN feedback f ON (p.id = f.product_id_fk) WHERE (p.promotion_id_fk IS NOT NULL AND p.price >= ? AND p.price <= ?) GROUP BY p.id HAVING (AVG(f.rating) >= ? OR AVG(f.rating) IS NULL);";
            }
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, minPrice);
            ps.setString(2, maxPrice);
            ps.setInt(3, ratings);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Product product = new Product();
                product.setProductId(rs.getInt("p.id"));
                product.setProductName(rs.getString("p.name"));
                product.setProductPrice(rs.getDouble("p.price"));
                product.setProductStockQuantity(rs.getInt("p.stock"));
                product.setProductDescription(rs.getString("p.description"));
                product.setProductImageURL(rs.getString("p.image_url"));
                product.setProductCategory(new ProductCategoryController().getProductCategoryDetailbyId(rs.getInt("p.category_id_fk")));
                if (rs.getInt("promotion_id_fk") != 0 || rs.getInt("promotion_id_fk") > 0)
                {
                    product.setProductHasPromotion(true);
                }
                products.add(product);
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
        return products;
    }

    // GET ALL PROMOTION PRODUCT BY RATING AND CATEGORY
    public List<Product> getAllPromotionProductByCategoryAndPrice(String categoryName, int ratings, String minPrice, String maxPrice)
    {
        List<Product> products = new ArrayList<>();
        try
        {
            String sql;
            if (ratings != 0 ){
                // SELECT WHEN RATING > 0
                sql = "SELECT p.id, p.name, p.description, p.price, p.stock, p.image_url, p.category_id_fk, p.promotion_id_fk FROM product p LEFT JOIN feedback f ON (p.id = f.product_id_fk) JOIN product_category pc ON (p.category_id_fk = pc.id) WHERE (p.promotion_id_fk IS NOT NULL AND pc.name = ? AND p.price >= ? AND p.price <= ?) GROUP BY p.id HAVING AVG(f.rating) >= ?;";
            } else {
                // SELECT WHEN RATING = 0 (SELECT ALL NULL RATING ALSO)
                sql = "SELECT p.id, p.name, p.description, p.price, p.stock, p.image_url, p.category_id_fk, p.promotion_id_fk FROM product p LEFT JOIN feedback f ON (p.id = f.product_id_fk) JOIN product_category pc ON (p.category_id_fk = pc.id) WHERE (p.promotion_id_fk IS NOT NULL AND pc.name = ? AND p.price >= ? AND p.price <= ?) GROUP BY p.id HAVING (AVG(f.rating) >= ? OR AVG(f.rating) IS NULL);";
            }
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, categoryName);
            ps.setString(2, minPrice);
            ps.setString(3, maxPrice);
            ps.setInt(4, ratings);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Product product = new Product();
                product.setProductId(rs.getInt("p.id"));
                product.setProductName(rs.getString("p.name"));
                product.setProductPrice(rs.getDouble("p.price"));
                product.setProductStockQuantity(rs.getInt("p.stock"));
                product.setProductDescription(rs.getString("p.description"));
                product.setProductImageURL(rs.getString("p.image_url"));
                product.setProductCategory(new ProductCategoryController().getProductCategoryDetailbyId(rs.getInt("p.category_id_fk")));
                if (rs.getInt("promotion_id_fk") != 0 || rs.getInt("promotion_id_fk") > 0)
                {
                    product.setProductHasPromotion(true);
                }
                products.add(product);
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
        return products;
    }

    // get product promotion percentage
    public double getProductPromotionPercentage(int productId)
    {
        double promotionPercentage = 0;
        try
        {
            String sql = "SELECT p.percentage FROM promotion p JOIN product pr ON (p.id = pr.promotion_id_fk) WHERE pr.id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                promotionPercentage = rs.getDouble("percentage");
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
        return promotionPercentage;
    }

    // get rating count list
    public double[] getRatingCountList(int productId)
    {
        double[] ratingCount = {0,0,0,0,0,0};
        try
        {
            String sql = "SELECT rating, COUNT(rating) AS rating_count FROM feedback WHERE product_id_fk = ? GROUP BY rating";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                ratingCount[rs.getInt("rating")] = Double.valueOf(rs.getInt("rating_count"));
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
        return ratingCount;
    }

    //get stock quantity
    public int getProductStockQuantity(int productId)
    {
        int stockQuantity = 0;
        try
        {
            String sql = "SELECT stock FROM product WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                stockQuantity = rs.getInt("stock");
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
        return stockQuantity;
    }
}
