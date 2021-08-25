public class DukeException extends IllegalArgumentException {
    private boolean isKeyword;
    private String msg;

    public DukeException(String userInput) {
        super(userInput);
        this.msg = replyInput(userInput);
    }

    private String replyInput(String userInput) {
        String defaultReply = "Invalid input!\n" +
            "    Available commands: [todo, deadline, event, list, bye]";

        if (userInput.matches("\\w+\\s*")) {
            String inputWord = userInput.split(" ", 2)[0].toLowerCase();
            switch (inputWord) {
                case "todo":
                    return ToDo.syntax();
                case "deadline":
                    return Deadline.syntax();
                case "event":
                    return Event.syntax();
                default:
                    return defaultReply;
            }
        } else {
            return defaultReply;
        }
    }

    @Override
    public String toString() {
            return this.msg;
    }
}

