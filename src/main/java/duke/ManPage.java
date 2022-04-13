package duke;

import java.util.ArrayList;
import java.util.List;

public class ManPage {
    // items, completed, todo, event, deadline, delete, find, bye
    private final List<String> listOfCommands = new ArrayList<>(List.of(
            "items: Returns the list of tasks in the tasklist\n",
            "completed: Marks a specified task as complete. \n[format: completed <index>]\n",
            "todo: Adds a todo task to the task list. \n[format: todo <task name>]\n",
            "event: Adds an event task to the task list.\n[format: event <task name> \\at <yyyy-mm-dd>]\n",
            "deadline: Adds a deadline task to the task list. \n[format: deadline <task name> \\by <yyyy-mm-dd>]\n",
            "delete: Deletes a task from the task list. \n[format: delete <index>\n",
            "find: Returns all tasks with the keyword in their \ndescription. \n[format: find <keyword>]\n",
            "bye: Saves the current items in the task list to file. \n[format: bye]\n"
            ));

    public ManPage() {

    }


    @Override
    public String toString() {
        return String.join("############################\n", listOfCommands);
    }
}
