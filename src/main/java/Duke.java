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

            String input = sc.nextLine();
            if (input.equals("bye")) { // Exit routine
                isActive = false;
            } else if (input.equals("list")) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(arrayToString(list));
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            } else if (input.length() >= 4 && input.substring(0, 4).equals("done")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
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
            } else {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("added: " + input + "\n");
                list.add(new Task(input));
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
