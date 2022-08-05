package util;

public final class PrintManager { 
  public static void clearConsole() {
    try {
      final String osName = System.getProperty("os.name");
      if (osName.contains("Windows")) {
        Runtime.getRuntime().exec("cls");
      } else {
        System.out.println("\033\143");
      }
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  public static void displayOptions(String[] options) {
    System.out.println("Choose one of the following options:");
    for (int i = 0; i < options.length; i++) {
      System.out.println("(" + (i + 1) + ") " + options[i]);
    }
  }

  public static void displayHorizontalLine() {
    System.out.println("-----------------------------------------------------------------------------------------------------------");
  }

  public static void displayBreadcrumbs(String path) {
    System.out.println(path);
    System.out.println("===========================================================================================================");
  }
}
