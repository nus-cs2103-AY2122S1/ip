package bob;

import bob.exception.NoSearchResultException;
import bob.task.Task;

/**
 * Represents an object that deals with interactions with the user.
 * It is responsible for displaying all of Bob's messages to the user.
 */
public class Ui {
    public Ui() {}

    /**
     * Displays message when Bob first starts up and initialises the task list.
     */
    public void showStart() {
        System.out.println("I'm waking up... please wait...\n");
    }
    /**
     * Displays message when the Data directory for the Bob.txt file does not yet exist.
     * Bob.txt does not exist yet either.
     */
    public void showDirectoryLoadingError() {
        System.out.println("You don't have the data directory and Bob text file yet." +
                " I'll make them for you now, you're welcome! :D\n");
    }
    /**
     * Displays message when the Bob.txt file does not yet exist.
     */
    public void showFileLoadingError() {
        System.out.println("You don't have the Bob.Bob text file yet. I'll make one for you now, you're welcome! :D\n");
    }

    /**
     * Displays message when Bob is ready to receive commands from the user.
     */
    public void showGreeting() {
        System.out.println("OKKAYYY finally awake!");
        System.out.println("Howwwwwwdy! I'm Bob :D");
        System.out.println("What do you want?\n");
    }

    /**
     * Displays current list of tasks.
     *
     * @param tasks Current list of tasks.
     */
    public void showList(TaskList tasks) {
        String result = "Here's your tasks! Wow I'm so helpful!\n";
        result = result + tasks.getList();
        System.out.println(result);
    }
    /**
     * Adds new task to the task list and displays the corresponding message.
     *
     * @param task New task to be added to the list.
     * @param tasks Current list of tasks.
     */
    public void showTaskAdded(Task task, TaskList tasks) {
        tasks.addTask(task);
        System.out.println("Okay okay I've added the task:\n" + task.printTask() + "\n"
                + "Yay " + tasks.getNoOfTasks() + " tasks!\n");
    }

    /**
     * Marks the specified task as completed and displays the corresponding message.
     *
     * @param index Index of the specified task in the task list.
     * @param tasks Current list of tasks.
     */
    public void showIndexCompleted(int index, TaskList tasks) {
        System.out.println("Wow you finally did something productive!\n" + tasks.markIndexCompleted(index) + "\n");
    }

    /**
     * Deletes the specified task and displays the corresponding message.
     *
     * @param index Index of the specified task in the task list.
     * @param tasks Current list of tasks.
     */
    public void showIndexDeleted(int index, TaskList tasks) {
        System.out.println("Okay task yeeted away :D\n" + tasks.deleteIndex(index) + "\n"
                + "Yay " + tasks.getNoOfTasks() + " tasks!\n");
    }

    /**
     * Searches for a specific keyword in the list of tasks and displays the tasks containing the keyword.
     *
     * @param keyword Keyword from the user to search for in the list of tasks.
     * @param tasks Current list of tasks.
     */
    public void showSearchResult(String keyword, TaskList tasks) {
        try {
            String searchResult = tasks.searchList(keyword);
            System.out.println("Are any of these tasks the one you're looking for?");
            System.out.println(searchResult);
        } catch (NoSearchResultException e) {
            System.out.println("None of your tasks contain this word -_-\n");
        }
    }

    /**
     * Displays message when the user input is not one of the supported commands.
     */
    public void showInvalidInputException() {
        System.out.println("That doesn't make any sense! >:(\n");
    }

    /**
     * Displays message when the user does not specify the task description.
     */
    public void showNoTaskException() {
        System.out.println("You didn't specify your task! >:(\n");
    }

    /**
     * Displays message when the user does not specify the deadline of their Deadline task.
     */
    public void showNoDeadlineException() {
        System.out.println("When is the deadline? >:(\n");
    }

    /**
     * Displays message when the user does not specify the timing of their Event task.
     */
    public void showNoEventTimingException() {
        System.out.println("When is the event? >:(\n");
    }

    /**
     * Displays message when the user does not specify any keyword for their search.
     */
    public void showNoKeywordException() {
        System.out.println("What are you even looking for >:(\n");
    }

    /**
     * Displays message when the user tries to mark as completed or remove a task that is not inside the task list.
     */
    public void showOutOfBoundsException() {
        System.out.println("Huh what task is that :/\n");
    }

    /**
     * Displays message when the user terminates the session and Bob stops running.
     */
    public void showGoodbye() {
        System.out.println("Bye! Shoo!");
    }
}
