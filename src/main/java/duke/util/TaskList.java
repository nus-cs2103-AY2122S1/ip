package duke.util;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyTaskListException;
import duke.exceptions.InvalidTaskException;
import duke.exceptions.NoMatchFoundException;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    Ui ui = new Ui();

    public void list(ArrayList<Task> commands) {
        try {
            if (commands.size() == 0) {
                throw new EmptyTaskListException();
            } else {
                ui.listTasksOutput(commands);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

    }

    public void remove(int listNumber, ArrayList<Task> commands) {
        try {
            if (listNumber < commands.size()) {
                ui.removeOutput(commands.get(listNumber), commands.size() - 1);
                commands.remove(listNumber);
            } else {
                throw new InvalidTaskException();
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Finds tasks related to a certain keyword
     *
     * @param keyword  keyword to look for in tasks
     * @param commands all tasks in chatbot
     */
    public void find(String keyword, ArrayList<Task> commands) {
        try {
            ArrayList<Task> matchingTasks = new ArrayList<>();
            boolean matchFound = false;
            for (Task command : commands) {
                String description = command.description.trim();
                String[] words = description.split(" ");
                for (String word : words) {
                    if (word.equalsIgnoreCase(keyword)) {
                        matchingTasks.add(command);
                        matchFound = true;
                    }
                }
            }
            if (matchFound) {
                System.out.println("Here are " + (matchingTasks.size()) + " matching tasks in your list:");
                for (int i = 0; i < matchingTasks.size(); i++) {
                    System.out.println((i + 1) + ". " + matchingTasks.get(i));
                }
            } else {
                throw new NoMatchFoundException();
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }


}
