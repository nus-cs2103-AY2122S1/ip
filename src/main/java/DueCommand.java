import java.util.ArrayList;

public final class DueCommand extends Command{

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
