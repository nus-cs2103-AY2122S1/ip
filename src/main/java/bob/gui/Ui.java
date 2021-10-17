package bob.gui;

import bob.TaskList;
import bob.exception.BobException;
import bob.exception.NoSearchResultException;
import bob.task.Task;

/**
 * Represents an object that deals with interactions with the user.
 * It is responsible for retrieving all of Bob's possible responses.
 */
public class Ui {
    public Ui() {}

    /**
     * Returns message for when Bob first starts up and initialises the task list.
     */
    public String getStartMessage() {
        return "I'm waking up... please wait...\n";
    }

    /**
     * Returns message for when the Data directory for the Bob.txt file does not yet exist.
     * Bob.txt does not exist yet either.
     */
    public String getDirectoryLoadingErrorMessage() {
        return "You don't have the data directory and Bob text file yet."
                + " I'll make them for you now, you're welcome! :D\n";
    }

    /**
     * Returns message for when the Bob.txt file does not yet exist.
     */
    public String getFileLoadingErrorMessage() {
        return "You don't have the Bob.Bob text file yet. I'll make one for you now, you're welcome! :D\n";
    }

    /**
     * Returns message for when Bob is ready to receive commands from the user.
     */
    public String getGreetingMessage() {
        return "OKKAYYY finally awake!\nHowwwwwwdy! I'm Bob :D\nWhat do you want?\n"
                + "(Psst... type 'help' if you don't know what to do!)";
    }

    /**
     * Returns current list of tasks.
     *
     * @param tasks Current list of tasks.
     */
    public String getListMessage(TaskList tasks) {
        String result = "Here's your tasks! Wow I'm so helpful!\n";
        result = result + tasks.getList();
        return result;
    }

    /**
     * Returns link to the user guide.
     *
     * @return Message referring the user to the user guide.
     */
    public String getHelpMessage() {
        return "HA HA do you not know how to talk to me? Click on this message to view my guide then!\n"
                + "(You'll need internet access though :p)";
    }

    /**
     * Adds new task to the task list and returns the corresponding message.
     *
     * @param task New task to be added to the list.
     * @param tasks Current list of tasks.
     */
    public String getTaskAddedMessage(Task task, TaskList tasks) {
        tasks.addTask(task);
        return "Okay okay I've added the task:\n" + task.printTask() + "\n"
                + "Yay " + tasks.getNoOfTasks() + " tasks!\n";
    }

    /**
     * Marks the specified task as completed and returns the corresponding message.
     *
     * @param index Index of the specified task in the task list.
     * @param tasks Current list of tasks.
     */
    public String getIndexCompletedMessage(int index, TaskList tasks) {
        return "Wow you finally did something productive!\n" + tasks.markIndexCompleted(index) + "\n";
    }

    /**
     * Deletes the specified task and returns the corresponding message.
     *
     * @param index Index of the specified task in the task list.
     * @param tasks Current list of tasks.
     */
    public String getIndexDeletedMessage(int index, TaskList tasks) {
        return "Okay task yeeted away :D\n" + tasks.deleteIndex(index) + "\n"
                + "Yay " + tasks.getNoOfTasks() + " tasks!\n";
    }

    /**
     * Searches for a specific keyword in the list of tasks and returns the tasks containing the keyword.
     *
     * @param keyword Keyword from the user to search for in the list of tasks.
     * @param tasks Current list of tasks.
     */
    public String getSearchResultMessage(String keyword, TaskList tasks) {
        try {
            String searchResult = tasks.searchList(keyword);
            return "Are any of these tasks the one you're looking for?" + "\n" + searchResult;
        } catch (NoSearchResultException e) {
            return "None of your tasks contain this word -_-\n";
        }
    }

    public String getErrorMessage(BobException bobException) {
        return bobException.getMessage();
    }

    /**
     * Returns message for when the user terminates the session and Bob stops running.
     */
    public String getGoodbyeMessage() {
        return "Bye! Shoo!";
    }
}
