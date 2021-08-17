import jarvis.action.Action;
import jarvis.action.ActionTypeEnum;
import jarvis.message.ExitMessage;
import jarvis.message.GreetingMessage;
import jarvis.message.OutputMessage;
import jarvis.output.Output;
import jarvis.task.TaskList;

import java.util.Scanner;

public class Jarvis {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        OutputMessage greetingMessage = new GreetingMessage();
        Output.showFormattedOutputMessage(greetingMessage);

        String userInput = scanner.nextLine();
        TaskList taskList = new TaskList();

        while(ActionTypeEnum.identifyActionType(userInput.trim()) != ActionTypeEnum.EXIT) {
            Action action2 = Action.createAction(userInput);
            action2.execute(taskList);
            userInput = scanner.nextLine();
        }

        OutputMessage exitMessage = new ExitMessage();
        Output.showFormattedOutputMessage(exitMessage);
        scanner.close();
    }
}
