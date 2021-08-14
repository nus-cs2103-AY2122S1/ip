import java.util.Scanner;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 * Current Progress: Level 2. Add, List
 *
 * Description:
 * On running the program, Duke greets the user and awaits for inputted text.
 * Program stores whatever text entered by the user and display them back
 * to the user when requested.
 *
 * @author Keith Tan
 */
public class Duke {

    private static final String horizontalLine = "____________________________________________________________";
    private static String[] textList;

    /**
     * Stores text entered by user into list
     *
     * @param newText new text entered by user to be stored
     */
    private static void addText(String newText) {

        if (textList == null) {
            textList = new String[1];
            textList[0] = newText;
        } else {

            String[] newTextList = new String[textList.length + 1];
            for (int i = 0; i < textList.length; i++) {
                newTextList[0] = textList[0];
            }
            newTextList[textList.length] = newText;
            textList = newTextList;

        }

    }

    /**
     * Prints out message according to desired format to user
     *
     * @param message message to bve printed
     */
    private static void printMessage(String message) {

        String finalMessage = horizontalLine
                + "\n" + message + "\n"
                + horizontalLine;
        System.out.println(finalMessage);
    }

    /**
     * Creates list string according to current list size
     *
     * @return String returns current text list as a string
     */
    private static String createListString() {

        if (textList == null) {
            return "Enter Text First!";
        } else {
            int lens = textList.length;
            String listString = "";
            for (int i = 0; i < lens; i++ ) {
                String nextItem = String.format("%d. %s", (i + 1), textList[i]);
                listString = listString + nextItem + "\n";
            }
            return listString;
        }
    }

    public static void main(String[] args) {
        //initialize program
        String currentCommand = "greeting";
        String greeting = "Hello! I'm Duke"
                + "\nWhat can I do for you?";
        printMessage(greeting);
        Scanner commandScanner = new Scanner(System.in);

        //awaits text
        while (!currentCommand.equals("bye")) {
            currentCommand = commandScanner.nextLine();
            if (currentCommand.equals("bye")) {
                String byeString = "Bye. Hope to see you again soon!";
                printMessage(byeString);

            } else if (currentCommand.equals("list")) {
                printMessage(createListString());

            } else {
                //ensures user enters some text
                if (currentCommand.equals("")) {
                    printMessage("Please enter some text");
                } else {
                    String[] checkWhiteSpace = currentCommand.split(" ");
                    if (checkWhiteSpace.length == 0) {
                        printMessage("Please enter some text");
                    } else {
                        addText(currentCommand);
                        String newText = "added: " + currentCommand;
                        printMessage(newText);
                    }

                }

            }

        }
    }
}