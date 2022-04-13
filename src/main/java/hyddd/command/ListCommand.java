package hyddd.command;

import hyddd.task.TaskList;

/**
 * @@author Hang Zelin
 *
 * ListCommand will handle the situation when a user wants to see all the tasks in the list.
 */
public class ListCommand extends Command {
    private final TaskList taskList;

    /**
     * Constructor for ListCommand class.
     *
     * @param taskList TaskList in hyddd.
     */
    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns hyddd's response when user wants to see all tasks in list.
     *
     * @return hyddd's response.
     */
    @Override
    public String returnResponse() {
        return printList();
    }

    private String printList() {
        return taskList.printListUi();
    }
}
