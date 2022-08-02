package account;

public class Account {
  private static int count = 0;
  private String username;
  private String password;
  private int id;

  public Account(String username, String password) {
    this.username = username;
    this.password = password;
    this.id = ++count;
  }

  public String getUsername() {
    return this.username;
  }

  public int getAccountId() {
    return this.id;
  }

  public boolean authenticate(String password) {
    return this.password.equals(password);
  }
}
