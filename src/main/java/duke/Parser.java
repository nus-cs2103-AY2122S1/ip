package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Parses the inputs
 */
public class Parser {

    /** Input string by the user. */
    private String inp;

    public Parser(String inp) {
        this.inp = inp;
    }

    /**
     * Parses the input task to extract the task content.
     *
     * @return The actual task content.
     */
    public String parseTask() {
        String desc = inp.split("/")[0];
        return desc;
    }

    /**
     * Parses the input task to extract the date.
     *
     * @return The date of the task.
     */
    public String parseTime() {
        String atByTime = ((inp.split("\\s", 2)[1]).split("/"))[1];
        String time = atByTime.split("\\s", 2)[1];
        LocalDate d1 = LocalDate.parse(time);
        return d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Parses the user input to return the appropriate command.
     *
     * @param inpCommand command entered by the user
     * @return Command matching the input.
     */
    public static Command parseCommand(String inpCommand) {

        String input = inpCommand.toLowerCase();
        if (input.contains("done")) {
            int ind = Integer.parseInt((input.split("\\s", 2))[1]) - 1;
            return new Done(ind);
        } else if (input.contains("delete")) {
            int ind = Integer.parseInt((input.split("\\s", 2))[1]) - 1;
            return new Delete(ind);
        } else if (input.contains("find")) {
            String trimmedFind = input.split("\\s", 2)[1];
            return new Find(trimmedFind);
        } else if (input.contains("list")) {
            return new List();
        } else if (input.contains("todo") || input.contains("deadline") || input.contains("event")) {
            return new Add(input);
        } else if (input.contains("bye")) {
            return new Bye();
        } else {
            return new Invalid();
        }
    }
}

