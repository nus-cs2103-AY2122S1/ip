import jarvis.action.Action;
import jarvis.action.ActionTypeEnum;
import jarvis.exception.JarvisException;
import jarvis.output.Output;
import jarvis.task.TaskList;

import java.util.Scanner;

public class Jarvis {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Output.showGreetingMessage();

        String userInput = scanner.nextLine();
        TaskList taskList = new TaskList();

        while(true) {
            try {
                if (userInput.equals("")) {
                    userInput = scanner.nextLine();
                    continue;
                }
                if (ActionTypeEnum.identifyActionType(userInput.trim()) == ActionTypeEnum.EXIT) {
                    break;
                }
                Action action2 = Action.createAction(userInput);
                action2.execute(taskList);
            } catch (JarvisException e) {
                Output.showError(e);
            }
            userInput = scanner.nextLine();
        }

        Output.showExitMessage();
        scanner.close();
    }
}
