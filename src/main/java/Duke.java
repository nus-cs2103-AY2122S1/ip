import java.util.Scanner;

public class Duke {
    private static int taskCount = 0;

    public static void main(String[] args) {
        int MAX = 100;
        String[] tasks = new String[MAX];
        System.out.println("Hello...\nWhat do you want?\n");
        Scanner myScanner = new Scanner(System.in);
        String answer = myScanner.nextLine();
        while (!answer.equals("bye")) {
            if (answer.equals("list")) {
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else {
                tasks[taskCount] = answer;
                taskCount += 1;
                System.out.println("added: " + answer);
            }
            answer = myScanner.nextLine();
        }
        System.out.println("Whatever...");
    }
}
