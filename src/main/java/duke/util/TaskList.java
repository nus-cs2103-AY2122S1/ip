package duke.util;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyTaskListException;
import duke.exceptions.InvalidTaskException;
import duke.exceptions.NoMatchFoundException;
import duke.task.Task;


public class TaskList {
    private Ui ui = new Ui();

    /**
     * Lists all the tasks in the chatbot
     * @param commands tasks in chatbot
     */
    public String list(ArrayList<Task> commands) {
        String response = "";
        try {
            if (commands.size() == 0) {
                throw new EmptyTaskListException();
            } else {
                response = ui.listTasksOutput(commands);
            }
        } catch (DukeException e) {
            response = e.getMessage();
        }
        return response;
    }

    /**
     * Deletes a task from the list
     * @param listNumber index of task to be deleted
     * @param commands tasks in chatbot
     */
    public String remove(int listNumber, ArrayList<Task> commands) {
        String response = "";
        try {
            if (listNumber < commands.size()) {
                response = ui.removeOutput(commands.get(listNumber), commands.size() - 1);
                commands.remove(listNumber);
            } else {
                throw new InvalidTaskException();
            }
        } catch (DukeException e) {
            response = e.getMessage();
        }
        return response;
    }

    /**
     * Finds tasks related to a certain keyword
     *
     * @param keyword  keyword to look for in tasks
     * @param commands all tasks in chatbot
     */
    public String find(ArrayList<Task> commands, String... keywords) {
        String response;
        try {
            ArrayList<Task> matchingTasks = new ArrayList<>();
            boolean matchFound = false;
            for (String s : keywords) {
                for (Task command : commands) {
                    String description = command.description.trim();
                    String[] words = description.split(" ");
                    for (String word : words) {
                        if (word.equalsIgnoreCase(s)) {
                            matchingTasks.add(command);
                            matchFound = true;
                        }
                    }
                }
            }
            if (matchFound) {
                response = "Here are " + (matchingTasks.size()) + " matching tasks in your list: \n";
                for (int i = 0; i < matchingTasks.size(); i++) {
                    response += (i + 1) + ". " + matchingTasks.get(i) + "\n";
                }
            } else {
                throw new NoMatchFoundException();
            }
        } catch (DukeException e) {
            response = e.getMessage();
        }
        return response;
    }


}
