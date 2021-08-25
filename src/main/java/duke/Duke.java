package duke;

import java.util.Scanner;

public class Duke {

    private Command command;


    Duke(String filePath) {
        this.command = new Command(filePath);
    }

    public static void main(String[] args) {
        Duke duke = new Duke("file.text");
        duke.command.startDuke();

    }

}
