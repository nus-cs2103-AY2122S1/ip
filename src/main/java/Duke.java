import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    public static void main(String[] args) {
        String end = "bye";
        String display = "list";
        String lineBreak = "------------------------------";
        String markDone = "done";
        ArrayList<Task> inputs = new ArrayList<>();


        // Welcome message
        System.out.println(lineBreak
                + "\n"
                + "Hello! I'm Azure.\n"
                + "How can I help you today?\n"
                + lineBreak);

        Scanner myObj = new Scanner(System.in);

        // String input
        while(true) {
            String input = myObj.nextLine();
            Task newTask = new Task(input);

            // mark a task as Done
            // regex: "done" + space + number
            // if number < inputs.size(), mark input[number] as done
            // Pattern pattern = Pattern.compile("done\\s\\d+");

            if (input.contains("done") && input.contains(" ")) {
                String[] parts = input.split(" ");
                int index = Integer.parseInt(parts[1]) - 1;
                if (parts[0].equals(markDone) && index < inputs.size()) {
                    inputs.get(index).setDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println("[X] " + inputs.get(index).taskName);
                    System.out.println(lineBreak + "\n");
                }
                continue;
            }

            // normal input
            if (!input.equals(end) && !input.equals(display)) {
                System.out.println("     added: " + input + "\n" + lineBreak + "\n");
                inputs.add(newTask);
                continue;
            }

            // display saved inputs
            if (input.equals(display)) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < inputs.size(); i++) {
                    int index = i + 1;
                    System.out.println(index + ". "
                            + "[" + inputs.get(i).getStatusIcon() + "] "
                            + inputs.get(i).taskName);
                }
                System.out.println(lineBreak + "\n");
                continue;
            }

            // exit
            System.out.println("     Bye! See you next time! :)" + "\n" + lineBreak + "\n");
            break;
        }
    }
}
