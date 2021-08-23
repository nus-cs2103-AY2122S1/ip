package commands;

import java.util.ArrayList;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;


public final class DueCommand extends Command {

  public DueCommand(ArrayList<String> s) {
    super(s);
  }

  @Override
  public boolean isExit() {
    return false;
  }

  @Override
  public void execute(TaskList lst, Ui ui, Storage storage) {
    try {
      lst.anyItemsDue(getInput().get(1));
    } catch (IndexOutOfBoundsException e) {
      Ui.showInput("Invalid input :(");
      Ui.helperMessage();
    }
  }
}
