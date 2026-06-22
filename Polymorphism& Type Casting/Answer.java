import java.util.ArrayList;
import java.util.List;

interface Product {
    String displayDetails();
}

abstract class InventoryItem implements Product {
    private String productName;
    private double price;
    private int quantity;

    public InventoryItem(String productName, double price, int quantity) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

class ElectronicsProduct extends InventoryItem {
    private String brand;

    public ElectronicsProduct(String productName, double price, int quantity, String brand) {
        super(productName, price, quantity);
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String displayDetails() {
        return "ElectronicsProduct";
    }
}

class ClothingProduct extends InventoryItem {
    private String size;

    public ClothingProduct(String productName, double price, int quantity, String size) {
        super(productName, price, quantity);
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String displayDetails() {
        return "ClothingProduct";
    }
}

class InventoryManager {
    public List<InventoryItem> updateInventory(List<InventoryItem> items) {
        for (InventoryItem item : items) {
            if (item instanceof ElectronicsProduct) {
                item.setPrice(item.getPrice() * 0.9);
            } else if (item instanceof ClothingProduct) {
                item.setPrice(item.getPrice() * 0.8);
            }
        }
        return items;
    }
}

public class Main {
    public static void main(String[] args) {
        List<InventoryItem> warehouseItems = new ArrayList<>();
        warehouseItems.add(new ElectronicsProduct("Laptop", 1000.0, 5, "Asus"));
        warehouseItems.add(new ClothingProduct("T-Shirt", 50.0, 20, "L"));

        InventoryManager manager = new InventoryManager();

        System.out.println("=== Before Update (Original Price) ===");
        for (InventoryItem item : warehouseItems) {
            System.out.println("Type: " + item.displayDetails() + " | Product: " + item.getProductName() + " | Price: $" + item.getPrice());
        }

        manager.updateInventory(warehouseItems);

        System.out.println("\n=== After Update (Discount Applied) ===");
        for (InventoryItem item : warehouseItems) {
            System.out.println("Type: " + item.displayDetails() + " | Product: " + item.getProductName() + " | Price: $" + item.getPrice());
        }
    }
}