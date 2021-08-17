import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static final String HORIZONTAL_LINE = "____________________________________________________________ \n";
    public static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";


        String greeting = "Hello! I am \n"
                    + logo
                    + "The awesome bot helper! \n"
                    + "How can I help you today?\n";

        String goodbye = "Bye. Hope to see you again soon!\n";

        System.out.print(reply(greeting));
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        while(true) {
            String userInput = myObj.nextLine();
            if(userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                StringBuilder result = new StringBuilder();
                int index = 1;
                for (String items : list) {
                    result.append(String.valueOf(index) + ". " + items +'\n');
                    index++;
                }
                System.out.print(reply(result.toString()));
            } else {
                list.add(userInput);
                System.out.print(reply("added: " + userInput + '\n'));
            }
        }

        System.out.print(reply(goodbye));
    }

    public static String reply(String output) {
        return (HORIZONTAL_LINE + output + HORIZONTAL_LINE);
    }
}
