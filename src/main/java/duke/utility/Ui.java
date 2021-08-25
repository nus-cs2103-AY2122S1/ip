package duke.utility;

import java.util.Scanner;

public class Ui {
    
    public Ui() {
        
    }
    
    public void startListening(TaskList tasks, Storage storage) {
        Parser parser = new Parser(tasks, storage);
        Scanner sc = new Scanner(System.in);
        String welcomeMessage = "Hello I'm Duke!\nWhat can I do for you?";
        this.printMessage(welcomeMessage);

        boolean shouldContinue = true;
        while (shouldContinue) {
            String command = sc.nextLine();
            String message = parser.parseCommand(command);
            if (message.equals("TERMINATE")) {
                shouldContinue = false;
            } else {
                this.printMessage(message);
            }
        }
        printMessage("Goodbye for now!");
    }
    
    public void printMessage(String message) {
        System.out.println("-------------------------");
        System.out.println(message);
        System.out.println("-------------------------");
    }
}
