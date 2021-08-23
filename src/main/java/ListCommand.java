import java.util.ArrayList;

public final class ListCommand extends Command{

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
