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
        switch (command) {
            case "bye":
                storage.unload(tasks);
                Ailurus.exit();
                return ui.sayBye();
            case "list":
                return ui.sayList(tasks);
            case "done":
                String str = Parser.parseMessage(fullCommand);
                return ui.sayDone(tasks.done(str));
            case "todo":
                String todoMessage = Parser.parseMessage(fullCommand);
                return ui.sayAdd(tasks.addTask(new Todo(todoMessage)), tasks.length());
            case "deadline":
                String deadlineMessage = Parser.parseMessage(fullCommand);
                return ui.sayAdd(tasks.addTask(new Deadline(deadlineMessage)), tasks.length());
            case "event":
                String eventMessage = Parser.parseMessage(fullCommand);
                return ui.sayAdd(tasks.addTask(new Event(eventMessage)), tasks.length());
            case "delete":
                String deleteMessage = Parser.parseMessage(fullCommand);
                return ui.sayDelete(tasks.deleteTask(deleteMessage), tasks.length());
            case "find":
                String match = Parser.parseMessage(fullCommand);
                return ui.sayFind(Parser.parseFind(match, tasks));
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
        assert dataArr.length != 0 : "dataArr cannot be length 0";
        switch (dataArr[0]) {
            case "T":
                assert dataArr.length > 2 : "todo format incorrect";
                Task todo = new Todo(dataArr[2]);
                if (Integer.parseInt(dataArr[1]) == 1) {
                    todo.markAsDone();
                }
                return todo;
            case "D":
                assert dataArr.length > 3 : "deadline format incorrect";
                Task deadline = new Deadline(dataArr[2] + "/by " + dataArr[3]);
                if (Integer.parseInt(dataArr[1]) == 1) {
                    deadline.markAsDone();
                }
                return deadline;
            case "E":
                assert dataArr.length > 3 : "event format incorrect";
                Task event = new Event(dataArr[2] + "/at " + dataArr[3]);
                if (Integer.parseInt(dataArr[1]) == 1) {
                    event.markAsDone();
                }
                return event;
            default:
                return null;
        }
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
            if (desc.contains(match)) {
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
