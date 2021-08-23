import java.io.IOException;
import java.time.LocalDate;

public class DeadlineCommand extends Command {
  private Deadline deadline;

  public DeadlineCommand(String description, LocalDate by) {
    this.deadline = new Deadline(description, by);
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    tasks.addTask(this.deadline);

    String response = "Got it. I've added this task:\n"
        + "       " + this.deadline + "\n"
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
