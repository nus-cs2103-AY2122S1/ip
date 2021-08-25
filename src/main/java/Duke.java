import java.io.IOException;
import java.util.*;

public class Duke {


    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void run() throws IOException {
        ui.showWelcome();
        Parser duke = new Parser(this.tasks);
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            ui.showLine();
            String str = sc.nextLine();
            duke.parse(str);
            ui.showLine();
            storage.save(tasks);
        }


    }

    public static void main(String[] args) throws IOException {
        new Duke("C:\\Users\\65906\\IdeaProjects\\ip\\duke.txt").run();
    }


}
