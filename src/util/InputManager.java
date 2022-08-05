package util;

import java.util.Scanner;

public class InputManager {
  private Scanner scanner;

  public InputManager() {
    this.scanner = new Scanner(System.in);
  }

  public String getUserInlineTextInput(String queryText) {
    System.out.print(queryText);
    this.scanner.useDelimiter("\n");
    String input = this.scanner.next();
    System.lineSeparator();

    return input.trim();
  }

  public int getUserOptionChoice(String queryText) {
    System.out.print(queryText);

    return this.scanner.nextInt();
  }

  public int getUserOptionChoice() {
    System.out.print("Enter your choice: ");

    return this.scanner.nextInt();
  }

  public static void promptEnterKey(String captionText) {
    System.out.println(captionText);
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine();
  }
}
