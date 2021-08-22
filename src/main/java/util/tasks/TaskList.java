package util.tasks;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task>{

    public static boolean isAdded(Task t, ArrayList<? extends Task> arr) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).equals(t)) return true;
        }
        return false;
    }


}
