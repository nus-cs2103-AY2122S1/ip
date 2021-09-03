package duke;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Parser {
    /**
     * Parses user input and executes the appropriate commands.
     *
     * @param input The input entered by the user.
     * @throws InvalidCommandException
     * @throws MissingToDoDescriptionException
     * @throws MissingDeadlineDescriptionException
     * @throws MissingEventDescriptionException
     */
    public String parse(String input, TaskList taskList, UserInterface userInterface, Storage storage)
            throws InvalidCommandException,
            MissingToDoDescriptionException,
            MissingDeadlineDescriptionException,
            MissingEventDescriptionException {
        try {
            input = input.trim().toLowerCase();

            if (input.equals("bye")) {
                return "Bye. Hope to see you again soon!";
            } else if (input.equals("list")){
                return userInterface.showList(taskList);
            } else if (input.contains("done")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                taskList.markAsDone(index);
                storage.write();
                return userInterface.showCompletedTask(taskList.getTask(index));
            } else if (input.contains("todo") || input.contains("deadline") || input.contains("event")){
                if (input.contains("todo") && input.length() == 4) {
                    throw new MissingToDoDescriptionException();
                } else if (input.contains("deadline") && input.length() == 8) {
                    throw new MissingDeadlineDescriptionException();
                } else if (input.contains("event") && input.length() == 5) {
                    throw new MissingEventDescriptionException();
                } else {
                    Task t = taskList.addItem(input);
                    storage.write();
                    return userInterface.showAddedTask(t, taskList);
                }
            } else if (input.contains("delete")) {
                int index = Integer.parseInt(input.split(" " )[1]) - 1;
                Task t = taskList.deleteItem(index);
                storage.write();
                return userInterface.showDeletedTask(t, taskList);
            } else if (input.contains("find")) {
                String keyword = input.split(" ")[1];
                List<Task> results = taskList.find(keyword);
                return userInterface.showResults(results);
            } else {
                throw new InvalidCommandException();
            }
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
