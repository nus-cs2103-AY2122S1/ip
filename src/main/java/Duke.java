import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Duke {
    static final String LOGO = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";;
    static final String GOODBYE = "Bye. Hope to see you again soon!";
    LinkedList<Item> itemList = new LinkedList<>();
    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        System.out.println("Hello from\n" + LOGO);
        Scanner sc = new Scanner(System.in);
        String currLine;
        scanner: while (true) {
            currLine = sc.nextLine();
            switch (currLine) {
                case "bye":
                    System.out.println(styleResponse(GOODBYE));
                    break scanner;
                case "list":
                    System.out.println(styleResponse(this.list()));
                    break;
                default:
                    System.out.println(styleResponse(this.add(currLine)));
            }
        }
        sc.close();
    }

    public String add(String inputString) {
        this.itemList.add(new Item(inputString));
        return "Added: " + inputString;
    }

    public String list() {
        ListIterator<Item> iterator = this.itemList.listIterator();
        if (!iterator.hasNext()) {
            return "Empty!";
        } else {
            String returnBuffer = "";
            Integer currIdx = 1;
            while (iterator.hasNext()) {
                returnBuffer = returnBuffer.concat(currIdx.toString() + ". " + iterator.next().toString()) + "\n";
                currIdx++;
            }
            return returnBuffer;
        }
    }

    public static String echo(String inputString) {
        return inputString;
    }

    public static String styleResponse(String inputString) {
        return "     --------------------\n     " + inputString + "\n     --------------------";
    }

    /**
     * Uses a line-wise input array of Strings and formats it for output.
     * @param inputStrings
     * @return String of output.
     */
    public static String styleResponse(ArrayList<String> inputStrings) {
        String returnBuffer = "     --------------------\n";
        for (String line : inputStrings) {
            returnBuffer = returnBuffer.concat("     " + line + "\n");
        }
        return returnBuffer + "\n     --------------------";
    }
}
