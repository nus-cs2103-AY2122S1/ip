public abstract class Task {

  protected String description;
  protected boolean done;
  protected static final String ALREADY_DONE = "Error! The task has already been marked as complete!";
  protected static final String DONE = "Nice! I've marked this task as done:";
  protected static final String INDENTATION_5 = "     ";

  public String handleMarkAsDone() {
    if (this.done) {
      return Task.ALREADY_DONE;
    } else {
      this.done = true;
      return Task.DONE + "\n" + INDENTATION_5 + this.toString();
    }
  }
}
