public class Expenditure {
   private String name;
   private String category;
   private String amount;
   private String date;

   public Expenditure(String name, String amount, String category) {
      this.name = name;
      this.amount = amount;
      this.category = category;
   }

   //getter and setter methods
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
