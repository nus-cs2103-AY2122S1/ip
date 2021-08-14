import java.util.Scanner;

public class Duke {

    private void printMessage(String[] msgLines) {
        System.out.println("\t_______________________________________________________");
        for (String msg : msgLines) {
            System.out.println("\t  " + msg);
        }
        System.out.println("\t_______________________________________________________");
    }

    private void printMessage(String msg) {
        printMessage(new String[] {msg});
    }

    public void run() {
       printMessage(new String[] {"Yo, I'm Xiri.", "How can I help you?"});

       Scanner scanner = new Scanner(System.in);
       String commandString = scanner.nextLine();
       while (!commandString.equals("bye")) {
           printMessage(commandString);
           commandString = scanner.nextLine();
       }
       printMessage("Bye, see you later.");
    }

    public static void main(String[] args) {
       Duke xiri = new Duke();
       xiri.run();
    }
}
