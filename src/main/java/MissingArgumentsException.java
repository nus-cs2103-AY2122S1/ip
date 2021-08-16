public class MissingArgumentsException extends DukeException {

    public MissingArgumentsException(String command) {
        super(String.format("The description of a %s cannot be left empty. " 
                + "Please try again.", command));
    }

}
