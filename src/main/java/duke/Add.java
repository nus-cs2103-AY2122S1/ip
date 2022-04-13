package duke;

import java.io.IOException;

/**
 * Adds task to TaskList.
 */
public class Add extends Command {

    /** The name of the input command by the user. */
    private String name;

    public Add(String name) {
        this.name = name;
    }

    /**
     * Runs the add command.
     *
     * @param tasks TaskList containing all tasks.
     * @param ui Ui to display to the user.
     * @param storage Storage to store tasks.
     * @return String to be displayed.
     */
    public String run(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (name.contains("todo ")) {
                String taskContent = name.replace("todo ", "").trim();
                if (taskContent.equals("")) {
                    throw new TodoException("sorry your todo is empty");
                }
                if (tasks.hasDuplicate(taskContent, "T")) {
                    return ui.showDuplicate();
                }
                Task t = new Task(taskContent, Duke.Category.TODO);
                tasks.addTaskToList(t);
                try {
                    storage.writeToFile(tasks.stringifyWholeList());
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
                return ui.showAdd(t.toString());
            } else if (name.contains("deadline ")) {
                String taskContent = name.replace("deadline ", "").trim();
                if (tasks.hasDuplicate(taskContent, "D")) {
                    return ui.showDuplicate();
                }
                Task t = new Task(taskContent, Duke.Category.DEADLINE);
                tasks.addTaskToList(t);
                try {
                    storage.writeToFile(tasks.stringifyWholeList());
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
                return ui.showAdd(t.toString());
            } else if (name.contains("event ")) {
                String taskContent = name.replace("event ", "").trim();
                if (tasks.hasDuplicate(taskContent, "E")) {
                    return ui.showDuplicate();
                }
                Task t = new Task(taskContent, Duke.Category.EVENT);
                tasks.addTaskToList(t);
                try {
                    storage.writeToFile(tasks.stringifyWholeList());
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
                return ui.showAdd(t.toString());
            }
        } catch (DukeException e) {
            return ui.showException(e);
        }
        return "";
    }
}
