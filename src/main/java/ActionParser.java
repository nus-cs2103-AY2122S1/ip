import java.util.Scanner;

public class ActionParser {
    public enum Action {
        BYE, LIST, DONE, TODO, DEADLINE, EVENT, DELETE, UNKNOWN
    }

    public static Action getAction(String input) {
        switch (input) {
            case "bye": return Action.BYE;
            case "list": return Action.LIST;
            case "done": return Action.DONE;
            case "todo": return Action.TODO;
            case "deadline": return Action.DEADLINE;
            case "event": return Action.EVENT;
            case "delete": return Action.DELETE;
            default: return Action.UNKNOWN;
        }
    }

    public static void run(Scanner sc, TaskManagement taskManagement) {
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            int firstWordIndex = input.indexOf(" ");
            String actionText = firstWordIndex == -1 ? input : input.substring(0, firstWordIndex);
            Action action = getAction(actionText);
            String rest = input.substring(firstWordIndex + 1);
            try {
                switch (action) {
                    case BYE: {
                        CommonUtils.bye();
                        sc.close();
                        return;
                    }
                    case LIST: {
                        taskManagement.showTasks();
                        break;
                    }
                    case DONE: {
                        try {
                            int taskNumber = Integer.parseInt(rest);
                            taskManagement.markTaskAsDone(taskNumber - 1);
                            break;
                        } catch (NumberFormatException e) {
                            throw new DukeException("A number must be given to specified the task.");
                        }
                    }
                    case TODO: {
                        if (firstWordIndex == -1) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                        Todo temp = new Todo(rest);
                        taskManagement.addTask(temp);
                        CommonUtils.showAddTaskMessage(temp, taskManagement.getSize());
                        break;
                    }
                    case DEADLINE: {
                        if (firstWordIndex == -1) {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        }
                        String[] arr = rest.split("/by ");
                        if (arr.length == 1) {
                            throw new DukeException("/by is needed to specified the time.");
                        }
                        String description = arr[0];
                        String detail = arr[1];
                        Deadline temp = new Deadline(description, detail);
                        taskManagement.addTask(temp);
                        CommonUtils.showAddTaskMessage(temp, taskManagement.getSize());
                        break;
                    }
                    case EVENT: {
                        if (firstWordIndex == -1) {
                            throw new DukeException("The description of an event cannot be empty.");
                        }
                        String[] arr = rest.split("/at ");
                        if (arr.length == 1) {
                            throw new DukeException("/at is needed to specified the time.");
                        }
                        String description = arr[0];
                        String detail = arr[1];
                        Event temp = new Event(description, detail);
                        taskManagement.addTask(temp);
                        CommonUtils.showAddTaskMessage(temp, taskManagement.getSize());
                        break;
                    }
                    case DELETE: {
                        try {
                            int taskNumber = Integer.parseInt(rest);
                            Task temp = taskManagement.removeTask(taskNumber - 1);
                            CommonUtils.showRemoveTaskMessage(temp, taskManagement.getSize());
                            break;
                        } catch (NumberFormatException e) {
                            throw new DukeException("A number must be given to specified the task.");
                        }
                    }
                    case UNKNOWN:
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                CommonUtils.showMessage(e.getMessage());
            }
        }
    }
}
