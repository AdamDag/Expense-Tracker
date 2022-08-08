package expense;

import java.io.Serializable;
import util.CurrentDateTime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; 

public class Expenditure implements Serializable {
  private String name;
  private String categoryName;
  private double amount;
  private String date;
  private LocalDateTime rawDate;

  public Expenditure(String name, double amount, String categoryName) {
    this.name = name;
    this.amount = amount;
    this.categoryName = categoryName;
    this.date = CurrentDateTime.dateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    this.rawDate = CurrentDateTime.dateTime();
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

  public LocalDateTime getRawDate() {
    return this.rawDate;
  }
}
