import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<String> textList = new ArrayList<>();

    private static void lineSpacing() {
        System.out.println("____________________________________________________________");
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                lineSpacing();
                System.out.println("Bye. Hope to see you again soon!");
                lineSpacing();
                break;
            }
            switch (userInput) {
                case "list":
                    lineSpacing();
                    for(int i = 0; i < textList.size(); i++) {
                        System.out.println(String.format("%d. %s", i + 1, textList.get(i)));
                    }
                    lineSpacing();
                    break;
                default:
                    textList.add(userInput);
                    lineSpacing();
                    System.out.println(String.format("added : %s", userInput));
                    lineSpacing();

            }
        }
    }
}
