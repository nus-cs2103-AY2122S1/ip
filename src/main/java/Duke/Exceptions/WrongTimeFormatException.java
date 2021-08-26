package Duke.Exceptions;

public class WrongTimeFormatException extends Exception{

    public WrongTimeFormatException(String message) {
        super(String.format("☹ OOPS!!! The input time format " + message + " is wrong, please enter in format yyyy-MM-dd HH:mm"));
    }
}
