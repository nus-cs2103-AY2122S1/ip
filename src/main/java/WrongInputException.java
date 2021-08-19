public class WrongInputException extends DukeException{

    WrongInputException() {
        super();
    }

    @Override
    public String toString() {
        return super.toString() + "Sorry I do not know what is this!";
    }
}
