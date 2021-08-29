package duke.data;

import duke.io.Command;
import duke.tasks.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    List<Task> taskData = null;

    public TaskList() {
        this.taskData = new ArrayList<>();
    }

    public TaskList(List<Task> taskData) {
        this.taskData = taskData;
    }

    public void add(Task task) {
        this.taskData.add(task);
    }

    public Task remove(int index) {
        return this.taskData.remove(index);
    }

    public Task get(int index) {
        return this.taskData.get(index);
    }

    public boolean isEmpty() {
        return this.taskData.isEmpty();
    }

    public int size() {
        return this.taskData.size();
    }

    public TaskList filterDate(LocalDate queryDate) {
        return new TaskList(this.taskData.stream().filter(x -> x.hasDueDate(queryDate))
                .collect(Collectors.toList()));
    }

    /**
     * Contains a new TaskList containing Tasks whose description contains the given keyword/String
     *
     * @param keyword A String to be used to filter Tasks with descriptions
     * @return A new TaskList of smaller/equal size
     */
    public TaskList containsKeyword(String keyword) {
        return new TaskList(this.taskData.stream().filter(x -> x.containsKeyword(keyword))
                .collect(Collectors.toList()));
    }

    @Override
    public boolean equals(Object otherObj) {
        if (!(otherObj instanceof TaskList)) {
            return false;
        } else {
            final TaskList otherTaskList = (TaskList) otherObj;

            if (this.taskData == null && otherTaskList.taskData == null) {
                return true;
            } else if (this.taskData == null || otherTaskList.taskData == null) {
                return false;
            } else {
                return this.taskData.equals(otherTaskList.taskData);
            }
        }
    }
}
