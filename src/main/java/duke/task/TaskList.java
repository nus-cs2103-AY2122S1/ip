package duke.task;

import duke.DukeDate;

import java.util.ArrayList;
import java.lang.StringBuilder;
public class TaskList {
    private ArrayList<Task> userList;
    private int listSize = 0;
    public TaskList() {
        userList = new ArrayList<Task>(100);
    }

    public int getListSize() {
        return listSize;
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

    public String markComplete(int taskId) {
        if (taskId - 1 < listSize) {
            if (userList.get(taskId - 1).completeTask()) {
                return String.format(
                        "You have completed duke.task %d. %s",
                        taskId,
                        userList.get(taskId - 1).getName()
                );
            } else {
                return "Something seems to have went terribly wrong";
            }
        } else {
            return String.format("Uh oh, seems like there is no duke.task number %d", taskId);
        }
    }

    public String deleteTask(int taskId) {
        if (taskId - 1 < listSize) {
            Task t = userList.get(taskId - 1);
            userList.remove(taskId - 1);
            listSize--;
            return String.format(
                    "Alright,\nduke.duke.Task.Task: %s [%s] [%s] (%s)\nHas been removed, you have %d tasks in the list",
                    t.getName(),
                    t.getTaskType(),
                    t.isCompleted() ? "X" : " ",
                    getTaskTime(t),
                    listSize
            );
        } else {
            return String.format("Uh oh, seems like there is no duke.task number %d", taskId);
        }
    }

    /**
     * Returns the specified duke.task in a string format used for saving in txt file.
     * Should not be exposed directly to the user.
     *
     * @param taskIndex
     * @return
     */
    public String getTaskSaveFormat(int taskIndex) {
        if (taskIndex < listSize) {
            Task t = userList.get(taskIndex);
            return t.getSaveFormat();
        } else {
            return "";
        }
    }

    private String getTaskTime(Task t) {
        String time;
        switch (t.getTaskType()) {
            case "D":
                Deadline d = (Deadline) t;
                time = "by: " + DukeDate.formatDateOutput(d.getTime());
                break;
            case "E":
                Event e = (Event) t;
                time = "at: " + DukeDate.formatDateOutput(e.getTime());
                break;
            default:
                time = "No time specified";
        }
        return time;
    }
}
