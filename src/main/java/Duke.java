import java.util.Locale;

public class Duke {

    public static boolean active;
    public static String startMessage = "Hello! I'm Duke \nWhat can I do for you?";
    public static String endMessage = "Bye! Hope to see you again soon!";
    public static String[] list = new String[100];
    public static int listIndex = 0;


    public static void awaken() {
        Duke.active = true;

        Duke.sendStartMessage();
        while (active) {
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            String input = scanner.nextLine();

            if (input.equals("list")) {
                Duke.printList();
            }

            if (!input.equals("list") && !input.equals("bye")) {
                Duke.addToList(input);
            }

            if (input.equals("bye")) {
                Duke.active = false;
                Duke.sendEndMessage();
                break;
            }
        }
    }

    public static void addToList(String input) {
        Duke.list[Duke.listIndex] = input;
        Duke.listIndex++;
        System.out.println("added: " + input);
    }

    public static void printList() {
        int number = 1;

        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                System.out.println(number + ". " + list[i]);
                number++;
            }
        }
    }

    public static void sendStartMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(startMessage);
        System.out.println("____________________________________________________________");
    }

    public static void sendEndMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(endMessage);
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
    /*
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");


        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String input = "";
        input = scanner.nextLine();

        while (!input.equals("bye")) {
            System.out.println("____________________________________________________________");
            System.out.println(input);
            System.out.println("____________________________________________________________");

            input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye! Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }
        }
     */

        Duke.awaken();
    }
}
