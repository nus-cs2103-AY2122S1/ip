import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CommandHandler {
    public static void handleList(String command, Storage storage, TaskList taskList, Ui ui) {
        ui.showTaskList(taskList);
    }

    public static void handleBye(String command, Storage storage, TaskList taskList, Ui ui) {
        ui.exit();
    }

    public static void handleDeadline(String command, Storage storage, TaskList taskList, Ui ui)
            throws LifelineException {
        String[] commands = command.split("\\s", 2);
        if (commands.length != 2) {
            throw new LifelineException("Details of task cannot be blank!");
        }
        String[] description = commands[1].split("/by", 2);
        if (description.length != 2) {
            throw new LifelineException("Deadline cannot be blank! Use deadline <name> /by <deadline>");
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(description[1].trim(), formatter);
            Task newTask = new Deadline(description[0].trim(), dateTime);
            taskList.add(newTask);
            storage.save(taskList);
        } catch (DateTimeParseException e) {
            throw new LifelineException("Deadline is not of the correct format! Please use deadline <name> /by " +
                    "<dd/MM/yy HHmm>\n");
        }
    }

    public static void handleEvent(String command, Storage storage, TaskList taskList, Ui ui) throws LifelineException {
        String[] commands = command.split("\\s", 2);
        if (commands.length != 2) {
            throw new LifelineException("Details of event cannot be blank!");
        }
        String[] description = commands[1].trim().split("/at", 2);
        if (description.length != 2) {
            throw new LifelineException("Event date/time cannot be blank! Use /at <Day> <Time>");
        }
        String[] eventDateAndDuration = description[1].trim().split("\\s", 2);
        String errorMessage = "Event date/time not in proper format! Please use event <name> /at " +
                "<dd/MM/yy> <HHmm>-<HHmm>";
        if (eventDateAndDuration.length!= 2) {
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
        } catch (DateTimeParseException e) {
            throw new LifelineException(errorMessage);
        }
    }

    public static void handleToDo(String command, Storage storage, TaskList taskList, Ui ui) throws LifelineException {
        String[] commands = command.split("\\s", 2);
        if (commands.length != 2) {
            throw new LifelineException("Details of event cannot be blank!");
        }
        Task newTask = new ToDo(commands[1].trim());
        taskList.add(newTask);
        ui.showAddedTask(newTask);
        storage.save(taskList);
    }

    public static void handleDone(String command, Storage storage, TaskList taskList, Ui ui) throws LifelineException {
        String[] commands = command.split("\\s", 2);
        if (commands.length != 2) {
            throw new LifelineException("You did not specify an integer! Please use done <number>");
        }
        try {
            int taskIndex = Integer.parseInt(commands[1]) - 1;
            if (taskIndex < 0 || taskIndex >= taskList.size()) {
                throw new LifelineException("Index is out of bounds!");
            }
            taskList.completeTask(taskIndex);
            ui.showAddedTask(taskList.get(taskIndex));
            ui.showTaskList(taskList);
            storage.save(taskList);
        } catch (NumberFormatException e) {
            throw new LifelineException("Index is not an integer! Please use done <number>");
        }
    }

    public static void handleDelete(String command, Storage storage, TaskList taskList, Ui ui)
            throws LifelineException {
        String[] commands = command.split("\\s", 2);
        if (commands.length != 2) {
            throw new LifelineException("You did not specify an integer! Please use done <number>");
        }
        try {
            int taskIndex = Integer.parseInt(commands[1]) - 1;
            if (taskIndex < 0 || taskIndex >= taskList.size()) {
                throw new LifelineException("Index is out of bounds!");
            }
            taskList.deleteTask(taskIndex);
            ui.showDeletedTask(taskList.get(taskIndex));
            ui.showTaskList(taskList);
            storage.save(taskList);
        } catch (NumberFormatException e) {
            throw new LifelineException("Index is not an integer! Please use done <number>");
        }
    }
}
