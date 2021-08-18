import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String welcomeLine = "Hello! I'm Duke\n" + "What can I do for you?\n";
        String goodbyeLine = "Bye. Hope to see you again soon!";
        System.out.println(welcomeLine);
        boolean isRunning = true;
        String input;
        Task[] listArray = new Task[100];
        int count = 0;
        int index;
        Scanner sc = new Scanner(System.in);
        do {
            input = sc.next(); //Reads the next input
            switch (input) {
                case "bye":  //Shuts down the bot when it gets a "bye" prompt
                    isRunning = false;
                    System.out.println(goodbyeLine);
                    break;
                case "list":  //Returns the list when it gets a "list" prompt
                    for (int i = 0; i < count; i++) {
                        System.out.println(i + 1 + ". " + listArray[i]);
                    }
                    break;
                case "done":  //Marks the task as done when it gets the "done x" prompt and updates the task as well.
                    index = sc.nextInt();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + listArray[index - 1].done());
                    break;
                case "todo":  //Inputs a todo task when given the "todo" prompt
                    input = sc.nextLine();
                    listArray[count++] = new Todo(input);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + listArray[count - 1]);
                    System.out.println("Now you have " + count + " in the list.");
                    break;
                case "deadline": //Inputs a Deadline task when given the "deadline" prompt
                    input = sc.nextLine();
                    listArray[count++] = new Deadline(input.split(" /by ")[0], input.split(" /by ")[1]);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + listArray[count - 1]);
                    System.out.println("Now you have " + count + " in the list.");
                    break;
                case "event": //Inputs an Event task when given the "event" prompt
                    input = sc.nextLine();
                    listArray[count++] = new Event(input.split(" /at ")[0], input.split(" /at ")[1]);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + listArray[count - 1]);
                    System.out.println("Now you have " + count + " in the list.");
                    break;
                default:
                    System.out.println("Oh No! I can't seem to understand you!");
                    System.out.println("Try something else :(");
                    System.out.println("But please don't leave me :((((((");
                    break;
            }
        } while (isRunning);
    }
}
