package duke;

/**
 * Represents the UI class that handles user interaction
 */
public class Ui {
    private String generateLine() {
        return "        ----------------";
    }

    public String byeResponse() {
        String message = generateLine() + "\n" + "    okay :<, bye!" + "\n" + generateLine();
        return message;
    }

    public String listResponse(TaskList tasks) {
        String message = generateLine() + "\n" + "    " + "Here are the tasks in your list:\n";
        int i = 0;
        while (i < tasks.getNumberOfTasks()) {
            message += "    " + (i + 1) + ". " + tasks.get(i).toString() + "\n";
            i++;
        }
        message += generateLine();
        return message;
    }



    /**
     * Outputs the greeting to the user
     */
    public void printGreeting() {
        String greeting = "Hello! I'm Duke, Joe Wel's personal slave!\n" + "What can I do for you?";
        System.out.println(greeting);
    }

}
