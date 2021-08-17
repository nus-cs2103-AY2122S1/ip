import java.util.Scanner;

public class Duke {
    private static String END_OF_CONVERSATION = "bye";

    private static void outputWrapper(String input) {
        String separator = "########################";

        System.out.println(separator);
        System.out.println(input);
        System.out.println(separator);
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
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello this is Jeeves, your personal chatbot. What can i do you for today?");

        echoInput();
    }
}
