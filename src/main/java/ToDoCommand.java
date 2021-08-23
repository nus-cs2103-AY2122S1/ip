import java.util.ArrayList;

public final class ToDoCommand extends Command{
  private final ArrayList<String> input;

  public ToDoCommand(ArrayList<String> s) {
    super(s);
    this.input = s;
  }

  @Override
  public boolean isExit() {
    return false;
  }

  @Override
  public void execute(TaskList lst, Ui ui, Storage storage) {
    if (super.getInput().size() < 2) {
      Ui.showInput("Oops, you have left out the task description for todo!");
    } else {
      ToDoTask t = new ToDoTask(lst.filterInfo(input));
      lst.addTask(t);
      storage.resetFile(lst.getTasks());
    }
  }
}
