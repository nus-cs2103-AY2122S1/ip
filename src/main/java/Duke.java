import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //enum userFunctions {BYE};
        String GREETING = "Hello! I'm Duck \n"
                + "*quack*  >(.)__\n"
                + "          (___/ \n"
                + "What can I do for you?";
        String BYE = "Bye. Hope to see you again soon!\n"
                + "   __(.)>   *quack*\n"
                + "~~ \\___)\n";
        String userInput = null;

        Scanner myObj = new Scanner(System.in);
        System.out.println(GREETING);
        while (true) {
            userInput = myObj.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(BYE);
                break;
            } else {
                System.out.println(userInput);
            }
        }


    }


}
