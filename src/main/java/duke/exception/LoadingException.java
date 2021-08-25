package duke.exception;

/**
 * Signals that an exception has occurred when loading the storage.
 */
public class LoadingException extends DukeException {
  /**
   * Constructs a LoadingException.
   */
  public LoadingException() {
    super("Duke forgot about your old task list liao, Duke create a new one for you ah!");
  }
}
