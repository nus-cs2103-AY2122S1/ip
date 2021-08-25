package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.time.LocalDate;

/**
 * Represents the DeadlineCommand in the Duke program.
 */
public class DeadlineCommand extends Command {
  private Deadline deadline;

  /**
   * Constructs a DeadlineCommand to create a Deadline task with the given description and deadline.
   *
   * @param description Description of the Deadline task.
   * @param by Deadline of the Deadline task.
   */
  public DeadlineCommand(String description, LocalDate by) {
    this.deadline = new Deadline(description, by);
  }

  /**
   * Defines the execution of the DeadlineCommand where a Deadline task is created and added to tasks.
   *
   * @param tasks Tasks of the Duke program.
   * @param ui Ui of the Duke program.
   * @param storage Storage of the Duke program.
   * @throws DukeException If changes cannot be saved to storage.
   */
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

  /**
   * Returns false as this command is not the ExitCommand.
   *
   * @return false as this command is not the ExitCommand.
   */
  @Override
  public boolean isExit() {
    return false;
  }
}
