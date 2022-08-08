package account;

import java.io.Serializable;
import java.util.ArrayList;

public class AccountsManager implements Serializable {
  private int loggedInAccountId;
  private ArrayList<Account> accounts;

  public AccountsManager() {
    this.accounts = new ArrayList<Account>(); 
  }

  private boolean doesAccountExist(String username) {
    for (Account account : this.accounts) {
      if (account.getUsername().equals(username)) return true;
    }

    return false;
  }

  public ArrayList<Account> getAccounts() {
    return this.accounts;
  }

  public boolean login(String username, String password) {
    if (this.doesAccountExist(username)) {
      for (Account account : this.accounts) {
        if (account.authenticate(username, password)) {
          this.loggedInAccountId = account.getAccountId();

          return true;
        } 
      }
    }

    return false;
  }

  public Account getCurrentAccount() {
    return this.accounts.get(this.loggedInAccountId);
  }

  public boolean addAccount(Account newAccount) {
    if (!this.doesAccountExist(newAccount.getUsername())) {
      this.accounts.add(newAccount);
      
      return true;
    }

    return false;
  }
}
