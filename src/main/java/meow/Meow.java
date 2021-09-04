package meow;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a chat bot Meow who will be performing different tasks
 * according to user input.
 */
public class Meow {
    protected Storage storage;
    protected TaskList tasks;
    protected Parser parser;
    protected Ui ui;
    private boolean isExit = false;

    enum Command {
        BYE,
        LIST,
        DONE,
        DELETE,
        FIND,
        TODO,
        EVENT,
        DEADLINE
    }

    /**
     * A public constructor to initialize a Meow object.
     */
    public Meow() {
        this.ui = new Ui();
        this.storage = new Storage("data/meow.txt");
        this.parser = new Parser();
        if (this.storage.canReadFromFile()) {
            // Read the file and convert the content to the taskList array
            this.tasks = new TaskList(this.storage.getStorageTasksList());
        } else {
            this.tasks = new TaskList();
        }
    }

    private String processCommand(String input) throws MeowException {
        try {
            Command userCommand = this.parser.checkCommand(input);
            switch (userCommand) {
            case BYE:
                System.exit(0);
            case LIST:
                return this.ui.displayList(this.tasks.getTasksList());
            case DONE:
                String output = this.tasks.completeTask(this.parser.getTaskNumber(input));
                this.storage.addArrayTaskToFile(this.tasks.getTasksList());
                return output;
            case FIND:
                List<Task> filteredTasks = this.tasks.searchTask(this.parser.getTaskNumber(input));
                return this.ui.displaySearchList(filteredTasks);
            case TODO:
                String todoTask = this.parser.getTask(input, userCommand);
                Todo newTodo = this.tasks.addTodo(todoTask);
                this.storage.addArrayTaskToFile(this.tasks.getTasksList());
                return this.ui.printTaskList(this.tasks.getTasksList(), newTodo);
            case EVENT:
                String eventTask = this.parser.getTask(input, userCommand);
                String[] eventTaskAndDate = this.parser.getTaskAndDate(eventTask, userCommand);
                try {
                    Event newEvent = this.tasks.addEvent(eventTaskAndDate[0], eventTaskAndDate[1]);
                    this.storage.addArrayTaskToFile(this.tasks.getTasksList());
                    return this.ui.printTaskList(this.tasks.getTasksList(), newEvent);
                } catch (ArrayIndexOutOfBoundsException exception) {
                    throw new EmptyEventTimeException();
                }
            case DEADLINE:
                String deadlineTask = this.parser.getTask(input, userCommand);
                String[] deadlineTaskAndDate = this.parser.getTaskAndDate(deadlineTask, userCommand);
                try {
                    String[] dateAndTime = deadlineTaskAndDate[1].split(" ");
                    if (this.parser.isLocalDateTime(dateAndTime[0], dateAndTime[1])) {
                        LocalDate date = this.parser.convertToLocalDate(dateAndTime[0]);
                        Deadline newDeadline = this.tasks.addDeadline(deadlineTaskAndDate[0], date, dateAndTime[1]);
                        this.storage.addArrayTaskToFile(this.tasks.getTasksList());
                        return this.ui.printTaskList(this.tasks.getTasksList(), newDeadline);
                    } else {
                        throw new InvalidDateTimeException();
                    }
                } catch (ArrayIndexOutOfBoundsException exception) {
                    throw new EmptyDeadlineTimeException();
                }
            case DELETE:
                String deleteOutput = this.tasks.deleteTask(this.parser.getTaskNumber(input));
                this.storage.addArrayTaskToFile(this.tasks.getTasksList());
                return deleteOutput;
            default:
                throw new InvalidInputException();
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        try {
            return processCommand(input);
        } catch (MeowException e) {
            return e.toString();
        }
    }
}
