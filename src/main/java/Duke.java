import java.util.Scanner;

public class Duke {

    public static String endWord = "bye";

    private void greetEchoExit() {
        System.out.println("Hello, what can I do for you.\n");
        Scanner sc = new Scanner(System.in);
        String inputWord = sc.nextLine();
        while(!inputWord.equals(Duke.endWord)) {
            System.out.println(inputWord + "\n");
            inputWord = sc.nextLine();
        }
        System.out.println("Bye bye");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.greetEchoExit();
    }

}
