import java.util.ArrayList;

public abstract class Command{
  private final ArrayList<String> input;

  public Command(ArrayList<String> s) {
    this.input = s;
  }

  public ArrayList<String> getInput() {
    return input;
  }

  public abstract boolean isExit();

  public abstract void execute(TaskList lst, Ui ui, Storage storage);
}
