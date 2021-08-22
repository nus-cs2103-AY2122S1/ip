package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private Storage storage;
    private Ui ui;

    public TaskList(Storage storage, Ui ui) {
        this.storage = storage;
        this.ui = ui;
    }

    //This method adds the user's input to the reminder list
    public void add(String userInput, ArrayList<Task> userInputRecord) {
        Task task;
        if(userInput.startsWith("todo")) {
            try {
                String description = userInput.substring(5);
                if (description.trim().isEmpty()) {
                    ui.nonEmptyDescriptionMessage("todo");
                    return;
                }
                task = new ToDo(description);
            } catch (StringIndexOutOfBoundsException e) {
                ui.nonEmptyDescriptionMessage("todo");
                return;
            }
        } else if(userInput.startsWith("deadline")) {
            try {
                int byPosition = userInput.lastIndexOf("/by");
                String description = userInput.substring(9,byPosition); //Length of "deadline " = 9
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


    //This method marks a saved event as done
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

    public Storage getStorage() {
        return storage;
    }
}
