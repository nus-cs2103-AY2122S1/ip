import java.util.Scanner;

public class Duke {
    public Duke() {}
    private static String greetText = "Hello I'm Duke\nWhat can I do for you?\n";
    private static String exitText = "Bye. Hope to see you again soon!";
    private static String dukeLogo = " ____        _        \n"
                                   + "|  _ \\ _   _| | _____ \n"
                                   + "| | | | | | | |/ / _ \\\n"
                                   + "| |_| | |_| |   <  __/\n"
                                   + "|____/ \\__,_|_|\\_\\___|\n";
    private static String[] dukeList = new String[100];
    private static int listCount = 0;

    public void greet() {
        System.out.print(greetText);
    }
    public void addToList(String input) {
        dukeList[listCount] = input;
        listCount++;
        System.out.println("added: " + input);
    }
    public void exit() {
        System.out.println(exitText);
    }

    public void showList() {
        int number = 1;
        for (String item : dukeList) {
            if (item != null) {
                System.out.println(number +". " + item);
                number++;
            }
        }
    }

    public void start() {
        greet();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("bye")) {
                exit();
                break;
            } else if (input.equals("list")) {
                showList();
            } else {
                addToList(input);
            }
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
