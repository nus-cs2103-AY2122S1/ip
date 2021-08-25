package duke.exceptions;

/**
 * Class that represents exceptions specific to incomplete input description of user.
 */
public class NoDescriptionException extends UserInputError {

  /**
   * Constructor for a NoDescriptionException.
   *
   * @param msg Message of exception to be displayed to user.
   */
  public NoDescriptionException(String msg) {
    super(msg);
  }
}
