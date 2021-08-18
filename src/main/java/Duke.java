import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String[] storedInputs = new String[100];
        int index = 0;
        Scanner scanObj = new Scanner(System.in);

        String greeting = "Hello! I'm BunnyBot, Joe Wel's personal slave!\n" + "What can I do for you?";
        System.out.println(greeting);

        while(true) {
            String userInput = scanObj.nextLine();
            if (userInput.equals("bye")) {
                String byeMsg = "    ----------------------------\n"
                        + "    okay :<, bye!" + "\n"
                        + "    ----------------------------";
                System.out.println(byeMsg);
                break;
            } else if (userInput.equals("list")) {
                String message = "    ----------------------------\n";
                int i = 0;
                while (storedInputs[i] != null) {
                    message += "    " + (i+1) + ". " + storedInputs[i] + "\n";
                    i++;
                }
                message += "    ----------------------------\n";
                System.out.println(message);
            } else {
                String echo = "    ----------------------------\n"
                        + "    " + "added: " + userInput + "\n"
                        + "    ----------------------------";
                System.out.println(echo);
                storedInputs[index] = userInput;
                index++;
            }
        }
    }
}
