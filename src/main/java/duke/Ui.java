package duke;

/**
 * Represents the UI class that handles user interaction
 */
public class Ui {

    /**
     * Generates a line to demarcate bot text
     * @return a line string
     */
    private String generateLine() {
        return "----------------";
    }

    /**
     * Formats the response for parser to use when a bye command is received
     * @return message to send to user when a bye command is received
     */
    public String byeResponse() {
        String message = generateLine() + "\n" + "okay :<, bye!" + "\n" + generateLine();
        return message;
    }

    /**
     * Formats the response for parser to use when a list command is received
     * @param tasks
     * @param body
     * @return message to send to user when a list command is received
     */
    public String listResponse(TaskList tasks, String body) {
        String message = generateLine() + "\n" + "Here are the tasks in your list:\n";
        message += body;
        message += generateLine();
        return message;
    }

    /**
     * Formats the response for parser to use when a done command is received
     * @param userIndex
     * @param tasks
     * @return message to send to user when a done command is received
     */
    public String doneResponse(int userIndex, TaskList tasks) {
        String message = generateLine() + "\n";
        if (tasks.get(userIndex - 1) == null) {
            message += "No task found!\n" + generateLine();
        } else {
            message += "Nice! I have marked this task as done:\n"
                    + "[X] " + tasks.get(userIndex - 1).getDescription() + "\n" + generateLine();
        }
        return message;
    }

    /**
     * Formats the response for parser to use when a todo, event or deadline command is received
     * @param index
     * @param task
     * @return message to send to user when a todo, event or deadline command is received
     */
    public String addTaskResponse(int index, Task task) {
        String message = generateLine() + "\n"
                + "Got it, I've added this task: \n"
                + task.toString() + "\n"
                + "Now you have " + index + " tasks in the list\n"
                + generateLine();
        return message;
    }

    /**
     * Formats the response for parser to use when a delete command is received
     * @param index
     * @param task
     * @return message to send to user when a delete command is received
     */
    public String deleteTaskResponse(int index, Task task) {
        String message = generateLine() + "\n"
                + "Got it, I've removed this task: \n"
                + task.toString() + "\n"
                + "Now you have " + index + " tasks in the list\n"
                + generateLine();
        return message;
    }

    /**
     * Formats the response for parser to use when a find command is received
     * @param taskString
     * @param hasTask
     * @return message to send to user when a find command is received
     */
    public String findResponse(String taskString, boolean hasTask) {
        if (hasTask) {
            String message = "    ----------------------------\n"
                    + "Here are your tasks: \n"
                    + taskString
                    + "    ----------------------------";
            return message;
        } else {
            String message = "    ----------------------------\n"
                    + "There are no tasks with this keyword!\n"
                    + "    ----------------------------";
            return message;
        }
    }

    /**
     * Outputs the greeting to the user
     */
    public void printGreeting() {
        String greeting = "Hello! I'm Duke, Joe Wel's personal slave!\n" + "What can I do for you?";
        System.out.println(greeting);
    }

    /**
     * Formats an error message for parser to use
     * @param task
     * @return error message to send to user
     */
    public String errorMessage(String task) {
        String message = generateLine() + "\n"
                + "Oops! The description of a " + task + " cannot be empty!\n" + generateLine();
        return message;
    }

}
