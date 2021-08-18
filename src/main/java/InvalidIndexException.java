public class InvalidIndexException extends DukeException{
    @Override
    public String toString() {
        return "OOPS!!! The item you are trying to access in the list does not exist :(";
    }
}
