import java.util.ArrayList;

public final class DeadlineCommand extends Command{

  public DeadlineCommand(ArrayList<String> s) {
    super(s);
  }

  @Override
  public boolean isExit() {
    return false;
  }

  @Override
  public void execute(TaskList lst, Ui ui, Storage storage) {
    try {
      DeadLineTask d = new DeadLineTask(lst.filterInfo(getInput()),
              lst.lookForDeadline(getInput()));
      lst.addTask(d);
      storage.resetFile(lst.getTasks());
    } catch (IllegalArgumentException e) {
      if (e.getMessage().equals("deadline")) {
        Ui.showInput("Invalid input :(",
                "Please input in the form: 'deadline <Name> /by <Date>'.");
      } else {
        Ui.showInput(e.getMessage(), "Hey, no deadline recorded does not mean no deadline >:(");
      }
    }
  }
}
