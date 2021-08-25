package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<TaskItem> arrayList;

    public TaskList() {
        this.arrayList = new ArrayList<>();
    }

    public TaskList(ArrayList<TaskItem> arrayList) {
        this.arrayList = arrayList;
    }

    public void add(TaskItem task) {
        arrayList.add(task);
    }

    public int size() {
        return arrayList.size();
    }

    public TaskItem get(int index) {
        return arrayList.get(index);
    }

    public void remove(int index) {
        this.arrayList.remove(index);

    }

    /**
     * finds tasks that contain a given keyword.
     * @param keyword if a task contains this, it will be printed to the user.
     * @return the TaskList containing tasks matching the keyword, if any.
     */
    public TaskList findTask(String keyword) {
        ArrayList<TaskItem> copyOfTaskList = new ArrayList<TaskItem>(arrayList);
        //System.out.println(copyOfTaskList.toString());
        copyOfTaskList.removeIf(task -> {
            String taskDescription = task.describeTaskItem();
            String[] splicedTaskDescription = taskDescription.split(" ");
            for (String s: splicedTaskDescription) {
                if (s.equals(keyword)) {
                    return false;
                }
            }
            return true;
        });
        return new TaskList(copyOfTaskList);
    }


}
