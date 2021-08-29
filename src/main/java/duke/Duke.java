package duke;


/**
 * Main class for the Duke.
 */
public class Duke {

    /**
     * Main method for Duke which runs the program.
     * @param args main method args.
     */
    public static void main(String[] args) {
        GreetingBot newBot = new GreetingBot();
        newBot.startBot("./data/list.txt");

    }


}




