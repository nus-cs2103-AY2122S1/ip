package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser to parse user inputs, and invoke corresponding functions
 */
public class Parser {
    private static boolean isRunning = true;

    public static boolean isRunning() {
        return isRunning;
    }

    /**
     * Static parse method of the class.
     *
     * @param userInput user inputs.
     * @param dukeTaskList a dukeTaskList object of the current run of Duke.
     * @throws NumberFormatException if user doesn't enter valid integers when entering task numbers.
     */
    public static void parse(String userInput, DukeTaskList dukeTaskList) {
        String[] slashSplitInput = userInput.split("/", 2);
        String[] spaceSplitInput = slashSplitInput[0].split(" ", 2); // Everything before the slash, split again by space

        try {
            if (userInput.equals("bye")) { // Exit
                Ui.printFarewell();
                isRunning = false;
            } else if (userInput.equals("list")) {
                Ui.printList(dukeTaskList.sendListForPrint());
            } else if (spaceSplitInput[0].equals("done")) {
                if (spaceSplitInput.length < 2) {
                    // No task number entered
                    throw new DukeException("Done Task number is missing!\n");
                }
                int doneTaskNo = Integer.parseInt(spaceSplitInput[1]); // Throws NumberFormatException if string cannot be parsed into valid int

                dukeTaskList.doneTask(doneTaskNo);
            } else if (spaceSplitInput[0].equals("delete")) {
                if (spaceSplitInput.length < 2) {
                    // No task number entered
                    throw new DukeException("Delete task number is missing!\n");
                }
                int deleteTaskNo = Integer.parseInt(spaceSplitInput[1]); // Throws NumberFormatException if string cannot be parsed into valid int

                dukeTaskList.deleteTask(deleteTaskNo);
            } else if (spaceSplitInput[0].equals("find")) {
                if (spaceSplitInput.length < 2) {
                    // No find keyword entered
                    throw new DukeException("Find keyword is missing!\n");
                }
                String keyword = spaceSplitInput[1];

                dukeTaskList.searchTask(keyword);
            } else if (spaceSplitInput[0].equals("todo")) {
                if (spaceSplitInput.length < 2) {
                    // Tudo has no description. If has, spaceSplitInput has length 2.
                    throw new DukeException("Todo description cannot be empty!\n");
                }
                String toDoText = spaceSplitInput[1].trim();
                dukeTaskList.addToDo(toDoText);
            } else if (spaceSplitInput[0].equals("deadline")) {
                if (spaceSplitInput.length < 2) {
                    // Deadline has no description
                    throw new DukeException("Deadline description cannot be empty!\n");
                }
                if (slashSplitInput.length < 2) {
                    // Deadline has no slash (thus no date)
                    throw new DukeException("Deadline must have a date and time written after a slash!\n");
                }

                String[] DdlDateTimeArr = slashSplitInput[1].split(" ", 3);
                if (DdlDateTimeArr.length < 3) {
                    // If there're less than 3 words behind slash e.g. No "by", nothing behind "by", etc.
                    throw new DukeException("Deadline date must be in the format of 'by date time'!\n");
                }
                LocalDate DdlDate = LocalDate.parse(DdlDateTimeArr[1]); // Throws DateTimeParseException if date cannot be parsed
                LocalTime DdlTime = LocalTime.parse(DdlDateTimeArr[2]);
                String DdlText = spaceSplitInput[1].trim();

                dukeTaskList.addDeadline(DdlText, DdlDate, DdlTime);
            } else if (spaceSplitInput[0].equals("event")) {
                if (spaceSplitInput.length < 2) {
                    // Event has no description
                    throw new DukeException("Event description cannot be empty!\n");
                }
                if (slashSplitInput.length < 2) {
                    // Event has no slash (thus no date)
                    throw new DukeException("Event must has a date written after a slash!\n");
                }

                String[] eventDateTimeArr = slashSplitInput[1].split(" ", 3);
                if (eventDateTimeArr.length < 3) {
                    // If there're less than 3 words behind slash e.g. No "at", nothing behind "at", etc.
                    throw new DukeException("Event date must be in the format of 'by date time-time'!\n");
                }
                LocalDate eventDate = LocalDate.parse(eventDateTimeArr[1]); // Throws DateTimeParseException if date cannot be parsed

                String[] eventTimes = eventDateTimeArr[2].split("-", 2); // split the start and end time
                if (eventTimes.length < 2) {
                    // There's no dash for start and end time
                    throw new DukeException("Event date must have a start and end time separated by a dash\n");
                }
                LocalTime eventStartTime = LocalTime.parse(eventTimes[0]);
                LocalTime eventEndTime = LocalTime.parse(eventTimes[1]);
                String eventText = spaceSplitInput[1].trim();

                dukeTaskList.addEvent(eventText, eventDate, eventStartTime, eventEndTime);
            } else {
                // User inputs unrecognized commands
                throw new DukeException("My intelligence has not evolved to understand this command :(\n");
            }
        } catch (DukeException e) {
            Ui.printError(e.getMessage());
        } catch (NumberFormatException e) {
            Ui.printError("Please enter a valid integer for task number!\n");
        } catch (DateTimeParseException e) {
            Ui.printError("Please enter date in the valid format: Deadlines:yyyy-mm-dd hh:mm; Events:yyyy-mm-dd hh:mm-hh:mm\n");
        }
    }
}
