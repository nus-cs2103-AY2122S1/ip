import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Duke {

//    private Storage storage;
    private TaskList tasks;
    private File file;

//    private Ui ui;

    public Duke(String filePath) throws IOException{

        File f = new File(filePath);
        this.file = f;
        TaskList tasks = new TaskList();
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        if (!f.createNewFile()) {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String currTask = sc.nextLine();
                Task task = Duke.stringToTask(currTask);
                tasks.add(task);
            }
        }
        this.tasks = tasks;
    }

    public static Task stringToTask(String fullString) {
        String[] arr = fullString.split("\\|");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].trim();
        }
        Task curr;
        if (arr[0].equals("T")) {
            curr = new Todo(arr[2]);
        } else if (arr[0].equals("E")) {
            curr = new Event(arr[2], arr[3]);
        } else {
            curr = new Deadline(arr[2], arr[3]);
        }
        if (arr[1].equals("1")) {
            curr.markAsDone();
        }
        return curr;
    }

    public static String taskToString(Task task) {
        return task.fileFormat();
    }

    public void saveTasks() throws IOException {
        FileWriter fw = new FileWriter(this.file);
        String curr= "";
        for (int i = 0; i < this.tasks.size(); i++) {
            curr += Duke.taskToString(this.tasks.get(i)) + "\n";
        }
        fw.write(curr);
        fw.close();
    }

    public void run() {
        Welcome.of().exec();
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("What can I do for you?");
        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                List.of(this.tasks).exec();
            } else if (input.startsWith("done")) {
                int value = Character.getNumericValue(input.charAt(5));
                Done.of(tasks, value).exec();
            }  else if (input.startsWith("delete")) {
                int value = Character.getNumericValue(input.charAt(7));
                Delete.of(tasks, value).exec();
            } else {
                try {
                    Add.of(this.tasks, input).exec();
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
            try {
                this.saveTasks();
            } catch (IOException e) {
                System.out.println("Oh no your action cannot be saved! Try again");
            }
        }
        Exit.of().exec();
    }

    public static void main(String[] args) {
        try {
            new Duke("./data/file.txt").run();
        } catch (IOException e) {
            System.out.println("wtf is your file bro!");
        }
    }
}
