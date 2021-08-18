import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner stdin = new Scanner(System.in);
        while (true){
            System.out.println("----------------------");
            System.out.println("Hello from\n" + logo);
            System.out.println("What can I do for you?");
            System.out.println("----------------------");
            String command = stdin.next();
            if ("bye".equals(command)){
                System.out.println("----------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("----------------------");
                break;
            }
            else {
                System.out.println("----------------------");
                System.out.println(command);
                System.out.println("----------------------");
            }
        }

    }
}
