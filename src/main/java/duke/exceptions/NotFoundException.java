package duke.exceptions;

public class NotFoundException extends Exception{
    public final String NOT_FOUND_LINE =
            "No similar tasks can be found";

    @Override
    public String getMessage(){
        return NOT_FOUND_LINE;
    }
}
