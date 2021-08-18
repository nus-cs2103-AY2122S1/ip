import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Nukem\n"
                + "What can I do for you?");
        String tempString;
        Scanner input = new Scanner(System.in);
        while (!(tempString = input.nextLine()).equals("bye")) {
            System.out.println(tempString);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
