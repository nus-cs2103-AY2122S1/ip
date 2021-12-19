package duke;

/**
 * Class to abstract the rendering of the UI of the Duke
 */
public class UI {

    /**
     * Method to return a String with all the Tasks in the Task List
     * @param taskList The List of Tasks in Duke
     * @return The String equivalent of all the Task List
     */
    public String printTaskList(TaskList taskList, boolean isSearchTaskList) throws DukeException {
        String output = "";
        if ((taskList != null) && (taskList.getTaskListLength() > 0)) {
            if (isSearchTaskList) {
                output += "These are the tasks matching the Search String:\n";
            } else {
                output += "Here are the tasks in your list:\n";
            }
            for (int i = 0; i < taskList.getTaskListLength(); i++) {
                output += (i + 1) + "." + taskList.getTask(i) + "\n";
            }
        } else {
            throw new DukeException("The Task List is Empty");
        }
        return output;
    }
}
