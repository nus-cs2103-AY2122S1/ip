package duke;

import duke.command.CommandBye;
import duke.command.CommandDeadline;
import duke.command.CommandDelete;
import duke.command.CommandDone;
import duke.command.CommandEvent;
import duke.command.CommandFind;
import duke.command.CommandGeneric;
import duke.command.CommandList;
import duke.command.CommandTodo;
import duke.command.DukeCommand;
import duke.exception.DukeArgumentException;
import duke.exception.DukeCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class DukeCommandParser {
    private enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    private static Task parseTask(String input, TaskType tType) throws DukeCommandException, DukeArgumentException {
        String[] inputArr;
        String[] taskArr;
        Task task;

        switch (tType) {
        case TODO:
            inputArr = input.split(" ", 2);
            if (inputArr.length != 2 || inputArr[1].equals("")) {
                throw new DukeCommandException("todo");
            }

            task = new ToDo(inputArr[1]);
            break;
        case DEADLINE:
            inputArr = input.split(" ", 2);
            if (inputArr.length != 2) {
                throw new DukeCommandException("deadline");
            }

            taskArr = inputArr[1].split(" /by ", 2);
            if (taskArr.length != 2 || taskArr[1].equals("")) {
                throw new DukeCommandException("deadline");
            }

            task = new Deadline(taskArr[0], DukeDate.parseDateInput(taskArr[1]));
            break;
        case EVENT:
            inputArr = input.split(" ", 2);
            if (inputArr.length != 2) {
                throw new DukeCommandException("event");
            }

            taskArr = inputArr[1].split(" /at ", 2);
            if (taskArr.length != 2 || taskArr[1].equals("")) {
                throw new DukeCommandException("event");
            }

            task = new Event(taskArr[0], DukeDate.parseDateInput(taskArr[1]));
            break;
        default:
            task = null;
        }

        return task;
    }

    public static DukeCommand parseCommand(String input) {
        String[] inputArr = input.split(" ", 2);
        DukeCommand dc = new CommandGeneric();
        try {
            if (inputArr.length == 1) {
                switch (inputArr[0]) {
                case "bye":
                    dc = new CommandBye();
                    break;
                case "list":
                    dc = new CommandList();
                    break;
                case "todo":
                    throw new DukeCommandException("todo");
                case "deadline":
                    throw new DukeCommandException("deadline");
                case "done":
                    throw new DukeCommandException("done");
                case "event":
                    throw new DukeCommandException("event");
                case "delete":
                    throw new DukeCommandException("delete");
                case "find":
                    throw new DukeCommandException("find");
                default:
                    throw new DukeCommandException(inputArr[0]);
                }
            } else {
                switch (inputArr[0]) {
                case "done":
                    try {
                        int taskId = Integer.parseInt(inputArr[1]);
                        dc = new CommandDone(taskId);
                    } catch (NumberFormatException nfe) {
                        DukeUi.printLine("Incorrect argument for command Done, must be an integer");
                    }
                    break;
                case "todo":
                    dc = new CommandTodo((ToDo) parseTask(input, TaskType.TODO));
                    break;
                case "deadline":
                    dc = new CommandDeadline((Deadline) parseTask(input, TaskType.DEADLINE));
                    break;
                case "event":
                    dc = new CommandEvent((Event) parseTask(input, TaskType.EVENT));
                    break;
                case "delete":
                    try {
                        int taskId = Integer.parseInt(inputArr[1]);
                        dc = new CommandDelete(taskId);
                    } catch (NumberFormatException nfe) {
                        DukeUi.printLine("Incorrect argument for command Delete, must be an integer");
                    }
                    break;
                case "find":
                    dc = new CommandFind(inputArr[1]);
                    break;
                default:
                    throw new DukeCommandException(inputArr[0]);
                }
            }
        } catch (DukeCommandException e) {
            DukeUi.printLine(e.getMsg());
        } catch (DukeArgumentException e) {
            DukeUi.printLine(e.getMessage());
        }
        return dc;
    }
}
