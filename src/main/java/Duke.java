import java.util.Scanner;

public class Duke {
    static private String[] myList = new String[100];
    static private int index = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        while (true) {
            Scanner sc = new Scanner(System.in);
            String text = sc.nextLine();
            if (text.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (text.equals("list")) {
                for (int i = 0; i < index; i++) {
                    System.out.printf("%s. %s\n", i + 1, myList[i]);
                }
            } else {
                myList[index] = text;
                index++;
                System.out.println("added: " + text);
            }
        }
    }
}
