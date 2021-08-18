import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Task[] storedInputs = new Task[100];
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
                String message = "    ----------------------------\n"
                        + "Here are the tasks in your list:\n";
                int i = 0;
                while (storedInputs[i] != null) {
                    message += "    " + (i+1) + ". [" + storedInputs[i].getStatusIcon() + "] " + storedInputs[i].getDescription() + "\n";
                    i++;
                }
                message += "    ----------------------------\n";
                System.out.println(message);
            } else if (userInput.substring(0,4).equals("done")) {
                String userIndex = userInput.substring(5);
                int i = Integer.valueOf(userIndex);
                if (storedInputs[i-1] == null) {
                    System.out.println("no task found!");
                } else {
                    storedInputs[i-1].markAsDone();
                    String message = "Nice! I have marked this task as done:\n"+
                            "[X] " + storedInputs[i-1].getDescription();
                    System.out.println(message);
                }
            } else {
                String echo = "    ----------------------------\n"
                        + "    " + "added task: " + userInput + "\n"
                        + "    ----------------------------";
                System.out.println(echo);
                storedInputs[index] = new Task(userInput);
                index++;
            }
        }
    }
}
