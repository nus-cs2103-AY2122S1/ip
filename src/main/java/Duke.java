import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //Greet
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke.");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        //Echo
        while(!exit){
            String input = scanner.nextLine();

            System.out.println("    ____________________________________________________________");

            if(input.equals("list")){
                System.out.println("    list");
            }else if(input.equals("bye")){
                System.out.println("    Bye. Hope to see you again soon!");
                exit = true;
            }else {
                System.out.println("    blah");
            }

            System.out.println("    ____________________________________________________________");
        }
    }
}
