package duke;


public class MainDukeStarter {


    /**
     * The main method that starts the whole program.
     * @param args Any arguments passed through the command-line when starting the program.
     */
    public static void main(String[] args) {
        Duke currDuke = Duke.getCurrDuke();

        Duke.dukeStarter(currDuke);
    }
}
