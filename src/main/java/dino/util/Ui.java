package dino.util;

import dino.task.TaskList;

/**
 * Deals with interactions with the user
 */
public class Ui {

    /**
     * Greets the user upon initialization of a Dino object
     */
    public static String greeting() {
        return "Hello! I'm dino, your cute dinosaur bot.\n"
                + "Anything I can do for you?";
    }

    /**
     * Saves any changes to the task list to the storage
     * Greets the user with a farewell message
     *
     * @param storage the local storage for task list
     * @param taskList the current version of task list
     */
    public String exit(Storage storage, TaskList taskList) {
        storage.saveToStorage(taskList.getTaskList());
        return "Goodbye~ \n"
                + "Your cute Dino is always around you :D";
    }

}
