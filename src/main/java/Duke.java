import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    private static StorageList sl = new StorageList();
    private static File file;
    private static Ui ui = new Ui();

    private static final int TASK_TODO = 1;
    private static final int TASK_DEADLINE = 2;
    private static final int TASK_EVENT = 3;
    private static final int TASK_OUTOFBOUNDS = 4;
    private static final int TASK_UNKNOWN = 5;

    public Duke(String filePath) {
//        storage = new Storage(filePath);
//        try {
//            tasks = new TaskList(storage.load());
//        } catch (DukeException e) {
//            ui.showLoadingError();
//            tasks = new TaskList();
//        }
    }

    public static void main(String[] args) throws DukeException {
        ui.greeting();

        try{
            file = new File("data/duketest.txt");
            if (!file.exists()) {
                throw new FileNotFoundException();
            } else {
                ui.fileConfirmation();
                Scanner sc = new Scanner(file);
                readFile(sc);
                result();
            }

        } catch (FileNotFoundException e) {
            ui.fileNotFoundMsg();
        }

    }

    private static void readFile(Scanner sc) {
        while (sc.hasNext()) {
            String input = sc.nextLine();

            char type = input.charAt(0);
            int doneState = Integer.parseInt(input.substring(4, 5));
            if (type == 'T') {
                String taskDesc = input.substring(8);
                ToDos todo = new ToDos(taskDesc);
                if (doneState == 1) {
                    todo.markAsDone();
                }
                sl.add(todo);
            } else {
                int thirdBarIdx = input.indexOf('|', 7);
                String taskDesc = input.substring(8, thirdBarIdx-1);
                String taskTime = input.substring(thirdBarIdx + 2);
                if(type == 'D'){
                    Deadlines dl = new Deadlines(taskDesc, taskTime);
                    if(doneState == 1){
                        dl.markAsDone();
                    }
                    sl.add(dl);
                } else if (type == 'E') {
                    Events event = new Events(taskDesc, taskTime);
                    if(doneState == 1){
                        event.markAsDone();
                    }
                    sl.add(event);
                }
            }
        }
    }


    private static void result() throws DukeException {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();

            try {
                if (input.length() >= 4 && input.substring(0, 4).equals("done")) {
                    marking(input);
                } else if (input.length() >= 4 && input.substring(0, 4).equals("todo")) {
                    if(input.length() == 4) {
                        throw new DukeException(ui.taskErrorMsg(TASK_TODO));
                    }
                    ToDos todo = new ToDos(input.substring(5));
                    sl.add(todo);
                    ui.taskAddedMsg(todo.toString(), sl.size());
                } else if (input.length() >= 8 && input.substring(0, 8).equals("deadline")) {
                    if (input.length() == 8) {
                        throw new DukeException(ui.taskErrorMsg(TASK_DEADLINE));
                    }
                    String by = input.substring(input.indexOf("/") + 4);
                    Deadlines dl = new Deadlines(input.substring(9, input.indexOf("/")), by);
                    sl.add(dl);
                    ui.taskAddedMsg(dl.toString(), sl.size());
                } else if (input.length() >= 5 && input.substring(0, 5).equals("event")) {
                    if (input.length() == 5) {
                        throw new DukeException(ui.taskErrorMsg(TASK_EVENT));
                    }
                    String at = input.substring(input.indexOf("/") + 4);
                    Events event = new Events(input.substring(6,  input.indexOf("/")), at);
                    sl.add(event);
                    ui.taskAddedMsg(event.toString(), sl.size());
                } else if (input.length() >= 6 && input.substring(0, 6).equals("delete")) {
                    if (input.length() == 6) {
                        throw new DukeException(ui.taskErrorMsg(TASK_UNKNOWN));
                    }
                    int idx = Integer.parseInt(input.substring(7)) - 1;
                    delete(idx);
                } else {
                    Task task = new Task(input);
                    String desc = task.getDescription();
                    switch (desc) {
                    case "bye":
                        ui.bye();
                        return;
                    case "list":
                        sl.display();
                        break;
                    default:
                        throw new DukeException(ui.taskErrorMsg(TASK_UNKNOWN));
                    }
                }
                save();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                ui.ioErrorMsg();
            }
        }

    }

    private static void save() throws IOException {
        FileWriter fw = new FileWriter("data/duketest.txt");
        String textToAdd = "";
        for (int i = 0; i < sl.size(); i++){
            Task task = sl.get(i);
            String taskType = "";
            String status = task.isDone() ? "1 " : "0 ";
            String taskDesc = task.getDescription();
            String taskTime = "";
            if (task instanceof ToDos) {
                taskType = "T ";
                textToAdd += taskType + "| " + status + "| " + taskDesc + "\n";
            } else {
                if (task instanceof Deadlines) {
                    taskType = "D ";
                    taskTime = ((Deadlines) task).getBy();
                } else if (task instanceof Events) {
                    taskType = "E ";
                    taskTime = ((Events) task).getAt();
                }
                textToAdd += taskType + "| " + status + "| " + taskDesc
                        + " | " + taskTime + "\n";
            }

        }

        fw.write(textToAdd);
        fw.close();
    }

    private static void delete(int idx) {
        String desc = sl.get(idx).getDescription();
        sl.delete(idx);
        ui.taskDeleteMsg(desc, sl.size());
    }


    public static void marking(String input) throws DukeException {
        if (input.length() >= 6) {
            if (input.substring(5).matches("[0-9]+")) {
                int taskNum = Integer.parseInt(input.substring(5)) - 1;
                if (taskNum < sl.size() && taskNum >= 0) {
                    sl.get(taskNum).markAsDone();
                    ui.taskDoneConfirmation();
                    sl.get(taskNum).displayTask();
                } else {
                    throw new DukeException(ui.taskErrorMsg(TASK_OUTOFBOUNDS));
                }
            } else {
                throw new DukeException(ui.taskErrorMsg(TASK_UNKNOWN));
            }
        } else {
            throw new DukeException(ui.taskErrorMsg(TASK_UNKNOWN));

        }
    }
}
