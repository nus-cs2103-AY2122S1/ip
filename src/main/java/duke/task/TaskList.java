package duke.task;

import java.util.ArrayList;

/**
 * Represents a TaskList object. The TaskList is able to add tasks, delete tasks, mark tasks as done,
 * as well as print out all the tasks in its list.
 */
public class TaskList {
    private ArrayList<Task> list;
    private int counter = 0;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds a task to the TaskList object
     *
     * @param task
     */
    public void addTask(Task task) {
        this.list.add(task);
        counter++;
    }

    /**
     * Marks the task with the respective index as done in the TaskList object
     *
     * @param index
     */
    public void markDone(int index) {
        int trueIndex = index - 1;
        list.get(trueIndex).isDone();
        String taskDoneMessage = "    ____________________________________________________________\n"
                + "     Nice! I've marked this task as done:\n"
                + "       " + "[" + list.get(trueIndex).showType() + "]"
                + list.get(trueIndex).checkDone() + " "
                + list.get(trueIndex).showTask() + "\n"
                + "    ____________________________________________________________";
        System.out.println(taskDoneMessage);
    }

    /**
     * Deletes the task with the respective index in the TaskList object
     *
     * @param index
     */
    public void deleteTask(int index) {
        int trueIndex = index - 1;
        String listData = "[" + list.get(trueIndex).showType() + "]"
                + list.get(trueIndex).checkDone() + " "
                + list.get(trueIndex).showTask() + "\n";
        System.out.println("    ____________________________________________________________\n"
                + "     Noted. I've removed this task:\n"
                + "       " + listData
                + "     Now you have " + (list.size() - 1) + " tasks in the list\n"
                + "    ____________________________________________________________");
        list.remove(trueIndex);
    }

    /**
     * Returns the size of the TaskList object.
     *
     * @return size/length of the TaskList
     */
    public int length() {
        return list.size();
    }

    /**
     * Prints out all the tasks in the entire TaskList object.
     */
    public void showList() {
        System.out.println("    ____________________________________________________________");
        String fullList = "     " + "Here are the tasks in your list:";
        for (int i = 0; i < list.size(); i++) {
                String taskItem = "\n" + "     " + (i + 1) + "." + "["
                        + list.get(i).showType() + "]"
                        + list.get(i).checkDone() + " "
                        + list.get(i).showTask();
                fullList += taskItem;
        }
        System.out.println(fullList);
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Stores all the task type, task's done status and task name and deadline
     * in a format readable by Duke's storage component.
     *
     * @return String containing the details of all the tasks
     */
    public String refreshList() {
        String refreshList = "";
        for (int j = 0; j < list.size(); j++) {
            if (list.get(j).showType().equals("D") || list.get(j).showType().equals("E")) {
                refreshList += list.get(j).showType() + " | "
                        + (list.get(j).checkDone().equals("[X]") ? "1" : "0") + " | "
                        + list.get(j).showTaskOnly() + " | "
                        + list.get(j).showWhen()+ "\n";
            } else {
                refreshList += list.get(j).showType() + " | "
                        + (list.get(j).checkDone().equals("[X]") ? "1" : "0") + " | "
                        + list.get(j).showTask() + "\n";
            }
        }
        return refreshList;
    }

    public void searchList(String keyword) {
        System.out.println("    ____________________________________________________________");
        String searchList = "     " + "Here are the matching tasks in your list:";
        for (int k = 0; k < list.size(); k++) {
            if (list.get(k).showTaskOnly().contains(keyword)) {
                String searchItem = "\n" + "     " + (k + 1) + "." + "["
                        + list.get(k).showType() + "]"
                        + list.get(k).checkDone() + " "
                        + list.get(k).showTask();
                searchList += searchItem;
            }
        }
        System.out.println(searchList);
        System.out.println("    ____________________________________________________________");
    }

}
