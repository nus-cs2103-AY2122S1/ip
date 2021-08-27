public class DukeException extends IllegalArgumentException {
//    private boolean isKeyword;
    private String messageReply;

    //package private
    static DukeException of(String invalidInput, String messageReply) {
        return new DukeException(invalidInput, messageReply);
    }

    static DukeException of(String userInput) {
        String reply = DukeException.replyInput(userInput);
        return new DukeException(userInput, reply);
    }

    private DukeException(String invalidInput, String messageReply) {
        super(invalidInput);
        this.messageReply = messageReply;
    }

    private static String replyInput(String userInput) {
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
        return this.messageReply;
    }
}

