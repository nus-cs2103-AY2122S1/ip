package duke;

import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The Duke bot.
 *
 * @author Luo Zhijie
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private MainWindow main;

    /**
     * Constructor for Duke bot
     *
     * @param filePath Path to duke file.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
    }

    public Duke() {

    }

    public void setMain(MainWindow main){
        this.main = main;
        Timer timer = new Timer();
        System.out.println("hello");
        TimerTask task = new TimerTask(){
            public void run(){
                System.out.println("complete todo");
                Platform.runLater(() -> {
                    main.popReminder("reminder!");
                });
            }
        };
        TimerTask task2 = new TimerTask(){
            public void run(){
                System.out.println("complete todo");
                Platform.runLater(() -> {
                    main.popReminder("reminder!");
                });
            }
        };

        Calendar date = Calendar.getInstance();
        System.out.println("hello");
        date.set(Calendar.YEAR,2021);
        date.set(Calendar.MONTH,Calendar.SEPTEMBER);
        date.set(Calendar.DAY_OF_MONTH,15);
        date.set(Calendar.HOUR_OF_DAY,14);
        date.set(Calendar.MINUTE,29);
        date.set(Calendar.SECOND,30);
        date.set(Calendar.MILLISECOND,0);
        timer.schedule(task, date.getTime());

        date.set(Calendar.SECOND,50);
        timer.schedule(task2, date.getTime());
    }

    /**
     * Greets the user at the start.
     *
     * @return Greeting message.
     */
    public String greeting() {
        return ui.greet();
    }

    /**
     * Says bye to user.
     *
     * @return Message that ends the duke conversation.
     */
    public String bye() {
        return ui.bye();
    }

    /**
     * Gets response from Duke.
     *
     * @param input Input string from user.
     */
    public String getResponse(String input) {
        try {
            tasks = storage.convertFileToTaskList();
        } catch (Exception e) {
            return e.getMessage();
        }
        Parser parser = new Parser(" ");
        try {
            Command c = parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }
}
