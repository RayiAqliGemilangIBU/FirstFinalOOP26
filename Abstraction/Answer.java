import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

interface PhysicalActivity {
    String exercise();
}

interface FinancialActivity {
    void manageExpense(String date, double amount);
}

class Individual implements PhysicalActivity, FinancialActivity {
    private String name;
    private HashMap<String, Double> expenses; 

    public Individual(String name) {
        this.name = name;
        this.expenses = new HashMap<>();
    }

    @Override
    public String exercise() {
        return "Exercising";
    }

    @Override
    public void manageExpense(String date, double amount) {
        if (expenses.containsKey(date)) {
            double oldAmount = expenses.get(date);
            double newTotal = oldAmount + amount;
            expenses.put(date, newTotal);
        } else {
            expenses.put(date, amount);
        }
    }

    public Optional<Double> getTotalExpense(String date) {
        double total = 0.0;
        boolean isFound = false;

        for (Map.Entry<String, Double> entry : expenses.entrySet()) {
            if (entry.getKey().equals(date)) {
                total = entry.getValue();
                isFound = true;
            }
        }

        if (isFound) {
            return Optional.of(total);
        } else {
            return Optional.empty();
        }
    }

    public String getName() {
        return name;
    }
}

public class Main {
    public static void main(String[] args) {
        Individual individual = new Individual("Alex");

        System.out.println("=== Testing Physical Activity ===");
        System.out.println(individual.getName() + " status: " + individual.exercise());

        System.out.println("\n=== Testing Financial Activity (Adding Expenses) ===");
        individual.manageExpense("2026-06-22", 15.50); 
        individual.manageExpense("2026-06-22", 45.00); 
        individual.manageExpense("2026-06-23", 120.00); 
        System.out.println("Expenses recorded successfully.");

        System.out.println("\n=== Testing Optional Handling (Retrieving Expenses) ===");
        
        String testDate1 = "2026-06-22";
        Optional<Double> result1 = individual.getTotalExpense(testDate1);
        result1.ifPresentOrElse(
            total -> System.out.println("Total expense for " + testDate1 + ": $" + total),
            () -> System.out.println("No expenses found for " + testDate1)
        );

        String testDate2 = "2026-06-25";
        Optional<Double> result2 = individual.getTotalExpense(testDate2);
        result2.ifPresentOrElse(
            total -> System.out.println("Total expense for " + testDate2 + ": $" + total),
            () -> System.out.println("No expenses found for " + testDate2)
        );
    }
}