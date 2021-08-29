package duke;

import duke.task.Task;

public class ResponseLogic {

    /** Returns the welcome message response */
    public String welcomeResponse() {
        return "Lollipop: Hello! I am your personal chatbot, Lollipop! "
                + "What would you like to do today?";
    }

    /** Returns the goodbye message response */
    public String goodByeResponse() {
        return "Lollipop: See you again soon!";
    }

    /** Returns a response when no task is available */
    public String noTaskAvailableResponse() {
        return "Lollipop: You have no tasks available.";
    }

    /**
     * Returns a response with a task and its index number.
     *
     * @param index The index of the task.
     * @param task The task to be printed.
     */
    public String taskWithIndexReponse(int index, Task task) {
        return String.format("%d. %s%n", index, task);
    }

    /**
     * Returns a response when a task is added.
     *
     * @param task The newly added task.
     */
    public String addTaskResponse(Task task) {
        return String.format("Lollipop: %s has been added.%n", task.toString());
    }

    /**
     * Returns a response when a task is marked done.
     *
     * @param task The task which has been marked as done.
     */
    public String taskMarkedDoneResponse(Task task) {
        return String.format("Lollipop: %s has been marked as done.%n", task.toString());
    }

    /**
     * Returns a response when a task is deleted.
     *
     * @param task The task which has been deleted.
     */
    public String deleteTaskResponse(Task task) {
        return String.format("Lollipop: %s has been deleted.%n", task.toString());
    }

    /** Returns the header when displaying the task list */
    public String taskListHeaderResponse() {
        return "Lollipop: Here are your tasks\n";
    }

    /** Returns the header when displaying tasks which occur on the specified date */
    public String tasksOnDateHeaderResponse() {
        return "Lollipop: Here the tasks that occurs on the specified date:\n";
    }

    /** Returns the header when displaying tasks with specified keyword */
    public String tasksWithKeywordHeaderResponse() {
        return "Lollipop: Here the tasks that match your keyword:\n";
    }

    // Error methods
    /**
     * Returns a response for the error when the data file is not found.
     *
     * @param filePath The filepath of the data file.
     */
    public String loadingErrorResponse(String filePath) {
        return String.format("Lollipop: %s is not found.%nThe file has been automatically created.", filePath);
    }

    /**
     * Returns a response for Duke Exceptions.
     *
     * @param e The Duke Exception.
     */
    public String dukeExceptionResponse(Exception e) {
        return String.format("Lollipop: %s%n", e.getMessage());
    }

    /** Returns a response for IndexOutOfBoundsException */
    public String indexOutOfBoundsExceptionResponse() {
        return "Lollipop: No such duke.task number found.";
    }

    /** Returns a response for NumberFormatException */
    public String numberFormatExceptionResponse() {
        return "Lollipop: Please input a number.";
    }

    /** Returns a response for DateTimeParseException */
    public String dateTimeParseExceptionResponse() {
        return "Lollipop: Please specify a valid date format, such as YYYY-MM-DD";
    }
}
