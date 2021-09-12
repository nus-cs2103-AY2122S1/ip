package janet;

import java.io.IOException;

import javafx.application.Platform;

/**
 * Main class of the Janet chat-bot. When the main class is run, it creates an
 * instance of Janet which allows text-based user interaction.
 */
public class Janet {
    private final Storage storage;
    private TaskList tasks = new TaskList();

    /**
     * Class constructor for Janet.
     */
    public Janet() {
        storage = new Storage("janet.txt");
        try {
            tasks = storage.readSave();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    /**
     * Returns a command that represents the user's input in the chat box.
     *
     * @param input String entered by the user into the chat box
     * @return Command that Janet understands
     */
    Command getCommandFromInput(String input) {
        try {
            return Parser.parseUserInput(input);
        } catch (JanetException e) {
            return new Command("echo", e.toString());
        }
    }

    /**
     * Returns the string to be shown in the chat as Janet's reply to user commands.
     *
     * @param command Command representing user's input
     * @return String representing Janet's reply
     * @throws IOException When unable to write the save
     */
    String getResponseFromCommand(Command command) throws IOException {
        assert(command.getOperation() != null);
        switch (command.getOperation()) {
        case "bye":
            Platform.exit();
            return "";
        case "list":
            return tasks.toString();
        case "done":
            return handleTaskDone(command);
        case "todo":
            return handleNewToDo(command);
        case "deadline":
            return handleNewDeadline(command);
        case "event":
            return handleNewEvent(command);
        case "delete":
            return handleDelete(command);
        case "find":
            return handleFind(command);
        case "schedule":
            return handleSchedule(command);
        case "echo":
            return command.getDescription();
        default:
            return "";
        }
    }

    private String handleTaskDone(Command command) throws IOException {
        int taskIndex = command.getIndex() - 1;
        System.out.println(tasks.size());
        if (taskIndex >= tasks.size()) {
            return Ui.formatOutOfBoundsString(command.getIndex());
        }
        Task task = tasks.get(taskIndex);
        task.setDone(true);
        storage.writeSave(tasks);
        return Ui.formatTaskDoneString(task);
    }

    private String handleNewToDo(Command command) throws IOException {
        ToDo task = new ToDo(command.getDescription());
        tasks.add(task);
        storage.writeSave(tasks);
        return Ui.formatTaskAddedString(task, tasks.size());
    }

    private String handleNewDeadline(Command command) throws IOException {
        Deadline task = new Deadline(command.getDescription(), command.getTime());
        tasks.add(task);
        storage.writeSave(tasks);
        return Ui.formatTaskAddedString(task, tasks.size());
    }

    private String handleNewEvent(Command command) throws IOException {
        Event task = new Event(command.getDescription(), command.getTime());
        tasks.add(task);
        storage.writeSave(tasks);
        return Ui.formatTaskAddedString(task, tasks.size());
    }

    private String handleDelete(Command command) throws IOException {
        Task task;
        try {
            task = tasks.get(command.getIndex() - 1);
        } catch (IndexOutOfBoundsException e) {
            return Ui.formatOutOfBoundsString(command.getIndex());
        }
        tasks.delete(command.getIndex() - 1);
        storage.writeSave(tasks);
        return Ui.formatTaskDeletedString(task, tasks.size());
    }

    private String handleFind(Command command) {
        TaskList filteredTasks = tasks.find(command.getDescription());
        return filteredTasks.toString();
    }

    private String handleSchedule(Command command) {
        TaskList filteredTasks = tasks.findByDate(command.getDescription());
        return filteredTasks.toString();
    }
}
