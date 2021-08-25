package ailurus;

import ailurus.task.*;

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
                Task todo = new Todo(dataArr[2]);
                if (Integer.parseInt(dataArr[1]) == 1) {
                    todo.markAsDone();
                }
                return todo;
            case "D":
                if (dataArr.length < 4) {
                    return null;
                }
                Task deadline = new Deadline(dataArr[2] + "/by " + dataArr[3]);
                if (Integer.parseInt(dataArr[1]) == 1) {
                    deadline.markAsDone();
                }
                return deadline;
            case "E":
                if (dataArr.length < 4) {
                    return null;
                }
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
