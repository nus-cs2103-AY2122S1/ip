package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


/**
 * The TaskList class is in charge of executing the users command on the saved record, as instructed by the parser.
 * @author Zhao Peiduo
 */
public class TaskList {
    private final Storage storage;

    /**
     * The constructor for a TaskList Object.
     */
    public TaskList(Storage storage) {
        this.storage = storage;
    }

    /**
     * Adds a ToDo task to the userInputRecords.
     *
     * @param userInput        input from the user.
     * @param userInputRecords the saved record.
     * @return the response on whether a ToDo is successfully added.
     */
    public String addToDo(String userInput, ArrayList<Task> userInputRecords) {
        int todoCommandLength = 5;
        String description = userInput.substring(todoCommandLength);
        if (description.trim().isEmpty()) {
            return "OOPS!!! The description of a todo cannot be empty.\n";
        }
        ToDo toDo = new ToDo(description);
        return saveTask(userInputRecords, toDo);
    }

    /**
     * Adds a Deadline task to the userInputRecords.
     *
     * @param userInput        input from the user.
     * @param userInputRecords the saved record.
     * @return the response on whether a Deadline is successfully added.
     */
    public String addDeadline(String userInput, ArrayList<Task> userInputRecords) {
        try {
            int deadlineCommandLength = 9;
            int indexOfBy = userInput.lastIndexOf("/by");
            if (indexOfBy == -1) {
                return "Please indicate the deadline!";
            }
            String description = userInput.substring(deadlineCommandLength, indexOfBy);
            if (description.trim().isEmpty()) {
                return "OOPS!!! The description of a deadline cannot be empty.\n";
            }
            LocalDate deadlineDate = LocalDate.parse(userInput.substring(indexOfBy + 4).trim());
            Deadline deadline = new Deadline(description, deadlineDate);
            return saveTask(userInputRecords, deadline);
        } catch (DateTimeParseException e) {
            return "Please enter a valid date in the format:/at yyyy-mm-dd!\n";
        }
    }

    /**
     * Adds an Event task to the userInputRecords.
     *
     * @param userInput        input from the user.
     * @param userInputRecords the saved record.
     * @return the response on whether an Event is successfully added.
     */
    public String addEvent(String userInput, ArrayList<Task> userInputRecords) {
        try {
            int eventCommandLength = 6;
            int indexOfAt = userInput.lastIndexOf("/at ");
            if (indexOfAt == -1) {
                return "Please indicate the date!";
            }
            String description = userInput.substring(eventCommandLength, indexOfAt);
            if (description.trim().isEmpty()) {
                return "OOPS!!! The description of an event cannot be empty.\n";
            }
            LocalDate time = LocalDate.parse(userInput.substring(indexOfAt + 4).trim());
            Event event = new Event(description, time);
            return saveTask(userInputRecords, event);
        } catch (DateTimeParseException e) {
            return "Please enter a valid date in the format:/at yyyy-mm-dd!\n";
        }
    }

    private String saveTask(ArrayList<Task> userInputRecords, Task task) {
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
        return executeCommandOnIndex(userInput, userInputRecords, "delete");
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
        return executeCommandOnIndex(userInput, userInputRecords, "markDone");
    }

    private String executeCommandOnIndex(String userInput, ArrayList<Task> userInputRecords, String command) {
        try {
            int itemIndex = Integer.parseInt(userInput.replaceAll("[^0-9]", "")) - 1;
            Task targetTask = userInputRecords.get(itemIndex);
            if (command.equals("markDone")) {
                targetTask.setDone(true);
                userInputRecords.set(itemIndex, targetTask);
                storage.autoSave();
                return "Nice! I've marked this task as done:\n" + userInputRecords.get(itemIndex) + "\n";
            } else {
                assert command.equals("delete");
                userInputRecords.remove(itemIndex);
                storage.autoSave();
                return "Noted. I've removed this task:\n" + targetTask + "\n"
                        + "Now you have " + userInputRecords.size() + " tasks in the list.\n";
            }
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
            if (userInputRecord.getTaskTitle().contains(keyword)) {
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
