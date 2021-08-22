package util.tasks;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task>{

    public boolean isAdded(Task t) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).equals(t)) return true;
        }
        return false;
    }


}
