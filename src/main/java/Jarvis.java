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
        String userInput = scanner.nextLine();
        TaskList taskList = new TaskList();

        while(!userInput.equals(exitTrigger)) {
            if (userInput.equals(listTrigger)) {
                OutputMessage taskListMessage = new OutputMessage(taskList.toString());
                Output.showFormattedOutputMessage(taskListMessage);
            } else {
                taskList.addTask(userInput);
            }
            userInput = scanner.nextLine();
        }

        OutputMessage exitMessage = new ExitMessage();
        Output.showFormattedOutputMessage(exitMessage);
    }
}
