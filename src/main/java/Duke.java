import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Nukem\n"
                + "What can I do for you?");
        String[] storage = new String[100];
        int index = 0;
        String tempString;
        Scanner input = new Scanner(System.in);
        while (!(tempString = input.nextLine()).equals("bye")) {
            switch (tempString) {
                case ("list"):
                    for (int i = 0; i < storage.length; i++) {
                        if (storage[i] == null) break;
                        else {
                            System.out.println((i + 1) + ". " + storage[i]);
                        }
                    }
                    break;
                default:
                    storage[index] = tempString;
                    index++;
                    System.out.println("added: " + tempString);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
