import java.util.Scanner;

public class Duke {
    private static String LINE_BREAK = "--------------------------\n";

    /**
     * Wrap a string between 2 line breaks
     * @param s String to be wrapped 
     * @return New string between 2 line breaks
     */
    private static String wrapInLineBreaks(String s) {
        return LINE_BREAK + s + "\n" + LINE_BREAK;
    }

    /**
     * Prompts users to input their commands to Duke
     */
    private static void promptUserCommands() {
        String introduction = wrapInLineBreaks("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(introduction);

        String TERMINATE_COMMAND = "bye";
        String userInput = "";
        Scanner reader = new Scanner(System.in);

        while(true) {
            userInput = reader.nextLine();
            if (userInput.equals(TERMINATE_COMMAND)) {
                break;
            }
            System.out.println(wrapInLineBreaks(userInput));
            
        }
        System.out.println(wrapInLineBreaks("Bye. Hope to see you again soon!"));
        reader.close();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        promptUserCommands();
    }
}
