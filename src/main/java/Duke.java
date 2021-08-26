import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    public static void main(String[] args) {

        Parser parser = new Parser();
        UI ui = new UI(new Scanner(System.in));
        Storage storage = new Storage("./data/tasks.txt");
        TaskList tasks;

        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            ui.showException(e);
            tasks = new TaskList(new ArrayList<>());
            // TODO: Create new file when cannot load tasks from current file
        }

        ui.showWelcome();

        String userInput;
        String userCommand;
        String userArgument;

        mainLoop:
        while (true) {
            try {
                userInput = ui.getUserInput();
                userCommand = parser.getUserCommand(userInput);
                userArgument = parser.getUserArgument(userInput);

                switch (userCommand) {
                    case "list":
                        if (tasks.isEmpty()) {
                            ui.showMessage("Currently no tasks!");
                        }
                        ui.showTasks(tasks);
                        break;
                    case "bye":
                        ui.goodBye();
                        break mainLoop;
                    case "done":
                        int done = getInputNumber(userArgument);

                        if (done >= tasks.numberOfTasks()|| done < 0) {
                            ui.showMessage("Task does not exist!");
                            continue;
                        }

                        Task doneTask = tasks.getTask(done);
                        doneTask.markAsDone();
                        storage.editTaskFromFile(done, tasks);

                        ui.showMessage(String.format("I've marked this task as done:\n" +
                                "%s\n", doneTask.toString()));

                        break;
                    case ("todo"):
                    case("deadline"):
                    case("event"):
                        if (userArgument.equals("")) {
                            throw new DukeException("The description of a Task cannot be empty.");
                        }

                        if (userCommand.equals("todo")) {
                            tasks.addTask(new Todo(userArgument, false));
                        } else if (userCommand.equals("deadline")) {
                            String[] deadlineInfo = splitBetween(userArgument, "/by");
                            tasks.addTask(new Deadline(deadlineInfo[0], deadlineInfo[1], false));
                        } else {
                            String[] eventInfo = splitBetween(userArgument, "/at");
                            tasks.addTask(new Event(eventInfo[0], eventInfo[1], false));
                        }
                        addTask(tasks.getTask(tasks.numberOfTasks() - 1), storage, tasks, ui);
                        break;
                    case("delete"):
                        int delete = getInputNumber(userArgument);
                        if (delete >= tasks.numberOfTasks() || delete < 0) {
                            ui.showMessage("Task does not exist!");
                            continue;
                        }

                        Task removedTask = tasks.getTask(delete);
                        storage.deleteTaskFromFile(delete, tasks);
                        tasks.removeTask(delete);

                        ui.showMessage(String.format("I've removed this task:\n%s", removedTask.toString()));
                        ui.showMessage(String.format("Now you have %d tasks in your list.\n", tasks.numberOfTasks()));
                        break;
                    default:
                        throw new DukeException("Sorry I do not understand this directive.");
                }}
            catch (DukeException exception) {
                ui.showException(exception);
            }
        }
    }

    private static void addTask(Task newTask, Storage storage, TaskList tasks, UI ui) {
        storage.saveTaskToFile(newTask);
        ui.showMessage(String.format("Got it, I've added this task:\n %s\n", newTask.toString()));
        ui.showMessage(String.format("Now you have %d tasks in your list.\n", tasks.numberOfTasks()));
    }

    private static String[] splitBetween(String str, String separator) throws DukeException {
        StringJoiner start = new StringJoiner(" ");
        StringJoiner end = new StringJoiner(" ");

        int i = 0;
        boolean after = false;

        String[] strArray = str.split(" ");

        while (i < strArray.length) {
            String currentString = strArray[i];
            if (after) {
                end.add(currentString);
            } else if (currentString.equals(separator)) {
                after = true;
            } else {
                start.add(currentString);
            }
            i++;
        }

        if (!after) {
            throw new DukeException("Incorrect format for command.");
        }

        if (String.valueOf(end).equals("")) {
            throw new DukeException("Please specify a time for the task.");
        }

        return new String[] {String.valueOf(start), String.valueOf(end)};
    }

    private static int getInputNumber(String userInput) throws DukeException {
        try {
           return Integer.parseInt(userInput) - 1;
        } catch (NumberFormatException exception) {
            throw new DukeException("Please enter a number after the command.");
        }
    }

}
