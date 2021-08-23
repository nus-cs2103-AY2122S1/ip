package duke;

import duke.task.DukeList;

public class Duke {

    private final Storage STORAGE;

    private final Ui UI;

    public Duke(String path) {
        DukeList LIST = new DukeList();
        this.STORAGE = new Storage(path, LIST);
        this.UI = new Ui(LIST, this.STORAGE);
    }

    public void run() {
        this.STORAGE.load();
        this.UI.run();
    }

    public static void main(String[] args) {
        String PATH = System.getProperty("user.dir");

        new Duke(PATH).run();
    }
}
