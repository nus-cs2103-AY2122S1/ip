import java.util.*;

public class Duke {
	
    public static void main(String[] args) {
        boolean isActive = true;
        ArrayList<String> list = new ArrayList<>(100);
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
            } else {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("added: " + input + "\n");
                list.add(input);
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
        }

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(exitMsg);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static String arrayToString(ArrayList<String> list) {
        String answer = "";
        int counter = 1;
        for (String item : list) {
            answer += String.format("%d: %s\n", counter, item);
            counter++;
        }
        return answer;
    }
}
