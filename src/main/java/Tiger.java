import tiger.actions.Action;
import tiger.actions.AppState;
import tiger.command.Command;
import tiger.components.TaskList;
import tiger.exceptions.inputs.TigerInvalidInputException;

import java.util.Scanner;

public class Tiger {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String greetingMessage = "Hello! I'm Tiger :)\nWhat can I do for you?";
        System.out.println(greetingMessage);

        AppState applicationState = new AppState(false, new TaskList());
        while (!applicationState.isExited()) {
            try {
                String userInput = scanner.nextLine();
                Action action = Command.getActionFromCommand(userInput, applicationState);
                applicationState = action.run();
            } catch (TigerInvalidInputException e) {
                System.out.println(e);
            }
        }

        if (applicationState.isExited()) {
            String goodbyeMessage = "Bye. Hope to see you again soon!";
            System.out.println(goodbyeMessage);
        }
    }
}
