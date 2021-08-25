import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.StringBuilder;

public class Kermit {
    private Storage storage;
    private ToDo taskList;
    private Ui ui;

    private static LocalDate formatUserDateFormat(String s) throws KermitException {
        String[] components = s.split("-");
        String day = components[0];
        String month = components[1];
        String year = components[2];
        try {
            LocalDate parsedDate = LocalDate.parse(String.join("-", year, month, day));
            return parsedDate;
        } catch (DateTimeParseException e) {
            throw new KermitException("BAH That's not a time is it?? Try writing like this DD/MM/YYYY");
        }
    }

    public Kermit(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new ToDo(storage.load());
        } catch (KermitException e) {
            ui.showLoadingError();
            taskList = new ToDo();
        }
    }

    public void run() {
        final String invalidCommandText = "I'm sorry, but I don't know what that means :-(";

        Scanner sc = new Scanner(System.in);
        String command = "";
        String flag;
        String word;

        StringBuilder descriptionBuilder = new StringBuilder();
        StringBuilder flagBuilder = new StringBuilder();

        // ArrayList of all valid commands and tasks
        String[] strCommands = {"bye", "list", "done", "deadline", "todo", "event", "delete"};
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(strCommands));
        String[] strTasks = {"deadline", "todo", "event"};
        ArrayList<String> validTasks = new ArrayList<>(Arrays.asList(strTasks));

        ui.showIntroMessage();
        while (true) {
            try {
                // Task description and flag should be separated by some /command
                String[] userInput = sc.nextLine().split("/");
                String commandString = userInput[0];
                String flagString = userInput.length > 1 ? userInput[1] : "";

                String[] commandArr = commandString.split(" ");
                String[] flagArr = flagString.split(" ");

                // first item is command
                command = commandArr[0];
                flag = flagArr[0];

                // Check if command is valid
                if (!commands.contains(command)) {
                    throw new KermitException(invalidCommandText);
                }
                String description = "";
                String flagArguments = "";

                // Clear contents of string builders
                descriptionBuilder.setLength(0);
                flagBuilder.setLength(0);

                // Get description of task and error check
                for (int i = 1; i < commandArr.length; i++) {
                    word = commandArr[i];
                    if (i != 1) {
                        descriptionBuilder.append(" ");
                    }
                    descriptionBuilder.append(word);
                }
                description = descriptionBuilder.toString();
                if (description.equals("") && validTasks.contains(command)) {
                    throw new KermitException("The description of a " + command + " cannot be empty");
                }

                // Get the flags provided for task and error check
                for (int i = 1; i < flagArr.length; i++) {
                    word = flagArr[i];
                    if (i != 1) {
                        flagBuilder.append(" ");
                    }
                    flagBuilder.append(word);
                }
                flagArguments = flagBuilder.toString();
                // flag arguments for these tasks should not be empty
                if (flagArguments.equals("")) {
                    switch (command) {
                        case "event":
                            throw new KermitException("Events should be formatted as:\nevent <description> /at <time of event>");
                        case "deadline":
                            throw new KermitException("Deadlines should be formatted as:\ndeadline <description> /by <deadline>");

                    }
                }

                int index;
                // Quit program
                switch (command) {
                    case "bye":
                        ui.showGoodbyeMessage();
                        storage.save(taskList);
                        return;
                    // List out all objects that user added to list
                    case "list":
                        ui.showListItems(taskList);
                        break;
                    // Add objects to list
                    case "done":
                        index = Integer.parseInt(description) - 1;
                        // Get task name
                        Task task = taskList.completeTask(index);
                        ui.showCompleteTaskMessage(task);
                        storage.save(taskList);
                        break;
                    // Add new todo task
                    case "todo":
                        Task newToDo = new ToDos(description);
                        taskList.add(newToDo);
                        ui.showAddTaskMessage(newToDo, taskList);
                        storage.save(taskList);
                        break;
                    // Add new deadline task
                    case "deadline":
                        Task newDeadline = new Deadline(description, formatUserDateFormat(flagArguments));
                        taskList.add(newDeadline);
                        ui.showAddTaskMessage(newDeadline, taskList);
                        storage.save(taskList);
                        break;

                    // Add new event task
                    case "event":
                        Task newEvent = new Event(description, formatUserDateFormat(flagArguments));
                        taskList.add(newEvent);
                        ui.showAddTaskMessage(newEvent, taskList);
                        storage.save(taskList);
                        break;
                    // Delete task
                    case "delete":
                        index = Integer.parseInt(description) - 1;
                        Task deletedTask = taskList.deleteTask(index);
                        ui.showDeleteTaskMessage(deletedTask, taskList);
                        storage.save(taskList);
                }
            } catch (KermitException e) {
                ui.showErrorMessage(e);
            }
        }
    }
    public static void main(String[] args){
        new Kermit("data/tasks.txt").run();
    }
}