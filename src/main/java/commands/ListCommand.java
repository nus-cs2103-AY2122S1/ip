package commands;

import java.util.ArrayList;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public final class ListCommand extends Command {

  public ListCommand(ArrayList<String> s) {
    super(s);
  }

  @Override
  public boolean isExit() {
    return false;
  }

  @Override
  public void execute(TaskList lst, Ui ui, Storage storage) {
    lst.getList();
  }
}
