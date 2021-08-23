import java.util.Scanner;

class Duke {

  private static final String LINE =
    "     ____________________________________________________________\n";
  private static final String INDENT = "      ";
  private boolean isEndChat = false;
  public static TaskList taskList;

  private void run() {
    Scanner sc = new Scanner(System.in);
    taskList = new TaskList(Storage.readDatabase());

    while (sc.hasNextLine()) {
      String input = sc.nextLine();
      Parser parser = new Parser(input);
      try {
        takeInput(parser.parse());
      } catch (UserInputError e){
        renderOutput(e.getMessage());
      }
      if (isEndChat) {
        break;
      }
    }
    sc.close();
    Storage.writeDatabase(taskList.toArrayList());
  }

  private void endChat() {
    isEndChat = true;
  }

  public void takeInput(String[] input) {
    try {
      String cmd = input[0];
      String details = input.length == 1 ? "" : input[1];
      if (cmd.equals("bye")) {
        renderOutput("Bye. Hope to see you again soon!");
        endChat();
      } else if (cmd.equals("list")) {
        renderList();
      } else if (cmd.equals("done")) {
        markTaskComplete(Integer.parseInt(details));
      } else if (cmd.equals("todo")) {
        addNewTask(details, Task.Type.TODO);
      } else if (cmd.equals("deadline")) {
        addNewTask(details, Task.Type.DEADLINE);
      } else if (cmd.equals("event")) {
        addNewTask(details, Task.Type.EVENT);
      } else if (cmd.equals("delete")) {
        deleteTask(Integer.parseInt(details));
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

  private void renderList() {
    StringBuilder op = new StringBuilder();
    for (int i = 0; i < taskList.length(); i++) {
      op
        .append(i + 1)
        .append(". ")
        .append(taskList.getTask(i).toString())
        .append("\n");
    }
    renderOutput("Here are the tasks in your list:\n" + op);
  }

  private void markTaskComplete(int index) throws UserInputError {
    Task task = taskList.getTask(index);
    if (task.isDone()) {
      renderOutput("Great! But you have already completed this task!");
    } else {
      task.markDone();
      renderOutput("Nice! I've marked this task as done: \n" + task);
    }
  }

  private void addNewTask(String input, Task.Type type) throws UserInputError {
      Task newTask = Task.createTask(getDesc(input), type);
      taskList.addTask(newTask);
      addTaskOutput(newTask);
  }

  private void addTaskOutput(Task task) {
    String output =
      "Got it. I've added this task:\n" +
      INDENT +
      task.toString() +
      "\nNow you have " +
      taskList.length() +
      " tasks in the list.";
    renderOutput(output);
  }

  private void deleteTask(int index) throws UserInputError {
    Task deleted = taskList.getTask(index);
    taskList.deleteTask(index);
    deleteTaskOutput(deleted);
  }

  private void deleteTaskOutput(Task task) {
    String output =
      "Noted. I've removed this task:\n" +
      INDENT +
      task.toString() +
      "\nNow you have " +
      taskList.length() +
      " tasks in the list.";
    renderOutput(output);
  }

  public static void renderOutput(String op) {
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
    bot.run();
  }
}
