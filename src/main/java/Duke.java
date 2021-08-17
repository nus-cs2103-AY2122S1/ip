import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String chatbot = "Ailurus";
        String you = "You";
        boolean endChat = false;
        Scanner scanner = new Scanner(System.in);
        Task[] list = new Task[100];
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
                        System.out.println(String.format("%d.[%s] %s", i + 1, list[i].getStatusIcon(),
                                list[i].getDescription()));
                    }
                    break;
                case "done":
                    String str = scanner.next();
                    try {
                        int taskNo = Integer.parseInt(str);
                        if (taskNo <= listLen) {
                            Task task = list[taskNo - 1];
                            task.markAsDone();
                            System.out.println(String.format("%s: Nice! I've marked this task as done:\n\t[%s] %s",
                                    chatbot, task.getStatusIcon(), task.getDescription()));
                        } else {
                            System.out.println(String.format("%s: Error in finding task number: %d", chatbot, taskNo));
                        }

                    } catch (NumberFormatException e) {
                        System.out.println(String.format("%s: Error in parsing number: %s", chatbot, str));
                    }
                    break;
                default:
                    list[listLen] = new Task(input);
                    System.out.println(String.format("%s: added: %s", chatbot, input));
                    listLen++;
            }
        }
        System.out.println(String.format("%s: Bye. Hope to see you again soon!", chatbot));
    }
}
