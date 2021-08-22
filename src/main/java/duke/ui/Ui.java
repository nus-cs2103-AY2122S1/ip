package duke.ui;

import duke.task.Task;

public class Ui {

    public class MessageBuilder {

        private StringBuilder messageSb;

        private MessageBuilder() {
            this.messageSb = new StringBuilder();
        }

        public MessageBuilder addLine(String line) {
            messageSb.append(line.stripTrailing()).append("\n");
            return this;
        }

        public MessageBuilder addTask(Task task) {
            messageSb.append(messageFormatter.formatTask(task)).append("\n");
            return this;
        }

        public MessageBuilder addTasksListLength(int length) {
            messageSb.append(getListLengthMessage(length)).append("\n");
            return this;
        }

        public void printFormatted() {
            String message = messageSb.toString().stripTrailing();
            printFormattedMessage(message);
        }
    }

    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    private static final String GREETING_MESSAGE = "Hello! I'm Duke!\n"
            + "What can I do for you?";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String UNEXPECTED_ERROR_MESSAGE = "An unexpected error has occurred.";
    private static final String INVALID_COMMAND_ERROR_TEMPLATE = "This command is invalid.\n%s\nPlease try again.";

    private MessageFormatter messageFormatter;

    public Ui() {
        this.messageFormatter = MessageFormatter.getInstance();
    }

    public void printGreeting() {
        printFormattedMessage(LOGO + "\n" + GREETING_MESSAGE);
    }

    public void printUnexpectedErrorMessage() {
        printFormattedMessage(UNEXPECTED_ERROR_MESSAGE);
    }

    public void printInvalidCommandErrorMessage(String message) {
        printFormattedMessage(String.format(INVALID_COMMAND_ERROR_TEMPLATE, message));
    }

    public void printExitMessage() {
        printFormattedMessage(EXIT_MESSAGE);
    }

    public MessageBuilder startMessage() {
        return new MessageBuilder();
    }

    private String getListLengthMessage(int listLength) {
        // Check whether singular or plural should be printed.
        if (listLength != 1) {
            return String.format("Now you have %d tasks in the list.", listLength);
        } else {
            return "Now you have 1 task in the list.";
        }
    }

    private void printFormattedMessage(String message) {
        System.out.println(messageFormatter.getFormattedMessage(message));
        System.out.println();
    }
}
