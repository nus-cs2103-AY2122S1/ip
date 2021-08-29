import java.util.ArrayList;

/**
 * This class contains the task list with operations that can
 * be used on the list.
 */
public class TaskList {
    private static ArrayList<Task> contents;

    public TaskList(ArrayList<Task> fileContents) {
        contents = fileContents;
    }

    public static void addTask(String userInput) {
        if (userInput.startsWith("deadline") || userInput.startsWith("event") ||
                userInput.startsWith("todo")) {
            Task newTask;
            if (userInput.startsWith("deadline") && userInput.contains("by")) {
                newTask = new Deadline(userInput);
            } else if (userInput.startsWith("event") && userInput.contains("at")) {
                newTask = new Event(userInput);
            } else {
                newTask = new ToDos(userInput);
            }
            contents.add(newTask);
            Ui.addedTask(newTask, contents.size());
        } else {
            Ui.invalidTask();
        }
    }

    public static ArrayList<Task> getList() {
        return contents;
    }

    public static void printList() {
        if (contents.size() == 0) {
            Ui.emptyListMessage();
        } else {
            Ui.getCurrentTasks(contents);
        }
    }

    public static void markTaskDone(String userInput) {
        String index = userInput.substring(5);
        int x = Integer.parseInt(index);
        Task temp = contents.get(x - 1);
        temp.markedDone();
        Ui.markedTask(temp);
    }

    public static void removeTask(String userInput) {
        String index = userInput.substring(7);
        int x = Integer.parseInt(index);
        Task temp = contents.get(x - 1);
        contents.remove(temp);
        Ui.removedTask(temp);
    }
}
