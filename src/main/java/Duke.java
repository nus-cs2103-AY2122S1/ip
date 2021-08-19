import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> xs = new ArrayList<>();

    public static String lineProducer() {
        return "   -----------------------------------------";
    }

    public static String indentationAdder() {
        return "\n    ";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(lineProducer() + indentationAdder() + "Hello I'm your friendly chatbot Duke!" +
                indentationAdder() + "What can I help you with?\n" + lineProducer() );
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                System.out.println(lineProducer() + indentationAdder() + "Bye! Please visit me again!\n" + lineProducer());
                break;
            } else if (str.equals("list")) {
                System.out.println(lineProducer());
                boolean allDone = true;
                for (int i = 0; i < xs.size(); i++) {
                    System.out.println("    " + (i + 1) + ": " + xs.get(i));
                    if (!xs.get(i).getIsDone()) {
                        allDone = false;
                    }
                }
                if (allDone) {
                    System.out.println("    " + "Congratulations! You've completed all your tasks!");
                }
                System.out.println(lineProducer());
                continue;
            } else if (str.contains("done")) {
                int doneNumber = Integer.parseInt(str.substring(5));
                if ((doneNumber > xs.size() || doneNumber < 0)) {
                    System.out.println(lineProducer() + indentationAdder() + "Uh oh! Item " + doneNumber + " does not seem to exist!\n" + lineProducer());
                    continue;
                }
                Task taskToChange = xs.get(doneNumber - 1);
                taskToChange.changeIsDone(true);
                System.out.println(lineProducer() + indentationAdder() + "Great job! I've marked the following as done\n" +
                        indentationAdder() + taskToChange + "\n" + lineProducer());
                continue;
            }
            xs.add(new Task(str));
            System.out.println(lineProducer() + indentationAdder() + "added: " + str + "\n" + lineProducer());
        }

        sc.close();
    }
}
