package duke;

import java.util.List;
import java.util.Scanner;

public class Duke {

  private Storage storage;
  private TaskList taskArray;
  private Ui ui;

  public Duke(String filePath) {
    this.ui = new Ui();
    this.storage = new Storage(filePath);
    List<String> taskArrayAsString = storage.load();
    this.taskArray = new TaskList(taskArrayAsString);
  }

  private static final List<String> GREETING = List.of("Hello! I'm Fergus' Chatbot!", "What can I do for you?");
  private static final String FAREWELL = "Bye. Hope to see you again soon!";
  private static final String ERROR_UNKNOWN_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

  private static final String BYE_ENUM = "bye";
  private static final String LIST_ENUM = "list";
  private static final String DONE_ENUM = "done";
  protected static final String TODO_ENUM = "todo";
  protected static final String EVENT_ENUM = "event";
  protected static final String DEADLINE_ENUM = "deadline";
  private static final String DELETE_ENUM = "delete";
  private static final String SAVE_ENUM = "save";

  /**
   * Handles the user commands using a switch.
   */
  public void handleCommands() {
    Scanner scanner = new Scanner(System.in);
    boolean hasCommands = true;

    while (hasCommands) {
      String commandLine = scanner.nextLine();
      String[] commands = commandLine.split(" ");
      String command = commands[0];
      switch (command) {
        case BYE_ENUM:
          {
            scanner.close();
            hasCommands = false;
            break;
          }
        case LIST_ENUM:
          {
            this.taskArray.handleList();
            break;
          }
        case EVENT_ENUM:
          {
            this.taskArray.handleAddEvent(commands);
            break;
          }
        case DEADLINE_ENUM:
          {
            this.taskArray.handleAddDeadline(commands);
            break;
          }
        case TODO_ENUM:
          {
            this.taskArray.handleAddToDo(commands);
            break;
          }
        case DONE_ENUM:
          {
            int taskIndex = Integer.parseInt(commands[1]);
            this.taskArray.handleDone(taskIndex);
            break;
          }
        case DELETE_ENUM:
          {
            int taskIndex = Integer.parseInt(commands[1]);
            this.taskArray.handleDelete(taskIndex);
            break;
          }
        case SAVE_ENUM:
          {
            this.taskArray.handleSave(this.storage);
            break;
          }
        default:
          {
            Printer.print(ERROR_UNKNOWN_COMMAND);
          }
      }
    }
  }

  /**
   * Runs the Duke program.
   */
  public void run() {
    Printer.print(GREETING);
    this.handleCommands();
    Printer.print(FAREWELL);
  }

  public static void main(String[] args) {
    new Duke("FergusChatBot.txt").run();
  }
}
