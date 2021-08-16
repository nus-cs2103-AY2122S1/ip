import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private static ArrayList<String> taskList = new ArrayList<String>();

    public static int handleInput() {
        Scanner scanner = new Scanner(System.in);
        String input;
        do{
            input = scanner.nextLine();
            switch (input.toUpperCase()) {
                case "BYE":
                    System.out.println("Bai bai!");
                    break;
                case "LIST":
                    System.out.println("Here are your tasks!");
                    for (int i = 0; i<taskList.size(); i++) {
                        System.out.println(String.format("%d. %s", i+1, taskList.get(i)));
                    }
                    break;
                default:
                    taskList.add(input);
                    System.out.println("Added: " + input);
            }

        }
        while (!input.toUpperCase().equals("BYE"));
        return 0;
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n";
        String greeting = "Hello! I'm Duke.\n" + "What can I do for you? :)";
        System.out.println(logo + greeting);

        handleInput();
    }

}
