import java.util.ArrayList;

public final class Parser {

  public Command parse(String str) {
    Command c = null;
    try {
      ArrayList<String> words = new ArrayList<>();
      String[] arr = str.split("\\s+");
      for (String ss : arr) {
        if (!ss.equals("")) words.add(ss);
      }
      c = chat(words);
    } catch (DukeException e) {
      Ui.helperMessage();
    }
    return c;
  }


  public Command chat(ArrayList<String> s) throws DukeException {
    if (s == null || s.isEmpty()) {
      throw new DukeException("Please chek your input!");
    } else if (s.get(0).equalsIgnoreCase("bye")) {
      return new ByeCommand(s);
    } else if (s.get(0).equalsIgnoreCase("list")) {
      return new ListCommand(s);
    } else if (s.get(0).equalsIgnoreCase("done")) {
      return new DoneCommand(s);
    } else if (s.get(0).equalsIgnoreCase("delete")) {
      return new DeleteCommand(s);
    } else if (s.get(0).equalsIgnoreCase("todo")) {
      return new ToDoCommand(s);
    } else if (s.get(0).equalsIgnoreCase("deadline")) {
      return new DeadlineCommand(s);
    } else if (s.get(0).equalsIgnoreCase("event")) {
      return new EventCommand(s);
    } else if (s.get(0).equalsIgnoreCase("due")) {
      return new DueCommand(s);
    } else {
      throw new DukeException("Invalid command!");
    }
  }
}
