public class EventCommand extends Command {
  private Event event;

  public EventCommand(String description, String at) {
    this.event = new Event(description, at);
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    tasks.addTask(this.event);

    String response = "Got it. I've added this task:\n"
        + "       " + this.event + "\n"
        + "     Now you have "
        + tasks.getSize() + (tasks.getSize() > 1 ? " tasks" : " task")
        + " in the list.";
    ui.showResponse(response);

    storage.save(tasks.getTaskList());
  }

  @Override
  public boolean isExit() {
    return false;
  }
}
