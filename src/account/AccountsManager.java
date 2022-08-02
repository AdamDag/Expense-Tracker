package account;

import java.util.ArrayList;

public class AccountsManager {
  private int loggedInAccountId;
  private ArrayList<Account> accounts;

  public AccountsManager() {
    this.accounts = new ArrayList<Account>(); 
  }

  private Account findAccount(String username) {
    for (int i = 0; i < this.accounts.size(); i++) {
      Account currentAccount = this.accounts.get(i);

      if (currentAccount.getUsername().equals(username)) {
        return currentAccount;
      }
    }

    return new Account("", "");
  }

  public boolean login(String username, String password) {
    Account foundAccount = this.findAccount(username);

    if (this.findAccount(username).authenticate(password)) {
      this.loggedInAccountId = foundAccount.getAccountId();
    } else {
      return false;
    }

    return true;
  }

  public Account getCurrentAccount() {
    return this.accounts.get(this.loggedInAccountId);
  }
}
