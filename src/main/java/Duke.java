import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Duke {

    private UI ui;

    public Duke() {
        ui = new UI();
        Scanner scanner = new Scanner(System.in);
        try {
            List<Task> taskList = TextFile.readFromTextFile();
            run(scanner, taskList);
        } catch (FileNotFoundException e) {
            ui.printLoadingError();
        }
    }

    public void run(Scanner scanner, List<Task> taskList) {
        ui.printWelcome();
        String command = ui.readCommand();

        // Commands
        while (!command.equals("bye")) {
            try {
                if (command.equals("list")) {
                    if (taskList.size() == 0) {
                        ui.printNoTaskAvailable();
                    } else {
                        ui.printTaskList(taskList);
                    }

                } else if (command.startsWith("done")) {
                    int taskNumber = parseInt(command.split(" ")[1]);
                    Task task = taskList.get(taskNumber - 1);
                    task.markAsDone();
                    TextFile.writeToTextFile(taskList);
                    ui.printTaskMarkedDone(task);

                } else if (command.startsWith("delete")) {
                    int taskNumber = parseInt(command.split(" ")[1]);
                    Task task = taskList.get(taskNumber - 1);
                    taskList.remove(taskNumber - 1);
                    TextFile.writeToTextFile(taskList);
                    ui.printDeleteTask(task);

                } else if (command.startsWith("todo")) {
                    String[] splitCommand = command.split(" ", 2);
                    if (splitCommand.length == 1) {
                        throw new DukeException("Please fill in a description for todo.");
                    }
                    String description = splitCommand[1];
                    Task task = new Todo(description);
                    taskList.add(task);
                    TextFile.writeToTextFile(taskList);
                    ui.printAddTask(task);

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
                    Task task = new Deadline(description, deadline);
                    taskList.add(task);
                    TextFile.writeToTextFile(taskList);
                    ui.printAddTask(task);

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
                    Task task = new Event(description, time);
                    taskList.add(task);
                    TextFile.writeToTextFile(taskList);
                    ui.printAddTask(task);

                } else if (command.startsWith(("date"))) {
                    String[] splitCommand = command.split(" ", 2);
                    if (splitCommand.length == 1) {
                        throw new DukeException("Please fill in a date");
                    }

                    LocalDate date = LocalDate.parse(splitCommand[1]);
                    ui.printTaskOnDate(taskList, date);

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
                ui.printLoadingError();
            } catch (DateTimeParseException e) {
                ui.printDateTimeParseException();
            }

            command = ui.readCommand();
        }

        ui.printGoodBye();
    }

    public static void main(String[] args) {
        new Duke();
    }
}
