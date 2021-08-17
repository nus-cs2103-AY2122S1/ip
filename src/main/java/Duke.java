import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String[] listOfItems = new String[100];
        int index = 0;
        while (true) {
            String input = sc.nextLine().toLowerCase();
            boolean isLastCharDigit =  Character.isDigit(input.charAt(input.length() - 1));
            if (input.equals("list")) {
                System.out.println("list");
                for (int i = 0; i < index; i++) {
                    System.out.println("\t" + (i + 1) + ". " + listOfItems[i]);
                }
            } else if (input.equals("bye")) {
                System.out.println(" \t Bye. Hope to see you again soon!");
                break;
            }else {
                listOfItems[index] = input;
                index++;
                System.out.println("\tadded: " + input);
            }
        }
    }
}
