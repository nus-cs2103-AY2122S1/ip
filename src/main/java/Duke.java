import java.util.*;

public class Duke {

    public static void main(String[] args) {
        //To do list storage DS
        ArrayList<String> toDo = new ArrayList<>();

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

        //Print welcome message to the user
        System.out.println(start + welcome + end);

        //Init new scanner to take in inputs
        Scanner s = new Scanner(System.in);
        System.out.println("Add to-do list (input)/ View list(list) / End (bye) :");
        String input = s.nextLine();

        //A loop to check for bye. Else will echo the users input
        while (!input.equals("bye")) {
            if (!input.equals("list")) {
                System.out.println(start + "added: " + input + "\n" + end);
                toDo.add(input);
                System.out.println("Add to-do list (input)/ View list(list) / End (bye) :");
                input = s.nextLine();
            }

            else if (input.equals("list")) {
                //print the list in order
                System.out.println(end);
                System.out.println("Your to-do list:");
                for (int i = 0; i < toDo.size(); i++) {
                    String res = (i + 1) + ". " + toDo.get(i);
                    System.out.println(res);
                }
                System.out.println(end);
                System.out.println("Add to-do list (just input)/ View list(command: list) / End (command: bye) :");
                input = s.nextLine();
            }
        }

        //If the input is bye, return this message
        System.out.println(start + end_message + "\n" + end);
        s.close();
    }
}
