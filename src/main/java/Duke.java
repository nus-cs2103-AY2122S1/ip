import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {



    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        initialiseBot();

        boolean status = true;
        List<String> textStored = new ArrayList<>();
        int numberOfText = 0;

        Scanner sc = new Scanner(System.in);

        while (status) {
            String message = sc.nextLine();

            if (message.equals("bye")) {
                status = false;
                closeBot();
            } else if (message.equals("list")) {
                printStoredText(textStored);
            } else {
//                echo(message);
                numberOfText++;
                add(message, numberOfText, textStored);
            }
        }

        sc.close();

    }

    private static void printStoredText(List<String> textStored) {
        String message = "Here are the tasks in your list:";
        System.out.println(message);
        textStored.forEach(System.out::println);
    }

    private static void add(String message, int numberOfText, List<String> textStored) {
        String displayedMessage = "added: " + message;
        String storedMessage = String.valueOf(numberOfText) + ". " + message;
        textStored.add(storedMessage);
        System.out.println(displayedMessage);
    }

    private static void closeBot() {
        String message = "Bye. Hope to see you again soon!";
        System.out.println(message);
    }

    private static void echo(String message) {
        System.out.println(message);
    }

    private static void initialiseBot() {
        String message = "Hello! I'm Duke \n"
                + "What can I do for you?";
        System.out.println(message);
    }


}
