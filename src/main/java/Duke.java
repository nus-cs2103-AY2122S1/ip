import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";


        String greeting = "Hello! I am \n"
                    + logo
                    + "The awesome bot helper! \n"
                    + "How can I help you today?";

        String goodbye = "Bye. Hope to see you again soon!";

        System.out.print(reply(greeting));
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        while(true) {
            String userInput = myObj.nextLine();
            if(userInput.equals("bye")) {
                break;
            } else {
                System.out.print(reply(userInput));
            }
        }

        System.out.print(reply(goodbye));
    }

    public static String reply(String output) {
        String hline = "____________________________________________________________ \n";
        return (hline + output + '\n' + hline);
    }
}
