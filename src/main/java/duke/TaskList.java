package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


/**
 * The TaskList class is in charge of executing the users command on the saved record, as instructed by the parser.
 * */
public class TaskList {
    private Storage storage;
    private TaskListUi ui;

    public TaskList(Storage storage) {
        this.storage = storage;
        this.ui = new TaskListUi();
    }

    /**
     * Add the user's input to the saved record only if the user's input is of a specific form.
     * @param userInput input from the user.
     * @param userInputRecord the saved record.
     * */
    public void add(String userInput, ArrayList<Task> userInputRecord) {
        Task task;
        if(userInput.startsWith("todo")) {
            String description = userInput.substring(4);
            if (description.trim().isEmpty()) {
                ui.nonEmptyDescriptionMessage("todo");
                return;
            }
            task = new ToDo(description);
        } else if(userInput.startsWith("deadline")) {
            try {
                int byPosition = userInput.lastIndexOf("/by");
                String description = userInput.substring(9,byPosition); //Length of "deadline" = 9
                LocalDate ddl = LocalDate.parse(userInput.substring(byPosition + 4));
                task = new Deadline(description,ddl);
            } catch (StringIndexOutOfBoundsException e) {
                ui.nonEmptyDescriptionMessage("deadline");
                return;
            } catch (DateTimeParseException e) {
                ui.invalidDateFormMessage();
                return;
            }
        } else if(userInput.startsWith("event")) {
            try {
                int atPosition = userInput.lastIndexOf("/at");
                String description = userInput.substring(6, atPosition);//Length of "event " = 6
                LocalDate time = LocalDate.parse(userInput.substring(atPosition + 4));
                task = new Event(description, time);
            } catch (StringIndexOutOfBoundsException e) {
                ui.nonEmptyDescriptionMessage("event");
                return;
            } catch (DateTimeParseException e) {
                ui.invalidDateFormMessage();
                return;
            }
        } else {
            ui.cannotInterpretMessage();
            return;
        }
        userInputRecord.add(task);
        storage.autoSave();
        ui.addMessage(userInputRecord,task);
    }

    /**
     * Delete the user-specified event, according to the task index.
     * @param userInput input from the user.
     * @param userInputRecord the saved record.
     * */
    public void delete(String userInput, ArrayList<Task> userInputRecord) {
        try {
            int itemToDelete = Integer.parseInt(userInput.replaceAll("[^0-9]", "")) - 1;
            Task itemDeleted = userInputRecord.get(itemToDelete);
            userInputRecord.remove(itemToDelete);
            storage.autoSave();
            ui.deleteMessage(userInputRecord,itemDeleted);
        } catch (IndexOutOfBoundsException e) {
            ui.absentIDMessage();
        } catch (NumberFormatException e) {
            ui.invalidIDMessage();
        }
    }

    /**
     * Delete all tasks saved in the record.
     * */
    public void deleteAll(ArrayList<Task> userInputRecord) {
        userInputRecord.clear();
        ui.deleteAllMessage();
    }

    public Storage getStorage() {
        return storage;
    }

    /**
     * Mark the user-specified event as done, according to the task index.
     * @param userInput input from the user.
     * @param userInputRecord the saved record.
     * */
    public void markAsDone(String userInput, ArrayList<Task> userInputRecord) {
        try {
            int itemToComplete = Integer.parseInt(userInput.replaceAll("[^0-9]", "")) - 1;
            Task taskDone = userInputRecord.get(itemToComplete);
            taskDone.setDone(true);
            userInputRecord.set(itemToComplete, taskDone);
            ui.markAsDoneMessage(userInputRecord,itemToComplete);
            storage.autoSave();
        } catch (IndexOutOfBoundsException e) {
            ui.absentIDMessage();
        } catch (NumberFormatException e) {
            ui.invalidIDMessage();
        }
    }
}
