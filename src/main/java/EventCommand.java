import java.util.ArrayList;

public final class EventCommand extends Command{

  public EventCommand(ArrayList<String> s) {
    super(s);
  }

  @Override
  public boolean isExit() {
    return false;
  }

  @Override
  public void execute(TaskList lst, Ui ui, Storage storage) {
    try {
      EventTask e = new EventTask(lst.filterInfo(getInput()),
              lst.searchForEventDay(getInput()));
      lst.addTask(e);
      storage.resetFile(lst.getTasks());
    } catch (IllegalArgumentException e) {
      if (e.getMessage().equals("event")) {
        Ui.showInput("Invalid input :(", "Please input in the form: 'event <Name> /at <Date>'.");
      } else {
        Ui.showInput(e.getMessage(), "I can't add an event without a date!");
      }
    }
  }


}
