package duke;

import java.util.ArrayList;

/**
 * CommandHandler manages all the commands the user can input.
 * It is an intermediary between the user inputs and the output UI messages.
 */
public class CommandHandler {

    protected Ui ui;
    protected TaskList taskList;

    /**
     * Constructor for the command handler.
     *
     * @param ui UI
     * @param taskList taskList
     */
    public CommandHandler(Ui ui, TaskList taskList) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Handles an event task command.
     *
     * @param eventTask The event task after the user's string was parsed.
     * @return A UI message after handling the event task.
     */
    public String handleEvent(Task eventTask) {
        assert (eventTask.getDescription() != null);

        taskList.add(eventTask);
        return ui.addTask(eventTask);
    }

    /**
     * Handles a deadline task command.
     *
     * @param deadlineTask The deadline task after the user's string was parsed.
     * @return A UI message after handling the deadline task.
     */
    public String handleDeadline(Task deadlineTask) {
        assert (deadlineTask.getDescription() != null);

        taskList.add(deadlineTask);
        return ui.addTask(deadlineTask);
    }

    /**
     * Handles a todo task command.
     *
     * @param toDoTask The todo task after the user's string was parsed.
     * @return A UI message after handling the todo task.
     */
    public String handleTodo(Task toDoTask) {
        assert (toDoTask.getDescription() != null);

        taskList.add(toDoTask);
        return ui.addTask(toDoTask);
    }

    /**
     * Handles the find command.
     *
     * @param keyword The find command which includes the keyword specified to find certain tasks.
     * @return A UI message with all the tasks that contain the keyword specified.
     */
    public String handleFind(String keyword) {
        ArrayList<Task> list = TaskList.getTaskList();
        String tasksWithKeywordList = "";
        int index = 0;
        for (Task task : list) {
            if (task.getDescription().contains(keyword)) {
                index++;
                tasksWithKeywordList = tasksWithKeywordList + (index)
                        + ". " + task.toString() + "\n";
            }
        }
        return ui.findTask(tasksWithKeywordList);
    }

    /**
     * Handles the delete command.
     *
     * @param index The index of the task to be deleted.
     * @return A UI message after deleting the task.
     */
    public String handleDelete(int index) {
        Task taskAtIndex = taskList.getTask(index);

        taskList.removeTask(index);
        return ui.deleteTask(taskAtIndex);
    }

    /**
     * Handles the done command.
     *
     * @param index The index of the task to be marked as done.
     * @return A UI message after marking the task as done.
     */
    public String handleDone(int index) {
        Task taskAtIndex = taskList.getTask(index);
        taskAtIndex.markAsDone();

        return ui.doneTask(taskAtIndex);
    }

    /**
     * Handles the tag command.
     *
     * @param indexOfTaskToBeTagged The index of the task to be tagged in the list.
     * @param tag The tag.
     * @return A UI message after tagging the task.
     */
    public String handleTag(int indexOfTaskToBeTagged, String tag) {

        Task taskAtIndex = taskList.getTask(indexOfTaskToBeTagged);
        taskAtIndex.setTag(tag);

        return ui.taggedTask(taskAtIndex);
    }

    public String help() {
        return ui.help();
    }
}
