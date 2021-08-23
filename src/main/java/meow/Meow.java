package meow;

import java.time.LocalDate;
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
        TODO,
        EVENT,
        DEADLINE
    }

    /**
     * A public constructor to initialize a Meow object.
     */
    public Meow(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        if (this.storage.canReadFromFile()) {
            // Read the file and convert the content to the taskList array
            this.tasks = new TaskList(this.storage.getStorageTasksList());
        } else {
            this.tasks = new TaskList();
        }
    }

    private void processCommand(String input) throws MeowException {
        try {
            Command userCommand = this.parser.checkCommand(input);
            switch (userCommand) {
            case BYE: {
                this.ui.exit();
                isExit = true;
                break;
            }
            case LIST: {
                this.ui.displayList(this.tasks.getTasksList());
                break;
            }
            case DONE: {
                this.tasks.completeTask(this.parser.getTaskNumber(input));
                this.storage.addArrayTaskToFile(this.tasks.getTasksList());
                break;
            }
            case TODO: {
                String task = this.parser.getTask(input, userCommand);
                Todo newTodo = this.tasks.addTodo(task);
                this.storage.addArrayTaskToFile(this.tasks.getTasksList());
                this.ui.printTaskList(this.tasks.getTasksList(), newTodo);
                break;
            }
            case EVENT: {
                String task = this.parser.getTask(input, userCommand);
                String[] taskAndDate = this.parser.getTaskAndDate(task, userCommand);
                try {
                    Event newEvent = this.tasks.addEvent(taskAndDate[0], taskAndDate[1]);
                    this.storage.addArrayTaskToFile(this.tasks.getTasksList());
                    this.ui.printTaskList(this.tasks.getTasksList(), newEvent);
                    break;
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
                        this.ui.printTaskList(this.tasks.getTasksList(), newDeadline);
                    } else {
                        throw new InvalidDateTimeException();
                    }
                    break;
                } catch (ArrayIndexOutOfBoundsException exception) {
                    throw new EmptyDeadlineTimeException();
                }

            }
            case DELETE: {
                this.tasks.deleteTask(this.parser.getTaskNumber(input));
                this.storage.addArrayTaskToFile(this.tasks.getTasksList());
                break;
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

}
