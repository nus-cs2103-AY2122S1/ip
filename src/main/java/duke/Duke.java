package duke;

import duke.task.DukeList;

public class Duke {
    private DukeList list;

    private Storage storage;

    private Ui ui;

    public Duke(String path) {
        this.list = new DukeList();
        this.storage = new Storage(path, this.list);
        this.ui = new Ui(this.list);
    }

    public void run() {
        this.storage.load();
        this.ui.run();
        this.storage.save();
    }

    public static void main(String[] args) {
        String PATH = System.getProperty("user.dir");

        new Duke(PATH).run();
    }
}
