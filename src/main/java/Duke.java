import java.util.Scanner;

/**
 * Main class for Duke, scans for inputs and responds to user.
 */
public class Duke {

    public static final String[] welcomeMsg = new String[] {"What's up, I'm duke!"};

    /**
     * Main execution when Duke is run.
     *
     * @param args Will not be used
     */
    public static void main(String[] args) {

        printMsg(welcomeMsg);

        try {
            Task.readCache();
        } catch (DukeException e){
            printMsg(new String[] {e.getMessage()});
        }

        try {
            Task.initializeCache();
        } catch (DukeException e){
            printMsg(new String[] {e.getMessage()});
        }

        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while(!exit) {
            try {
                if (sc.hasNextLine()) {
                    String nextInput = sc.nextLine();
                    Task.cacheInput(nextInput);
                    String[] resultMsg = Task.processInput(nextInput);
                    printMsg(resultMsg);
                    if (Task.isByeMsg(resultMsg)) {
                        exit = true;
                    }
                } else {
                    exit = true;
                }
            } catch(DukeException e) {
                printMsg(new String[] {e.getMessage()});
            }

        }

        sc.close();
    }

    /**
     * Prints out an array of messages.
     *
     * @param msgs Messages to be printed, as a string array.
     */
    private static void printMsg(String[] msgs) {
        System.out.println("    ---");
        for (String msg : msgs) {
            System.out.println("    " + msg);
        }
        System.out.println("    ---");
    }
}
