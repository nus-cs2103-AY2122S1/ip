package commands;


import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

import java.util.ArrayList;


public final class DoneCommand extends Command {

  public DoneCommand(ArrayList<String> s) {
    super(s);
  }

  @Override
  public boolean isExit() {
    return false;
  }

  @Override
  public void execute(TaskList lst, Ui ui, Storage storage) {
    ArrayList<Task> tasks = lst.getTasks();
    try {
      if (super.getInput().size() == 1) {
        throw new IllegalArgumentException("Please input index :)");
      }
      if (super.getInput().size() > 2) {
        throw new IllegalArgumentException("Please input in the form: 'done <index>'.");
      }
      int index = Integer.parseInt(super.getInput().get(1)) - 1;
      if (index >= tasks.size() || index < 0) {
        throw new IllegalArgumentException("No such index. Please input correct index, no such index :(");
      }
      tasks.get(index).setIsDone();
      storage.resetFile(tasks);
    } catch (IndexOutOfBoundsException e) {
      Ui.showInput("Please input a valid index :)",
              "Note: 'list' can be used to see the current tasks.");
    } catch (NumberFormatException e) {
      Ui.showInput("Please use a number instead :(");
    } catch (IllegalArgumentException e) {
      Ui.showInput(e.getMessage());
    }
  }
}
