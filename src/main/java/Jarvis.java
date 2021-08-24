import jarvis.action.Action;
import jarvis.action.ActionTypeEnum;
import jarvis.exception.JarvisException;
import jarvis.output.Output;
import jarvis.storage.Storage;
import jarvis.task.TaskList;

import java.util.Scanner;

public class Jarvis {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Storage storage;
        TaskList taskList;

        try {
            storage = new Storage("./data/jarvis.txt");
            taskList = new TaskList(storage.loadTasksFromFile());
        } catch (JarvisException e) {
            Output.showError(e);
            return;
        }

        Output.showGreetingMessage();

        String userInput = scanner.nextLine();

        while(true) {
            try {
                if (userInput.equals("")) {
                    userInput = scanner.nextLine();
                    continue;
                }
                if (ActionTypeEnum.identifyActionType(userInput.trim()) == ActionTypeEnum.EXIT) {
                    break;
                }
                Action action = Action.createAction(userInput);
                action.execute(taskList, storage);
            } catch (JarvisException e) {
                Output.showError(e);
            }
            userInput = scanner.nextLine();
        }

        Output.showExitMessage();
        scanner.close();
    }
}
