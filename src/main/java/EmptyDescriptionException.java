public class EmptyDescriptionException extends DukeException{
  public EmptyDescriptionException(String task) {
    super("The description of a " + task + " cannot be empty.");
  }
}
