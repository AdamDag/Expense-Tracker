import java.util.*;
import java.io.*;

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
