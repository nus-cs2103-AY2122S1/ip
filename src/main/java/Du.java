import java.util.Objects;
import java.util.Scanner;

public class Du {
    public static void main(String[] args) {
        String logo = " ____ \n"
                + "|  _ \\ _   _\n"
                + "| | | | | | | \n"
                + "| |_| | |_| | \n"
                + "|____/ \\__,_|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Du, your personal assistant chatbot!:)\n"
                + "What can I do for you?");

        Scanner sc= new Scanner(System.in);
        String command = sc.nextLine();

        while (!Objects.equals(command, "bye")) {
            echo(command);
            System.out.println("echoing: ");
            command = sc.nextLine();
        }
        System.out.println("____________________________________________________________\n"
                + "Bye. Hope to see you again soon!" + "\n"
                + "____________________________________________________________");
    }


    public static void echo(String command) {
        System.out.println("____________________________________________________________\n"
                + command + "\n"
                + "____________________________________________________________");
    }


}
