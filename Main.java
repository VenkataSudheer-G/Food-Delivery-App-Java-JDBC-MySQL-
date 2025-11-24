package com.Food_Delivery_App;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        FoodDAO foodDAO = new FoodDAO();
        OrderDAO orderDAO = new OrderDAO();

        while (true) {

            System.out.println("\n=== FOOD DELIVERY APP ===");
            System.out.println("1. View Menu");
            System.out.println("2. Place Order");
            System.out.println("3. View Order History");
            System.out.println("4. Add Food (Admin)");
            System.out.println("5. Delete Food (Admin)");
            System.out.println("6. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.printf("\n%-3s %-15s %-15s %-10s\n",
                            "ID", "Name", "Category", "Price");
                    System.out.println("--------------------------------------------------");

                    for (FoodItem f : foodDAO.getAllFood()) {
                        System.out.printf("%-3d %-15s %-15s ₹%-10.2f\n",
                                f.getId(), f.getName(), f.getCategory(), f.getPrice());
                    }
                    break;

                case 2:
                    System.out.print("Enter Food ID: ");
                    int foodId = sc.nextInt();
                    FoodItem item = foodDAO.getFoodById(foodId);

                    if (item == null) {
                        System.out.println("❌ Food not found!");
                        break;
                    }

                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();

                    orderDAO.placeOrder(item.getId(), qty, item.getPrice());
                    System.out.println("✅ Order Placed Successfully!");
                    break;

                case 3:
                    System.out.println("\n=== ORDER HISTORY ===");
                    orderDAO.getOrderHistory().forEach(System.out::println);
                    break;

                case 4:
                    sc.nextLine();
                    System.out.print("Name: ");
                    String n = sc.nextLine();
                    System.out.print("Category: ");
                    String c = sc.nextLine();
                    System.out.print("Price: ");
                    double p = sc.nextDouble();

                    foodDAO.addFood(n, c, p);
                    System.out.println("✅ Food Added!");
                    break;

                case 5:
                    System.out.print("Enter Food ID to delete: ");
                    foodDAO.deleteFood(sc.nextInt());
                    System.out.println("🗑️ Deleted!");
                    break;

                case 6:
                    System.out.println("👋 Goodbye!");
                    return;

                default:
                    System.out.println("❌ Invalid choice!");
            }
        }
    }
}
