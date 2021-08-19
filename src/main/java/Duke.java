import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> xs = new ArrayList<>();

    public static String lineProducer() {
        return "   ------------------------------------------------------------------";
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
                System.out.println(lineProducer() + indentationAdder() + "Great job! I've marked the following as done" +
                        indentationAdder() + taskToChange + "\n" + lineProducer());
                continue;
            } else if (str.contains("deadline")) {
                if (!str.contains("/")) {
                    System.out.println(lineProducer() + indentationAdder() + "Sorry please indicate your deadline time with a '/by' after your deadline title!");
                    System.out.println(lineProducer());
                    continue;
                }
                int endDescription = str.indexOf("/");
                if (endDescription <= 9) {
                    System.out.println(lineProducer() + indentationAdder() + "Oh no! Deadlines cannot be empty! Please try again");
                    System.out.println(lineProducer());
                    continue;
                }
                String description = str.substring(9, endDescription - 1);
                if (description.equals("") || description.equals(" ")) {
                    System.out.println(lineProducer() + indentationAdder() + "Oh no! Deadlines cannot be empty! Please try again");
                    System.out.println(lineProducer());
                    continue;
                }
                String endTime = str.substring(endDescription + 4);
                Deadline dl = new Deadline(description, endTime);
                xs.add(dl);
                System.out.println(lineProducer() + indentationAdder() + "Understood! The following task has been added:" + indentationAdder() + " " + dl);
                System.out.println("    You have " + xs.size() + " " + (xs.size() == 1? "task" : "tasks" ) + " in your current list");
                System.out.println(lineProducer());
                continue;
            } else if (str.contains("event")) {
                if (!str.contains("/")) {
                    System.out.println(lineProducer() + indentationAdder() + "Sorry please indicate a time your event begins with a '/at' after your event title!");
                    System.out.println(lineProducer());
                    continue;
                }
                int endDescription = str.indexOf("/");
                if (endDescription < 6) {
                    System.out.println(lineProducer() + indentationAdder() + "Oh no! Events cannot be empty! Please try again");
                    System.out.println(lineProducer());
                    continue;
                }
                String description = str.substring(6, endDescription - 1);
                if (description.equals("") || description.equals(" ")) {
                    System.out.println(lineProducer() + indentationAdder() + "Oh no! Events cannot be empty! Please try again");
                    System.out.println(lineProducer());
                    continue;
                }
                String endTime = str.substring(endDescription + 4);
                Event ev = new Event(description, endTime);
                xs.add(ev);
                System.out.println(lineProducer() + indentationAdder() + "Understood! The following task has been added:" + indentationAdder() + " " + ev);
                System.out.println("    You have " + xs.size() + " " + (xs.size() == 1? "task" : "tasks" ) + " in your current list");
                System.out.println(lineProducer());
                continue;
            } else if (str.contains("todo")) {
                if (str.length() < 5) {
                    System.out.println(lineProducer() + indentationAdder() + "Oh no! ToDos cannot be empty! Please try again");
                    System.out.println(lineProducer());
                    continue;
                }
                String description = str.substring(5);
                if (description.equals("") || description.equals(" ")) {
                    System.out.println(lineProducer() + indentationAdder() + "Oh no! ToDos cannot be empty! Please try again");
                    System.out.println(lineProducer());
                    continue;
                }
                Todo td = new Todo(description);
                xs.add(td);
                System.out.println(lineProducer() + indentationAdder() + "Understood! The following task has been added:" + indentationAdder() + " " + td);
                System.out.println("    You have " + xs.size() + " " + (xs.size() == 1? "task" : "tasks" ) + " in your current list");
                System.out.println(lineProducer());
                continue;
            }
            System.out.println(lineProducer() + indentationAdder() + "I'm sorry :( I don't quite seem to understand, try again pls!");
            System.out.println(lineProducer());
        }

        sc.close();
    }
}
