import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String startText = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(startText);
        Scanner sc = new Scanner(System.in);
        ArrayList<String> savedInputs = new ArrayList<>();
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                String closingMessage =  "Goodbye! Hope to see you again soon!\n";
                System.out.println(outputTemplate(closingMessage));
                sc.close();
                break;
            } else if (command.equals("list")) {
                String list = "";
                for (int i = 0; i < savedInputs.size(); i++) {
                    list += Integer.toString(i + 1) + ". " + savedInputs.get(i) + "\n";
                }
                System.out.println(outputTemplate(list));
            } else {
                savedInputs.add(command);
                System.out.println(outputTemplate(command + "\n"));
            }
        }
    }

    private static String outputTemplate(String output) {
        String line = "~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~";
        return line + "\n" + output + line;
    }
}
