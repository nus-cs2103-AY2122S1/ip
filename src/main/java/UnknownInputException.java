public class UnknownInputException extends DukeException{

    public UnknownInputException(String e) {
        super("Error: Invalid command! Please enter a valid input. For the list of valid inputs, type 'listInputs'");
    }

}
