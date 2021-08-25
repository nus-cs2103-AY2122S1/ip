public class Duke {

    private static void runDuke() {
        DukeList list = new DukeList();
        Ui.greet();
        Ui.display(Storage.load(list));
        Ui.begin();
        while (true) {
            String input = Ui.readLine();
            DukeCommand command = Parser.parseInput(input);
            if (command instanceof ExitCommand) {
                break;
            }
            try {
                Ui.display(command.run(list));
            } catch (DukeException e) {
                Ui.display(e.getMessage());
            }
        }
        try {
            Storage.saveFile(list);
            Ui.display("File saved");
        } catch(CorruptedFileException e) {
            Ui.display("Error saving file");
        }
        Ui.bye();
    }

    public static void main(String[] args) {
        runDuke();
    }

}
