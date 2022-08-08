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
    String name = this.inputManager.getUserInlineTextInput("Enter expenditure category name: ");
    String description = this.inputManager.getUserInlineTextInput("Enter expenditure category description: ");

    ExpenditureCategory newExpenditureCategory = new ExpenditureCategory(name, description);
    this.accountsManager.getCurrentAccount().addExpenditureCategory(newExpenditureCategory);

    InputManager.promptEnterKey("Expenditure category added! Press Enter to return to main menu...");
  }

  public void displayViewAllExpenditures() throws IOException{
    PrintManager.displayHorizontalLine();
    PrintManager.displayBreadcrumbs("Main Menu / View Expenditures");
    ArrayList<Expenditure> expenditures = this.accountsManager.getCurrentAccount().getExpenditures();
    if (expenditures.size() <= 0) {
      System.out.println("No expenditures found!");
    } else {
      for (Expenditure expenditure : expenditures) {
        System.out.println(expenditure.toString());
      }
    }
    InputManager.promptEnterKey("Press Enter to return to main menu...");
  }

  public void viewExpendituresByCategory() throws IOException{
    PrintManager.displayHorizontalLine();
    PrintManager.displayBreadcrumbs("Main Menu / View Expenditures by Category");
    String[] categoryNames = this.accountsManager.getCurrentAccount().getExpenditureCategoryNames();
    PrintManager.displayOptions(categoryNames);
    int userChoice = this.inputManager.getUserOptionChoice();
    String expenditureCategoryName = categoryNames[userChoice <= 0 ? 0 : userChoice - 1];
    ArrayList<Expenditure> expenditures = this.accountsManager.getCurrentAccount().getExpendituresByCategory(expenditureCategoryName);
    if (expenditures.size() <= 0) {
      System.out.println("No expenditures found!");
    } else {
      for (Expenditure expenditure : expenditures) {
        System.out.println(expenditure.toString());
      }
    }
    InputManager.promptEnterKey("Press Enter to return to main menu...");
 
  }

  public void viewExpendituresByAmount() throws IOException{
    PrintManager.displayHorizontalLine();
    PrintManager.displayBreadcrumbs("Main Menu / View Expenditures by Amount");
    ArrayList<Expenditure> expenditures = this.accountsManager.getCurrentAccount().getExpenditures();
    String filterAmount = this.inputManager.getUserInlineTextInput("Enter minimum expenditure filter amount: ");
    if (expenditures.size() <= 0) {
      System.out.println("No expenditures found!");
    } else {
      for (Expenditure expenditure : expenditures) {
        if (expenditure.getAmount() >= Double.parseDouble(filterAmount)) {
          System.out.println(expenditure.toString());
        }
      }
    }
    InputManager.promptEnterKey("Press Enter to return to main menu...");
 
  }

  public void viewExpendituresByDate() throws IOException{
    PrintManager.displayHorizontalLine();
    PrintManager.displayBreadcrumbs("Main Menu / View Expenditures by Date");
    ArrayList<Expenditure> expenditures = this.accountsManager.getCurrentAccount().getExpenditures();
    String filterDate = this.inputManager.getUserInlineTextInput("Enter minimum expenditure filter date in the form 'yyyy-MM-dd HH:mm:ss': ");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime filterDateTime = LocalDateTime.parse(filterDate, formatter);
    if (expenditures.size() <= 0) {
      System.out.println("No expenditures found!");
    } else {
      for (Expenditure expenditure : expenditures) {
        if (expenditure.getRawDate().compareTo(filterDateTime) >= 0) {
          System.out.println(expenditure.toString());
        }
      }
    }
    InputManager.promptEnterKey("Press Enter to return to main menu...");
 
  }
}

public class App {
  public static void main(String[] args) throws Exception {
    Scanner input = new Scanner(System.in);
  
    while (true) {
    //LOGIN LOGIC
      System.out.println("Main Window");
      System.out.println("==========================");
      System.out.println("1. Add an Expenditure");
      System.out.println("2. Add an Expenditure Category");
      System.out.println("3. View Expenditures");
      System.out.println("4. Quit");
      System.out.print("Enter your choice: ");
    
      int choice = Integer.parseInt(input.nextLine());

      if (choice == 1) {
        System.out.println("\033[H\033[2J");
        System.out.println("Add an Expenditure");
        System.out.println("Enter the name of the expenditure: ");
        String name = input.nextLine();
        System.out.println("Enter the amount of the expenditure: ");
        double amount = Double.parseDouble(input.nextLine());
        System.out.println("Enter the Category of the expenditure: ");
        String category = input.nextLine();
        //if category does not exist in Expenditure Category Array ask again/ try again or return to menu
        //Get date manually or automatically
        //need expenditure class
        Expenditure expenditure = new Expenditure(name, amount, category);

        //Add to array of expenditures
      } else if (choice == 2) {
        System.out.println("\033[H\033[2J");
        System.out.println("Add an Expenditure Category");
        System.out.println("Enter the name of the expenditure category: ");
        String catname = input.nextLine(); 
        //need expenditure category class
        ExpenditureCategory expenditureCategory = new ExpenditureCategory(catname);

        //Add to array of expenditure categories
      } else if(choice == 3){
        System.out.println("1. Filter by Category");
        System.out.println("2. Filter by Date");
        System.out.println("3. Filter by Amount");
        System.out.print("Enter your choice: ");
        int searchType = Integer.parseInt(input.nextLine());

        if (searchType == 1) {
          //Insert search by category logic
        } else if (searchType == 2) {
          //Insert search by date logic
        } else if(searchType == 3){
          //Insert search by amount logic
        } else {
          System.out.println("Invalid input, returning to main menu");
          //short wait time here
          System.out.println("\033[H\033[2J");
          continue;
        }
      } else if (choice == 4) {
        // When the user quits, we need to serialize the data and save it locally
        System.out.println("Quitting...");
        //insert short wait time here
        System.out.println("\033[H\033[2J");
        break;
      }
    }
  }
}
