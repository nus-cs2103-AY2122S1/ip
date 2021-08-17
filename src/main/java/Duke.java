import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String chatbot = "Ailurus";
        String you = "You";
        boolean endChat = false;
        Scanner scanner = new Scanner(System.in);
        String[] list = new String[100];
        int listLen = 0;

        System.out.println(String.format("%s: Hello! I'm %s. What can I do for you?", chatbot, chatbot));
        while (!endChat) {
            System.out.print(you + ": ");
            String input = scanner.next();
            switch (input) {
                case "bye":
                    endChat = true;
                    break;
                case "list":
                    System.out.println(chatbot + ":");
                    if (listLen == 0) {
                        System.out.println("No item in list!");
                    }
                    for(int i = 0; i < listLen; i++) {
                        System.out.println(String.format("%d. %s", i + 1, list[i]));
                    }
                    break;
                default:
                    list[listLen] = input;
                    System.out.println(String.format("%s: added: %s", chatbot, input));
                    listLen++;
            }
        }
        System.out.println(String.format("%s: Bye. Hope to see you again soon!", chatbot));
    }
}
