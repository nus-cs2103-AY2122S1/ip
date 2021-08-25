package duke.data;

public class InvalidDateTimeException extends DukeException{
    public InvalidDateTimeException(){ super(); }

    @Override
    public String getMessage(){
        return super.formatMessage("Entered DateTime format not supported. " +
                "Please enter Date and Time in YYYY-MM-DD HH:MM.");
    }
}
