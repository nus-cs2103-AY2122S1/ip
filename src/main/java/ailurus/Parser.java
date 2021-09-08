package ailurus;

import ailurus.task.Deadline;
import ailurus.task.Event;
import ailurus.task.TaskList;
import ailurus.task.Todo;
import ailurus.task.Task;

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
                storage.unload(tasks);
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
        String[] dataArr = data.split("[|]");
        switch (dataArr[0]) {
            case "T":
                if (dataArr.length < 3) {
                    return null;
                }
                return loadTask(new Todo(dataArr[2]), dataArr[1]);
            case "D":
                if (dataArr.length < 4) {
                    return null;
                }
                return loadTask(new Deadline(dataArr[2] + "/by " + dataArr[3]), dataArr[1]);
            case "E":
                if (dataArr.length < 4) {
                    return null;
                }
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
            String desc = task.getDescription();
            if (desc.indexOf(match) != -1) {
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
