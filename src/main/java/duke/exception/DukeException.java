package duke.exception;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

public class DukeException extends IllegalArgumentException {
//    private boolean isKeyword;
    private final String MESSAGE_REPLY;

    //package private
    public static DukeException of(String invalidInput, String messageReply) {
        return new DukeException(invalidInput, messageReply);
    }

    public static DukeException of(String userInput) {
        String reply = DukeException.replyInput(userInput);
        return new DukeException(userInput, reply);
    }

    private DukeException(String invalidInput, String messageReply) {
        super(invalidInput);
        this.MESSAGE_REPLY = messageReply;
    }

    private static String replyInput(String userInput) {
        String defaultReply = "Invalid input!\n" +
            "    Available commands: [todo, deadline, event, list, bye]";

        if (userInput.matches("\\w+\\s*")) {
            String inputWord = userInput.split(" ", 2)[0].toLowerCase();
            return switch (inputWord) {
                case "todo" -> ToDo.syntax();
                case "deadline" -> Deadline.syntax();
                case "event" -> Event.syntax();
                default -> defaultReply;
            };
        } else {
            return defaultReply;
        }
    }

    @Override
    public String toString() {
        return this.MESSAGE_REPLY;
    }
}

