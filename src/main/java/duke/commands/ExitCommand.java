package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The ExitCommand class extends the Command class and is the Command that * edits a Task to the
 * TaskList.
 */
public class ExitCommand extends Command {

    /** The constructor for the ExitCommand object. */
    public ExitCommand() {
        super(CommandType.EXIT, true);
    }

  /**
   * The execute command that executes the necessary actions when issued with the Exit Command.
   *
   * @param tasks The TaskList to be added to
   * @param ui The Ui object to interact with the user
   * @param storage The Storage object that stores the TaskList on the Local Machine
   */
  public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
    }
}

