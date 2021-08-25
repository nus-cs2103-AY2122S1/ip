package duke.exceptions;

/**
 * Class that represents exceptions specific to incompatible datetime format of user.
 */
public class NoDatetimeException extends UserInputError {

  /**
   * Constructor for a NoDatetimeException.
   *
   * @param msg Message of exception to be displayed to user.
   */
  NoDatetimeException(String msg) {
    super(msg);
  }
}
