import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] storage = new String[100];
        int storageCount = 0;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello, I am\n" + logo);
        System.out.println("What can I do for you today?");
        System.out.println("------------------");
        String input;
        while (sc.hasNextLine()) {
            input = sc.nextLine();
            switch (input) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    for (int i = 1; i <= storageCount; i++) {
                        System.out.println(i + ": " + storage[i - 1]);
                    }
                    System.out.println("------------------");
                    break;
                default:
                    if (storageCount < 100) {
                        storage[storageCount++] = input;
                        System.out.println("added: " + input);
                    } else {
                        System.out.println("Maximum storage size reached.");
                    }
                    System.out.println("------------------");
            }
        }
    }
}
