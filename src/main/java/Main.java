import Duke.Main.Duke;

public class Main {
    /**
     * Main class
     * @param args argument for Main class
     */
    public static void main(String[] args) {
        Duke duke = new Duke("taskFile/taskList.txt");
        duke.run();
    }
}
