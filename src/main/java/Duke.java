import java.util.Scanner;

public class Duke {
    private final String[] tasks = new String[100];
    private int count = 0;

    private void printMessage(String[] msgLines) {
        if (msgLines.length == 0) {
            return;
        }
        System.out.println("\t_______________________________________________________");
        for (String msg : msgLines) {
            System.out.println("\t  " + msg);
        }
        System.out.println("\t_______________________________________________________");
    }

    private void printMessage(String msg) {
        if (msg.length() == 0) {
            return;
        }
        printMessage(new String[] {msg});
    }

    public void addTask(String task) {
        this.tasks[count++] = task;
    }

    public void listTasks() {
        if (this.count == 0) {
            printMessage("No task added yet.");
            return;
        }
        String[] messages = new String[this.count];
        for (int i = 0; i < this.count; i++) {
            messages[i] = String.format("%d - %s", i + 1, this.tasks[i]);
        }
        printMessage(messages);
    }

    public void run() {
       printMessage(new String[] {"Yo, I'm Xiri.", "How can I help you?"});

       Scanner scanner = new Scanner(System.in);
       String commandString = scanner.nextLine();
       while (!commandString.equals("bye")) {
           if (commandString.equals("list")) {
               listTasks();
           } else {
               addTask(commandString);
               printMessage("Added: " + commandString);
           }
           commandString = scanner.nextLine();
       }
       printMessage("Bye, see you later.");
    }

    public static void main(String[] args) {
       Duke xiri = new Duke();
       xiri.run();
    }
}
