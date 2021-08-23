import java.time.format.DateTimeFormatter;

class Task {

  public enum Type {
    TODO, EVENT, DEADLINE,
  }

  private static final String SEPARATE = " ~#~ ";
  private final DateTimeFormatter formatter =DateTimeFormatter.ofPattern("dd/MM/yy");

  private final String description;
  private final Type type;
  private boolean isDone;

  protected Task(String description, Type type, boolean done) {
    this.description = description;
    this.type = type;
    this.isDone = done;
  }

  public static Task createTask(String input, Type type) throws UserInputError {
    String[] details;
    switch (type) {
      case TODO:
        return new Todo(input, false);
      case EVENT:
        details = separateDetails(input, "/at");
        return new Event(details[0], details[1], false);
      case DEADLINE:
        details = separateDetails(input, "/by");
        return new Deadline(details[0], details[1], false);
      default:
        return new Todo(input, false);
    }
  }

  public static Task stringToTask(String dataString) {
    Task newTask = null;
    String[] infoArr = dataString.split(SEPARATE);

    String type = infoArr[0];
    boolean isDone = infoArr[1].equals("1");
    String desc = infoArr[2];
    String date = infoArr.length == 3 ? "" : infoArr[3];

    switch (type) {
      case "T":
          newTask = new Todo(desc, isDone);
          break;
      case "D":
          newTask = new Deadline(desc, date, isDone);
          break;
      case "E":
          newTask = new Event(desc, date, isDone);
          break;
    }
    return newTask;
  }

  private static String[] separateDetails(String str, String key)
      throws NoDescriptionException {
    if (str.split(key).length == 1) {
      throw new NoDescriptionException("Oops! Please use the correct format with " + key + " to indicate\ndatetime");
    }
    return str.split(key);
  }

  protected String taskToString() {
    String type;
    String done = this.isDone ? "1" : "0";

    switch (this.type) {
      case TODO:
        type = "T";
        break;
      case EVENT:
        type = "E";
        break;
      case DEADLINE:
        type = "D";
        break;
      default:
        throw new IllegalArgumentException("Task type enums inconsistent");
    }
    return String.format(
      "%s%s%s%s%s%s",
      type,
      SEPARATE,
      done,
      SEPARATE,
      this.description,
      SEPARATE
    );
  }

  private String getStatusIcon() {
    return (isDone ? "[X]" : "[ ]"); // mark done task with X
  }

  protected void markDone() {
    isDone = true;
  }

  protected boolean isDone() {
    return isDone;
  }

  @Override
  public String toString() {
    return getStatusIcon() + " " + description;
  }
}
