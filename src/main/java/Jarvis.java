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

        String exitTrigger = "bye";
        String listTrigger = "list";
        String doneTrigger = "done";
        String userInput = scanner.nextLine().trim();
        TaskList taskList = new TaskList();

        while(!userInput.equals(exitTrigger)) {
            String[] action = userInput.split(" ");
            String actionType = action[0];

            if (actionType.equals(listTrigger)) {
                OutputMessage taskListMessage = new OutputMessage(taskList.toString());
                Output.showFormattedOutputMessage(taskListMessage);
            } else if (actionType.equals(doneTrigger)) {
                int taskIndex = Integer.parseInt(action[1]) - 1;
                taskList.markAsDone(taskIndex);
            } else {
                taskList.addTask(userInput);
            }
            userInput = scanner.nextLine();
        }

        OutputMessage exitMessage = new ExitMessage();
        Output.showFormattedOutputMessage(exitMessage);
    }
}
