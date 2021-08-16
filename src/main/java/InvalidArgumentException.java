class InvalidArgumentException extends DukeException {
    public InvalidArgumentException(String command) {
        super(String.format("Sorry, but I'm not sure what that means :(\n", command));
    }
}