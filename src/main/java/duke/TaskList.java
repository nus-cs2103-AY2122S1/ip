package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


/**
 * The TaskList class is in charge of executing the users command on the saved record, as instructed by the parser.
 */
public class TaskList {
    private Storage storage;
//Todo: Doc for @return
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
     */
    public String add(String userInput, ArrayList<Task> userInputRecords) {
        Task task;
        if (userInput.startsWith("todo")) {
            String description = userInput.substring(5);
            if (description.trim().isEmpty()) {
                return "OOPS!!! The description of a todo cannot be empty.\n";
            }
            task = new ToDo(description);
        } else if (userInput.startsWith("deadline")) {
            try {
                int byPosition = userInput.lastIndexOf("/by");
                String description = userInput.substring(9, byPosition); //Length of "deadline" = 9
                LocalDate ddl = LocalDate.parse(userInput.substring(byPosition + 4));
                task = new Deadline(description, ddl);
            } catch (StringIndexOutOfBoundsException e) {
                return "OOPS!!! The description of a deadline cannot be empty.\n";
            } catch (DateTimeParseException e) {
                return "Please enter a valid date in the format:/at yyyy-mm-dd!\n";
            }
        } else if (userInput.startsWith("event")) {
            try {
                int atPosition = userInput.lastIndexOf("/at");
                String description = userInput.substring(6, atPosition); //Length of "event " = 6
                LocalDate time = LocalDate.parse(userInput.substring(atPosition + 4));
                task = new Event(description, time);
            } catch (StringIndexOutOfBoundsException e) {
                return "OOPS!!! The description of an event cannot be empty.\n";
            } catch (DateTimeParseException e) {
                return "Please enter a valid date in the format:/at yyyy-mm-dd!\n";
            }
        } else {
            //Should not reach here
            return "unexpected error.";
        }
        userInputRecords.add(task);
        storage.autoSave();
        return "Got it. I've added this task:\n" + task + "\n" +"Now you have "
                + userInputRecords.size() + " tasks in the list.\n";
    }

    /**
     * Deletes the user-specified event, according to the task index.
     *
     * @param userInput        input from the user.
     * @param userInputRecords the saved record.
     */
    public String delete(String userInput, ArrayList<Task> userInputRecords) {
        try {
            int itemToDelete = Integer.parseInt(userInput.replaceAll("[^0-9]", "")) - 1;
            Task itemDeleted = userInputRecords.get(itemToDelete);
            userInputRecords.remove(itemToDelete);
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
     */
    public String markAsDone(String userInput, ArrayList<Task> userInputRecords) {
        try {
            int itemToComplete = Integer.parseInt(userInput.replaceAll("[^0-9]", "")) - 1;
            Task taskDone = userInputRecords.get(itemToComplete);
            taskDone.setDone(true);
            userInputRecords.set(itemToComplete, taskDone);
            storage.autoSave();
            return "Nice! I've marked this task as done:\n" + userInputRecords.get(itemToComplete) + "\n";
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
     */
    public String search(String userInput, ArrayList<Task> userInputRecords) {
        String keyword = userInput.replace("find", "").trim();
        ArrayList<Task> searchResult = new ArrayList<>();
        for (Task userInputRecord : userInputRecords) {
            String taskTitle = userInputRecord.getTaskTitle();
            if (taskTitle.contains(keyword)) {
                searchResult.add(userInputRecord);
            }
        }

        if (searchResult.isEmpty()) {
            return "Oops,there is no record for the keyword " + keyword + "\n";
        } else {
            StringBuilder builder = new StringBuilder();
            builder.append("Here are the matching tasks in your list:");
            for (int i = 0; i < searchResult.size(); i++) {
                builder.append("     " + (i + 1) + "." + searchResult.get(i));
            }
            return builder.toString();
        }
    }
}
