public class Deadline extends Task {
    private String by;

    public static Task create(String userInput) throws MalformedCommandException{
        try {
            String[] userInputSplit = userInput.split(" ", 2);
            String[] userParamsSplit = userInputSplit[1].split(" /", 2);
            String description = userParamsSplit[0];
            String by = "";
            if(userParamsSplit[1].startsWith("by")) {
                by = userParamsSplit[1].replaceFirst("by", "");
            }
            return new Deadline(description, by);
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new MalformedCommandException("Creating an deadline needs to follow the following format: " +
                "deadline [description] /by [Description of deadline]");
        }

    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    private String identifier() {
        return "D";
    }

    @Override
    public String toString() {
        return "[" + identifier() + "]" + super.toString() + " (by:" + by + ")";
    }

    public String formatForStorage() {
        return identifier() + STORAGE_DELIMITER + super.formatForStorage() + STORAGE_DELIMITER + by;
    }
}
