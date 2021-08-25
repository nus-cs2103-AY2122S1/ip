package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a personal assistance to manage users' tasks list.
 */
public class Duke {
  private Storage storage;
  private Ui ui;
  private TaskList tasks;

  /**
   * Sets up the Duke program by instantiating a user interface, a storage and a task list.
   *
   * @param filePath File path of the storage.
   */
  public Duke(String filePath) {
    this.ui = new Ui();
    try {
      this.storage = new Storage(filePath);
      this.tasks = new TaskList(storage.load());
    } catch (DukeException e) {
      ui.showError(e.toString());
      this.tasks = new TaskList();
    }
  }

  /**
   * Runs the Duke program.
   */
  public void run() {
    ui.showWelcome();
    boolean isExit = false;
    while (!isExit) {
      try {
        String fullCommand = ui.readCommand();
        ui.showOpeningLine();
        Command c = Parser.parse(fullCommand);
        c.execute(tasks, ui, storage);
        isExit = c.isExit();
      } catch (DukeException e) {
        ui.showError(e.toString());
      } finally {
        ui.showClosingLine();
      }
    }
  }

  public static void main(String[] args) {
    new Duke("data/tasks.txt").run();
  }
}
