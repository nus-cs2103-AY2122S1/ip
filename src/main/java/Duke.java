import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String message = "Hello! I'm Duke \n" + "What can I do for you? \n";
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] tasks = new String[100];
        int counter = 0;
        while(!input.equals("bye")) {
            if(input.equals("list")) {
                for(String s:tasks) {
                    if(s != null) {
                        System.out.println(("\t" + s));
                    }
                }
                input = scanner.nextLine();
            } else {
                counter = counter + 1;
                tasks[counter - 1] = Integer.toString(counter) + ". " + input;
                System.out.println("\t" + "added: " + input);
                input = scanner.nextLine();
            }
        }
        String ending = "Bye. Hope to see you again soon!";

        System.out.println("\t" + ending);


    }
}
