package commands;

import java.util.ArrayList;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public final class DeleteCommand extends Command {

  public DeleteCommand(ArrayList<String> s) {
    super(s);
  }

  public boolean isExit() {
    return false;
  }

  @Override
  public void execute(TaskList lst, Ui ui, Storage storage) {
    if (super.getInput().size() == 1) {
      Ui.showInput("Unable to delete task without an index. Please input index :)",
              "Please input in the form: 'delete <task index>'.",
              "Note: list can be used to see the current tasks.");
    } else {
      if (lst.getTasks().isEmpty()) {
        Ui.showInput("List is empty, no tasks to delete, looking good!");
      } else if (super.getInput().size() > 2) {
        Ui.showInput("Please input in the form: 'delete <index>'.");
      } else {
        try {
          int index = Integer.parseInt(super.getInput().get(1)) - 1;
          lst.deleteTask(index);
          storage.resetFile(lst.getTasks());
        } catch (NumberFormatException e) {
          Ui.showInput("Please use a number instead :(");
        } catch (IndexOutOfBoundsException e) {
          Ui.showInput("Please input a valid index :)",
                  "Note: 'list' can be used to see the current tasks.");
        }
      }
    }
  }
}
