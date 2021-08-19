import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);



        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        ArrayList<Task> aList = new ArrayList<>();  // Initialize list
        int counter = 1;

        System.out.println("Hello! I'm Duke\n" +   // Welcome Message
                "What can I do for you?");

        String input = scanner.nextLine();  // Read user input
        while (!input.equals("bye")){
            switch(input.split(" ")[0]) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i <= aList.size(); i++){
                    System.out.println(String.valueOf(i) + ". " + aList.get(i - 1));
                    }
                    break;
                case "done":
                    System.out.println("Nice! I've marked this task as done");
                    Task tempTask = aList.get(Integer.parseInt(input.split(" ")[1])-1);
                    aList.set(Integer.parseInt(input.split(" ")[1])-1, tempTask.markDone());
                    System.out.println(aList.get(Integer.parseInt(input.split(" ")[1])-1));
                    break;
                case "todo":
                    System.out.println("Got it. I've added this task:");
                    aList.add(new ToDos(input, false));
                    System.out.println(" " + aList.get(aList.size() - 1));
                    System.out.println(String.format("Now you have %d tasks in the list",aList.size()));
                    break;
                case "deadline":
                    System.out.println("Got it. I've added this task:");
                    aList.add(new Deadlines(Deadlines.getNameInput(input), false, Deadlines.getDeadlineInput(input)));
                    System.out.println(" " + aList.get(aList.size() - 1));
                    System.out.println(String.format("Now you have %d tasks in the list",aList.size()));
                    break;
                case "event":
                    System.out.println("Got it. I've added this task:");
                    aList.add(new Events(Events.getNameInput(input), false, Events.getDeadlineInput(input)));
                    System.out.println(" " + aList.get(aList.size() - 1));
                    System.out.println(String.format("Now you have %d tasks in the list",aList.size()));
                    break;
                default:
                    aList.add(new Task(input,false));
                    counter++;
                    System.out.println("added: " + input);  // Output user input
            }

            input = scanner.nextLine();  // Read user input
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}
