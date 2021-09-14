package dino.user;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import dino.data.TaskList;

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
     * @throws DinoException if the command is misused or none of the command words was used
     */
    public String[] checkInput(String input) throws DinoException {

        assert input != null;
        String command = input.split(" ")[0]; //Gets the first word in the input
        String parsedInput;
        int taskDescIndex = 0;
        int taskDateIndex = 1;
        int taskTimeIndex = 2;

        // Switch statement to execute the correct axillary method
        switch (command) {
        case "done":
            parsedInput = checkDone(input);
            return formatDone(command, parsedInput);
        case "delete":
            parsedInput = checkDelete(input);
            return formatDelete(command, parsedInput);
        case "todo":
            parsedInput = checkTodo(input);
            return formatTodo(command, parsedInput);
        case "event":
            parsedInput = checkEvent(input);
            return formatEvent(command, parsedInput, taskDescIndex, taskDateIndex);
        case "deadline":
            parsedInput = checkDeadline(input);
            return formatDeadline(command, parsedInput, taskDescIndex, taskDateIndex, taskTimeIndex);

        // bye and list can be combined as these are meant to be one-word commands
        case "bye":
            // Fallthrough
        case "list":
            return new String[]{command};
        case "find":
            parsedInput = checkFind(input);
            return formatFind(command, parsedInput);

        default:
            throw new DinoException("Please enter a valid command");
        }
    }

    // Formats the input into [Command, description].
    private String[] formatGeneral(String command, String parsedInput) {
        String[] extractedInfo;
        extractedInfo = new String[]{command, parsedInput};
        return extractedInfo;
    }

    private String[] formatFind(String command, String parsedInput) {
        return formatGeneral(command, parsedInput);
    }

    private String[] formatDeadline(String command, String parsedInput, int taskDescIndex,
                                    int taskDateIndex, int taskTimeIndex) {
        String[] extractedInfo;
        extractedInfo = new String[]{command, parsedInput.split(Ui.SPLIT_DELIMITER)[taskDescIndex],
                parsedInput.split(Ui.SPLIT_DELIMITER)[taskDateIndex],
                parsedInput.split(Ui.SPLIT_DELIMITER)[taskTimeIndex]};
        return extractedInfo;
    }

    private String[] formatEvent(String command, String parsedInput, int taskDescIndex, int taskDateIndex) {
        String[] extractedInfo;
        extractedInfo = new String[]{command, parsedInput.split(Ui.SPLIT_DELIMITER)[taskDescIndex],
                parsedInput.split(Ui.SPLIT_DELIMITER)[taskDateIndex]};
        return extractedInfo;
    }

    private String[] formatTodo(String command, String parsedInput) {
        return formatGeneral(command, parsedInput);
    }

    private String[] formatDone(String command, String parsedInput) {
        return formatGeneral(command, parsedInput);
    }

    private String[] formatDelete(String command, String parsedInput) {
        return formatGeneral(command, parsedInput);
    }

    /**
     * Checks that the done command word is used correctly.
     *
     * @param input The input String
     * @return The parsed output of the input with only the task number left (no formatting)
     * @throws DinoException if the task number is invalid
     */
    public String checkDone(String input) throws DinoException {
        int lenOfDoneCommand = 4;

        if (input.length() == lenOfDoneCommand) {
            throw new DinoException("Please use this format: 'done (task number)'");
        } else if (Integer.parseInt(input.split(" ")[1]) <= 0
                || Integer.parseInt(input.split(" ")[1]) > taskList.getLength()) {
            throw new DinoException("Invalid task number!");
        }
        return input.split(" ")[1];
    }

    /**
     * Checks that the delete command word is used correctly.
     *
     * @param input The input String
     * @return The parsed output of the input with only the task number left (no formatting)
     * @throws DinoException if the task number is invalid
     */
    public String checkDelete(String input) throws DinoException {
        int lenOfDeleteCommand = 6;

        if (input.length() == lenOfDeleteCommand) {
            throw new DinoException("Please use this format: 'delete (task number)'");
        } else if (Integer.parseInt(input.split(" ")[1]) <= 0
                || Integer.parseInt(input.split(" ")[1]) > taskList.getLength()) {
            throw new DinoException("Invalid task number!");
        }
        return input.split(" ")[1];
    }

    /**
     * Checks that the to do command word is used correctly.
     *
     * @param input The input String
     * @return The parsed output of the input with only the to do description left (no formatting)
     * @throws DinoException if the task number is invalid
     */
    public String checkTodo(String input) throws DinoException {

        int lenOfTodoCommand = 5;

        if (input.length() <= lenOfTodoCommand || input.charAt(lenOfTodoCommand) == ' ') {
            throw new DinoException("The description of a todo cannot be empty!");
        }
        return input.substring(lenOfTodoCommand);
    }

    /**
     * Checks that the event command word is used correctly.
     *
     * @param input The input String
     * @return The parsed output of the input with only the event description and date left (no formatting)
     * @throws DinoException if the format is not followed
     */
    public String checkEvent(String input) throws DinoException {

        int lenOfEventCommand = 6;
        int lenOfAtKeyword = 4;
        int eventDateIndex = input.indexOf("/at ") + lenOfAtKeyword;

        // need to check that for event they use the /at properly else reject
        if (input.length() <= lenOfEventCommand || !input.contains("/at ")) {
            throw new DinoException("Please use this format: 'event <task> /at <date and time>' "
                    + "to specify the date and time!");
        }

        String eventDesc = input.substring(lenOfEventCommand, eventDateIndex - lenOfAtKeyword);
        if (input.charAt(lenOfEventCommand) == ' ' || input.charAt(lenOfEventCommand) == '/') {
            throw new DinoException("The description cannot be empty!");
        }

        String eventDate = input.substring(eventDateIndex);
        return eventDesc + Ui.DELIMITER + eventDate;
    }

    /**
     * Checks that the deadline command word is used correctly.
     *
     * @param input The input String
     * @return The parsed output of the input with only the deadline description, date and time left (no formatting)
     * @throws DinoException if format is not followed
     */
    public String checkDeadline(String input) throws DinoException {

        int lengthOfByKeyword = 4;
        int deadlineDateIndex = input.indexOf("/by ") + lengthOfByKeyword;
        int lengthOfDateFormat = 11;
        int deadlineTimeIndex = deadlineDateIndex + lengthOfDateFormat;
        int indexAfterDeadlineCommand = 9;

        // need to check that for deadline they use the /by properly else reject
        if (!input.contains("/by ")) {
            throw new DinoException("Please use this format: 'deadline <task> /by YYYY-MM-DD HH:MM' "
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
                throw new DinoException("The description cannot be empty!");
            }
            String deadlineDesc = input.substring(indexAfterDeadlineCommand, deadlineDateIndex - lengthOfByKeyword);
            return deadlineDesc + Ui.DELIMITER + date + Ui.DELIMITER + time;

        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            throw new DinoException("Invalid date or time format! Use: YYYY-MM-DD HH:MM");
        }

    }

    /**
     * Checks that the find command word is used correctly.
     *
     * @param input The input String
     * @return The parsed output of the input with the keyword to be searched for
     * @throws DinoException only one keyword was not used
     */
    public String checkFind(String input) throws DinoException {
        int lenOfFindCommand = 4;

        if (input.length() == lenOfFindCommand) {
            throw new DinoException("Please enter a keyword!");
        } else if (input.split(" ").length > 2) {
            throw new DinoException("Please enter ONLY 1 keyword!");
        }
        return input.split(" ")[1];
    }


}
