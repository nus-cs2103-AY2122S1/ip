public class NonExistentActionTypeException extends DukeException {
    public NonExistentActionTypeException(String inputMessage) {
        super(String.format(
                "Sorry I do not recognise this command '%s'",
                inputMessage
        ));
    }
}
