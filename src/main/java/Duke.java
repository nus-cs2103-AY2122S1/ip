import java.util.*;
import java.util.logging.Logger;

public class Duke {
	
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("Duke");
        boolean isActive = true;
        ArrayList<Task> list = new ArrayList<>(100);
        String welcomeMsg = "Hey, I'm Duke.\n"
                + "What's up?\n";
        String exitMsg = "Bye! Hope I helped!\n"
                + "See you next time :)\n";

        System.out.println(welcomeMsg);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Scanner sc = new Scanner(System.in);

        while (isActive) {
            String[] inputs = sc.nextLine().split("\\s+", 2);
            String eventType = inputs[0];
            String remainder = inputs.length == 1 ? null : inputs[1];

            if (eventType.equals("bye")) { // Exit routine
                isActive = false;
            } else if (eventType.equals("list")) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(arrayToString(list));
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            } else if (eventType.equals("done")) {
                int index = Integer.parseInt(remainder) - 1;
                if (index < 0 || index > list.size() - 1) {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Something went wrong.. to mark as done,\n" +
                                        "format your text as <done [number]>.\n");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    // Throw exception in the future
                } else {
                    list.get(index).markAsDone();
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Awesome! I marked this as done:\n" +
                            list.get(index).toString());
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                }
            } else if (eventType.equals("todo")) {
                Todo event = new Todo(remainder);
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Alright! I added this task: \n" + event.toString() + "\n");
                list.add(event);
                System.out.println(String.format("You have %d task(s) at the moment!\n", list.size()));
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            } else if (eventType.equals("deadline")) {
                String[] tokens = remainder.split(" /by ", 2);
                Deadline event = new Deadline(tokens[0], tokens[1]);
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Alright! I added this task: \n" + event.toString() + "\n");
                list.add(event);
                System.out.println(String.format("You have %d task(s) at the moment!\n", list.size()));
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            } else if (eventType.equals("event")) {
                String[] tokens = remainder.split(" /at ", 2);
                Event event = new Event(tokens[0], tokens[1]);
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Alright! I added this task: \n" + event.toString() + "\n");
                list.add(event);
                System.out.println(String.format("You have %d task(s) at the moment!\n", list.size()));
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            } else {
                System.out.println("Input not understood. Try again.\n");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
        }

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(exitMsg);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static String arrayToString(ArrayList<Task> list) {
        String answer = "";
        int counter = 1;
        for (Task item : list) {
            answer += String.format("%d: %s\n", counter, item.toString());
            counter++;
        }
        return answer;
    }
}
