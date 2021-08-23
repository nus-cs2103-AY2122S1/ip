public class Task {

  public enum Type {
    TODO, EVENT, DEADLINE,
  }
  
  private final String description;
  private final Type type;
  private boolean done;

  public static Task createTask(String input, Type type) throws UserInputError {
    String[] details;
    switch (type) {
      case TODO:
        return new Todo(input);
      case EVENT:
        details = separateDetails(input, "/at");
        return new Event(details[0], details[1]);
      case DEADLINE:
        details = separateDetails(input, "/by");
        return new Deadline(details[0], details[1]);
      default:
        return new Todo(input);
    }
  }

  protected Task(String description, Type type) {
    this.description = description;
    this.type = type;
    this.done = false;
  }

    private static String[] separateDetails(String str, String key)
      throws NoDescriptionException {
    if (str.split(key).length == 1) {
      throw new NoDescriptionException("Oops! Please use the correct format with " + key + " to indicate\ndatetime");
    }
    return str.split(key);
  }
    
  private String getStatusIcon() {
    return (done ? "[X]" : "[ ]"); // mark done task with X
  }

  protected void markDone() {
    done = true;
  }

  protected boolean isDone() {
    return done;
  }

  @Override
  public String toString() {
    return getStatusIcon() + " " + description;
  }
}
