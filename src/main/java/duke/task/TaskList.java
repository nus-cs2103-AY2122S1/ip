package duke.task;

import duke.main.DukeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t \t " + task);
        System.out.println(taskSummary());
    }

    public String taskSummary() {
        int numTasks = this.taskList.size();
        String size = numTasks == 0 ? "no" : String.valueOf(numTasks);
        String maybePlural = numTasks == 1 ? " task " : " tasks ";
        return "\t You have " + size + maybePlural + "in the list.\n";
    }

    public void deleteTask(Task task) {
        this.taskList.remove(task);
        System.out.println("\t Got it. I've removed this task:");
        System.out.println("\t \t " + task);
        System.out.println(taskSummary());
    }

    /**
     * Finds tasks that match any keyword.
     *
     * @param query for matching Tasks.
     * @return List of matching Tasks.
     */
    public List<Task> find(String query) {
        if (query.isEmpty()) {
            throw new DukeException("\tYou haven't specified any keywords\n");
        }
        String[] keywords = query.split(" ");
        List<Task> matches = new ArrayList<>();
        for (Task task : taskList) {
            if (task.containsKeyword(keywords)) {
                matches.add(task);
            }
        }
        return matches;
    }

    public List<String> formatForStorage() {
        return taskList.stream().map(Task::storageString).collect(Collectors.toList());
    }

    public boolean isEmpty() {
        return this.taskList.size() == 0;
    }

    /**
     * Enumerates the list of tasks.
     *
     * @param taskList to enumerate
     * @return String enumeration of taskList.
     */
    public static String enumerateTasks(List<Task> taskList) {
        String toPrint = "";
        for (int i = 0; i < taskList.size(); i++) {
            int index = i + 1;
            toPrint += ("\t " + index + ". " + taskList.get(i) + "\n");
        }
        return toPrint;
    }

    @Override
    public String toString() {
        return enumerateTasks(taskList);
    }

    public void clearTasks() {
        taskList.clear();
    }

    public Task getTask(int taskNum) {
        if (taskNum > this.taskList.size() || taskNum < 1) {
            return null;
        } else {
            return this.taskList.get(taskNum - 1);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaskList taskList1 = (TaskList) o;
        return taskList.equals(taskList1.taskList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskList);
    }
}
