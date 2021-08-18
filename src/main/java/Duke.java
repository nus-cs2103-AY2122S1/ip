import java.util.Scanner;

public class Duke {
    private static int lv = 1;
    private static String[] features = {"Nothing", "Greet, Echo, Exit"};
    private static String[] featuresCombined;
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
        // Welcome message
        String welcome = "Hello! I'm Duke: Level " + lv + "\n"
                + "What would you like to do today?\n"
                + "My current features are: " + features[lv];


        System.out.println(sandwich(welcome));

        // Goodbye message
        String goodbye = "Thank you for using Duke: Level " + lv + "\n"
                + "See you soon!";

        // Scanner to read user inputs
        Scanner scanner = new Scanner(System.in);
        while (!canExit) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                canExit = true;
                System.out.println(sandwich(goodbye));
            } else {
                System.out.println(sandwich("You have entered: " + userInput));
            }
        }
    }


}
