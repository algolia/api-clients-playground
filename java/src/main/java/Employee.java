import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Employee implements Serializable {

  public Employee() {}

  public Employee(String company, String name) {
    this.company = company;
    this.name = name;
  }

  public String getCompany() {
    return company;
  }

  public Employee setCompany(String company) {
    this.company = company;
    return this;
  }

  public String getName() {
    return name;
  }

  public Employee setName(String name) {
    this.name = name;
    return this;
  }

  public String getQueryID() {
    return queryID;
  }

  public Employee setQueryID(String queryID) {
    this.queryID = queryID;
    return this;
  }

  private String company;
  private String name;
  private String queryID;

  public static List<Employee> hireEmployees() {
    return Arrays.asList(
        new Employee("Algolia", "Julien Lemoine"),
        new Employee("Algolia", "Julien Lemoine"),
        new Employee("Amazon", "Jeff Bezos"),
        new Employee("Apple", "Steve Jobs"),
        new Employee("Apple", "Steve Wozniak"),
        new Employee("Arista Networks", "Jayshree Ullal"),
        new Employee("Google", "Lary Page"),
        new Employee("Google", "Rob Pike"),
        new Employee("Google", "Sergueï Brin"),
        new Employee("Microsoft", "Bill Gates"),
        new Employee("SpaceX", "Elon Musk"),
        new Employee("Tesla", "Elon Musk"),
        new Employee("Yahoo", "Marissa Mayer"));
  }
}
