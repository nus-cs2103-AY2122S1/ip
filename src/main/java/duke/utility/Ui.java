package duke.utility;

import java.util.Scanner;

public class Ui {
    
    public Ui() {
        
    }

    /**
     * Starts listening for commands from the user.
     * @param tasks the {@link duke.utility.TaskList} this Ui is linked to.
     * @param storage the {@link duke.utility.Storage} this Ui is linked to.
     */
    public void startListening(TaskList tasks, Storage storage) {
        Parser parser = new Parser(tasks, storage);
        Scanner sc = new Scanner(System.in);
        String welcomeMessage = "Hello I'm Duke!\nWhat can I do for you?";
        this.printMessage(welcomeMessage);

        boolean isCompleted = false;
        while (! isCompleted) {
            String command = sc.nextLine();
            String message = parser.parseCommand(command);
            if (message.equals("TERMINATE")) {
                isCompleted = true;
            } else {
                this.printMessage(message);
            }
        }
        printMessage("Goodbye for now!");
    }

    /**
     * Outputs a message.
     * @param message the message to print.
     */
    public void printMessage(String message) {
        System.out.println("-------------------------");
        System.out.println(message);
        System.out.println("-------------------------");
    }
}
