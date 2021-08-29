import java.io.File;
import java.io.IOException;

public class Duke {
    private Parser parser;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String pathName, String fileName) {
        parser = new Parser();
        storage = new Storage(pathName, fileName);
        tasks = new TaskList();
        ui = new Ui();
    }

    public static void main(String[] args) {
        new Duke("data", "tasks.txt").run();
    }

    public void run() {
        try {
            File dataDirectory = storage.initialiseDirectory();
            File dataFile = storage.initialiseFile(dataDirectory);

            ui.greet();
            storage.loadTasksFromFile(dataFile, tasks);

            String commandLine;

            do {
                commandLine = ui.readCommand();
                Command command = parser.parseInput(commandLine);

                try {
                    switch (command.getType()) {
                    case "bye":
                        ui.exit();
                        break;
                    case "list":
                        displayTasksList(tasks);
                        break;
                    case "done":
                        markDone(command, tasks);
                        storage.saveTasksToFile(dataFile, tasks);
                        break;
                    case "delete":
                        deleteTask(command, tasks);
                        storage.saveTasksToFile(dataFile, tasks);
                        break;
                    case "todo":
                        addToDoTask(command, tasks);
                        storage.saveTasksToFile(dataFile, tasks);
                        break;
                    case "deadline":
                        addDeadlineTask(command, tasks);
                        storage.saveTasksToFile(dataFile, tasks);
                        break;
                    case "event":
                        addEventTask(command, tasks);
                        storage.saveTasksToFile(dataFile, tasks);
                        break;
                    case "print":
                        printTasksOnDate(command, tasks);
                        break;
                    default:
                        throw new DukeException("INVALID COMMAND. Please try again!");
                    }
                } catch (DukeException e) {
                    ui.displayError(e.getMessage());
                }
            } while (!commandLine.equals("bye"));

        } catch (DukeException | IOException e) {
            ui.displayError(e.getMessage());
        }
    }

    public void displayTasksList(TaskList tasks) throws DukeException {
        if (tasks.size() != 0) {
            String response = ui.tasksInYourList(tasks);
            ui.displayResponse(response);
        } else {
            throw new DukeException("There are no tasks in your list!");
        }
    }

    public void markDone(Command command, TaskList tasks) throws DukeException {
        String commandDetails = command.getDetails();

        if (commandDetails.trim().length() > 0) {
            try {
                int taskNum = Integer.parseInt(commandDetails);

                if (taskNum >= 1 && taskNum <= tasks.size()) {
                    tasks.get(taskNum - 1).setDone();
                    Task taskDone = tasks.get(taskNum - 1);

                    String response = ui.taskDoneMessage(taskDone);
                    ui.displayResponse(response);
                } else {
                    throw new DukeException("Please enter a valid task number to be marked as done!");
                }
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a valid task number to be marked as done!");
            }
        } else {
            throw new DukeException("Please enter a task number to be marked as done!");
        }
    }

    public void deleteTask(Command command, TaskList tasks) throws DukeException {
        String commandDetails = command.getDetails();

        if (commandDetails.trim().length() > 0) {
            try {
                int taskNum = Integer.parseInt(commandDetails);

                if (taskNum >= 1 && taskNum <= tasks.size()) {
                    Task taskDeleted = tasks.remove(taskNum - 1);

                    String response = ui.taskDeletedMessage(taskDeleted)
                            + System.lineSeparator() + ui.numOfTasksInList(tasks);
                    ui.displayResponse(response);
                } else {
                    throw new DukeException("Please enter a valid task number to be deleted!");
                }
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a valid task number to be deleted!");
            }
        } else {
            throw new DukeException("Please enter a task number to be deleted!");
        }
    }

    public void addToDoTask(Command command, TaskList tasks) throws DukeException {
        String commandDetails = command.getDetails();

        if (commandDetails.trim().length() > 0) {
            Task newToDoTask = new ToDo(TaskType.TODO, commandDetails);
            tasks.add(newToDoTask);

            String response = ui.taskAddedMessage(newToDoTask)
                    + System.lineSeparator() + ui.numOfTasksInList(tasks);
            ui.displayResponse(response);
        } else {
            throw new DukeException("The description of a todo cannot be empty!");
        }
    }

    public void addDeadlineTask(Command command, TaskList tasks) throws DukeException {
        String commandDetails = command.getDetails();

        if (commandDetails.trim().length() > 0) {
            String[] deadlineTaskDetails = commandDetails.split("/", 2);

            if (deadlineTaskDetails.length == 2) {
                if (deadlineTaskDetails[0].trim().length() > 0) {
                    if (deadlineTaskDetails[1].trim().startsWith("by")) {
                        String description = deadlineTaskDetails[0].trim();
                        String beforeDateTime = deadlineTaskDetails[1].trim();
                        String[] beforeDateTimeParts = beforeDateTime.split("\\s+", 2);

                        if (beforeDateTimeParts.length == 2) {
                            Task newDeadlineTask = new Deadline(TaskType.DEADLINE,
                                    description, beforeDateTimeParts[1]);
                            tasks.add(newDeadlineTask);

                            String response = ui.taskAddedMessage(newDeadlineTask)
                                    + System.lineSeparator() + ui.numOfTasksInList(tasks);
                            ui.displayResponse(response);
                        } else {
                            throw new DukeException("INCOMPLETE COMMAND"
                                    + System.lineSeparator() + "\t"
                                    + "The date/time of a deadline cannot be empty!"
                                    + System.lineSeparator() + "\t"
                                    + "[Note: Enter /by before specifying the date/time]");
                        }
                    } else {
                        throw new DukeException("WRONG COMMAND"
                                + System.lineSeparator() + "\t"
                                + "Enter /by before specifying the date!");
                    }
                } else {
                    if (deadlineTaskDetails[1].trim().startsWith("by")) {
                        String beforeDateTime = deadlineTaskDetails[1].trim();
                        String[] beforeDateTimeParts = beforeDateTime.split("\\s+", 2);

                        if (beforeDateTimeParts.length == 2) {
                            throw new DukeException("INCOMPLETE COMMAND"
                                    + System.lineSeparator() + "\t"
                                    + "The description of a deadline cannot be empty!");
                        } else {
                            throw new DukeException("INCOMPLETE COMMAND"
                                    + System.lineSeparator() + "\t"
                                    + "The description of a deadline cannot be empty!"
                                    + System.lineSeparator() + "\t"
                                    + "The date/time of a deadline cannot be empty!"
                                    + System.lineSeparator() + "\t"
                                    + "[Note: Enter /by before specifying the date/time]");
                        }
                    } else {
                        throw new DukeException("INCOMPLETE & WRONG COMMAND"
                                + System.lineSeparator() + "\t"
                                + "The description of a deadline cannot be empty!"
                                + System.lineSeparator() + "\t"
                                + "Enter /by before specifying the date/time!");
                    }
                }
            } else {
                throw new DukeException("INCOMPLETE COMMAND"
                        + System.lineSeparator() + "\t"
                        + "The date/time of a deadline cannot be empty!"
                        + System.lineSeparator() + "\t"
                        + "[Note: Enter /by before specifying the date/time]");
            }
        } else {
            throw new DukeException("INCOMPLETE COMMAND"
                    + System.lineSeparator() + "\t"
                    + "The description and date/time of a deadline cannot be empty!"
                    + System.lineSeparator() + "\t"
                    + "[Note: Enter /by before specifying the date/time]");
        }
    }

    public void addEventTask(Command command, TaskList tasks) throws DukeException {
        String commandDetails = command.getDetails();

        if (commandDetails.trim().length() > 0) {
            String[] eventTaskDetails = commandDetails.split("/", 2);

            if (eventTaskDetails.length == 2) {
                if (eventTaskDetails[0].trim().length() > 0) {
                    if (eventTaskDetails[1].trim().startsWith("at")) {
                        String description = eventTaskDetails[0].trim();
                        String startEndDateTime = eventTaskDetails[1].trim();
                        String[] startEndDateTimeParts = startEndDateTime.split("\\s+", 2);

                        if (startEndDateTimeParts.length == 2) {
                            Task newEventTask = new Event(TaskType.EVENT,
                                    description, startEndDateTimeParts[1]);
                            tasks.add(newEventTask);

                            String response = ui.taskAddedMessage(newEventTask)
                                    + System.lineSeparator() + ui.numOfTasksInList(tasks);
                            ui.displayResponse(response);
                        } else {
                            throw new DukeException("INCOMPLETE COMMAND"
                                    + System.lineSeparator() + "\t"
                                    + "The date/time of an event cannot be empty!"
                                    + System.lineSeparator() + "\t"
                                    + "[Note: Enter /at before specifying the date/time]");
                        }
                    } else {
                        throw new DukeException("WRONG COMMAND"
                                + System.lineSeparator() + "\t"
                                + "Enter /at before specifying the date!");
                    }
                } else {
                    if (eventTaskDetails[1].trim().startsWith("at")) {
                        String startEndDateTime = eventTaskDetails[1].trim();
                        String[] startEndDateTimeParts = startEndDateTime.split("\\s+", 2);

                        if (startEndDateTimeParts.length == 2) {
                            throw new DukeException("INCOMPLETE COMMAND"
                                    + System.lineSeparator() + "\t"
                                    + "The description of an event cannot be empty!");
                        } else {
                            throw new DukeException("INCOMPLETE COMMAND"
                                    + System.lineSeparator() + "\t"
                                    + "The description of an event cannot be empty!"
                                    + System.lineSeparator() + "\t"
                                    + "The date/time of an event cannot be empty!"
                                    + System.lineSeparator() + "\t"
                                    + "[Note: Enter /at before specifying the date/time]");
                        }
                    } else {
                        throw new DukeException("INCOMPLETE & WRONG COMMAND"
                                + System.lineSeparator() + "\t"
                                + "The description of an event cannot be empty!"
                                + System.lineSeparator() + "\t"
                                + "Enter /at before specifying the date/time!");
                    }
                }
            } else {
                throw new DukeException("INCOMPLETE COMMAND"
                        + System.lineSeparator() + "\t"
                        + "The date/time of an event cannot be empty!"
                        + System.lineSeparator() + "\t"
                        + "[Note: Enter /at before specifying the date/time]");
            }
        } else {
            throw new DukeException("INCOMPLETE COMMAND"
                    + System.lineSeparator() + "\t"
                    + "The description and date/time of an event cannot be empty!"
                    + System.lineSeparator() + "\t"
                    + "[Note: Enter /at before specifying the date/time]");
        }
    }

    public void printTasksOnDate(Command command, TaskList tasks) throws DukeException {
        if (tasks.size() != 0) {
            String commandDetails = command.getDetails();

            if (commandDetails.trim().length() > 0) {
                if (commandDetails.trim().startsWith("/on")) {
                    String[] specificDateParts = commandDetails.split("\\s+", 2);

                    if (specificDateParts.length == 2) {
                        String response = ui.tasksOnDate(specificDateParts[1], tasks);
                        ui.displayResponse(response);
                    } else {
                        throw new DukeException("INCOMPLETE COMMAND"
                                + System.lineSeparator() + "\t"
                                + "The date is not specified!"
                                + System.lineSeparator() + "\t"
                                + "[Note: Enter /on before specifying the date]");
                    }
                } else {
                    throw new DukeException("WRONG COMMAND"
                            + System.lineSeparator() + "\t"
                            + "Enter /on before specifying the date!");
                }
            } else {
                throw new DukeException("INCOMPLETE COMMAND"
                        + System.lineSeparator() + "\t"
                        + "Enter /on before specifying the date!");
            }
        } else {
            throw new DukeException("There are no tasks in your list!");
        }
    }
}
