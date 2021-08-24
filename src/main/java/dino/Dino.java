package dino;

import dino.command.Command;
import dino.exception.*;
import dino.task.TaskList;
import dino.util.Parser;
import dino.util.Ui;
import dino.util.Storage;

public class Dino {
    private TaskList taskList;
    private final Storage storage;
    private Ui ui;
    private boolean isExited = false;

    public Dino(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(this.storage.loadStorage());
        this.ui = new Ui();
    }

    public void run() {
        ui.greeting();
        while(!this.isExited) {
            String input = ui.readNextLine();
            if (input.equals("bye")) isExited = true;
            else {
                try {
                    Command cmd = Parser.parse(input);
                    cmd.execute(this.storage, this.taskList);
                } catch (DinoException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        ui.processExit(this.storage, this.taskList);
    }

    public static void main(String[] args) {
        new Dino("data/dino.txt").run();
    }
}
