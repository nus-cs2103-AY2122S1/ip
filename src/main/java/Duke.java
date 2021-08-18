import java.util.Scanner;

public class Duke {

    private void greet() {
        System.out.println("______________________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("______________________________________________________________________\n");
    }

    private void getInput() {
        Scanner getInput = new Scanner(System.in);
        String command = "";
        String buffer = "     ";
        while(!(command.equals("bye"))) {
            command = getInput.nextLine();
            System.out.println("______________________________________________________________________");
            if(command.equals("bye")) {
                System.out.println(buffer + "Bye. Hope to see you again soon!");
                System.out.println("______________________________________________________________________\n");
            } else {
                System.out.println(buffer + command);
                System.out.println("______________________________________________________________________\n");
            }
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke instance = new Duke();
        instance.greet();
        instance.getInput();
    }
}