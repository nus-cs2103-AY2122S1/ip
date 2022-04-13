package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser to parse user inputs, and invoke corresponding functions
 */
public class Parser {
    DukeTaskList dukeTaskList;
    Storage storage;

    /**
     * Parser class constructor
     *
     * @param dukeTaskList dukeTaskList.
     * @param storage storage.
     */
    public Parser(DukeTaskList dukeTaskList, Storage storage) {
        this.dukeTaskList = dukeTaskList;
        this.storage = storage;
    }

    /**
     * Parses user input and returns response string.
     *
     * @param userInput user inputs.
     * @return duke's response in string.
     */
    public String parse(String userInput) {
        String response;
        String[] slashSplitInput = userInput.split("/", 2);
        String[] spaceSplitInput = slashSplitInput[0].split(" ", 2);
        // Everything before the slash, split again by space

        try {
            if (userInput.equals("bye")) { // Exit
                response = "Bye. Hope to see you again soon!\n";
            } else if (userInput.equals("list")) {
                response = dukeTaskList.displayList();
            } else if (spaceSplitInput[0].equals("done")) {
                response = parseDone(spaceSplitInput);
            } else if (spaceSplitInput[0].equals("delete")) {
                response = parseDelete(spaceSplitInput);
            } else if (spaceSplitInput[0].equals("update")) {
                response = parseUpdate(spaceSplitInput);
            } else if (spaceSplitInput[0].equals("find")) {
                response = parseFind(spaceSplitInput);
            } else if (spaceSplitInput[0].equals("todo")) {
                response = parseToDo(spaceSplitInput);
            } else if (spaceSplitInput[0].equals("deadline")) {
                response = parseDeadline(spaceSplitInput, slashSplitInput);
            } else if (spaceSplitInput[0].equals("event")) {
                response = parseEvent(spaceSplitInput, slashSplitInput);
            } else { // User inputs unrecognized commands
                throw new DukeException("My intelligence has not evolved to understand this command :(\n");
            }

            return response;
        } catch (DukeException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return "Please enter a valid integer for task number!\n";
        } catch (DateTimeParseException e) {
            return "Please enter date-time in the valid format: yyyy-mm-dd hh:mm\n";
        }
    }

    /**
     * Parses done task input and mark tasks as done.
     *
     * @param spaceSplitInput string array of inputs split by space.
     * @throws DukeException if done task input format is incorrect.
     * @return duke's response in string.
     */
    private String parseDone(String[] spaceSplitInput) throws DukeException, NumberFormatException {
        String response;
        if (spaceSplitInput.length < 2) { // No task number entered
            throw new DukeException("Done Task number is missing!\n");
        }
        int doneTaskNo = Integer.parseInt(spaceSplitInput[1]); // Throws NumberFormatException

        response = dukeTaskList.doneTask(doneTaskNo);
        storage.saveToDataFile();

        return response;
    }

    /**
     * Parses delete task input and deletes task.
     *
     * @param spaceSplitInput string array of inputs split by space.
     * @throws DukeException if delete task input format is incorrect.
     * @return duke's response in string.
     */
    private String parseDelete(String[] spaceSplitInput) throws DukeException, NumberFormatException {
        String response;
        if (spaceSplitInput.length < 2) { // No task number entered
            throw new DukeException("Delete task number is missing!\n");
        }
        int deleteTaskNo = Integer.parseInt(spaceSplitInput[1]); // Throws NumberFormatException

        response = dukeTaskList.deleteTask(deleteTaskNo);
        storage.saveToDataFile();

        return response;
    }

    /**
     * Parses update task input and updates task.
     *
     * @param spaceSplitInput string array of inputs split by space.
     * @throws DukeException if update task input format is incorrect.
     * @return duke's response in string.
     */
    private String parseUpdate(String[] spaceSplitInput) throws DukeException {
        String response;
        if (spaceSplitInput.length < 2) { // No task number entered
            throw new DukeException("Update task number is missing!\n");
        }
        String updateCommand = spaceSplitInput[1];

        response = dukeTaskList.updateTask(updateCommand);
        storage.saveToDataFile();

        return response;
    }

    /**
     * Parses find task input and prints out matching task.
     *
     * @param spaceSplitInput string array of inputs split by space.
     * @throws DukeException if find task input format is incorrect.
     * @return duke's response in string.
     */
    private String parseFind(String[] spaceSplitInput) throws DukeException {
        String response;
        if (spaceSplitInput.length < 2) { // No find keyword entered
            throw new DukeException("Find keyword is missing!\n");
        }
        String keyword = spaceSplitInput[1];

        response = dukeTaskList.searchTask(keyword);

        return response;
    }

    /**
     * Parses todos input and adds todos task.
     *
     * @param spaceSplitInput string array of inputs split by space.
     * @throws DukeException if todos input format is incorrect.
     * @return duke's response in string.
     */
    private String parseToDo(String[] spaceSplitInput) throws DukeException{
        String response;
        if (spaceSplitInput.length < 2) { // Todos has no description. If has, spaceSplitInput has length 2.
            throw new DukeException("Todo description cannot be empty!\n");
        }
        String toDoText = spaceSplitInput[1].trim();
        response = dukeTaskList.addToDo(toDoText);
        storage.saveToDataFile();

        return response;
    }

    /**
     * Parses deadline input and adds deadline task.
     *
     * @param spaceSplitInput string array of inputs split by space.
     * @param slashSplitInput string array of inputs split by slash.
     * @throws DukeException if deadline input format is incorrect.
     * @throws DateTimeParseException if date/time entered cannot be parsed.
     * @return duke's response in string.
     */
    private String parseDeadline(String[] spaceSplitInput, String[] slashSplitInput) throws DukeException,
            DateTimeParseException {
        String response;
        if (spaceSplitInput.length < 2) { // Deadline has no description
            throw new DukeException("Deadline description cannot be empty!\n");
        }
        if (slashSplitInput.length < 2) { // Deadline has no slash (thus no date)
            throw new DukeException("Deadline must have a date and time written after a slash!\n");
        }

        String[] ddlDateTimeArr = slashSplitInput[1].split(" ", 3);
        if (ddlDateTimeArr.length < 3) {
            // If there're less than 3 words behind slash e.g. No "by", nothing behind "by", etc.
            throw new DukeException("Deadline date must be in the format of 'by date time'!\n");
        }
        // Throws DateTimeParseException if date cannot be parsed
        LocalDate ddlDate = LocalDate.parse(ddlDateTimeArr[1]);
        LocalTime ddlTime = LocalTime.parse(ddlDateTimeArr[2]);
        String ddlText = spaceSplitInput[1].trim();
        response = dukeTaskList.addDeadline(ddlText, ddlDate, ddlTime);
        storage.saveToDataFile();

        return response;
    }

    /**
     * Parses event input and adds event task.
     *
     * @param spaceSplitInput string array of inputs split by space.
     * @param slashSplitInput string array of inputs split by slash.
     * @throws DukeException if event input format is incorrect.
     *  @throws DateTimeParseException if date/time entered cannot be parsed.
     * @return duke's response in string.
     */
    private String parseEvent(String[] spaceSplitInput, String[] slashSplitInput) throws DukeException,
            DateTimeParseException {
        String response;
        if (spaceSplitInput.length < 2) { // Event has no description
            throw new DukeException("Event description cannot be empty!\n");
        }
        if (slashSplitInput.length < 2) { // Event has no slash (thus no date)
            throw new DukeException("Event must has a date written after a slash!\n");
        }

        String[] eventDateTimeArr = slashSplitInput[1].split(" ", 3);
        if (eventDateTimeArr.length < 3) {
            // If there're less than 3 words behind slash e.g. No "at", nothing behind "at", etc.
            throw new DukeException("Event date must be in the format of 'by date time-time'!\n");
        }
        // Throws DateTimeParseException if date cannot be parsed
        LocalDate eventDate = LocalDate.parse(eventDateTimeArr[1]);

        String[] eventTimes = eventDateTimeArr[2].split("-", 2); // split the start and end time
        if (eventTimes.length < 2) { // There's no dash for start and end time
            throw new DukeException("Event date must have a start and end time separated by a dash\n");
        }
        LocalTime eventStartTime = LocalTime.parse(eventTimes[0]);
        LocalTime eventEndTime = LocalTime.parse(eventTimes[1]);
        String eventText = spaceSplitInput[1].trim();
        response = dukeTaskList.addEvent(eventText, eventDate, eventStartTime, eventEndTime);
        storage.saveToDataFile();

        return response;
    }
}
