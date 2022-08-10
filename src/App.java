import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import account.Account;
import account.AccountsManager;

import expense.Expenditure;
import expense.ExpenditureCategory;

import util.InputManager;
import util.PrintManager;
import util.SerializationManager;

class DisplayManager {
  private InputManager inputManager;
  private AccountsManager accountsManager;

  public DisplayManager(InputManager inputManager, AccountsManager accountsManager) {
    this.inputManager = inputManager;
    this.accountsManager = accountsManager;
  }

  private void displayLogin() {
    // loop the login three times
    // we give the users a choice to try login in 3 times
    // before we exit them from the app
    for (int i = 0; i < 3; i++) {
      PrintManager.clearConsole();
      PrintManager.displayHorizontalLine();
      PrintManager.displayBreadcrumbs("Login");

      if (i > 0) {
        System.out.println("You have " + ( 3 - i) + " login attempt(s) remaining before the program closes");
      }

      String username = this.inputManager.getUserInlineTextInput("Enter your username: ");
      String password = this.inputManager.getUserInlineTextInput("Enter your password: ");

      if (this.accountsManager.login(username, password)) {
        return;
      }
    }
    
    // since loop exited we can close the program because they didnt succeed
    System.exit(0);
  }

  private void displayRegistration() {
    PrintManager.clearConsole();
    PrintManager.displayHorizontalLine();
    PrintManager.displayBreadcrumbs("Register");

    String username = this.inputManager.getUserInlineTextInput("Enter your username: ");
    String password = this.inputManager.getUserInlineTextInput("Enter your password: ");
    String confirmPassword = this.inputManager.getUserInlineTextInput("Confirm password: ");

    while (!password.equals(confirmPassword)) {
      confirmPassword = this.inputManager.getUserInlineTextInput("Try again: ");
    }

    this.accountsManager.addAccount(new Account(username, password));
    this.accountsManager.login(username, password);
  }

  public void displayInitialMenu() throws IOException {
    PrintManager.clearConsole();
    PrintManager.displayHorizontalLine();
    String[] options = {"Login", "Register", "Exit"};
    PrintManager.displayOptions(options);
    int userChoice = this.inputManager.getUserOptionChoice();
    
    if (userChoice == 1) {
      this.displayLogin();

      return;
    }

    if (userChoice == 2) {
      this.displayRegistration();

      return;
    } 

    System.exit(0);
  }

  public int displayMainMenu() throws IOException {
    PrintManager.displayHorizontalLine();
    System.out.println("Welcome back, " + accountsManager.getCurrentAccount().getUsername() + "!");
    String[] userOptions = {"Add an expenditure", "Add an expenditure category", "View expenditures", "Exit"};
    PrintManager.displayOptions(userOptions);

    return this.inputManager.getUserOptionChoice();
  }

  public void displayAddExpenditure() throws IOException {
    PrintManager.displayHorizontalLine();
    PrintManager.displayBreadcrumbs("Main Menu / Add Expenditure");
    String[] categoryNames = this.accountsManager.getCurrentAccount().getExpenditureCategoryNames();

    if (categoryNames.length <= 0) {
      System.out.println("Please add expenditure category before adding any expenses!");
      InputManager.promptEnterKey("Press Enter to return to Main Menu...");
    } else {
      PrintManager.displayOptions(categoryNames);
      int userChoice = this.inputManager.getUserOptionChoice();
      String expenditureCategoryName = categoryNames[userChoice <= 0 ? 0 : userChoice - 1];
      String expenseName = this.inputManager.getUserInlineTextInput("What was the expense for: ");
      String amount = this.inputManager.getUserInlineTextInput("How much did you spend: ");

      Expenditure newExpenditure = new Expenditure(expenseName, Double.parseDouble(amount), expenditureCategoryName);
      this.accountsManager.getCurrentAccount().addExpenditure(newExpenditure);

      InputManager.promptEnterKey("Expenditure added! Press Enter to return to main menu...");
    }
  }

  public void displayAddExpenditureCategory() throws IOException {
    PrintManager.displayHorizontalLine();
    PrintManager.displayBreadcrumbs("Main Menu / Add Expenditure Category");
    String name = this.inputManager.getUserInlineTextInput("Enter expenditue category name: ");
    String description = this.inputManager.getUserInlineTextInput("Enter expenditure category description: ");

    ExpenditureCategory newExpenditureCategory = new ExpenditureCategory(name, description);
    this.accountsManager.getCurrentAccount().addExpenditureCategory(newExpenditureCategory);

    InputManager.promptEnterKey("Expenditure category added! Press Enter to return to main menu...");
  }

  private void displayViewAllExpenditures() throws IOException{
    PrintManager.displayHorizontalLine();
    PrintManager.displayBreadcrumbs("Main Menu / View Expenditures / View all");
    ArrayList<Expenditure> expenditures = this.accountsManager.getCurrentAccount().getExpenditures();

    if (expenditures.size() <= 0) {
      System.out.println("No expenditures found!");
    } else {
      PrintManager.clearConsole();
      PrintManager.displayHorizontalLine();
      PrintManager.displayBreadcrumbs("Main Menu / View Expenditures / View all");
      PrintManager.displayExpenditureTableHeading();
      for (Expenditure expenditure : expenditures) {
        System.out.println(expenditure.toString());
      }
    }

    InputManager.promptEnterKey("Press Enter to return to main menu...");
  }

  private void displayViewExpendituresByCategory() throws IOException{
    PrintManager.displayHorizontalLine();
    PrintManager.displayBreadcrumbs("Main Menu / View Expenditures / View by category");
    String[] categoryNames = this.accountsManager.getCurrentAccount().getExpenditureCategoryNames();
    PrintManager.displayOptions(categoryNames);
    int userChoice = this.inputManager.getUserOptionChoice();
    String expenditureCategoryName = categoryNames[userChoice <= 0 ? 0 : userChoice - 1];
    ArrayList<Expenditure> expenditures = this.accountsManager.getCurrentAccount().getExpendituresByCategory(expenditureCategoryName);
  
    if (expenditures.size() <= 0) {
      System.out.println("No expenditures found!");
    } else {
      PrintManager.clearConsole();
      PrintManager.displayHorizontalLine();
      PrintManager.displayBreadcrumbs("Main Menu / View Expenditures / View by category");
      PrintManager.displayExpenditureTableHeading();
      for (Expenditure expenditure : expenditures) {
        System.out.println(expenditure.toString());
      }
    }

    InputManager.promptEnterKey("Press Enter to return to main menu..."); 
  }

  private void displayViewExpendituresByAmount() throws IOException{
    PrintManager.displayHorizontalLine();
    PrintManager.displayBreadcrumbs("Main Menu / View Expenditures / View by amount");
    ArrayList<Expenditure> expenditures = this.accountsManager.getCurrentAccount().getExpenditures();
    String filterAmount = this.inputManager.getUserInlineTextInput("Enter minimum expenditure filter amount: ");
    if (expenditures.size() <= 0) {
      System.out.println("No expenditures found!");
    } else {
      PrintManager.clearConsole();
      PrintManager.displayHorizontalLine();
      PrintManager.displayBreadcrumbs("Main Menu / View Expenditures / View by amount");
      PrintManager.displayExpenditureTableHeading();
      for (Expenditure expenditure : expenditures) {
        if (expenditure.getAmount() >= Double.parseDouble(filterAmount)) {
          System.out.println(expenditure.toString());
        }
      }
    }

    InputManager.promptEnterKey("Press Enter to return to main menu..."); 
  }

  private void displayViewExpendituresByDate() throws IOException{
    PrintManager.displayHorizontalLine();
    PrintManager.displayBreadcrumbs("Main Menu / View Expenditures / View by date");
    ArrayList<Expenditure> expenditures = this.accountsManager.getCurrentAccount().getExpenditures();
    String filterDate = this.inputManager.getUserInlineTextInput("Enter minimum expenditure filter date in the form 'yyyy-MM-dd HH:mm:ss': ");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime filterDateTime = LocalDateTime.parse(filterDate, formatter);

    if (expenditures.size() <= 0) {
      System.out.println("No expenditures found!");
    } else {
      PrintManager.clearConsole();
      PrintManager.displayHorizontalLine();
      PrintManager.displayBreadcrumbs("Main Menu / View Expenditures / View by date");
      PrintManager.displayExpenditureTableHeading();
      for (Expenditure expenditure : expenditures) {
        if (expenditure.getRawDate().compareTo(filterDateTime) >= 0) {
          System.out.println(expenditure.toString());
        }
      }
    }

    InputManager.promptEnterKey("Press Enter to return to main menu..."); 
  }

  public void displayViewExpendituresMenu() throws IOException {
    PrintManager.displayHorizontalLine();
    PrintManager.displayBreadcrumbs("Main Menu / View Expenditures");
    String[] options = {
      "View all expenditures",
      "View expenditures by category",
      "View expenditures by amount",
      "View expenditures by date",
      "Return to main screen"
    };
    PrintManager.displayOptions(options);
    int userChoice = this.inputManager.getUserOptionChoice();

    if (userChoice == 1) {
      PrintManager.clearConsole();
      this.displayViewAllExpenditures();
    }

    if (userChoice == 2) {
      PrintManager.clearConsole();
      this.displayViewExpendituresByCategory();
    }

    if (userChoice == 3) {
      PrintManager.clearConsole();
      this.displayViewExpendituresByAmount();
    }

    if (userChoice == 4) {
      PrintManager.clearConsole();
      this.displayViewExpendituresByDate();
    }
  }
}

public class App {
  public static void main(String[] args) throws Exception {
    AccountsManager accountsManager = SerializationManager.deserialize();
    InputManager inputManager = new InputManager();
    DisplayManager displayManager = new DisplayManager(inputManager, accountsManager);

    displayManager.displayInitialMenu();

    int userEntry = 0;

    while (true) {
      PrintManager.clearConsole();

      if (userEntry == 1) {
        userEntry = 0;
        displayManager.displayAddExpenditure();
      } else if (userEntry == 2) {
        userEntry = 0;
        displayManager.displayAddExpenditureCategory();
      } else if (userEntry == 3) {
        userEntry = 0;
        displayManager.displayViewExpendituresMenu();
      } else if (userEntry == 4) {
        System.out.println("Exiting program...");
        SerializationManager.serialize(accountsManager);
        break;
      } else {
        userEntry = displayManager.displayMainMenu();
      }
    }
  }
}
