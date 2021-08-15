import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String greeting = "    ____________________________________________________________\n" +
                "     Hello! I'm Peoduo \n" +
                "     Can I help you? \n" +
                "    ____________________________________________________________";
        System.out.println(greeting);
        Scanner myScanner = new Scanner(System.in);
        String userInput = myScanner.nextLine();
        while(!userInput.equals("bye")) {
            System.out.println("    ____________________________________________________________\n" +
                    "     " + userInput + "\n" +
                    "    ____________________________________________________________");
            userInput = myScanner.nextLine();
        }
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }
}
