import java.util.Scanner;

public class Duke {


    static String line = "____________________________________________________________\n";

    public static void HelloMessage() {
        String Hello_message = "Hello! I'm Duke\n" +
                                "What can I do for you?\n";

        System.out.println(line + Hello_message + line);
    }


    public static void PrintMessage(){
        Scanner scanner = new Scanner(System.in);
        String Echo = "";
        String Goodbye_message = "Bye. Hope to see you again soon!\n";

        //Use loop to determine if a user enters "bye" or not.
        while (true) {
            Echo = scanner.nextLine();
            if (Echo.equals("bye")) {
                System.out.println(line + Goodbye_message + line);
                break;
            }
            else {
                System.out.println(line + Echo + "\n" + line);
            }
        }
    }




    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        //Print Hello.
        HelloMessage();

        //Echo Message();
        PrintMessage();

    }
}
