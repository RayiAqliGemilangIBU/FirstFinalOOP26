class Item {
    private String name;
    private int price;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double calculateTax() {
        return 0.0;
    }
}

class Chocolate extends Item {
    private double cocoaPercentage;

    public Chocolate(String name, int price, double cocoaPercentage) {
        super(name, price);
        this.cocoaPercentage = cocoaPercentage;
    }

    public double getCocoaPercentage() {
        return cocoaPercentage;
    }

    public void setCocoaPercentage(double cocoaPercentage) {
        this.cocoaPercentage = cocoaPercentage;
    }

    @Override
    public double calculateTax() {
        return getPrice() * 0.12;
    }
}

class Cigarettes extends Item {
    private double nicotinePercentage;

    public Cigarettes(String name, int price, double nicotinePercentage) {
        super(name, price);
        this.nicotinePercentage = nicotinePercentage;
    }

    public double getNicotinePercentage() {
        return nicotinePercentage;
    }

    public void setNicotinePercentage(double nicotinePercentage) {
        this.nicotinePercentage = nicotinePercentage;
    }

    @Override
    public double calculateTax() {
        return getPrice() * 0.89;
    }
}

public class Main {
    public static void main(String[] args) {
        Item genericItem = new Item("Notebook", 5000);
        Chocolate darkChocolate = new Chocolate("Dark Chocolate Bar", 25000, 75.5);
        Cigarettes herbalCigarettes = new Cigarettes("Premium Tobacco", 40000, 1.2);

        System.out.println("=== Testing Base Item ===");
        System.out.println("Item Name: " + genericItem.getName());
        System.out.println("Base Price: IDR " + genericItem.getPrice());
        System.out.println("Calculated Tax: IDR " + genericItem.calculateTax());

        System.out.println("\n=== Testing Chocolate Subclass ===");
        System.out.println("Item Name: " + darkChocolate.getName());
        System.out.println("Cocoa Percentage: " + darkChocolate.getCocoaPercentage() + "%");
        System.out.println("Base Price: IDR " + darkChocolate.getPrice());
        System.out.println("Calculated Tax (12%): IDR " + darkChocolate.calculateTax());

        System.out.println("\n=== Testing Cigarettes Subclass ===");
        System.out.println("Item Name: " + herbalCigarettes.getName());
        System.out.println("Nicotine Percentage: " + herbalCigarettes.getNicotinePercentage() + "%");
        System.out.println("Base Price: IDR " + herbalCigarettes.getPrice());
        System.out.println("Calculated Tax (89%): IDR " + herbalCigarettes.calculateTax());
    }
}