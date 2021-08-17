import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Duke {


    static String line = "____________________________________________________________";

    static List<Task> list = new ArrayList<Task>();

    public static void HelloMessage() {
        String Hello_message = "Hello! I'm Duke\n" +
                                "What can I do for you?\n";

        System.out.println(line + "\n" + Hello_message + line + "\n");
    }

    public static void PrintList() {
        int index = 0;

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            index++;
            System.out.println(index + "." + list.get(i).PrintTaskInfo());
        }
    }

    public static void PrintMessage(){
        Scanner scanner = new Scanner(System.in);
        String Message = "";
        String Goodbye_message = "Bye. Hope to see you again soon!\n";

        //Use loop to determine if a user enters "bye" or not.
        while (true) {
            Message = scanner.nextLine();
            System.out.println(line);
            if (Message.equals("bye")) {
                System.out.println(Goodbye_message);
                break;
            } else if (Message.equals("list")){
                PrintList();
            } else if (Message.startsWith("done")) {
                int index = Message.charAt(Message.length() - 1) - 49;

                System.out.println("Nice! I've marked this task as done:");
                list.get(index).MarkDone();
                System.out.println(" " + list.get(index).PrintTaskInfo());
            }
            else {
                System.out.println("added: " + Message);
                list.add(new Task(false, Message));
            }

            System.out.println(line + "\n");
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
