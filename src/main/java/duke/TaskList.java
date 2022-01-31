package duke;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> arrayList;
    private final DataFile dataFile;

    public TaskList(){
        this.arrayList = new ArrayList<>();
        this.dataFile = null;
    }

    public TaskList(DataFile dataFile) {
        this.dataFile = dataFile;
        this.arrayList= dataFile.parseTasks();

    }

    /**
     * Adds a Task to this TaskList.
     *
     * @param task The Task to be added to this TaskList.
     * @return The String message to be displayed on the UI to the user.
     */
    public String add(Task task) {
        this.arrayList.add(task);
        String type = "";
        switch (task.getType()) {
            case TODO:
                type = "ToDo";
                break;
            case DEADLINE:
                type = "DeadLine";
                break;
            case EVENT:
                type = "Event";
                break;
            default:
                assert false;
        }
        return "Added this " + type + " task:\n" + task.toString();
    }

    public String list() {
        int i = 1;
        String returnString = "";
        for (Task t : this.arrayList) {
            returnString += String.valueOf(i) + ". " + t.toString() + "\n";
            i++;
        }
        return returnString;
    }

    public String markDone(int index) {
        Task t = this.arrayList.get(index - 1);
        boolean isValid = t.markDone();
        if (isValid) {
            return "Nice! This task is marked as done\n" + t.toString();
        } else {
            return "Already Done!" + t.toString();
        }
    }
    public String delete(int index) {
        Task t = this.arrayList.get(index - 1);
        this.arrayList.remove(index - 1);
        return "Noted. Removed this task:\n" + t.toString();
    }

    public void save() {
        if (this.dataFile != null) {
            this.dataFile.saveToDisk(this.list());
        }
    }

    public String findTask(String searchString) {
        String result = "";
        for (Task t : this.arrayList) {
            if (t.getDescription().contains(searchString)) {
                result += t.toString() + "\n";
            }
        }
        return result;
    }

}



