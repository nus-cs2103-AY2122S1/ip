import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner input = new Scanner(System.in);
        String[] storedInfo = new String[100];
        int count = 0;
        while (input.hasNext()) {
            String in = input.next();
            if (in.equals("bye") || in.equals("Bye")) {
                break;
            }
            if (in.equals("list") || in.equals("List")) {
                for (String item: storedInfo) {
                    if(item != null) {
                        System.out.println(item);
                    }
                }
                continue;
            }
                System.out.println("added: " + in);
                storedInfo[count] = in;
                count++;

            }


        System.out.println("Bye. Hope to see you again!");
    }
}
