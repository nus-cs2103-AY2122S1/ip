import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello, I'm Duke!\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        boolean finished = false;
        String[] listOfWords = new String[100];
        int arrayCounter = 0;

        while(!finished) {
            String userResponse = sc.nextLine();

            switch (userResponse) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    finished = true;
                    break;
                case "list":
                    for (int i = 0; i < listOfWords.length; i++) {
                        if (listOfWords[i] != null) {
                            System.out.println((i + 1) + ". " + listOfWords[i]);
                        }
                    }
                    break;
                default:
                    System.out.println("added: " + userResponse);
                    listOfWords[arrayCounter] = userResponse;
                    arrayCounter++;
            }

        }
    }
}
