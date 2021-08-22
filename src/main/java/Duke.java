import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
            run(filePath);
        } catch (FileNotFoundException e) {
            ui.printLoadingError(filePath);
        }
    }

    public void run(String filePath) {
        ui.printWelcome();
        String command = ui.readCommand();

        // Commands
        while (!command.equals("bye")) {
            try {
                if (command.equals("list")) {
                    this.tasks.printTaskList(ui);

                } else if (command.startsWith("done")) {
                    int taskNumber = parseInt(command.split(" ")[1]);
                    this.tasks.markTaskAsDone(taskNumber, this.storage, this.ui);

                } else if (command.startsWith("delete")) {
                    int taskNumber = parseInt(command.split(" ")[1]);
                    this.tasks.deleteTask(taskNumber, this.storage, this.ui);

                } else if (command.startsWith("todo")) {
                    String[] splitCommand = command.split(" ", 2);
                    if (splitCommand.length == 1) {
                        throw new DukeException("Please fill in a description for todo.");
                    }
                    String description = splitCommand[1];
                    tasks.addTodo(description, this.storage, this.ui);

                } else if (command.startsWith("deadline")) {
                    String[] splitCommand = command.split(" ", 2);
                    if (splitCommand.length == 1) {
                        throw new DukeException("Please fill in a description for deadline.");
                    }

                    String description = splitCommand[1];
                    String[] splitDescription = description.split(" /by ");
                    if (splitDescription.length == 1) {
                        throw new DukeException("Please add in /by, following by a dateline.");
                    }

                    description = splitDescription[0];
                    String deadline = splitDescription[1];
                    this.tasks.addDeadline(description, deadline, this.storage, this.ui);

                } else if (command.startsWith(("event"))) {
                    String[] splitCommand = command.split(" ", 2);
                    if (splitCommand.length == 1) {
                        throw new DukeException("Please fill in a description for event.");
                    }

                    String description = splitCommand[1];
                    String[] splitDescription = description.split(" /at ");
                    if (splitDescription.length == 1) {
                        throw new DukeException("Please add in /at, followed by the event's time.");
                    }

                    description = splitDescription[0];
                    String time = splitDescription[1];
                    tasks.addEvent(description, time, this.storage, this.ui);

                } else if (command.startsWith(("date"))) {
                    String[] splitCommand = command.split(" ", 2);
                    if (splitCommand.length == 1) {
                        throw new DukeException("Please fill in a date");
                    }

                    tasks.printTasksOnDate(splitCommand[1], this.ui);
                } else {
                    throw new DukeException("I do not understand what that means :(");
                }
            } catch (DukeException e) {
                ui.printDukeException(e);
            } catch (IndexOutOfBoundsException e) {
                ui.printIndexOutOfBoundsException();
            } catch (NumberFormatException e) {
                ui.printNumberFormatException();
            } catch (FileNotFoundException e) {
                ui.printLoadingError(filePath);
            } catch (DateTimeParseException e) {
                ui.printDateTimeParseException();
            }

            command = ui.readCommand();
        }

        ui.printGoodBye();
    }

    public static void main(String[] args) {
        new Duke("./data/task_list.txt");
    }
}
