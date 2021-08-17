import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        String toAdd = scanner.nextLine();

        String[] tasks = new String[100];
        int index = 0;

        while (!toAdd.equals("bye")) {
            if (toAdd.equals("list")) {
                displayTasks(tasks);
            } else {
                tasks[index] = toAdd;
                System.out.println("\t added: " + toAdd + "\n");
                index++;
            }
            toAdd = scanner.nextLine();
        }

        scanner.close();
    }

    private static void displayTasks(String[] tasks) {
        for(int i = 0; i < tasks.length && tasks[i] != null; i++) {
            int index = i + 1;
            System.out.println("\t " + index + ". " + tasks[i]);
        }
        System.out.println();
    }

}
