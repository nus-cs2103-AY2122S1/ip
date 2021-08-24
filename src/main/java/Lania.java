import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Lania {

    private ArrayList<Task> taskArrayList;
    private Ui ui;
    private Storage storage;

    public Lania(String filePath) {
        taskArrayList = new ArrayList<Task>();
        ui = new Ui();
        storage = new Storage(filePath);
    }

    /**
     * Store user input in task array and show that it is added.
     *
     * @param s String provided by the user.
     */
    public void update(String s) throws LaniaException {
        Task t;
        String[] split = s.split(" ", 2);
        if (split[0].equals("todo")) {
            if (split.length == 1) {
                throw new LaniaEmptyDescriptionException(split[0]);
            } else {
                t = new Todo(split[1]);
            }
        } else if (split[0].equals("deadline")) {
            if (split.length == 1) {
                throw new LaniaEmptyDescriptionException(split[0]);
            } else {
                String[] splitDeadline = split[1].split(" /by ");
                t = new Deadline(splitDeadline[0], splitDeadline[1]);
            }
        } else if (split[0].equals("event")) {
            if (split.length == 1) {
                throw new LaniaEmptyDescriptionException(split[0]);
            } else {
                String[] splitEvent = split[1].split(" /at ");
                t = new Event(splitEvent[0], splitEvent[1]);
            }
        } else {
            throw new LaniaException("Sorry, but Lania doesn't know what that means");
        }
        taskArrayList.add(t);
        try {
            storage.save(taskArrayList);
        } catch (IOException e) {
            ui.loadingErrorMessage();
        }
        ui.updateMessage(taskArrayList, t);
    }

    /**
     * Completes given task number.
     *
     * @param i The task number to be completed.
     */
    public void complete(int i) {
        i--;
        taskArrayList.get(i).markAsDone();
        try {
            storage.save(taskArrayList);
        } catch (IOException e) {
            ui.loadingErrorMessage();
        }
        ui.taskCompleteMessage(taskArrayList, i);
    }

    /**
     * Removes given task number.
     *
     * @param i The task number to be completed.
     */
    public void remove(int i) {
        i--;
        Task t = taskArrayList.get(i);
        taskArrayList.remove(i);
        try {
            storage.save(taskArrayList);
        } catch (IOException e) {
            ui.loadingErrorMessage();
        }
        ui.removeTaskMessage(taskArrayList, t);
    }

    public void run() {
        try {
            taskArrayList = storage.load();
        } catch (IOException e) {
            ui.loadingErrorMessage();
            e.printStackTrace();
        }
        ui.listMessage(taskArrayList);
        ui.greetingMessage();
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        while(!input.equals("bye")) {
            try {
                String[] split = input.split(" ");
                if (input.equals("list")) {
                    ui.listMessage(taskArrayList);
                } else if (split[0].equals("done")) {
                    complete(Integer.parseInt(split[1]));
                } else if (split[0].equals("delete")) {
                    remove(Integer.parseInt(split[1]));
                } else {
                    update(input);
                }
            } catch (LaniaException e) {
                ui.laniaExceptionMessage(e);
            } catch (DateTimeParseException e) {
                ui.dateTimeExceptionMessage();
            } finally {
                input = s.nextLine();
            }
        }
        s.close();
        ui.goodbyeMessage();
    }

    /**
     * Chatbot that stores and displays the user input.
     *
     * @param args The command line arguments. Not required here.
     **/
    public static void main(String[] args) {
        Lania lania = new Lania("data/lania.txt");
        lania.run();
    }
}
