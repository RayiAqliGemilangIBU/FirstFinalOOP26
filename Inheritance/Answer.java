import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

enum Gender {
    MALE, FEMALE
}

class Person {
    private String firstName;
    private int age;
    private Gender gender;

    public Person(String firstName, int age, Gender gender) {
        this.firstName = firstName;
        this.age = age;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}

class Assistant extends Person {
    public Assistant(String firstName, int age, Gender gender) {
        super(firstName, age, gender);
    }
}

class Professor extends Person {
    public Professor(String firstName, int age, Gender gender) {
        super(firstName, age, gender);
    }
}

class Company<T extends Person> {
    private List<T> employees;

    public Company(List<T> employees) {
        this.employees = employees;
    }

    public List<T> getEmployees() {
        return employees;
    }

    public void setEmployees(List<T> employees) {
        this.employees = employees;
    }

    public Optional<List<T>> filterByGender(Gender gender) {
        List<T> filteredList = new ArrayList<>();
        boolean isFound = false;

        for (T emp : employees) {
            if (emp.getGender() == gender) {
                filteredList.add(emp);
                isFound = true;
            }
        }

        if (isFound) {
            return Optional.of(filteredList);
        } else {
            return Optional.empty();
        }
    }

    public Optional<T> getByFirstName(String firstName) {
        for (T emp : employees) {
            if (emp.getFirstName().equals(firstName)) {
                return Optional.of(emp);
            }
        }
        return Optional.empty();
    }
}

public class Main {
    public static void main(String[] args) {
        List<Person> staffList = new ArrayList<>();
        staffList.add(new Professor("Alex", 45, Gender.MALE));
        staffList.add(new Assistant("Jane", 28, Gender.FEMALE));
        staffList.add(new Assistant("John", 24, Gender.MALE));

        Company<Person> company = new Company<>(staffList);

        System.out.println("=== Testing filterByGender ===");
        Optional<List<Person>> maleEmployees = company.filterByGender(Gender.MALE);
        maleEmployees.ifPresentOrElse(
            list -> {
                System.out.println("Male employees found:");
                for (Person p : list) {
                    System.out.println("- " + p.getFirstName() + " (" + p.getAge() + ")");
                }
            },
            () -> System.out.println("No male employees found.")
        );

        System.out.println("\n=== Testing getByFirstName ===");
        String searchName1 = "Jane";
        Optional<Person> emp1 = company.getByFirstName(searchName1);
        emp1.ifPresentOrElse(
            p -> System.out.println("Found: " + p.getFirstName() + ", Age: " + p.getAge() + ", Gender: " + p.getGender()),
            () -> System.out.println("Employee with name " + searchName1 + " not found.")
        );

        String searchName2 = "Mark";
        Optional<Person> emp2 = company.getByFirstName(searchName2);
        emp2.ifPresentOrElse(
            p -> System.out.println("Found: " + p.getFirstName()),
            () -> System.out.println("Employee with name " + searchName2 + " not found.")
        );
    }
}