package Duke;

 /**
  * <pre>
  * InputNotValidError class
  * </pre>
  */
public class InputNotValidError extends Exception { 
    public InputNotValidError(String errorMessage) {
        super(errorMessage);
    }
}