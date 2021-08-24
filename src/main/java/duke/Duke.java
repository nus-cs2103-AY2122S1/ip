package duke;

import duke.storage.TaskList;

public class Duke {
    private Ui ui;

    public Duke() {
        this.ui = new Ui();
    }

    public void run() {
       ui.greet();

       String commandString;
       while (true) {
           commandString = ui.readCommand();
           try {
               if(CommandParser.isExit(commandString)) {
                   break;
               }
               String message = CommandParser.parse(commandString).run();
               ui.print(message);
           } catch (DukeException e) {
               ui.printError(e.getMessage());
           }
       }
       ui.bye();
    }

    public static void main(String[] args) {
       Duke xiri = new Duke();
       xiri.run();
    }
}
