package duke;

import command.AddCommand;
import command.ByeCommand;
import command.Command;
import command.DoneCommand;
import command.DeleteCommand;
import command.FindCommand;
import command.ListCommand;

import exception.DukeException;
import exception.DukeExceptionType;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Parser {
    
    /**
     * Parses the given user input and returns the corresponding Command object for execution based on the input.
     * 
     * @param fullCommand The full user input string for parsing.
     * @param taskList The list of currently saved tasks.
     * @return A Command object based on the parsed user input.
     * @throws DukeException If the parser encounters an invalid command.
     */
    public static Command parse(String fullCommand, TaskList taskList) throws DukeException {
        
        // Find case based on first word of command
        switch (fullCommand.split("\\s+")[0]) {
        
        // "bye" command given  
        case "bye":   
            return new ByeCommand();

        // "list" command given    
        case "list":   
            return new ListCommand();
            
        // "done" command given
        case "done":  
            if (fullCommand.split("\\s+").length == 1) {
                throw new DukeException(DukeExceptionType.INVALID_TASK_INDEX);
            } else {
                int toSet = Integer.parseInt(fullCommand.split("\\s+")[1]);
                if (taskList.isInvalidIndex(toSet)) {
                    throw new DukeException(DukeExceptionType.INVALID_TASK_INDEX);
                } else {
                    return new DoneCommand(toSet - 1);
                }
            }
            
        // "delete" command given
        case "delete":   
            if (fullCommand.split("\\s+").length == 1) {
                throw new DukeException(DukeExceptionType.INVALID_TASK_INDEX);
            } else {
                int toDelete = Integer.parseInt(fullCommand.split("\\s+")[1]);
                if (taskList.isInvalidIndex(toDelete)) {
                    throw new DukeException(DukeExceptionType.INVALID_TASK_INDEX);
                } else {
                    return new DeleteCommand(toDelete - 1);
                }
            }
            
        // "find" command given
        case "find":
            if (fullCommand.split("\\s+").length == 1) {
                throw new DukeException(DukeExceptionType.INVALID_FIND);
            } else {
                LocalDate desiredDate = LocalDate.parse(fullCommand.split("\\s+")[1]);
                return new FindCommand(desiredDate);
            }
            
        // Task command given
        default:
            
            // Incomplete or invalid command
            if (fullCommand.split("\\s+").length == 1) { 
                switch (fullCommand) {
                case "deadline":
                    throw new DukeException(DukeExceptionType.MISSING_DEADLINE_DESC);

                case "event":
                    throw new DukeException(DukeExceptionType.MISSING_EVENT_DESC);

                case "todo":
                    throw new DukeException(DukeExceptionType.MISSING_TODO_DESC);

                default:
                    throw new DukeException(DukeExceptionType.INVALID_INPUT);
                }

            } else {
                Task newTask;
                
                String[] taskString = fullCommand.split("\\s+", 2);
                String taskType = taskString[0];
                String taskDetails = taskString[1];

                switch (taskType) {
                    
                // "deadline" command given
                case "deadline": {
                    String[] details = taskDetails.split(" /by ");

                    if (details.length == 1) { 
                        throw new DukeException(DukeExceptionType.MISSING_DEADLINE_DATETIME);
                    } else {
                        String[] deadline = details[1].split(" ");
                        if (deadline.length == 1) {
                            newTask = new Deadline(details[0], LocalDate.parse(details[1]));
                        } else if (deadline.length == 2) {
                            newTask = new Deadline(details[0], LocalDate.parse(deadline[0]),
                                    LocalTime.parse(deadline[1]));
                        } else {
                            throw new DukeException(DukeExceptionType.INVALID_DATETIME);
                        }
                    }
                    break;
                }

                // "event" command given
                case "event": {
                    String[] details = taskDetails.split(" /at ");

                    if (details.length == 1) { 
                        throw new DukeException(DukeExceptionType.MISSING_EVENT_PERIOD);
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
                            throw new DukeException(DukeExceptionType.INVALID_PERIOD);
                        }
                    }
                    break;
                }
                
                // "todo" command given
                case "todo":
                    newTask = new Todo(taskDetails);
                    break;
                    
                // Invalid command
                default:  
                    throw new DukeException(DukeExceptionType.INVALID_INPUT);
                }
                
                return new AddCommand(newTask);
            }
        }
    }
}
