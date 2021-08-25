public class Event extends Task{
    private String at;

    public static Task create(String userInput) throws MalformedCommandException {
        try {
            String[] userInputSplit = userInput.split(" ", 2);
            String[] userParamsSplit = userInputSplit[1].split(" /", 2);
            String description = userParamsSplit[0];
            String at = "";
            if(userParamsSplit[1].startsWith("at")) {
                at = userParamsSplit[1].replaceFirst("at", "");
            }
            return new Event(description, at);
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new MalformedCommandException("Creating an event needs to follow the following format: " +
                "event [description] /at [Description of when event occurs]");
        }
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    private String identifier() {
        return "E";
    }

    @Override
    public String toString() {
        return "[" + identifier() + "]" + super.toString() + " (at:" + at + ")";
    }

    public String formatForStorage() {
        return identifier() + STORAGE_DELIMITER + super.formatForStorage() + STORAGE_DELIMITER + at;
    }
}
