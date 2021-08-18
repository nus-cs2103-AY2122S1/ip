import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private List<String> LIST;

    public Duke() {
        LIST = new ArrayList<String>();
    }

    private void greet() {
        System.out.println("______________________________________________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("______________________________________________________________________\n");
    }

    private void getInput() {
        Scanner getInput = new Scanner(System.in);
        String command = "";
        String buffer = "     ";
        while(!(command.equals("bye"))) {
            command = getInput.nextLine();
            System.out.println("______________________________________________________________________");
            if (command.equals("bye")) {
                System.out.println(buffer + "Bye. Hope to see you again soon!");
            } else if (command.equals("list")) {
                for (int i = 0; i < LIST.size(); i++) {
                    System.out.println(buffer + (i + 1) + ". " + LIST.get(i));
                }
            } else {
                LIST.add(command);
                System.out.println(buffer + "added: " + command);
            }
            System.out.println("______________________________________________________________________\n");
        }
    }

    public static void main(String[] args) {
        String buffer = "     ";
        String logo =   buffer + " ____        _        \n" +
                        buffer + "|  _ \\ _   _| | _____ \n" +
                        buffer + "| | | | | | | |/ / _ \\\n" +
                        buffer + "| |_| | |_| |   <  __/\n" +
                        buffer + "|____/ \\__,_|_|\\_\\___|\n";
        
        System.out.println("Hello from\n" + logo);

        Duke instance = new Duke();
        instance.greet();
        instance.getInput();
    }
}