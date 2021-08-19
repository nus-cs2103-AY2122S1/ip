import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Dude");
        System.out.println("What can I do for you?");

        Scanner sc =  new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            String input = prompt(sc);
            isRunning = processInput(input);
        }
    }

    private static String prompt(Scanner sc) {
        return sc.nextLine();
    }

    static boolean processInput(String str){
        if (str.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            return false;
        }
        System.out.println(str);
        return true;
    }
}
