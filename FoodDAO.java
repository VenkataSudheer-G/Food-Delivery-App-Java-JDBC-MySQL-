package com.Food_Delivery_App;

import java.sql.*;
import java.util.*;

public class FoodDAO {

    public List<FoodItem> getAllFood() {
        List<FoodItem> list = new ArrayList<>();
        String sql = "SELECT * FROM food_items";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new FoodItem(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price")
                ));
            }

        } catch (Exception e) { e.printStackTrace(); }

        return list;
    }

    public void addFood(String name, String category, double price) {
        String sql = "INSERT INTO food_items(name, category, price) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, category);
            ps.setDouble(3, price);
            ps.executeUpdate();

        } catch (Exception e) { e.printStackTrace(); }
    }

    public void deleteFood(int id) {
        String sql = "DELETE FROM food_items WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public FoodItem getFoodById(int id) {
        String sql = "SELECT * FROM food_items WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new FoodItem(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price")
                );
            }

        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }
}
