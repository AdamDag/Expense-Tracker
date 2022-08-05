package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import account.AccountsManager;

public final class SerializationManager {
  public static String dataFilename = "data.ser";

  public static void serialize(AccountsManager accountsManager) {
    try {
      FileOutputStream file = new FileOutputStream(dataFilename);
      ObjectOutputStream dataOutput = new ObjectOutputStream(file);
      dataOutput.writeObject(accountsManager);
      dataOutput.close();
      file.close();
    } catch (IOException exception) {
      System.out.println("IOException was caught!");
    }
  }

  public static AccountsManager deserialize() {
    try {
      FileInputStream file = new FileInputStream(dataFilename);
      ObjectInputStream dataInput = new ObjectInputStream(file);
      AccountsManager retrievedAccountsData = (AccountsManager)dataInput.readObject();
      dataInput.close();
      file.close();
      return retrievedAccountsData;
    } catch (IOException exception) {
      System.out.println("IOException was caught!");
    } catch (ClassNotFoundException exception) {
      System.out.println("Unhandled class exception was caught!");
    }
    
    return new AccountsManager();
  }
}
