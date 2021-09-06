package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class TagCommand extends Command {
    private final boolean IS_EXIT = false;
    private String taskDescription;
    private String keyword;

    public TagCommand(String taskDescription, String keyword) {
        this.taskDescription = taskDescription;
        this.keyword = keyword;
    }

    /**
     * Lists the tasks with the matching keyword associated with the FindCommand.
     * @param tasks The TaskList associated with the current bot.
     * @param userInt The User Interface associated with the current bot.
     * @param storage The storage associated with the current bot.
     * @return The string to be printed to the GUI.
     */
    @Override
    public String execute(TaskList tasks, Ui userInt, Storage storage) {
        ArrayList<Task> taskArrList = tasks.getAllTasks();
        int listLength = taskArrList.size();

        ArrayList<Task> matches = new ArrayList<>();
        for (int i = 0; i < listLength; i++) {
            Task currTask = taskArrList.get(i);
            String description = currTask.getTaskName();
            if (description.contains(this.taskDescription)) {
                matches.add(currTask);
                currTask.tag(this.keyword);
            }
        }
        storage.save(tasks);
        if (matches.size() == 0) {
            return userInt.notifyNoMatching();
        } else {
            return userInt.notifyTaggedList(matches, this.keyword);
        }
    }

    /**
     * Returns a boolean determining whether the input should exit the bot.
     *
     * @return If the command exits the bot.
     */
    @Override
    public boolean isExit() {
        return this.IS_EXIT;
    }

    /**
     * Gets the task associated with the command.
     *
     * @return null, as no task is associated with the command.
     */
    @Override
    public Task getTask() {
        return null;
    }
}
