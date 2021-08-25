package duke.commands;

import duke.data.TaskList;

import java.util.ArrayList;

public class Find {
    public boolean isMatch(String input, String description) {
        return description.contains(input);
    }

    public TaskList findMatch(String input, TaskList taskList) {
        TaskList newList = new TaskList(new ArrayList<>());
        for (Task task : taskList.getEntire()) {
            if (isMatch(input, task.getDescription())) {
                newList.add(task);
            }
        }
        return newList;
    }
}
