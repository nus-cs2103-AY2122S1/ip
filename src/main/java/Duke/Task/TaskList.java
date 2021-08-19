package Duke.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static final String EMPTY_LIST_MESSAGE = "Hooray! You have no tasks currently.";
    private static final String CANNOT_FIND_TASK_MESSAGE = "Cannot find task with number %d.";
    private final List<Task> list = new ArrayList<>();

    public void add(Task newTask) {
        this.list.add(newTask);
    }

    public Task get(int taskIndex) throws InvalidTaskException {
        try {
            return this.list.get(taskIndex - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskException(String.format(CANNOT_FIND_TASK_MESSAGE, taskIndex));
        }
    }

    public int size() {
       return this.list.size();
    }

    @Override
    public String toString() {
        if (this.size() == 0) {
            return EMPTY_LIST_MESSAGE;
        }

        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < this.list.size(); i++) {
           builder.append(String.format("%d. %s\n", i + 1, this.list.get(i).toString()));
        }
        return builder.toString();
    }
}
