import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String[] storedMessages = new String[100];
        int idx = 0;
        String echoInput = sc.nextLine();
        while (!echoInput.equals("bye")) {
            if (echoInput.equals("list")) {
                for (int i = 0; i < idx; i++) {
                    System.out.println(i + ": " + storedMessages[i]);
                }
            } else {
                storedMessages[idx] = echoInput;
                System.out.println("added: " + echoInput);
                idx++;
            }
            echoInput = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
