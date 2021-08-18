import java.util.Scanner;  // Import the Scanner clas

public class Duke {
    public static void main(String[] args) {
        String botName = "HuAI";
        System.out.printf("Hello! I'm %s\n", botName);
        System.out.printf("What can I do for you?\n");
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        while (true) {
            String command = scanner.nextLine();
            boolean aborted = false;
            switch (command) {
                case "bye":
                    aborted = true;
                    System.out.printf("Bye. Hope to see you again soon!\n");
                    break;
                default:
                    System.out.printf("%s\n", command);
                    break;
            }
            if (aborted) {
                break;
            }
        }
    }
}
