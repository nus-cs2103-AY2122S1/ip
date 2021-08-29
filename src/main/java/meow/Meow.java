package meow;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


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
            case BYE: {
                System.exit(0);
//                return this.ui.exit();
            }
            case LIST: {
                return this.ui.displayList(this.tasks.getTasksList());
            }
            case DONE: {
                String output = this.tasks.completeTask(this.parser.getTaskNumber(input));
                this.storage.addArrayTaskToFile(this.tasks.getTasksList());
                return output;
            }
            case FIND: {
                List<Task> filteredTasks = this.tasks.searchTask(this.parser.getTaskNumber(input));
                return this.ui.displaySearchList(filteredTasks);
            }
            case TODO: {
                String task = this.parser.getTask(input, userCommand);
                Todo newTodo = this.tasks.addTodo(task);
                this.storage.addArrayTaskToFile(this.tasks.getTasksList());
                return this.ui.printTaskList(this.tasks.getTasksList(), newTodo);
            }
            case EVENT: {
                String task = this.parser.getTask(input, userCommand);
                String[] taskAndDate = this.parser.getTaskAndDate(task, userCommand);
                try {
                    Event newEvent = this.tasks.addEvent(taskAndDate[0], taskAndDate[1]);
                    this.storage.addArrayTaskToFile(this.tasks.getTasksList());
                    return this.ui.printTaskList(this.tasks.getTasksList(), newEvent);
                } catch (ArrayIndexOutOfBoundsException exception) {
                    throw new EmptyEventTimeException();
                }
            }
            case DEADLINE: {
                String task = this.parser.getTask(input, userCommand);
                String[] taskAndDate = this.parser.getTaskAndDate(task, userCommand);
                try {
                    String[] dateAndTime = taskAndDate[1].split(" ");
                    if (this.parser.isLocalDateTime(dateAndTime[0], dateAndTime[1])) {
                        LocalDate date = this.parser.convertToLocalDate(dateAndTime[0]);
                        Deadline newDeadline = this.tasks.addDeadline(taskAndDate[0], date, dateAndTime[1]);
                        this.storage.addArrayTaskToFile(this.tasks.getTasksList());
                        return this.ui.printTaskList(this.tasks.getTasksList(), newDeadline);
                    } else {
                        throw new InvalidDateTimeException();
                    }
                } catch (ArrayIndexOutOfBoundsException exception) {
                    throw new EmptyDeadlineTimeException();
                }

            }
            case DELETE: {
                String output = this.tasks.deleteTask(this.parser.getTaskNumber(input));
                this.storage.addArrayTaskToFile(this.tasks.getTasksList());
                return output;
            }
            default: {
                throw new InvalidInputException();
            }
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException();
        }
    }

    /**
     * Runs the Meow chat bot.
     */
    public void run() {
        // Greeting from chat bot.
        this.ui.greet();
        // Create a scanner to read from standard input.
        Scanner scanner = new Scanner(System.in);
        while (!this.isExit) {
            try {
                String input = scanner.nextLine();
                processCommand(input);
            } catch (MeowException exception) {
                System.out.println(exception.toString());
            }
        }
        // Clean up the scanner.
        scanner.close();
    }

    public static void main(String[] args) {
        new Meow().run();
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
