import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static int lv = 2;
    private static String[] features = {"", "Greet, Echo, Exit", ", Add, List"};
    private static boolean canExit = false;

    /**
     * This function takes an input string and formats it by including horizontal lines above
     * and below the input string
     *
     * @param str
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

        // storing userInput
        ArrayList<String> inputs = new ArrayList<>();

        while (!canExit) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) { // user inputs 'bye', set canExit to true and Exit
                canExit = true;
                System.out.println(sandwich(goodbye));
            } else { // user inputs 'list' or something else
                if (userInput.equals("list")) { // user inputs 'list', return all text stored
                    StringBuilder userInputsList = new StringBuilder();
                    for (int i = 0; i < inputs.size() - 1; i++) { // new line except for last item
                        userInputsList.append(i + 1).append(". ").append(inputs.get(i)).append("\n");
                    }
                    userInputsList.append(inputs.size()).append(". ")
                            .append(inputs.get(inputs.size() - 1));
                    System.out.println(sandwich(userInputsList.toString()));
                } else { // store userInput, tell user their input has been added
                    inputs.add(userInput);
                    System.out.println(sandwich("added: " + userInput));
                }
            }
        }
    }
}
