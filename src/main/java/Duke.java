import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String startText = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(startText);
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String command = sc.next();
            if (command.equals("bye")) {
                String closingMessage =  "Goodbye! Hope to see you again soon!";
                System.out.println(outputTemplate(closingMessage));
                sc.close();
                break;
            } else {
                System.out.println(outputTemplate(command));
            }
        }
    }

    private static String outputTemplate(String output) {
        String line = "~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~";
        return line + "\n" + output + "\n" + line;
    }
}
