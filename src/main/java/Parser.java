import java.time.LocalDate;

/**
 * Make sense of user commands.
 */
public class Parser {
    Ui ui;
    TaskList taskList;
    Storage storage;
    private enum Command {LIST, DONE, DELETE, TODO, DEADLINE, EVENT, CHECKDATE, INVALID};

    public Parser(Storage storage, Ui ui, TaskList taskList) {
        this.storage = storage;
        this.ui = ui;
        this.taskList = taskList;
    }


    public void parse(String input) {
        try {
            Command caseId = findCase(input);

            switch (caseId) {
                case LIST:
                    this.ui.list(taskList.getTasks());
                    break;

                case DONE:
                    if (isValidDone(input)) {
                        int donePos = Integer.valueOf(input.substring(5));

                        taskList.getTasks().get(donePos - 1).markAsDone();
                        this.storage.markAsDone(taskList, donePos);
                        this.ui.done(this.taskList, donePos);
                    }
                    break;

                case DELETE:
                    if (isValidDelete(input)) {
                        int deletePos = Integer.valueOf(input.substring(7));

                        this.storage.delete(deletePos);
                        this.ui.delete(taskList, deletePos);
                        this.taskList.deleteTask(deletePos);
                    }
                    break;

                case TODO:
                    // handles any characters after 'todo' that are not white space
                    if (!input.startsWith("todo ")) {
                        throw new DukeException("Oops! Improper formatting for todo. " +
                                "Please use: todo <description>");
                    }
                    if (input.length() == 5) {
                        throw new DukeException("Oops! The description of todo cannot be empty.");
                    }

                    Todo todo = new Todo(input.substring(5));
                    this.taskList.addTask(todo);
                    this.storage.addTodo(todo);
                    this.ui.addTodo(taskList, todo);
                    break;

                case DEADLINE:
                    // handles any characters after 'deadline' that are not white space
                    if (!input.startsWith("deadline ")) {
                        throw new DukeException("Oops! Improper formatting for deadline. " +
                                "Please use: deadline <description> /by <date/time>");
                    }
                    // handles case of no description and date/time
                    if (input.length() == 9) {
                        throw new DukeException("Oops! The description and date/time of deadline cannot be empty.");
                    }
                    if (!input.contains(" /by ")) {
                        throw new DukeException("Oops! Inappropriate formatting for deadlines.");
                    }
                    // handles: deadline /by <time>
                    if (input.split(" /by ")[0].equals("deadline")) {
                        throw new DukeException("Oops! Missing description for deadline.");
                    }

                    String by = input.split(" /by ")[1];
                    LocalDate deadlineDate = LocalDate.parse(by);
                    String deadlineDescription = input.split(" /by ")[0].substring(9);

                    Deadline deadline = new Deadline(deadlineDescription, deadlineDate);
                    this.taskList.addTask(deadline);
                    this.storage.addDeadline(deadline);
                    this.ui.addDeadline(taskList, deadline);
                    break;

                case EVENT:
                    // handles any characters after 'event' that are not white space
                    if (!input.startsWith("event ")) {
                        throw new DukeException("Oops! Improper formatting for event. " +
                                "Please use: event <description> /at <date/time>");
                    }
                    if (input.length() == 6) {
                        throw new DukeException("Oops! The description and date/time of event cannot be empty.");
                    }
                    if(!input.contains(" /at ")){
                        throw new DukeException("Oops! Inappropriate formatting for events.");
                    }
                    if (input.split(" /at ")[0].equals("event")) {
                        throw new DukeException("Oops! Missing description for event.");
                    }

                    String at = input.split(" /at ")[1];
                    LocalDate eventDate = LocalDate.parse(at);
                    String eventDescription = input.split(" /at ")[0].substring(6);

                    Event event = new Event(eventDescription, eventDate);

                    this.taskList.addTask(event);
                    this.storage.addEvent(event);
                    this.ui.addEvent(taskList, event);
                    break;

                case CHECKDATE:
                    String date = input.substring(6);
                    LocalDate parsedDate = LocalDate.parse(date);

                    this.ui.checkDate(taskList, parsedDate);

                    break;

                default:
                    this.ui.invalidUserInput();
            }
        } catch (DukeException dukeException) {
            System.out.println(dukeException);
        }
    }

    /**
     * Returns the identifier of each case (for switch in chat method).
     *
     * @param input User entered into Command Line.
     * @return CaseId of type Command (Enum).
     */
    private Command findCase(String input) {
        if (input.equals("list")) {
            return Command.LIST;
        } else if (input.startsWith("done")) {
            return Command.DONE;
        } else if (input.startsWith("delete")) {
            return Command.DELETE;
        } else if (input.startsWith("todo")) {
            return Command.TODO;
        } else if (input.startsWith("deadline")) {
            return Command.DEADLINE;
        } else if (input.startsWith("event")) {
            return Command.EVENT;
        } else if (input.startsWith("check")) {
            return Command.CHECKDATE;
        } else {
            return Command.INVALID;
        }
    }

    private boolean isValidDone(String input) {
        try {
            // handles any characters after 'done' that are not white space
            if (!input.startsWith("done ")) {
                throw new DukeException("Oops! Improper formatting for done. " +
                        "Please use: done <task number>");
            }
            if (input.length() == 5) {
                throw new DukeException("Oops! The number following 'done' cannot be empty.");
            }

            //checks if the task to be marked as done is within the range of the list
            if (Integer.valueOf(input.substring(5)) <= 0 ||
                    Integer.valueOf(input.substring(5)) > taskList.getTasks().size()) {
                throw new DukeException("Oops! " +
                        "The task to mark as done is not within the range of the list.");
            }
            return true;
        } catch (DukeException dukeException) {
            System.out.println(dukeException);
            return false;
        }
    }

    private boolean isValidDelete(String input) {
        try {
            if (!input.startsWith("delete ")) {
                throw new DukeException("Oops! Improper formatting for delete. " +
                        "Please use: delete <task number>");
            }
            if (input.length() == 7) {
                throw new DukeException("Oops! The number following 'delete' cannot be empty.");
            }

            if (Integer.valueOf(input.substring(7)) <= 0 ||
                    Integer.valueOf(input.substring(7)) > this.taskList.getTasks().size()) {
                throw new DukeException("Oops! " +
                        "The task to delete is not within the range of the list.");
            }
            return true;
        } catch (DukeException dukeException) {
            System.out.println(dukeException);
            return false;
        }
    }
}
