public class Duke {
    private TaskList taskStorage;
    private Ui ui;

    public Duke() {
        this.ui = new Ui();
        try {
            taskStorage = TaskList.getInstance();
        } catch (DukeException e) {
            ui.printError(e.getMessage());
        }
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
