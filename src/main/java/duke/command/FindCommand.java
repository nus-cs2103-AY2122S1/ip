package duke.command;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class FindCommand extends Command {
    private String[] keywords;

    public FindCommand(String ... keywords) {
        this.keywords = keywords;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filterTaskList = keywords.length == 1
                ? filterWithSingleKeyword(tasks, keywords[0])
                : filterWithMultipleKeywords(tasks);

        return ui.showTasksFound(filterTaskList);
    }

    private TaskList filterWithMultipleKeywords(TaskList tasks) {
        TaskList filteredTasks = new TaskList();
        for (String keyword : keywords) {
            for (Task t : tasks.getTaskList()) {
                if (!filteredTasks.getTaskList().contains(t)
                        && t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                    assert (filteredTasks.getListSize() <= tasks.getListSize())
                            : "Task list found by find command should not be greater than original task list.";
                    filteredTasks.addTask(t);
                }
            }
        }
        return filteredTasks;
    }

    public TaskList filterWithSingleKeyword(TaskList tasks, String keyword) {
        List<Task> taskList = tasks
                .getTaskList()
                .stream()
                .filter((task -> containsKeyword(task, keyword)))
                .collect(Collectors.toList());
        ArrayList<Task> keywordTasks = new ArrayList<>(taskList);
        return new TaskList(keywordTasks);
    }

    public boolean containsKeyword(Task t, String keyword) {
        return t.getDescription().toLowerCase().contains(keyword.toLowerCase());
    }
}
