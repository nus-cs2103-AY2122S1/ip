import java.util.*;

public class Duke {

    public static void main(String[] args) {
        //To do list storage DS
        ArrayList<Task> toDo = new ArrayList<>();

        // Some String format
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String start = "____________________________________________________________\n";
        String end = "____________________________________________________________";
        String welcome = "Hello! I'm Duke. A friendly chatbot!! :)\n" +
                    "What can I do for you?\n";
        String end_message = "Bye. I hope to talk to you again soon! :)";
        String prompt_message = "Add to-do list ({input})/ View list (list) / Complete task (done {input}) / End (bye) :";

        //Print welcome message to the user
        System.out.println(start + logo + "\n" +  welcome + end);

        //Init new scanner to take in inputs
        Scanner s = new Scanner(System.in);
        System.out.println(prompt_message);
        String input = s.nextLine();
        Task t = new Task(input);

        //A loop to check for bye. Else will echo the users input
        while (!input.equals("bye")) {
            if (!input.equals("list") && !input.contains("done")) {
                System.out.println(start + "added: " + input + "\n" + end);
                toDo.add(t);
                System.out.println(prompt_message);
                input = s.nextLine();
                t = new Task(input);
            }

            else if (input.contains("done")) {
                System.out.println("Nice! I've marked this task as done: ");
                int i = Integer.valueOf(input.substring(5));
                toDo.get((int) i - 1).markAsDone();
                String res = (i) + ". " + toDo.get(i-1).toString();
                System.out.println(res);
                System.out.println(prompt_message);
                input = s.nextLine();
                t = new Task(input);
            }

            else if (input.equals("list")) {
                //print the list in order
                System.out.println(end);
                System.out.println("Your to-do list:");
                for (int i = 0; i < toDo.size(); i++) {
                    String res = (i + 1) + ". " + toDo.get(i).toString();
                    System.out.println(res);
                }
                System.out.println(end);
                System.out.println(prompt_message);
                input = s.nextLine();
                t = new Task(input);
            }
        }

        //If the input is bye, return this message
        System.out.println(start + end_message + "\n" + end);
        s.close();
    }
}
