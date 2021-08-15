import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Duke {
    // constant declarations
    private final static String LINE_BREAK = "--------------------------\n";
    
    // class static attributes
    private final static List<String> taskList = new ArrayList<String>();

    /**
     * Wrap a string between 2 line breaks
     * @param s String to be wrapped 
     * @return New string between 2 line breaks
     */
    private static String wrapBetweenLines(String s) {
        return LINE_BREAK + s + "\n" + LINE_BREAK;
    }

    /**
     * Run tasks based on the corresponding commands given by the user
     * @param s Command input of user
     */
    private static void handleInput(String s) {
        switch(s) {
            case "bye":
                System.out.println(wrapBetweenLines("Bye. Hope to see you again soon!"));
                break;
            case "list":
                AtomicInteger idx = new AtomicInteger(1);
                String outputList = taskList.stream()
                    .map(task -> Integer.toString(idx.getAndIncrement()) + ". " + task + "\n")
                    .collect(Collectors.joining());
                System.out.println(wrapBetweenLines(outputList));
                break;
            default:
                taskList.add(s);
                System.out.println(wrapBetweenLines("added task: " + s));

        }
    }

    /**
     * Prompts users to input their commands to Duke
     */
    private static void promptUserCommands() {
        String introduction = wrapBetweenLines("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(introduction);

        String TERMINATE_COMMAND = "bye";
        String userInput = "";
        Scanner reader = new Scanner(System.in);

        while(true) {
            userInput = reader.nextLine();
            handleInput(userInput);
            if (userInput.equals(TERMINATE_COMMAND)) {
                break;
            }
            
        }
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
