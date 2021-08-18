import java.util.ArrayList;

public class Logic {

  private static final String LINE =
    "     ____________________________________________________________\n";
  private static final String INDENT = "      ";

  private static final ArrayList<Task> taskList = new ArrayList<>();
  private static boolean continueChat = true;

  private static String getFirstWord(String text) {
    int index = text.indexOf(' ');
    if (index > -1) { // Check if there is more than one word.
      return text.substring(0, index).trim(); // Extract first word.
    } else {
      return text; // Text is the first word itself.
    }
  }

  public static void takeInput(String input) {
    try {
      String command = getFirstWord(input);
      if (input.equals("bye")) {
        renderOutput("Bye. Hope to see you again soon!");
        continueChat = false;
      } else if (input.equals("list")) {
        renderList();
      } else if (command.equals("done")) {
        markTaskComplete(input);
      } else if (command.equals("todo")) {
        addTodoTask(input);
      } else if (command.equals("deadline")) {
        addDeadlineTask(input);
      } else if (command.equals("event")) {
        addEventTask(input);
      } else if (command.equals("delete")) {
        deleteTask(input);
      } else {
        throw new InvalidInputException();
      }
    } catch (UserInputError e) {
      renderOutput(e.getMessage());
    }
  }

  private static String getDesc(String input) {
    return input.split(" ", 2)[1];
  }

  //check for incomplete input
  private static void checkDescExist(String input)
    throws NoDescriptionException {
    if (input.split(" ").length == 1) {
      throw new NoDescriptionException(
        "Oops! Please add description for your command."
      );
    }
  }

  public static boolean endChat() {
    return !continueChat;
  }

  private static int getTaskNumber(String cmd) {
    String[] result = cmd.split(" ");
    if (result[1].matches("\\d+")) {
      return Integer.parseInt(result[1]);
    }
    return -1;
  }

  private static void renderList() {
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

  private static void checkIndexRange(int index)
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

  private static void markTaskComplete(String cmd) throws UserInputError {
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

  private static void addTodoTask(String input) throws UserInputError {
    checkDescExist(input);
    Task newTask = new Todo(getDesc(input));
    taskList.add(newTask);
    addTaskOutput(newTask);
  }

  private static String[] separateDetails(String str, String key)
    throws NoDescriptionException {
    if (str.split(key).length == 1) {
      throw new NoDescriptionException(
        "Oops! Please use the correct format with " +
        key +
        " to indicate\ndatetime"
      );
    }
    return str.split(key);
  }

  private static void addDeadlineTask(String input) throws UserInputError {
    checkDescExist(input);
    String[] details = separateDetails(getDesc(input), "/by");
    Task newTask = new Deadline(details[0], details[1]);
    taskList.add(newTask);
    addTaskOutput(newTask);
  }

  private static void addEventTask(String input) throws UserInputError {
    checkDescExist(input);
    String[] details = separateDetails(getDesc(input), "/at");
    Task newTask = new Event(details[0], details[1]);
    taskList.add(newTask);
    addTaskOutput(newTask);
  }

  private static void addTaskOutput(Task task) {
    String output =
      "Got it. I've added this task:\n" +
      INDENT +
      task.toString() +
      "\nNow you have " +
      taskList.size() +
      " tasks in the list.";
    renderOutput(output);
  }

  private static void deleteTask(String input) throws UserInputError {
    checkDescExist(input);
    int index = getTaskNumber(input) - 1;
    checkIndexRange(index);

    Task deleted = taskList.get(index);
    taskList.remove(index);
    deleteTaskOutput(deleted);
  }

  private static void deleteTaskOutput(Task task) {
    String output =
      "Noted. I've removed this task:\n" +
      INDENT +
      task.toString() +
      "\nNow you have " +
      taskList.size() +
      " tasks in the list.";
    renderOutput(output);
  }

  private static void renderOutput(String op) {
    System.out.println(LINE);
    op.lines().forEach(line -> System.out.println("      " + line));
    System.out.println(LINE);
  }
}
