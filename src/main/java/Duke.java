import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

  private static final String INDENTATION_4 = "    ";
  private static final String INDENTATION_5 = "     ";
  private static final String BREAK_LINE = "____________________________________________________________";
  private static final List<String> GREETING = List.of("Hello! I'm Fergus' Chatbot", "What can I do for you?");
  private static final String FAREWELL = "Bye. Hope to see you again soon!";

  private static String formatOutput(String inputText) {
    return INDENTATION_4 + BREAK_LINE + "\n" + INDENTATION_5 + inputText + "\n" + INDENTATION_4 + BREAK_LINE + "\n";
  }

  private static void print(String inputText) {
    System.out.println(formatOutput(inputText));
  }

  private static String formatOutput(List<String> inputText) {
    String output = INDENTATION_4 + BREAK_LINE + "\n";
    for (int i = 0; i < inputText.size(); i++) {
      output += INDENTATION_5 + inputText.get(i) + "\n";
    }
    output += INDENTATION_4 + BREAK_LINE + "\n";
    return output;
  }

  private static void print(List<String> inputText) {
    System.out.println(formatOutput(inputText));
  }

  public static final String BYE_ENUM = "bye";
  public static final String LIST_ENUM = "list";

  public static void handleList(List<String> inputList) {
    List<String> outputList = new ArrayList<>();
    for (int i = 0; i < inputList.size(); i++) {
      outputList.add(i + 1 + ". " + inputList.get(i));
    }
    print(outputList);
  }

  public static void handleAdd(List<String> inputList, String newTask) {
    inputList.add(newTask);
    print("added: " + newTask);
  }

  public static void handleCommands() {
    List<String> taskArray = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    boolean hasCommands = true;

    while (hasCommands) {
      String command = scanner.nextLine();
      switch (command) {
        case BYE_ENUM:
          {
            scanner.close();
            hasCommands = false;
            break;
          }
        case LIST_ENUM:
          {
            handleList(taskArray);
            break;
          }
        default:
          {
            handleAdd(taskArray, command);
            break;
          }
      }
    }
  }

  public static void main(String[] args) {
    print(GREETING);
    handleCommands();
    print(FAREWELL);
  }
}
