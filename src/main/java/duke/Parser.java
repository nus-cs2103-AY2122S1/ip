package duke;

/**
 * Parser class parses the commands given by the users.
 * @author Dominic Siew Zhen Yu
 */

public class Parser {

    private String taskName;
    private String command;
    private String deadlineOrTimeline;

    /**
     * constructor for the Parser class
     */
    public Parser() {
    }

    /**
     * parsing(String input) takes in a string from the given input and parses the string
     * into the taskName, command and deadlineOrTimeline variables.
     *
     * @param input the given input by the user
     */
    public void parsing(String input) {

        if (input.equals("list") || input.equals("bye")) {
            this.command = input;
            return;
        }

        String[] parsedInput = input.split(" ", 2);
        String eventType = parsedInput[0];
        this.command = eventType;

        if (this.command.equals("deadline")) {
            String[] parsedAgain = parsedInput[1].split(" /by ", 2);
            this.taskName = parsedAgain[0];
            this.deadlineOrTimeline = parsedAgain[1];
        } else if (this.command.equals("event")) {
            String[] parsedAgain = parsedInput[1].split(" /at ", 2);
            this.taskName = parsedAgain[0];
            this.deadlineOrTimeline = parsedAgain[1];
        } else if (this.command.equals("todo") || this.command.equals("done")
                || this.command.equals("remove") || this.command.equals("find")
                || this.command.equals("schedule")) {
            this.taskName = parsedInput[1];
        } else {
            throw new DukeException("parse");
        }
    }

    /**
     * returns the command of the user's input.
     * @return the command of the user's input
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * returns the task's name from the user's input.
     * @return the name of the task
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * getTimeline() returns the timeline or the deadline of the event/deadline respectively.
     * @return the timeline/deadline of the task
     */
    public String getTimeline() {
        return this.deadlineOrTimeline;
    }
}
