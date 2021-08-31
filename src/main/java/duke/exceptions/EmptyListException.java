package duke.exceptions;

public class EmptyListException extends Exception {
    public final String EMPTY_LIST_LINE =
            ":( Oh no! List is empty.";

    @Override
    public String getMessage(){
        return EMPTY_LIST_LINE;
    }
}
