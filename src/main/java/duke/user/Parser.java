package duke.user;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import duke.data.TaskList;

/**
 * Represents a parser which reads the user input and performs the checks to verify that the inputs are valid.
 */
public class Parser {

    protected TaskList taskList;

    /**
     * Basic Constructor for the parser object.
     *
     * @param taskList the TaskList where all the tasks are stored
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Checks the user input by making sure the first word is a valid command, and then calling an axillary method
     * to further check the use of the command word.
     *
     * @param input The String entered by the user
     * @return A String[] with each index storing the key information after the input is parsed
     * @throws DukeException if the command is misused or none of the command words was used
     */
    public String[] checkInput(String input) throws DukeException {

        // Get the first word of the input String
        String command = input.split(" ")[0];
        String[] extractedInfo;
        String parsedInput;
        int taskDescIndex = 0;
        int taskDateIndex = 1;
        int taskTimeIndex = 2;

        // Switch statement to execute the correct axillary method
        switch (command) {
        case "done":
            parsedInput = checkDone(input);
            extractedInfo = new String[]{command, parsedInput};
            return extractedInfo;

        case "delete":
            parsedInput = checkDelete(input);
            extractedInfo = new String[]{command, parsedInput};
            return extractedInfo;

        case "todo":
            parsedInput = checkTodo(input);
            extractedInfo = new String[]{command, parsedInput};
            return extractedInfo;

        case "event":
            parsedInput = checkEvent(input);

            extractedInfo = new String[]{command, parsedInput.split(Ui.SPLIT_DELIMITER)[taskDescIndex],
                    parsedInput.split(Ui.SPLIT_DELIMITER)[taskDescIndex]};
            return extractedInfo;

        case "deadline":
            parsedInput = checkDeadline(input);
            extractedInfo = new String[]{command, parsedInput.split(Ui.SPLIT_DELIMITER)[taskDescIndex],
                    parsedInput.split(Ui.SPLIT_DELIMITER)[taskDateIndex],
                    parsedInput.split(Ui.SPLIT_DELIMITER)[taskTimeIndex]};
            return extractedInfo;

        // bye and list can be combined as these are meant to be one-word commands
        case "bye":
            // Fallthrough
        case "list":
            return new String[]{command};

        case "find":
            parsedInput = checkFind(input);
            extractedInfo = new String[]{command, parsedInput};
            return extractedInfo;

        // If none of the command words was used as the first word, throw an exception

        default:
            throw new DukeException("Please enter a valid command");
        }
    }

    /**
     * Axillary function to check that the done command word is used correctly.
     *
     * @param input The input String
     * @return The parsed output of the input with only the task number left (no formatting)
     * @throws DukeException if the task number is invalid
     */
    public String checkDone(String input) throws DukeException {
        int lenOfDoneCommand = 4;

        if (input.length() == lenOfDoneCommand) {
            throw new DukeException("Please use this format: 'done (task number)'");
        } else if (Integer.parseInt(input.split(" ")[1]) < 0
                || Integer.parseInt(input.split(" ")[1]) > taskList.getLength()) {
            throw new DukeException("Invalid task number!");
        }
        return input.split(" ")[1];
    }

    /**
     * Axillary function to check that the delete command word is used correctly.
     *
     * @param input The input String
     * @return The parsed output of the input with only the task number left (no formatting)
     * @throws DukeException if the task number is invalid
     */
    public String checkDelete(String input) throws DukeException {
        int lenOfDeleteCommand = 6;

        if (input.length() == lenOfDeleteCommand) {
            throw new DukeException("Please use this format: 'delete (task number)'");
        } else if (Integer.parseInt(input.split(" ")[1]) < 0
                || Integer.parseInt(input.split(" ")[1]) > taskList.getLength()) {
            throw new DukeException("Invalid task number!");
        }
        return input.split(" ")[1];
    }

    /**
     * Axillary function to check that the to do command word is used correctly.
     *
     * @param input The input String
     * @return The parsed output of the input with only the to do description left (no formatting)
     * @throws DukeException if the task number is invalid
     */
    public String checkTodo(String input) throws DukeException {

        int lenOfTodoCommand = 5;

        if (input.length() <= lenOfTodoCommand || input.charAt(lenOfTodoCommand) == ' ') {
            throw new DukeException("The description of a todo cannot be empty!");
        }
        return input.substring(lenOfTodoCommand);
    }

    /**
     * Axillary function to check that the event command word is used correctly.
     *
     * @param input The input String
     * @return The parsed output of the input with only the event description and date left (no formatting)
     * @throws DukeException if the format is not followed
     */
    public String checkEvent(String input) throws DukeException {

        int lenOfEventCommand = 6;
        int lenOfAtKeyword = 4;
        int eventDateIndex = input.indexOf("/at ") + lenOfAtKeyword;

        // need to check that for event they use the /at properly else reject
        if (input.length() <= lenOfEventCommand || !input.contains("/at ")) {
            throw new DukeException("Please use this format: 'event <task> /at <date and time>' "
                    + "to specify the date and time!");
        }

        String eventDesc = input.substring(lenOfEventCommand, eventDateIndex - lenOfAtKeyword);
        if (input.charAt(lenOfEventCommand) == ' ' || input.charAt(lenOfEventCommand) == '/') {
            throw new DukeException("The description cannot be empty!");
        }

        String eventDate = input.substring(eventDateIndex);
        return eventDesc + Ui.DELIMITER + eventDate;
    }

    /**
     * Axillary function to check that the deadline command word is used correctly.
     *
     * @param input The input String
     * @return The parsed output of the input with only the deadline description, date and time left (no formatting)
     * @throws DukeException if format is not followed
     */
    public String checkDeadline(String input) throws DukeException {

        int lengthOfByKeyword = 4;
        int deadlineDateIndex = input.indexOf("/by ") + lengthOfByKeyword;
        int lengthOfDateFormat = 11;
        int deadlineTimeIndex = deadlineDateIndex + lengthOfDateFormat;
        int indexAfterDeadlineCommand = 9;

        // need to check that for deadline they use the /by properly else reject
        if (!input.contains("/by ")) {
            throw new DukeException("Please use this format: 'deadline <task> /by YYYY-MM-DD HH:MM' "
                    + "to specify the date and time!");
        }

        try {
            // Splits up the date and time from the initial string
            String date = input.substring(deadlineDateIndex, deadlineDateIndex + lengthOfDateFormat - 1);
            String time = input.substring(deadlineTimeIndex);

            // Checks that the string can be parsed to time and date
            LocalTime.parse(time);
            LocalDate.parse(date);


            if (input.charAt(indexAfterDeadlineCommand) == ' ' || input.charAt(indexAfterDeadlineCommand) == '/') {
                throw new DukeException("The description cannot be empty!");
            }
            String deadlineDesc = input.substring(indexAfterDeadlineCommand, deadlineDateIndex - lengthOfByKeyword);
            return deadlineDesc + Ui.DELIMITER + date + Ui.DELIMITER + time;

        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            throw new DukeException("Invalid date or time format! Use: YYYY-MM-DD HH:MM");
        }

    }

    /**
     * Axillary function to check that the find command word is used correctly.
     *
     * @param input The input String
     * @return The parsed output of the input with the keyword to be searched for
     * @throws DukeException only one keyword was not used
     */
    public String checkFind(String input) throws DukeException {
        int lenOfFindCommand = 4;

        if (input.length() == lenOfFindCommand) {
            throw new DukeException("Please enter a keyword!");
        } else if (input.split(" ").length > 2) {
            throw new DukeException("Please enter ONLY 1 keyword!");
        }
        return input.split(" ")[1];
    }


}
