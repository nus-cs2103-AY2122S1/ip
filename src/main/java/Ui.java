import java.util.Scanner;

public class Ui {

    public static final String[] welcomeMsg = new String[] {"What's up, I'm duke!"};

    public void run() {
        printMsg(welcomeMsg);

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
    public static void printMsg(String[] msgs) {
        System.out.println("    ---");
        for (String msg : msgs) {
            System.out.println("    " + msg);
        }
        System.out.println("    ---");
    }
}