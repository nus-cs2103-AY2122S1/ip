package ailurus;

import ailurus.task.Deadline;
import ailurus.task.Event;
import ailurus.task.Task;
import ailurus.task.TaskList;
import ailurus.task.Todo;


public class Parser {

    /**
     * Parse message and return command
     *
     * @param message message to be parsed
     * @return command that is passed
     */
    public static String parse(String message) {
        return message.split(" ")[0];
    }

    /**
     * Parses command and returns the UI message to be sent.
     *
     * @param command command to be used
     * @param fullCommand full command to be parsed
     * @param ui ui object used
     * @param storage storage object used
     * @param tasks tasks list used
     * @return UI message for the command.
     */
    public static String parseCommand(String command, String fullCommand, Ui ui, Storage storage, TaskList tasks) {
        String message = Parser.parseMessage(fullCommand);
        switch (command) {
        case "list":
            return ui.sayList(tasks);
        case "done":
            return ui.sayDone(tasks.done(message));
        case "todo":
            return ui.sayAdd(tasks.addTask(new Todo(message)), tasks.length());
        case "deadline":
            return ui.sayAdd(tasks.addTask(new Deadline(message)), tasks.length());
        case "event":
            return ui.sayAdd(tasks.addTask(new Event(message)), tasks.length());
        case "delete":
            return ui.sayDelete(tasks.deleteTask(message), tasks.length());
        case "find":
            return ui.sayFind(Parser.parseFind(message, tasks));
        case "bye":
            Ailurus.exit();
            return ui.sayBye();
        default:
            return ui.sayInvalidCommand();
        }
    }

    /**
     * Parse message and return string after command
     *
     * @param message message to be parsed
     * @return message string that is after command
     */
    public static String parseMessage(String message) {
        int index = message.indexOf(" ");
        if (index > 0) {
            return message.substring(index + 1);
        } else {
            return "";
        }
    }

    /**
     * Parse data stored in hard disk by line
     *
     * @param data line of data to be parsed
     * @return task that is parsed from line
     */
    public static Task parseData(String data) {
        String[] dataArr = data.split("☺");
        assert dataArr.length != 0 : "dataArr cannot be length 0";
        switch (dataArr[0]) {
        case "T":
            assert dataArr.length > 2 : "todo format incorrect";
            return loadTask(new Todo(dataArr[2]), dataArr[1]);
        case "D":
            assert dataArr.length > 3 : "deadline format incorrect";
            return loadTask(new Deadline(dataArr[2] + "/by " + dataArr[3]), dataArr[1]);
        case "E":
            assert dataArr.length > 3 : "event format incorrect";
            return loadTask(new Event(dataArr[2] + "/at " + dataArr[3]), dataArr[1]);
        default:
            return null;
        }
    }

    /**
     * Load task with marking as done
     * @param task task to be loaded
     * @param marked string for whether task is marked
     * @return task that is either marked or not marked as done
     */
    private static Task loadTask(Task task, String marked) {
        if (Integer.parseInt(marked) == 1) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Parse matching string and return Tasklist of matching string
     *
     * @param match matching string
     * @param tasks list of tasks to find from
     * @return TaskList matching string
     */
    public static TaskList parseFind(String match, TaskList tasks) throws AilurusException {
        TaskList newList = new TaskList();
        for (int i = 0; i < tasks.length(); i++) {
            Task task = tasks.getTask(i);
            String desc = task.getDescription().toUpperCase();
            if (desc.contains(match.toUpperCase())) {
                newList.addTask(task);
            }
        }
        if (newList.length() != 0) {
            return newList;
        } else {
            throw new AilurusException(AilurusException.Error.FIND);
        }
    }

}
