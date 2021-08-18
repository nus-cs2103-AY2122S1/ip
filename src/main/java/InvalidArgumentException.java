public class InvalidArgumentException extends DukeException{
  public InvalidArgumentException(int numOfTask) {
    super(numOfTask < 0
        ? "Please input a positive number."
        : numOfTask == 0
        ? "You do not have any tasks."
        : numOfTask == 1
        ? "You only have " + numOfTask + " task."
        : "You only have " + numOfTask + " tasks."
    );
  }
}
