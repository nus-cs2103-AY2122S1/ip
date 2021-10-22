package ashy.util;

import java.util.ArrayList;

import ashy.exceptions.AshyException;
import ashy.exceptions.EmptyTaskListException;
import ashy.exceptions.InvalidTaskException;
import ashy.exceptions.NoMatchFoundException;
import ashy.task.Task;


public class TaskList {
    private Ui ui = new Ui();
    private Storage storage = new Storage();

    /**
     * Lists all the tasks in the chatbot
     * @param commands tasks in chatbot
     */
    public String list(ArrayList<Task> commands) {
        String response;
        try {
            if (commands.size() == 0) {
                throw new EmptyTaskListException();
            }
            response = ui.listTasksOutput(commands);
        } catch (AshyException e) {
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
        String response;
        assert (listNumber <= commands.size());
        try {
            if (listNumber >= commands.size()) {
                throw new InvalidTaskException();
            }
            response = ui.removeOutput(commands.get(listNumber), commands.size() - 1);
            commands.remove(listNumber);
        } catch (AshyException e) {
            response = e.getMessage();
        }
        storage.saveCommands(commands);
        return response;
    }
    /**
     * Finds tasks related to a certain keyword
     *
     * @param keywords  keyword to look for in tasks
     * @param commands all tasks in chatbot
     */
    public String find(ArrayList<Task> commands, String... keywords) {
        String response;
        assert(keywords != null);
        try {
            ArrayList<Task> matchingTasks = new ArrayList<>();
            boolean matchFound = false;
            for (String s : keywords) {
                for (Task command : commands) {
                    String description = command.getDescription().trim();
                    String[] words = description.split(" ");
                    for (String word : words) {
                        if (word.equalsIgnoreCase(s)) {
                            matchingTasks.add(command);
                            matchFound = true;
                        }
                    }
                }
            }
            if (!matchFound) {
                throw new NoMatchFoundException();
            }
            response = ui.findOutput(matchingTasks, matchingTasks.size());
        } catch (AshyException e) {
            response = e.getMessage();
        }
        storage.saveCommands(commands);
        return response;
    }
    /**
     * Updates the description of a given task
     * @param commands
     * @param updatedDescription
     * @param listNumber
     * @return
     */
    public String updateDescription(ArrayList<Task> commands, String updatedDescription, int listNumber) {
        String response;
        try {
            if (listNumber >= commands.size()) {
                throw new InvalidTaskException();
            }
            commands.get(listNumber).updateDescription(updatedDescription);
            response = ui.updateOutput(commands.get(listNumber));
        } catch (AshyException e) {
            response = e.getMessage();
        }
        return response;
    }


}
