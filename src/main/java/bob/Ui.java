package bob;

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
        return "You don't have the data directory and Bob text file yet." +
                " I'll make them for you now, you're welcome! :D\n";
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
        return "OKKAYYY finally awake!\n Howwwwwwdy! I'm Bob :D\n What do you want?\n";
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

    /**
     * Returns message for when the user does not input a valid date for their deadline or event timing.
     */
    public String getInvalidDateExceptionMessage() {
        return "What kind of date is that >:(\n";
    }

    /**
     * Returns message for when the user input is not one of the supported commands.
     */
    public String getInvalidInputExceptionMessage() {
        return "That doesn't make any sense! >:(\n";
    }

    /**
     * Returns message for when the user does not specify the task description.
     */
    public String getNoTaskExceptionMessage() {
        return "You didn't specify your task! >:(\n";
    }

    /**
     * Returns message for when the user does not specify the task description and date of event or deadline.
     */
    public String getNoTaskAndDateExceptionMessage() {
        return "You didn't specify your task OR its date! >:(\n";
    }

    /**
     * Returns message for when the user does not specify the deadline of their Deadline task.
     */
    public String getNoDeadlineExceptionMessage() {
        return "When is the deadline? >:(\n";
    }

    /**
     * Returns message for when the user does not specify the timing of their Event task.
     */
    public String getNoEventTimingExceptionMessage() {
        return "When is the event? >:(\n";
    }

    /**
     * Returns message for when the user does not specify any keyword for their search.
     */
    public String getNoKeywordExceptionMessage() {
        return"What are you even looking for >:(\n";
    }

    /**
     * Returns message for when the user tries to mark as completed or remove a task that is not inside the task list.
     */
    public String getOutOfBoundsExceptionMessage() {
        return "Huh what task is that :/\n";
    }

    /**
     * Returns message for when the user terminates the session and Bob stops running.
     */
    public String getGoodbyeMessage() {
        return "Bye! Shoo!";
    }
}
