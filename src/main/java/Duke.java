
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Recieve recieve;

    public Duke(String filePath) {
        recieve = new Recieve();
        storage = new Storage(filePath);
//        try {
////            tasks = new TaskList(storage.load());
//            recieve.run(); //take in tasks storage
//        } catch (DukeException e) {
//            System.out.println("Creating new tasklist...");
////            ui.showLoadingError();
//            tasks = new TaskList();
//        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" +
                "To add a Todo, type -> todo <Description> \n" +
                "To add a Deadline, type -> deadline <Description> /by <deadline>\n" +
                "To add an Event, type -> event <Description> /at <details>\n" +
                "To mark as done, type -> done <task list index>\n" +
                "To see all of your tasks, type -> list\n" +
                "To end session, type -> bye\n" +
                "What can I do for you today?");



    }
}
