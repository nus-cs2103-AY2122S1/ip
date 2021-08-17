import java.util.Scanner;

public class Duke {

    void startConversation() {
        Scanner sc = new Scanner(System.in);

        greeting("Alex");
        while(true) {
            // conversation loop
            String input = sc.next();
            if(!input.equals("bye")) {
                printHorizLine();
                System.out.println("\t" + input);
                printHorizLine();
            } else {
                break;
            }
        }
        sayBye("Alex");
    }

    void greeting(String name) {
        printHorizLine();
        System.out.println("\tHello " + name + "!");
        System.out.println("\tI'm Duke");
        printHorizLine();
    }

    void sayBye(String name) {
        printHorizLine();
        System.out.println("\tBye " + name + ", hope to see you soon!");
        printHorizLine();
    }

    void printHorizLine() {
        System.out.println("\t————————————————————————————————————————");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.startConversation();
    }
}
