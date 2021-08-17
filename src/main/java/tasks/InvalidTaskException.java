package tasks;

/**
 * This exception indicates a task of the right type was not returned.
 */
public class InvalidTaskException extends IllegalArgumentException {

  InvalidTaskException(String message) {
    super(message);
  }
}
