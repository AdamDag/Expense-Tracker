package account;

public class Account {
  private static int count = 0;
  private String username;
  private String password;
  private int id;

  public Account(String username, String password) {
    this.username = username;
    this.password = password;
    this.id = count++;
  }

  public String getUsername() {
    return this.username;
  }

  public int getAccountId() {
    return this.id;
  }

  public boolean authenticate(String username, String password) {
    return this.username.equals(username) && this.password.equals(password);
  }
<<<<<<< Updated upstream
=======

  public void addExpenditure(Expenditure expenditure) {
    this.expenditures.add(expenditure);
  }

  public ArrayList<Expenditure> getExpenditures() {
    return this.expenditures;
  }

  public void addExpenditureCategory(ExpenditureCategory expenditureCategory) {
    this.expenditureCategories.add(expenditureCategory);
  }

  public ArrayList<ExpenditureCategory> getExpenditureCategories() {
    return this.expenditureCategories;
  }

  public String[] getExpenditureCategoryNames() {
    int expenditureCategoriesLength = this.expenditureCategories.size();
    String[] categoryNames = new String[expenditureCategoriesLength];

    for (int i = 0; i < expenditureCategoriesLength; i++) {
      categoryNames[i] = this.expenditureCategories.get(i).getName();
    }

    return categoryNames;
  }

  public ArrayList<Expenditure> getExpendituresByCategory(String expenditureCategoryName) {
    ArrayList<Expenditure> expenditures = new ArrayList<Expenditure>();
    for (Expenditure expenditure : this.expenditures) {
      if (expenditure.getCategoryName().equals(expenditureCategoryName)) {
        expenditures.add(expenditure);
      }
    }
    return expenditures;
  }
>>>>>>> Stashed changes
}
