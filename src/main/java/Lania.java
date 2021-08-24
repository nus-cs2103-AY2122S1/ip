import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

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
     * @param t Task provided by the user.
     */
    public void update(Task t) throws LaniaException {
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
        String command = new Parser().parseCommand(input);
        while(!command.equals("bye")) {
            try {
                if (command.equals("list")) {
                    ui.listMessage(taskArrayList);
                } else if (command.equals("done")) {
                    complete(new Parser().getIndex(input));
                } else if (command.equals("delete")) {
                    remove(new Parser().getIndex(input));
                } else {
                    if (command.equals("todo")) {
                        String taskDescription = new Parser().parseTaskDescription(input);
                        update(new Todo(taskDescription));
                    } else if (command.equals("deadline")) {
                        String taskDescription = new Parser().parseTaskDescription(input);
                        String[] task = new Parser().parseDeadline(taskDescription);
                        update(new Deadline(task[0], task[1]));
                    } else if (command.equals("event")) {
                        String taskDescription = new Parser().parseTaskDescription(input);
                        String[] task = new Parser().parseEvent(taskDescription);
                        update(new Event(task[0], task[1]));
                    } else {
                        throw new LaniaException("Sorry, but Lania does not know what that means.");
                    }
                }
            } catch (LaniaException e) {
                ui.laniaExceptionMessage(e);
            } catch (DateTimeParseException e) {
                ui.dateTimeExceptionMessage();
            } finally {
                input = s.nextLine();
                command = new Parser().parseCommand(input);
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
