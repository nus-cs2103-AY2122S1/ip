package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


/**
 * The TaskList class is in charge of executing the users command on the saved record, as instructed by the parser.
 */
public class TaskList {
    private Storage storage;

    /**
     * The constructor for a TaskList Object.
     */
    public TaskList(Storage storage) {
        this.storage = storage;
    }

    /**
     * Adds the user's input to the saved record only if the user's input is of a specific form.
     *
     * @param userInput        input from the user.
     * @param userInputRecords the saved record.
     * @return the response on whether a task is successfully added.
     */
    public String add(String userInput, ArrayList<Task> userInputRecords) {
        Task task;
        if (userInput.startsWith("todo ")) {
            int lengthOfDeadlineString = 5;
            String description = userInput.substring(lengthOfDeadlineString);
            if (description.trim().isEmpty()) {
                return "OOPS!!! The description of a todo cannot be empty.\n";
            }
            task = new ToDo(description);
        } else if (userInput.startsWith("deadline ")) {
            try {
                int lengthOfDeadlineString = 9;
                int byPosition = userInput.lastIndexOf("/by");
                String description = userInput.substring(lengthOfDeadlineString, byPosition);
                LocalDate deadline = LocalDate.parse(userInput.substring(byPosition + 4));
                task = new Deadline(description, deadline);
            } catch (DateTimeParseException e) {
                return "Please enter a valid date in the format:/at yyyy-mm-dd!\n";
            }
        } else {
            assert userInput.startsWith("event ");
            try {
                int lengthOfEventString = 6;
                int atPosition = userInput.lastIndexOf("/at");
                String description = userInput.substring(lengthOfEventString, atPosition);
                LocalDate time = LocalDate.parse(userInput.substring(atPosition + 4));
                task = new Event(description, time);
            } catch (DateTimeParseException e) {
                return "Please enter a valid date in the format:/at yyyy-mm-dd!\n";
            }
        }
        userInputRecords.add(task);
        storage.autoSave();
        return "Got it. I've added this task:\n" + task + "\n" + "Now you have "
                + userInputRecords.size() + " tasks in the list.\n";
    }

    /**
     * Deletes the user-specified event, according to the task index.
     *
     * @param userInput        input from the user.
     * @param userInputRecords the saved record.
     * @return the response on whether a task is successfully deleted.
     */
    public String delete(String userInput, ArrayList<Task> userInputRecords) {
        try {
            int itemIndex = Integer.parseInt(userInput.replaceAll("[^0-9]", "")) - 1;
            Task itemDeleted = userInputRecords.get(itemIndex);
            userInputRecords.remove(itemIndex);
            storage.autoSave();
            return "Noted. I've removed this task:\n" + itemDeleted + "\n"
                    + "Now you have " + userInputRecords.size() + " tasks in the list.\n";
        } catch (IndexOutOfBoundsException e) {
            return "Oops, the ID of the task does not exist!\n";
        } catch (NumberFormatException e) {
            return "Please enter a valid ID!\n";
        }
    }

    /**
     * Deletes all tasks saved in the record.
     * @return the response on whether all tasks are successfully deleted.
     */
    public String deleteAll(ArrayList<Task> userInputRecords) {
        userInputRecords.clear();
        storage.autoSave();
        return "All records deleted!\n";
    }

    public Storage getStorage() {
        return storage;
    }

    /**
     * Marks the user-specified event as done, according to the task index.
     *
     * @param userInput        input from the user.
     * @param userInputRecords the saved record.
     * @return the response on whether a task is successfully marked done.
     */
    public String markAsDone(String userInput, ArrayList<Task> userInputRecords) {
        try {
            int itemIndex = Integer.parseInt(userInput.replaceAll("[^0-9]", "")) - 1;
            Task taskDone = userInputRecords.get(itemIndex);
            taskDone.setDone(true);
            userInputRecords.set(itemIndex, taskDone);
            storage.autoSave();
            return "Nice! I've marked this task as done:\n" + userInputRecords.get(itemIndex) + "\n";
        } catch (IndexOutOfBoundsException e) {
            return "Oops, the ID of the task does not exist!\n";
        } catch (NumberFormatException e) {
            return "Please enter a valid ID!\n";
        }
    }

    /**
     * Searches for the events according to the user-specified keyword.
     *
     * @param userInput        input from the user.
     * @param userInputRecords the saved record.
     * @return the response on whether a task is successfully found.
     */
    public String search(String userInput, ArrayList<Task> userInputRecords) {
        String keyword = userInput.replace("find", "").trim();
        ArrayList<Task> searchResults = new ArrayList<>();
        for (Task userInputRecord : userInputRecords) {
            String taskTitle = userInputRecord.getTaskTitle();
            if (taskTitle.contains(keyword)) {
                searchResults.add(userInputRecord);
            }
        }

        if (searchResults.isEmpty()) {
            return "Oops,there is no record for the keyword " + keyword + "\n";
        } else {
            StringBuilder builder = new StringBuilder();
            builder.append("Here are the matching tasks in your list:");
            for (int i = 0; i < searchResults.size(); i++) {
                builder.append("     ").append(i + 1).append(".").append(searchResults.get(i));
            }
            return builder.toString();
        }
    }
}
