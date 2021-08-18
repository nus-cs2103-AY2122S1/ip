import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final int lv = 3;
    private static final String[] features = {"", "Greet, Echo, Exit", ", Add, List", ", Mark as Done"};
    private static boolean canExit = false;
    private static final ArrayList<Task> taskArrayList = new ArrayList<>();

    /**
     * This function takes an input string and formats it by including horizontal lines above
     * and below the input string
     *
     * @param str input string to be sandwiched
     * @return the original string sandwiched between two horizontal lines
     */
    public static String sandwich(String str) {
        return "____________________________________________________________\n"
                + str + "\n"
                + "____________________________________________________________";
    }

    public static void main(String[] args) {
        // commented out logo
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        // populating featuresCombined so each level has all elements of levels before it
        StringBuilder featuresCombined = new StringBuilder();
        for (int count = 0; count <= lv; count++) {
            featuresCombined.append(features[count]);
        }

        // Welcome message
        String welcome = "Hello! I'm Duke: Level " + lv + "\n"
                + "What would you like to do today?\n"
                + "My current features are: " + featuresCombined;

        System.out.println(sandwich(welcome));

        // Goodbye message
        String goodbye = "Thank you for using Duke: Level " + lv + "\n"
                + "See you soon!";

        // Scanner to read user inputs
        Scanner scanner = new Scanner(System.in);

        while (!canExit) {
            String userInput = scanner.next();
            if (userInput.equals("bye")) { // user inputs "bye, set canExit to true and Exit
                canExit = true;
                System.out.println(sandwich(goodbye));
            } else { // user inputs "done", "list" or something else
                if (userInput.equals("done")) { // first input is done, check second input for integer
                    if (scanner.hasNextInt()) {
                        int taskNum = scanner.nextInt();
                        taskArrayList.get(taskNum - 1).markAsDone();
                        System.out.println(sandwich("Congratulations! You finished task "
                                + taskNum
                                + ": [X] "
                                + taskArrayList.get(taskNum - 1).description));
                    }
                } else if (userInput.equals("list")) { // user inputs 'list', return all text stored
                    StringBuilder userInputsList = new StringBuilder();
                    for (int i = 0; i < taskArrayList.size() - 1; i++) { // new line except for last item
                        userInputsList.append(i + 1).append(".")
                                .append("[")
                                .append(taskArrayList.get(i).getStatusIcon())
                                .append("] ")
                                .append(taskArrayList.get(i).description).append("\n"); // formatted for nicer look
                    }
                    userInputsList.append(taskArrayList.size()).append(".")
                            .append("[")
                            .append(taskArrayList.get(taskArrayList.size() - 1).getStatusIcon())
                            .append("] ")
                            .append(taskArrayList.get(taskArrayList.size() - 1).description); // no \n for last item
                    System.out.println(sandwich(userInputsList.toString()));
                } else { // store userInput, tell user their input has been added
                    taskArrayList.add(new Task(userInput));
                    System.out.println(sandwich("Task added: " + userInput));
                }
            }
        }
    }
}
