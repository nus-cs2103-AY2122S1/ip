package meap.exception;

import meap.util.Ui;

import java.util.Arrays;
import java.util.stream.Collectors;

public class DukeException extends IllegalArgumentException {
    private final String MESSAGE_REPLY;

    /**
     * Factory method of DukeException which takes in the invalidInput
     * and a messageReply
     *
     * @param invalidInput invalid user input which triggered exception
     * @param messageReply message to reply user triggered exception
     * @return DukeException instance
     */
    public static DukeException of(String invalidInput, String messageReply) {
        return new DukeException(invalidInput, messageReply);
    }

    /**
     * Factory method of DukeException which takes in the invalidInput
     *
     * @param userInput invalid user input which couldn't be parsed
     * @return DukeException instance
     */
    public static DukeException of(String userInput) {
        String reply = DukeException.replyInput(userInput);
        return new DukeException(userInput, reply);
    }

    /**
     * Constructor for DukeException class which takes in the invalidInput
     * and a messageReply
     *
     * @param invalidInput invalid user input which triggered exception
     * @param messageReply message to reply user triggered exception
     */
    private DukeException(String invalidInput, String messageReply) {
        super(invalidInput);
        this.MESSAGE_REPLY = messageReply;
    }

    /**
     * Replies a user input that has failed to identified as a valid command.
     *
     * @param userInput
     * @return message reply that will be sent back to user
     */
    private static String replyInput(String userInput) {
        String defaultReply = "Invalid input!\n\n" +
                "Available commands:\n" +
                Arrays.asList(Ui.USER_SUPPORTED_COMMANDS)
                        .stream()
                        .map(s -> "- " + s + "\n")
                        .collect(Collectors.joining());

        if (userInput.matches("\\w+\\s*")) {
            String inputWord = userInput.split(" ", 2)[0].toLowerCase();
            if (Arrays.asList(Ui.USER_SUPPORTED_COMMANDS).contains(inputWord)) {
                return "Proper command syntax: \n" + Ui.INDENT + Ui.commandSyntax(inputWord);
            }
        }
        return defaultReply;
    }

    @Override
    public String toString() {
        return this.MESSAGE_REPLY;
    }
}

