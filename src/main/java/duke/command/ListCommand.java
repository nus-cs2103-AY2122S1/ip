package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class ListCommand extends Command {
  public ListCommand() {

  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) {
    String response;
    if (tasks.getSize() == 0) {
      response = "You do not have any tasks.";
    } else {
      response = "Here are the tasks in your list:\n" + tasks;
    }
    ui.showResponse(response);
  }

  @Override
  public boolean isExit() {
    return false;
  }
}
