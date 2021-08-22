package duke;

public abstract class Task {

  protected String description;
  protected boolean done;
  protected static final String ERROR_ALREADY_DONE = "Error! The task has already been marked as complete!";
  protected static final String SUCCESS_DONE = "Nice! I've marked this task as done:";
  protected static final String INDENTATION_5 = "     ";
  protected static final String DIVIDER = " | ";

  public String handleMarkAsDone() {
    if (this.done) {
      return Task.ERROR_ALREADY_DONE;
    } else {
      this.done = true;
      return Task.SUCCESS_DONE + "\n" + INDENTATION_5 + this.toString();
    }
  }

  abstract String toWriteString();
}
