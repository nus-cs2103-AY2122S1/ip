package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Methods to parse strings typed by user.
 * 
 * @author Tianqi-Zhu
 */
public class Parser {
    
    /**
     * Parse a line of texts typed by user. 
     * 
     * @param newInput User's new line of input. 
     * @param taskList Current taskList.
     * @return Commands to execute or tasks to put into list.
     * @throws DukeExcpetion If the input is valid.
     */
    public static Executable parse(String newInput, TaskList taskList) throws DukeExcpetion {
        if (newInput.equals("list")) {
            return new ListCommand(); 
        } else if (newInput.length() > 5 && newInput.substring(0, 5).equals("done ")) {               
            try {
                int doneIndex = Integer.parseInt(newInput.substring(5));
                if (doneIndex <= 0 || doneIndex > taskList.taskAmount()) {
                    throw new DukeExcpetion("Please input a valid task index.");
                } else {
                    return new DoneCommand(doneIndex);
                }
            } catch (NumberFormatException e) {
                throw new DukeExcpetion("Invalid task index, please input an integer.");
            }
        } else if (newInput.length() > 4 && newInput.substring(0, 4).equals("done")) {
            throw new DukeExcpetion("Please leave a space between done and the index of the task.");
        } else if (newInput.length() > 7 && newInput.substring(0, 7).equals("delete ")) {               
            try {
                int deleteIndex = Integer.parseInt(newInput.substring(7));
                if (deleteIndex <= 0 || deleteIndex > taskList.taskAmount()) {
                    throw new DukeExcpetion("Please input a valid task index.");
                } else {
                    return new DeleteCommand(deleteIndex);
                }
            } catch (NumberFormatException e) {
                throw new DukeExcpetion("Invalid task index, please input an integer.");
            }
        } else if (newInput.length() > 6 && newInput.substring(0, 6).equals("delete")) {
            throw new DukeExcpetion("Please leave a space between delete and the index of the task.");
        } else if (newInput.length() > 5 && newInput.subSequence(0, 5).equals("todo ")) {
            return new ToDo(newInput.substring(5));
        } else if (newInput.length() > 6 && newInput.subSequence(0, 6).equals("event ")) {
            int timeIndex = newInput.indexOf("/at", 6);
            if (timeIndex == -1) {
                throw new DukeExcpetion("Not a valid event. Please add a time with /at or mark it as a todo.");
            } else if (timeIndex < 7) {
                throw new DukeExcpetion("Not a valid event. Please enter a valid name of the event.");
            } else if (newInput.length() > timeIndex + 16) {
                try {
                    return new Event(newInput.substring(6, timeIndex - 1),
                            LocalDate.parse(newInput.substring(timeIndex + 4, timeIndex + 14)),
                                    parseTime(newInput.substring(timeIndex + 15)));
                } catch (DukeExcpetion e) {
                    return new Event(newInput.substring(6, timeIndex - 1), newInput.substring(timeIndex + 4));
                }
            } else {
                try {
                    return new Event(newInput.substring(6, timeIndex - 1),
                            LocalDate.parse(newInput.substring(timeIndex + 4)));
                } catch (DateTimeParseException e) {
                    return new Event(newInput.substring(6, timeIndex - 1), newInput.substring(timeIndex + 4));
                }
            }
        } else if (newInput.length() > 9 && newInput.subSequence(0, 9).equals("deadline ")) {
            int timeIndex = newInput.indexOf("/by", 9);
            if (timeIndex == -1) {
                throw new DukeExcpetion("Not a valid deadline. Please add a time with /by or mark it as a todo.");
            } else if (timeIndex < 10) {
                throw new DukeExcpetion("Not a valid deadline. Please enter a name of the deadline.");
            } else if (newInput.length() > timeIndex + 16) {
                try {
                    return new Deadline(newInput.substring(9, timeIndex - 1),
                            LocalDate.parse(newInput.substring(timeIndex + 4, timeIndex + 14)),
                                    parseTime(newInput.substring(timeIndex + 15)));
                } catch (DukeExcpetion e) {
                    return new Deadline(newInput.substring(9, timeIndex - 1), newInput.substring(timeIndex + 4));
                }
            } else {
                try {
                    return new Deadline(newInput.substring(9, timeIndex - 1),
                            LocalDate.parse(newInput.substring(timeIndex + 4)));
                } catch (DateTimeParseException e) {
                    return new Deadline(newInput.substring(9, timeIndex - 1), newInput.substring(timeIndex + 4));
                }
            }
        } else if (newInput.length() > 5 && newInput.substring(0, 5).equals("find ")) {
            return new FindCommand(newInput.substring(5));
        } else {
            throw new DukeExcpetion("Sorry, I don't understand the input.");
        }
    }
    
    /**
     * Parse a string of time typed by user of format hhmm.
     * 
     * @param timeString String of hhmm format to parse.
     * @return 
     * @throws DukeExcpetion
     */
    private static LocalTime parseTime(String timeString) throws DukeExcpetion {
        String expanded = timeString.substring(0, 2) + ":" + timeString.substring(2) + ":00";
        try {
            return LocalTime.parse(expanded);
        } catch (DateTimeParseException e) {
            throw new DukeExcpetion("Please input a correct time format of hhmm");
        }
    }
}