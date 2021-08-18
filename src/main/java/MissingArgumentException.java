public class MissingArgumentException extends DukeException{
  public MissingArgumentException(String task, String argument) {
    super(task + " is missing the " + argument + " argument.");
  }
}
