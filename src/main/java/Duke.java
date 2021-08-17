import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        String s = "";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";


        System.out.println(logo);
        System.out.println("___________________________________________");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println("___________________________________________");

        while(true) {
            Scanner input = new Scanner(System.in);
            s = input.nextLine();

            if (s.equals("bye")) {
                System.out.println("___________________________________________");
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("___________________________________________");
                break;
            }

            System.out.println("___________________________________________");
            System.out.println("\t" + s);
            System.out.println("___________________________________________");
        }
    }
}
