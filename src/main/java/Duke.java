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
        //Array to store whatever text entered by the user
        String[] userList = new String[100];
        //Pointer for array
        int pointer = 0;

        Scanner userInput = new Scanner(System.in);
        System.out.println(greetingStatement);

        while (true) {
            String task = userInput.nextLine();
            //Stop duke if user types "bye"
            if (task.equals("bye")) {
                break;
            }
            //prints out all the stored tasks if user types "list"
            else if (task.equals("list")) {
                System.out.println("    ____________________________________________________________");
                for (int i = 0; i < pointer; i++) {
                    System.out.println("    " + (i + 1 +  ". " + userList[i]));
                }
                System.out.println("    ____________________________________________________________");
            }
            //else add the task to array and print it out to user
            else {
                userList[pointer] = task;
                pointer++;
                System.out.println("    ____________________________________________________________\n    " +
                    "added: " + task + "\n" +
                    "    ____________________________________________________________");
            }

        }

        System.out.println(byeStatement);

    }
}
