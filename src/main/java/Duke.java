import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<String> itemList = new ArrayList<>();

    /**
     * Adds item to itemList.
     * @param item Item to add to itemList.
     */
    private static void add(String item) {
        itemList.add(item);
        System.out.println("added: " + item);
    }

    /**
     * Prints out itemList.
     */
    private static void readList() {
        for (int i = 0; i < itemList.size(); i++) {
            System.out.println((i + 1) + ". " + itemList.get(i));
        }
    }

    /**
     * Echo function. Prints out given input String.
     * @param input The String to be printed.
     */
    private static void echo(String input) {
        System.out.println(input);
    }

    /**
     * Main function.
     * @param args
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Howdy! The name's\n" + logo + "\nWhat can I do for ya?");

        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {

            String input = sc.next();

            System.out.println("____________________________________________________________");

            switch (input) {
                case "bye":
                    System.out.println("Seeya!");
                    exit = true;
                    break;
                case "list":
                    readList();
                    break;
                default:
                    add(input);
                    break;
            }

            System.out.println("____________________________________________________________");

        }

        sc.close();
    }
}
