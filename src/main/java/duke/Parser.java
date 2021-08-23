package duke;

import command.*;
import exception.DukeException;
import exception.DukeExceptionType;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Parser {
    
    public static Command parse(String fullCommand, TaskList taskList) throws DukeException {
        
        // task function: add tasks
        switch (fullCommand.split("\\s+")[0]) {
        case "bye":   // bye function: exits the loop, ends process
            return new ByeCommand();
            
        case "list":   // list function: iterates through taskList, prints Tasks' listEntry
            return new ListCommand();
            
        case "done":   // done function: sets a task to done
            if (fullCommand.split("\\s+").length == 1) {
                throw new DukeException(DukeExceptionType.INVALIDINDEX);
            } else {
                int toSet = Integer.parseInt(fullCommand.split("\\s+")[1]);
                if (taskList.isInvalidIndex(toSet)) {
                    throw new DukeException(DukeExceptionType.INVALIDINDEX);
                } else {
                    return new DoneCommand(toSet - 1);
                }
            }
            
        case "delete":   // delete function: delete a task
            if (fullCommand.split("\\s+").length == 1) {
                throw new DukeException(DukeExceptionType.INVALIDINDEX);
            } else {
                int toDelete = Integer.parseInt(fullCommand.split("\\s+")[1]);
                if (taskList.isInvalidIndex(toDelete)) {
                    throw new DukeException(DukeExceptionType.INVALIDINDEX);
                } else {
                    return new DeleteCommand(toDelete - 1);
                }
            }
            
        case "find":
            if (fullCommand.split("\\s+").length == 1) {
                throw new DukeException(DukeExceptionType.INVALIDFIND);
            } else {
                LocalDate desiredDate = LocalDate.parse(fullCommand.split("\\s+")[1]);
                return new FindCommand(desiredDate);
            }
            
        default:
            if (fullCommand.split("\\s+").length == 1) { // task details not given or not valid task
                switch (fullCommand) {
                case "deadline":
                    throw new DukeException(DukeExceptionType.DEADLINEDESC);

                case "event":
                    throw new DukeException(DukeExceptionType.EVENTDESC);

                case "todo":
                    throw new DukeException(DukeExceptionType.TODODESC);

                default:
                    throw new DukeException(DukeExceptionType.INVALIDINPUT);
                }

            } else {
                Task newTask;

                // split text string, first string will be the task type and second string will be task details
                String[] taskString = fullCommand.split("\\s+", 2);
                String taskType = taskString[0];
                String taskDetails = taskString[1];

                // determine type of task, create new task
                switch (taskType) {
                case "deadline": {
                    String[] details = taskDetails.split(" /by ");

                    if (details.length == 1) { // time of deadline not given
                        throw new DukeException(DukeExceptionType.DEADLINETIME);
                    } else {
                        String[] deadline = details[1].split(" ");
                        if (deadline.length == 1) {
                            newTask = new Deadline(details[0], LocalDate.parse(details[1]));
                        } else {
                            newTask = new Deadline(details[0], LocalDate.parse(deadline[0]),
                                    LocalTime.parse(deadline[1]));
                        }
                    }
                    break;
                }
                case "event": {
                    String[] details = taskDetails.split(" /at ");

                    if (details.length == 1) { // period of event not given
                        throw new DukeException(DukeExceptionType.EVENTPERIOD);
                    } else {
                        String[] periodRange = details[1].split(" ");
                        if (periodRange.length == 2) {
                            newTask = new Event(details[0], LocalDate.parse(periodRange[0]),
                                    LocalDate.parse(periodRange[1]));
                        } else if (periodRange.length == 3) {
                            newTask = new Event(details[0], LocalDate.parse(periodRange[0]),
                                    LocalTime.parse(periodRange[1]), LocalTime.parse(periodRange[2]));
                        } else if (periodRange.length == 4) {
                            newTask = new Event(details[0],
                                    LocalDate.parse(periodRange[0]), LocalTime.parse(periodRange[1]),
                                    LocalDate.parse(periodRange[2]), LocalTime.parse(periodRange[3]));
                        } else {
                            throw new DukeException(DukeExceptionType.INVALIDPERIOD);
                        }
                    }
                    break;
                }
                case "todo":
                    newTask = new Todo(taskDetails);
                    break;

                default:  // taskName is invalid
                    throw new DukeException(DukeExceptionType.INVALIDINPUT);
                }
                // add task to taskList
                return new AddCommand(newTask);
            }
        }
    }
}
