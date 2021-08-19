import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String GREETING = "Hello! I'm Duck \n"
                + "*quack*  >(.)__\n"
                + "          (___/ \n"
                + "What can I do for you?";
        String BYE = "Bye. Hope to see you again soon!\n"
                + "   __(.)>   *quack*\n"
                + "~~ \\___)\n";

        String newUserInput = null;
        String[] userInput = new String[100];
        int index = 0;
        boolean active = true;

        Scanner myObj = new Scanner(System.in);
        System.out.println(GREETING);
        while (active) {
            newUserInput = myObj.nextLine();
            switch (newUserInput) {
                case "bye":
                    active = false;
                    System.out.println(BYE);
                    break;
                case "list":
                    for (int i = 0; i < index; i++) {
                        System.out.println(i+1 + ". " + userInput[i]);
                    }
                    break;
                default:
                    userInput[index] = newUserInput;
                    index++;
                    System.out.println("added: " + newUserInput);
            }
        }
    }


}



