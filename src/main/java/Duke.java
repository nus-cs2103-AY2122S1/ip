import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
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
            }
            String echo = "    ----------------------------\n"
                    + "    " + userInput + "\n"
                    + "    ----------------------------";
            System.out.println(echo);

        }
    }
}
