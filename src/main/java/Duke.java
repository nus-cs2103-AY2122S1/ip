import java.util.Scanner;

public class Duke {

  private static final String LINE =
    "     ____________________________________________________________\n";
  private static final String INDENT = "      ";

  private static void formatOutput(String inp) {
    System.out.println(LINE + INDENT + inp + '\n' + LINE);
  }

  private static void getInput() {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNextLine()) {
      String input = sc.nextLine();
      Logic.takeInput(input);
      if (Logic.endChat()) break;
    }
  }

  public static void main(String[] args) {
    String logo =
      " ____        _        \n" +
      "|  _ \\ _   _| | _____ \n" +
      "| | | | | | | |/ / _ \\\n" +
      "| |_| | |_| |   <  __/\n" +
      "|____/ \\__,_|_|\\_\\___|\n";
    String output = "Hello! Welcome to\n" + logo + "\nHow may i help you?\n";
    System.out.println(LINE.trim());
    output.lines().forEach(op -> System.out.println("      " + op));
    System.out.println(LINE.trim());
    getInput();
  }
}
