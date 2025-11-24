package com.Food_Delivery_App;

import java.sql.*;
import java.util.*;

public class OrderDAO {

    public void placeOrder(int foodId, int qty, double price) {
        String sql = "INSERT INTO orders(food_id, quantity, total_price) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, foodId);
            ps.setInt(2, qty);
            ps.setDouble(3, price * qty);

            ps.executeUpdate();

        } catch (Exception e) { e.printStackTrace(); }
    }

    public List<Order> getOrderHistory() {
        List<Order> list = new ArrayList<>();

        String sql =
                "SELECT o.id, f.name, o.quantity, o.total_price " +
                "FROM orders o JOIN food_items f ON o.food_id = f.id";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Order(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getDouble(4)
                ));
            }

        } catch (Exception e) { e.printStackTrace(); }

        return list;
    }
}
