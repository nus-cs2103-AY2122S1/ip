package duke.commands;

import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * The FindCommand class is a Command that returns the tasks which contain the user's String input
 */
public class FindCommand extends Command {

    String word;

    /**
     * Public constructor which is used to initialise the input word
     *
     * @param word input word which is checked against the list
     */
    public FindCommand(String word) {
        this.word = word;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Checks whether a word is present in any of the tasks in the taskList and if a task contains the word, it
     * appends it to a temporary list
     *
     * @param taskList the list of tasks upon which the operations need to be performed
     * @param ui the ui in which the result message of the command execution is displayed to the user
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        TaskList list = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.getDescription().contains(word)) {
                list.add(task);
            }
        }
        ui.displayFind(word, list);
    }
}
