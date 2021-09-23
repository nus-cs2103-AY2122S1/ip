package duke;

public class UnknownInputException extends DukeException{

    public UnknownInputException(String e) {
        super("Error: Invalid command! Please enter a valid input. For the list of valid inputs, type 'help'" +
                "\nTo learn how to use commands in Duke, refer here: https://aakanshanarain.github.io/ip/");
    }

}
