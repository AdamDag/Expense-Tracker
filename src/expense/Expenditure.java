package expense;

public class Expenditure {
  private String name;
  private String categoryName;
  private double amount;
  private String date;

  public Expenditure(String name, double amount, String categoryName) {
    this.name = name;
    this.amount = amount;
    this.categoryName = categoryName;
    // we need to set the date here, in the constructor
    // once the instance is created
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCategoryName() {
    return this.categoryName;
  }

  public void setCategory(String categoryName) {
    this.categoryName = categoryName;
  }

  public double getAmount() {
    return this.amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public String getDate() {
    return this.date;
  }

  public void setDate(String date) {
    this.date = date;
  }
}
