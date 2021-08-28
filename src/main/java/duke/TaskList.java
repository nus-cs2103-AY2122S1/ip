package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


/**
 * The TaskList class is in charge of executing the users command on the saved record, as instructed by the parser.
 */
public class TaskList {
    private Storage storage;
    private TaskListUi ui;

    /**
     * The constructor for a TaskList Object.
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        this.ui = new TaskListUi();
    }

    /**
     * Adds the user's input to the saved record only if the user's input is of a specific form.
     *
     * @param userInput        input from the user.
     * @param userInputRecords the saved record.
     */
    public void add(String userInput, ArrayList<Task> userInputRecords) {
        Task task;
        if (userInput.startsWith("todo")) {
            String description = userInput.substring(5);
            if (description.trim().isEmpty()) {
                ui.printNonEmptyDescriptionMessage("todo");
                return;
            }
            task = new ToDo(description);
        } else if (userInput.startsWith("deadline")) {
            try {
                int byPosition = userInput.lastIndexOf("/by");
                String description = userInput.substring(9, byPosition); //Length of "deadline" = 9
                LocalDate ddl = LocalDate.parse(userInput.substring(byPosition + 4));
                task = new Deadline(description, ddl);
            } catch (StringIndexOutOfBoundsException e) {
                ui.printNonEmptyDescriptionMessage("deadline");
                return;
            } catch (DateTimeParseException e) {
                ui.printInvalidDateFormMessage();
                return;
            }
        } else if (userInput.startsWith("event")) {
            try {
                int atPosition = userInput.lastIndexOf("/at");
                String description = userInput.substring(6, atPosition);//Length of "event " = 6
                LocalDate time = LocalDate.parse(userInput.substring(atPosition + 4));
                task = new Event(description, time);
            } catch (StringIndexOutOfBoundsException e) {
                ui.printNonEmptyDescriptionMessage("event");
                return;
            } catch (DateTimeParseException e) {
                ui.printInvalidDateFormMessage();
                return;
            }
        } else {
            ui.printCannotInterpretMessage();
            return;
        }
        userInputRecords.add(task);
        storage.autoSave();
        ui.printAddMessage(userInputRecords, task);
    }

    /**
     * Deletes the user-specified event, according to the task index.
     *
     * @param userInput        input from the user.
     * @param userInputRecords the saved record.
     */
    public void delete(String userInput, ArrayList<Task> userInputRecords) {
        try {
            int itemToDelete = Integer.parseInt(userInput.replaceAll("[^0-9]", "")) - 1;
            Task itemDeleted = userInputRecords.get(itemToDelete);
            userInputRecords.remove(itemToDelete);
            storage.autoSave();
            ui.printDeleteMessage(userInputRecords, itemDeleted);
        } catch (IndexOutOfBoundsException e) {
            ui.printAbsentIdMessage();
        } catch (NumberFormatException e) {
            ui.printInvalidIdMessage();
        }
    }

    /**
     * Deletes all tasks saved in the record.
     */
    public void deleteAll(ArrayList<Task> userInputRecords) {

        userInputRecords.clear();
        ui.printDeleteAllMessage();
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
    public void markAsDone(String userInput, ArrayList<Task> userInputRecords) {
        try {
            int itemToComplete = Integer.parseInt(userInput.replaceAll("[^0-9]", "")) - 1;
            Task taskDone = userInputRecords.get(itemToComplete);
            taskDone.setDone(true);
            userInputRecords.set(itemToComplete, taskDone);
            ui.printMarkAsDoneMessage(userInputRecords, itemToComplete);
            storage.autoSave();
        } catch (IndexOutOfBoundsException e) {
            ui.printAbsentIdMessage();
        } catch (NumberFormatException e) {
            ui.printInvalidIdMessage();
        }
    }

    /**
     * Searches for the events according to the user-specified keyword.
     *
     * @param userInput        input from the user.
     * @param userInputRecords the saved record.
     */
    public void search(String userInput, ArrayList<Task> userInputRecords) {
        String keyword = userInput.replace("find", "").trim();
        ArrayList<Task> searchResult = new ArrayList<>();
        for (Task userInputRecord : userInputRecords) {
            String taskTitle = userInputRecord.getTaskTitle();
            if (taskTitle.contains(keyword)) {
                searchResult.add(userInputRecord);
            }
        }
        ui.printSearchResult(searchResult, keyword);
    }
}
