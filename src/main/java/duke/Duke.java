package duke;

import java.util.Scanner;

public class Duke {

    private TaskList taskManager = new TaskList();

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     * @param input String entered by user.
     * @return Response from chatbot.
     */
    public String getResponse(String input) {
        return taskManager.runWithGraphicUI(input);
    }

    /**
     * The main function that starts off the chatbot.
     * @param args Standard.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        String hello = "Hello! I'm duke.Duke\n"
                + "What can I do for you?";
        System.out.println(hello);
        Scanner scanner = new Scanner(System.in);
        duke.taskManager.run(scanner);
    }
}
