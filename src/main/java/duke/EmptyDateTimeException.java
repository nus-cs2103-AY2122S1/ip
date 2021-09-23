package duke;

public class EmptyDateTimeException extends DukeException {

    public EmptyDateTimeException(String e) {
        super("Error: The date and time need to be specified." +
                "\nTo learn how to use commands in Duke, refer here: https://aakanshanarain.github.io/ip/");
    }
}
