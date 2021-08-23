import java.util.ArrayList;
import java.util.Scanner;

class Duke {

  private static final String LINE =
    "     ____________________________________________________________\n";
  private static final String INDENT = "      ";
  private boolean isEndChat = false;
  private static final ArrayList<Task> taskList = new ArrayList<>();
 
  private void getInput() {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNextLine()) {
      String input = sc.nextLine();
      takeInput(input);
      if (isEndChat) {
        sc.close();
        break;
      }
    }
  }

  private void endChat() {
    isEndChat = false;
  }

  private static String getFirstWord(String text) {
    int index = text.indexOf(' ');
    if (index > -1) { // Check if there is more than one word.
      return text.substring(0, index).trim(); // Extract first word.
    } else {
      return text; // Text is the first word itself.
    }
  }

  public void takeInput(String input) {
    try {
      String command = getFirstWord(input);
      if (input.equals("bye")) {
        renderOutput("Bye. Hope to see you again soon!");
        endChat();
      } else if (input.equals("list")) {
        renderList();
      } else if (command.equals("done")) {
        markTaskComplete(input);
      } else if (command.equals("todo")) {
        addNewTask(input, Task.Type.TODO);
      } else if (command.equals("deadline")) {
        addNewTask(input, Task.Type.DEADLINE);
      } else if (command.equals("event")) {
        addNewTask(input, Task.Type.EVENT);
      } else if (command.equals("delete")) {
        deleteTask(input);
      } else {
        throw new InvalidInputException();
      }
    } catch (UserInputError e) {
      renderOutput(e.getMessage());
    }
  }

  private String getDesc(String input) {
    return input.split(" ", 2)[1];
  }

  //check for incomplete input
  private void checkDescExist(String input)
    throws NoDescriptionException {
    if (input.split(" ").length == 1) {
      throw new NoDescriptionException(
        "Oops! Please add description for your command."
      );
    }
  }

  private int getTaskNumber(String cmd) {
    String[] result = cmd.split(" ");
    if (result[1].matches("\\d+")) {
      return Integer.parseInt(result[1]);
    }
    return -1;
  }

  private void renderList() {
    StringBuilder op = new StringBuilder();
    for (int i = 0; i < taskList.size(); i++) {
      op
        .append(i + 1)
        .append(". ")
        .append(taskList.get(i).toString())
        .append("\n");
    }
    renderOutput("Here are the tasks in your list:\n" + op);
  }

  private void checkIndexRange(int index)
    throws ExceedListSizeException {
    if (index < 0) {
      throw new ExceedListSizeException(
        "Invalid task reference!\nIndex should be more than 0."
      );
    }

    if (index > taskList.size() - 1) {
      throw new ExceedListSizeException(
        "Invalid task reference!\nYou currently have " +
        taskList.size() +
        " tasks."
      );
    }
  }

  private void markTaskComplete(String cmd) throws UserInputError {
    checkDescExist(cmd);
    int index = getTaskNumber(cmd) - 1;
    checkIndexRange(index);

    Task task = taskList.get(index);
    if (task.isDone()) {
      renderOutput("Great! But you have already completed this task!");
    } else {
      task.markDone();
      renderOutput("Nice! I've marked this task as done: \n" + task);
    }
  }


  private void addNewTask(String input, Task.Type type) throws UserInputError {
      checkDescExist(input);
      Task newTask = Task.createTask(getDesc(input), type);
      taskList.add(newTask);
      addTaskOutput(newTask);
  }

  private void addTaskOutput(Task task) {
    String output =
      "Got it. I've added this task:\n" +
      INDENT +
      task.toString() +
      "\nNow you have " +
      taskList.size() +
      " tasks in the list.";
    renderOutput(output);
  }

  private void deleteTask(String input) throws UserInputError {
    checkDescExist(input);
    int index = getTaskNumber(input) - 1;
    checkIndexRange(index);

    Task deleted = taskList.get(index);
    taskList.remove(index);
    deleteTaskOutput(deleted);
  }

  private void deleteTaskOutput(Task task) {
    String output =
      "Noted. I've removed this task:\n" +
      INDENT +
      task.toString() +
      "\nNow you have " +
      taskList.size() +
      " tasks in the list.";
    renderOutput(output);
  }

  private void renderOutput(String op) {
    System.out.println(LINE);
    op.lines().forEach(line -> System.out.println("      " + line));
    System.out.println(LINE);
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

    Duke bot = new Duke();
    bot.getInput();
  }
}
