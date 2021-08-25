package duke;

import java.util.ArrayList;

public class Tasklist {
    public static ArrayList<Task> dukeList;

    public Tasklist() {
        dukeList = new ArrayList<>();
    }

    public static void add(Task task) {
        dukeList.add(task);
    }

    public static void delete(int taskIndex) {
        dukeList.remove(taskIndex);
    }
}
