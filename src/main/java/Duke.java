import java.util.*;
public class Duke {
    enum Command {

    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        Scanner sc= new Scanner(System.in);
        while(true) {
            String userInput = sc.nextLine();
            duke.messageHandle(userInput);
        }
    }

    private void messageHandle(String userInput) {
        if (userInput.equals("exit")) {
            this.exit();
        } else {
            this.echo(userInput);
        }
    }

    private void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    private void echo(String args) {
        String start = "_____________________________________\n";
        String end = "\n_____________________________________";
        System.out.println(start + args + end);
    }

    private void exit() {
        String start = "_____________________________________\n";
        String end = "\n_____________________________________";
        System.out.println(start + "Bye. Hope to see you again soon!" + end);
        System.exit(0);
    }
}
