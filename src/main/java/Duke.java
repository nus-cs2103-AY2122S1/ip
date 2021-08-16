import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String separator = "     _______________________________________________________";
        System.out.println("Hello from\n" + logo);
        System.out.println(separator);
        System.out.println("     Hi! I am Duke!\n" + "     What can I do for you?");
        System.out.println(separator);
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String curr = sc.nextLine();
            System.out.println(curr);
            System.out.println(separator);
            String message = chat(curr);
            System.out.println("     " + message);
            System.out.println(separator);
            if (message.equals("Bye. Hope to see you again soon!")){
                System.exit(0);
            }
        }
    }

    public static String chat(String s){
        String check = s.replaceAll(" ", "");
        if (check.toLowerCase().equals("bye")) {
            return "Bye. Hope to see you again soon!";
        } else {
            return s;
        }
    }
}