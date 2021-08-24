package commands;

import java.util.ArrayList;
import storage.Storage;
import tasks.TaskList;
import tasks.ToDoTask;
import ui.Ui;

/**
 * The ToDoCommand Class inherits Command and is
 * a specific type of executable command.
 */
public final class ToDoCommand extends Command {

  /**
   * Constructs the ToDoCommand object.
   *
   * @param s the entire line of user input
   */
  public ToDoCommand(ArrayList<String> s) {
    super(s);
  }

  /**
   * Executes the command.
   *
   * @param lst the TaskList object that stores the list of tasks
   * @param ui the Ui object that interacts with the user
   * @param storage the Storage object that saves changes to stored tasks, if any
   */
  @Override
  public void execute(TaskList lst, Ui ui, Storage storage) {
    if (super.getInput().size() < 2) {
      Ui.showInput("Oops, you have left out the task description for todo!");
    } else {
      ToDoTask t = new ToDoTask(lst.filterInfo(getInput()));
      lst.addTask(t);
      storage.resetFile(lst.getTasks());
    }
  }
}
