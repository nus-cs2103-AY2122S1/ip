package duke.utility;

import duke.exception.DukeException;
import java.util.Scanner;

public class Ui {
    
    public Ui() {
        
    }
    
    public void startListening(TaskList tasks, Storage storage) {
        Parser parser = new Parser(tasks, storage);
        Scanner sc = new Scanner(System.in);
        String welcomeMessage = "Hello I'm Duke!\nWhat can I do for you?";
        this.printMessage(welcomeMessage);

        boolean isCompleted = false;
        while (! isCompleted) {
            String command = sc.nextLine();
            try {
                String message = parser.parseCommand(command);
                if (message.equals("TERMINATE")) {
                    isCompleted = true;
                } else {
                    this.printMessage(message);
                }
            } catch (DukeException ex) {
                this.printMessage(ex.getMessage());
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
