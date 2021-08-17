public class InvalidDoneException extends DukeException{

    public InvalidDoneException(){
        super("Please enter a valid task number");
    }
}
