import java.util.ArrayList;
import java.lang.StringBuilder;
public class TaskList {
    private ArrayList<Task> userList;
    private int listSize = 0;
    public TaskList() {
        userList = new ArrayList<Task>(100);
    }

    public String getTasks() {
        if (listSize == 0) {
            return "The list is empty! *quack*";
        }
        StringBuilder sb = new StringBuilder();
        Task t;
        String time;
        for (int i = 1; i<=this.listSize; i++) {
            t = userList.get(i - 1);
            time = getTaskTime(t);
            sb.append(
                    String.format(
                            "%d. %s  [%s] [%s] (%s)\n",
                            i,
                            t.getName(),
                            t.getTaskType(),
                            t.isCompleted() ? "X" : " ",
                            time
                    )
            );
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    public String addTask(Task task) {
        userList.add(task);
        listSize++;
        String time = getTaskTime(task);
        return String.format(
                "added: %s [%s] (%s)\nCurrently %d tasks in the list",
                task.getName(),
                task.getTaskType(),
                time,
                listSize
        );
    }

    public String markComplete(int taskID) {
        if (taskID - 1 < listSize) {
            if (userList.get(taskID - 1).completeTask()) {
                return String.format(
                        "You have completed task %d. %s",
                        taskID,
                        userList.get(taskID - 1).getName()
                );
            } else {
                return "Something seems to have went terribly wrong";
            }
        } else {
            return String.format("Uh oh, seems like there is no task number %d", taskID);
        }
    }

    private String getTaskTime(Task t) {
        String time;
        switch (t.getTaskType()) {
            case "D":
                Deadline d = (Deadline) t;
                time = "by: " + d.getTime();
                break;
            case "E":
                Event e = (Event) t;
                time = "at: " + e.getTime();
                break;
            default:
                time = "No time specified";
        }
        return time;
    }
}
