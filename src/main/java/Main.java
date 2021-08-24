public class Main {
    public static void main(String[] args) {
        DukeDB db = new DukeDB("./data/dukeStore.txt");
        // Start listening to user input through a duke instance
        Duke duke = new Duke(db);
        duke.listen();
    }
}
