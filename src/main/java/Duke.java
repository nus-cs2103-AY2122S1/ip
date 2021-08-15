import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //Statement to show the user upon exit
        String byeStatement = "    ____________________________________________________________\n" +
            "     Bye. Hope to see you again soon!\n" +
            "    ____________________________________________________________";

        //Statement to show the user upon running Duke
        String greetingStatement = "    ____________________________________________________________\n" +
            "     Hello! I'm Duke\n" +
            "     What can I do for you?\n" +
            "    ____________________________________________________________";

        Scanner userInput = new Scanner(System.in);
        System.out.println(greetingStatement);

        while (true) {
            String userName = userInput.nextLine();
            //Stop duke if user types "bye"
            if (userName.equals("bye")) {
                break;
            }
            System.out.println("    ____________________________________________________________\n    " +
                userName + "\n" +
                "    ____________________________________________________________");
        }

        System.out.println(byeStatement);

    }
}
