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
        
        switch (fullCommand.split("\\s+")[0]) {
        case "bye":   
            return new ByeCommand();
            
        case "list":   
            return new ListCommand();
            
        case "done": 
            if (fullCommand.split("\\s+").length == 1) {
                throw new DukeException(DukeExceptionType.INVALID_INDEX);
            } else {
                int toSet = Integer.parseInt(fullCommand.split("\\s+")[1]);
                if (taskList.isInvalidIndex(toSet)) {
                    throw new DukeException(DukeExceptionType.INVALID_INDEX);
                } else {
                    return new DoneCommand(toSet - 1);
                }
            }
            
        case "delete":  
            if (fullCommand.split("\\s+").length == 1) {
                throw new DukeException(DukeExceptionType.INVALID_INDEX);
            } else {
                int toDelete = Integer.parseInt(fullCommand.split("\\s+")[1]);
                if (taskList.isInvalidIndex(toDelete)) {
                    throw new DukeException(DukeExceptionType.INVALID_INDEX);
                } else {
                    return new DeleteCommand(toDelete - 1);
                }
            }
            
        case "find":
            String[] parsedCommand = fullCommand.split("\\s+");
            if (parsedCommand.length == 1) {
                throw new DukeException(DukeExceptionType.INVALID_FIND); // should be general invalid find 
            } else {
                if (parsedCommand[1].contains("/date")) {
                    LocalDate desiredDate = LocalDate.parse(parsedCommand[2]); // handle if date missing
                    return new FindDateCommand(desiredDate);
                } else if (parsedCommand[1].contains("/keyword")) {
                    String keyword = parsedCommand[2]; // handle if keyword missing
                    return new FindKeywordCommand(keyword);
                } else {
                    throw new DukeException(DukeExceptionType.INVALID_FIND); // should be invalid find keyword
                }
                
            }
            
        default:
            if (fullCommand.split("\\s+").length == 1) { 
                switch (fullCommand) {
                case "deadline":
                    throw new DukeException(DukeExceptionType.DEADLINE_DESC);

                case "event":
                    throw new DukeException(DukeExceptionType.EVENT_DESC);

                case "todo":
                    throw new DukeException(DukeExceptionType.TODO_DESC);

                default:
                    throw new DukeException(DukeExceptionType.INVALID_INPUT);
                }

            } else {
                Task newTask;
                
                String[] taskString = fullCommand.split("\\s+", 2);
                String taskType = taskString[0];
                String taskDetails = taskString[1];

                switch (taskType) {
                case "deadline": {
                    String[] details = taskDetails.split(" /by ");

                    if (details.length == 1) { 
                        throw new DukeException(DukeExceptionType.DEADLINE_TIME);
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

                    if (details.length == 1) { 
                        throw new DukeException(DukeExceptionType.EVENT_PERIOD);
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
                case "todo":
                    newTask = new Todo(taskDetails);
                    break;

                default: 
                    throw new DukeException(DukeExceptionType.INVALID_INPUT);
                }
                
                return new AddCommand(newTask);
            }
        }
    }
}
