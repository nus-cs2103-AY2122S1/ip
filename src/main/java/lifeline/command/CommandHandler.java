package lifeline.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import lifeline.exception.LifelineException;
import lifeline.storage.Storage;
import lifeline.task.Deadline;
import lifeline.task.Event;
import lifeline.task.Task;
import lifeline.task.TaskList;
import lifeline.task.ToDo;
import lifeline.ui.Ui;


public class CommandHandler {
    public static String handleList(String command, Storage storage, TaskList taskList, Ui ui) {
        return ui.showTaskList(taskList);
    }

    public static String handleBye(String command, Storage storage, TaskList taskList, Ui ui) {
        return ui.exit();
    }

    public static String handleDeadline(String command, Storage storage, TaskList taskList, Ui ui)
            throws LifelineException {
        String[] commands = getCommands(command);
        String[] description = commands[1].split("/by", 2);
        if (description.length != 2) {
            throw new LifelineException("Deadline is not of the correct format! Please use deadline <name> /by "
                    + "<dd/MM/yy HHmm>");
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(description[1].trim(), formatter);
            Task newTask = new Deadline(description[0].trim(), dateTime);
            taskList.add(newTask);
            storage.save(taskList);
            return ui.showAddedTask(newTask);
        } catch (DateTimeParseException e) {
            throw new LifelineException("Deadline is not of the correct format! Please use deadline <name> /by "
                    + "<dd/MM/yy HHmm>");
        }
    }

    public static String handleEvent(String command, Storage storage, TaskList taskList, Ui ui) throws LifelineException {
        String[] commands = getCommands(command);
        String[] description = commands[1].trim().split("/at", 2);
        String errorMessage = "Event date/time not in proper format! Please use event <name> /at "
                + "<dd/MM/yy> <HHmm>-<HHmm>";
        if (description.length != 2) {
            throw new LifelineException(errorMessage);
        }
        String[] eventDateAndDuration = description[1].trim().split("\\s", 2);
        if (eventDateAndDuration.length != 2) {
            throw new LifelineException(errorMessage);
        }
        String eventDate = eventDateAndDuration[0];
        String eventDuration = eventDateAndDuration[1];
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
            LocalDate date = LocalDate.parse(eventDate.trim(), dateFormatter);
            String[] duration = eventDuration.split("-", 2);
            if (duration.length != 2) {
                throw new LifelineException(errorMessage);
            }
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
            LocalTime startTime = LocalTime.parse(duration[0], timeFormatter);
            LocalTime endTime = LocalTime.parse(duration[1], timeFormatter);
            Task newTask = new Event(description[0].trim(), date, startTime, endTime);
            taskList.add(newTask);
            storage.save(taskList);
            return ui.showAddedTask(newTask);
        } catch (DateTimeParseException e) {
            throw new LifelineException(errorMessage);
        }
    }

    public static String handleToDo(String command, Storage storage, TaskList taskList, Ui ui) throws LifelineException {
        String[] commands = getCommands(command);
        Task newTask = new ToDo(commands[1].trim());
        taskList.add(newTask);
        storage.save(taskList);
        return ui.showAddedTask(newTask);
    }

    public static String handleDone(String command, Storage storage, TaskList taskList, Ui ui) throws LifelineException {
        String[] commands = getCommands(command);
        int taskIndex = convertIndexToInt(commands[0], commands[1], taskList);
        taskList.completeTask(taskIndex);
        storage.save(taskList);
        return ui.showCompletedTask(taskList.get(taskIndex));
    }

    public static String handleDelete(String command, Storage storage, TaskList taskList, Ui ui)
            throws LifelineException {
        String[] commands = getCommands(command);
        int taskIndex = convertIndexToInt(commands[0], commands[1], taskList);
        Task taskToDelete = taskList.get(taskIndex);
        taskList.deleteTask(taskIndex);
        storage.save(taskList);
        return ui.showDeletedTask(taskToDelete);
    }

    private static String[] getCommands(String command) throws LifelineException {
        String[] commands = command.split("\\s", 2);
        if (commands.length < 2) {
            handleIncomplete(commands[0]);
        }
        return commands;
    }

    private static void handleIncomplete(String command) throws LifelineException {
        switch (command) {
        case "done":
            throw new LifelineException("You did not specify an integer! Please use done <number>");
        case "delete":
            throw new LifelineException("You did not specify an integer! Please use delete <number>");
        case "todo":
            throw new LifelineException("Details of todo cannot be blank!");
        case "deadline":
            throw new LifelineException("Details of deadline cannot be blank!");
        case "event":
            throw new LifelineException("Details of event cannot be blank!");
        }
    }

    private static int convertIndexToInt(String command, String index, TaskList taskList) throws LifelineException {
        try {
            int taskIndex = Integer.parseInt(index) - 1;
            if (taskIndex > taskList.size() || taskIndex < 0) {
                throw new LifelineException("Index is out of bounds!");
            }
            return taskIndex;
        } catch (NumberFormatException e) {
            throw new LifelineException("Index is not an integer! Please use " + command + " <number>");
        }
    }
}
