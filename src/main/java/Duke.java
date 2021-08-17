import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

public class Duke {

    private static String welcomeLabel = "Hello! I'm Banana \n" + "     What can I do for you?";
    private static String byeLabel = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        /*(String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/

        System.out.println(displayLabel(welcomeLabel));
        //Level1(new Scanner(System.in));
        Level2(new Scanner(System.in));
    }

    // displays the information keyed in with lines and indentation
    public static String displayLabel(String information) {
        String label = "    ____________________________________________________________\n" +
                "     " +
                information + "\n    " +
                "____________________________________________________________\n";
        return label;
    }

    /* displays the information that was inputted,
     *  and displays the bye message and terminates when the
     *  user inputs bye
     */
    public static void Level1(Scanner userInput) {
        String input = userInput.nextLine();
        while (!input.equals("bye")) {
            System.out.println(displayLabel(input));
            input = userInput.nextLine();
        }
        System.out.println(displayLabel(byeLabel));
    }

    // gets the items in the list when the user inputs list
    public static String getItems(ArrayList<String> items) {
        String collection = "";
        for (int index = 0; index < items.size(); index++) {
            if (index != 0) {
                collection += "     ";
            }
            collection += Integer.toString(index + 1) + ". " + items.get(index);
            if (index != items.size() - 1) {
                collection += "\n";
            }
        }
        return collection;
    }

    /* added the information that was inputted,
       prints out all the information when list is inputted,
     *  and displays the bye message and terminates when the
     *  user inputs bye
     */
    public static void Level2(Scanner userInput) {
        String input = userInput.nextLine();
        ArrayList<String> items = new ArrayList<>();
        while (!input.equals("bye")) {
            if (!input.equals("list")) {
                items.add(input);
                System.out.println(displayLabel("added: " + input));
            } else {
                String itemCollection = getItems(items);
                System.out.println(displayLabel(itemCollection));
            }
            input = userInput.nextLine();
        }
        System.out.println(displayLabel(byeLabel));
    }


}