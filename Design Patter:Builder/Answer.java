public class Vehicle {
    private final String model;
    private final int year;
    private final boolean isElectric;

    private Vehicle(VehicleBuilder builder) {
        this.model = builder.model;
        this.year = builder.year;
        this.isElectric = builder.isElectric;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public boolean getIsElectric() {
        return isElectric;
    }

    public static class VehicleBuilder {
        private final String model;
        private int year;
        private boolean isElectric;

        public VehicleBuilder(String model) {
            this.model = model;
        }

        public VehicleBuilder setYear(int year) {
            this.year = year;
            return this;
        }

        public VehicleBuilder setIsElectric(boolean isElectric) {
            this.isElectric = isElectric;
            return this;
        }

        public Vehicle build() {
            return new Vehicle(this);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Vehicle tesla = new Vehicle.VehicleBuilder("Tesla Model 3")
                            .setYear(2022)
                            .setIsElectric(true)
                            .build();

        Vehicle basicCar = new Vehicle.VehicleBuilder("Ford Mustang")
                                .build();

        System.out.println("=== Testing Tesla ===");
        System.out.println("Model: " + tesla.getModel());
        System.out.println("Year: " + tesla.getYear());
        System.out.println("Is Electric: " + tesla.getIsElectric());

        System.out.println("\n=== Testing Basic Car ===");
        System.out.println("Model: " + basicCar.getModel());
        System.out.println("Year (Default): " + basicCar.getYear());
        System.out.println("Is Electric (Default): " + basicCar.getIsElectric());
    }
}