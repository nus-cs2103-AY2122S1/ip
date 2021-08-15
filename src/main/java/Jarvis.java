import jarvis.message.ExitMessage;
import jarvis.message.GreetingMessage;
import jarvis.message.OutputMessage;

import java.util.Scanner;

public class Jarvis {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        OutputMessage greetingMessage = new GreetingMessage();
        System.out.println(greetingMessage.getFormattedMessage());

        String exitTrigger = "bye";
        String userInput = scanner.nextLine();

        while(!userInput.equals(exitTrigger)) {
            OutputMessage echoMessage = new OutputMessage(userInput);
            System.out.println(echoMessage.getFormattedMessage());
            userInput = scanner.nextLine();
        }

        OutputMessage exitMessage = new ExitMessage();
        System.out.println(exitMessage.getFormattedMessage());
    }
}
