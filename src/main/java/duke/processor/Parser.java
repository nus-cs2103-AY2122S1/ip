package duke.processor;

import duke.exception.DukeExcpetion;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.task.Deadline;
import duke.task.Doafter;
import duke.task.Event;
import duke.task.ToDo;

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
            return parseDone(newInput, taskList);
        } else if (newInput.length() > 4 && newInput.substring(0, 4).equals("done")) {
            throw new DukeExcpetion("Please leave a space between done and the index of the task.");
        } else if (newInput.length() > 7 && newInput.substring(0, 7).equals("delete ")) {               
            return parseDelete(newInput, taskList);
        } else if (newInput.length() > 6 && newInput.substring(0, 6).equals("delete")) {
            throw new DukeExcpetion("Please leave a space between delete and the index of the task.");
        } else if (newInput.length() > 5 && newInput.subSequence(0, 5).equals("todo ")) {
            return new ToDo(newInput.substring(5));
        } else if (newInput.length() > 6 && newInput.subSequence(0, 6).equals("event ")) {
            return parseEvent(newInput);
        } else if (newInput.length() > 9 && newInput.subSequence(0, 9).equals("deadline ")) {
            return parseDeadline(newInput);
        } else if (newInput.length() > 8 && newInput.subSequence(0, 8).equals("doafter ")) {
            return parseDoAfter(newInput);
        } else if (newInput.length() > 5 && newInput.substring(0, 5).equals("find ")) {
            return new FindCommand(newInput.substring(5));
        } else {
            throw new DukeExcpetion("Sorry, I don't understand the input.");
        }
    }

    private static Executable parseDone(String newInput, TaskList taskList) throws DukeExcpetion {
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
    }

    private static Executable parseDelete(String newInput, TaskList taskList) throws DukeExcpetion {
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
    }

    private static Executable parseEvent(String newInput) throws DukeExcpetion {
        int timeIndex = newInput.indexOf("/at", 6);
        int endTimeIndex = newInput.indexOf("/to", 6);
        if (timeIndex == -1) {
            throw new DukeExcpetion("Not a valid event. Please add a time with /at or mark it as a todo.");
        } else if (timeIndex < 7 && endTimeIndex == -1) {
            throw new DukeExcpetion("Not a valid event. Please enter a valid name of the event.");
        } else {
            String name = newInput.substring(6, timeIndex - 1);
            try {
                LocalDate startDate = LocalDate.parse(newInput.substring(timeIndex + 4, timeIndex + 14));
                if (newInput.length() > timeIndex + 16) {
                    LocalTime startTime = parseTime(newInput.substring(timeIndex + 15));
                    Event out = new Event(name, startDate, startTime);
                    if (endTimeIndex != -1) {
                        LocalDate endDate = LocalDate.parse(newInput.substring(endTimeIndex + 4, endTimeIndex + 14));
                        LocalTime endTime = parseTime(newInput.substring(endTimeIndex + 15));
                        out.addEndDateTime(endDate, endTime);
                    }
                    return out;
                } else {
                    Event out = new Event(name, startDate);
                    if (endTimeIndex != -1) {
                        LocalDate endDate = LocalDate.parse(newInput.substring(endTimeIndex + 4, endTimeIndex + 14));
                        out.addEndDate(endDate);
                    }
                    return out;
                }
            } catch (DukeExcpetion | DateTimeParseException | IndexOutOfBoundsException e) {
                return new Event(name, newInput.substring(timeIndex + 4));
            }
        }
    }

    private static Executable parseDoAfter(String newInput) throws DukeExcpetion {
        int timeIndex = newInput.indexOf("/after", 8);
        if (timeIndex == -1) {
            throw new DukeExcpetion("Not a valid doafter. Please add a time with /after or mark it as a todo.");
        } else if (timeIndex < 9) {
            throw new DukeExcpetion("Not a valid doafter. Please enter a valid name of the doafter.");
        } else {
            String name = newInput.substring(8, timeIndex - 1);
            try {
                LocalDate startDate = LocalDate.parse(newInput.substring(timeIndex + 7, timeIndex + 17));
                if (newInput.length() > timeIndex + 19) {
                    LocalTime startTime = parseTime(newInput.substring(timeIndex + 18));
                    return new Doafter(name, startDate, startTime);
                } else {
                    return new Doafter(name, startDate);
                }
            } catch (DukeExcpetion | IndexOutOfBoundsException | DateTimeParseException e) {
                return new Doafter(name, newInput.substring(timeIndex + 7));
            }
        }
    }

    private static Executable parseDeadline(String newInput) throws DukeExcpetion {
        int timeIndex = newInput.indexOf("/by", 9);
        if (timeIndex == -1) {
            throw new DukeExcpetion("Not a valid deadline. Please add a time with /by or mark it as a todo.");
        } else if (timeIndex < 10) {
            throw new DukeExcpetion("Not a valid deadline. Please enter a name of the deadline.");
        } else {
            String name = newInput.substring(9, timeIndex - 1);
            try {
                LocalDate endDate = LocalDate.parse(newInput.substring(timeIndex + 4, timeIndex + 14));
                if (newInput.length() > timeIndex + 16) {
                    LocalTime endTime = parseTime(newInput.substring(timeIndex + 15));
                    return new Deadline(name, endDate, endTime);
                } else {
                    return new Deadline(name, endDate);
                }
            } catch (DukeExcpetion | IndexOutOfBoundsException | DateTimeParseException e) {
                return new Deadline(name, newInput.substring(timeIndex + 4));
            }
        }
    }

    /**
     * Parse a string of time typed by user of format hhmm.
     * 
     * @param timeString String of hhmm format to parse.
     * @return time parsed
     * @throws DukeExcpetion if the format is not correct
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