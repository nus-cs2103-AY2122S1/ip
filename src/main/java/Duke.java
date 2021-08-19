import java.util.Scanner;
public class Duke {

    public static void talk() {
        String userInput = "";
        System.out.println("Hello, What Can I do for you ?");
        while(!userInput.equals("bye")) {
            Scanner scanner = new Scanner(System.in);
            userInput = scanner.next();
            if(userInput.equals("bye")) {
                System.out.println("Bye, Hope to see you again soon !");
            }
            System.out.println("Duke : " + userInput);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke.talk();
    }
}
