import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.ListIterator;

public class Duke {
    private static final String END_OF_CONVERSATION = "bye";
    private static List<String> listOfItems = new ArrayList<String>();
    private static final String SEPARATOR = "########################";

    private static void outputWrapper(String input) {
        System.out.println(SEPARATOR);
        System.out.println(input);
        System.out.println(SEPARATOR);
    }

    private static void outputWrapper(List elements) {
        ListIterator<String> it = elements.listIterator();
        System.out.println(SEPARATOR);
        while (it.hasNext()) {
            Integer number = it.nextIndex() + 1;
            System.out.println(number + ". " + it.next());
        }
        System.out.println(SEPARATOR);
    }

    private static void echoInput() {
        Scanner i = new Scanner(System.in);
        String input = i.nextLine();
        while (!input.equals(END_OF_CONVERSATION)) {
            outputWrapper(input);
            input = i.nextLine();
        }
        outputWrapper("Thanks for using me. See you again soon!");
        i.close();

    }

    private static void handleInput(String input) {
        switch(input) {
            case "items":
                outputWrapper(listOfItems);
                break;
            default:
                listOfItems.add(input);
                outputWrapper("added \"" + input + "\" to list");
                break;
        }
    }

    private static void startBot() {
        Scanner i = new Scanner(System.in);
        String input = i.nextLine();

        while (!input.equals(END_OF_CONVERSATION)) {
            handleInput(input);
            input = i.nextLine();
        }
        outputWrapper("Thanks for using me. See you again soon!");
        i.close();
    }
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello this is Jeeves, your personal chatbot. What can i do you for today?");

        startBot();
    }
}
