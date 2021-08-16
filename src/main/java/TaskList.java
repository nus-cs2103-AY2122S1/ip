import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void printSize() {
        System.out.println(tasks.size() == 1
                ? "There is 1 task in your list"
                : "There are " + tasks.size() + " tasks in your list");

    }

    public void add(Matcher input, TaskType type) {
        Optional.ofNullable(
                TaskType.getTask(input, type))
                    .map(tasks::add);
    }

    public void toggleDone(int index) {
        boolean result = tasks.get(index - 1).toggleDone();
        System.out.println(result
                ? Display.OUTPUT_DISPLAY + "すごい! Duke-さん marked this task as done! (ﾉ◕ヮ◕)ﾉ*:･ﾟ✧"
                : Display.OUTPUT_DISPLAY + "Duke-さん marked this task as not done!");
        System.out.println(Display.OUTPUT_SPACES + tasks.get(index - 1));
    }

    public void displayList() {
        if (tasks.size() == 0) {
            System.out.println(Display.OUTPUT_DISPLAY + "There is nothing to display! :angery:");
        } else {
            System.out.println(Display.OUTPUT_DISPLAY + "Displaying List:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(Display.OUTPUT_SPACES + (i + 1) + "." + tasks.get(i));
            }
        }
    }
}
