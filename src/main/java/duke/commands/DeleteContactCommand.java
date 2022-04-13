package main.java.duke.commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import main.java.duke.DukeException;
import main.java.duke.MainWindow;
import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.tasks.Task;

/**
 * A command that deletes a contact from the contact list.
 */
public class DeleteContactCommand extends ContactCommand {
    private final int contactIndex;

    /**
     * Constructs a new delete contact command with the given contact
     */
    public DeleteContactCommand(int contactIndex) {
        this.contactIndex = contactIndex;
    }

    /**
     * Executes the delete command.
     *
     * @param contacts given list of tasks
     * @param gui given gui object
     * @param storage given storage object
     */
    public String execute(TaskList contacts, MainWindow gui, Storage storage) throws IOException, DukeException {
        if (contacts.getTaskList().size() < this.contactIndex) {
            throw new DukeException("You cannot delete a contact that does not exist!");
        } else {
            Scanner newSc = new Scanner(new File(storage.getFilePath()));
            storage.deleteTaskFromFile(contactIndex, newSc, contacts);
            ArrayList<Task> taskList = contacts.getTaskList();
            Task taskToDelete = taskList.get(contactIndex - 1);
            taskList.remove(taskToDelete);
            String message1 = ("Noted. I've removed this contact: \n");
            String message2 = taskToDelete.toString();
            String contactForm;
            if (taskList.size() == 1 || taskList.size() == 0) {
                contactForm = " contact";
            } else {
                contactForm = " contacts";
            }
            String message3 = ("Now you have " + taskList.size() + contactForm + " in the list.");
            return message1 + message2 + message3;
        }
    }
}
