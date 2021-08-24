import java.io.IOException;

public class Duke {
    private static final String FILE_DIR = "data";
    private static final String FILE_NAME = "duke.txt";

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.printError(e.getMessage());
        }

    }

//    private static void writeToFile(List<Task> tasks) throws IOException {
//        // Solution adapted from:
//        // https://stackoverflow.com/questions/9658297/java-how-to-create-a-file-in-a-directory-using-relative-path
//        File dukeFile = new File(FILE_DIR, FILE_NAME);
//
//        if (!dukeFile.exists()) {
//            dukeFile.getParentFile().mkdir();
//            dukeFile.createNewFile();
//        }
//
//        FileWriter fw = new FileWriter(dukeFile.getAbsoluteFile());
//
//        for (Task t : tasks) {
//            fw.write(t.toSaveString() + System.lineSeparator());
//        }
//
//        fw.close();
//    }

//    private static void readFile(String filePath) {
//        try {
//            File f = new File(filePath);
//            if (f.exists()) {
//                Scanner sc = new Scanner(f);
//                while (sc.hasNext()) {
//                    String t = sc.nextLine();
//                    String[] task = t.split("\\|");
//                    switch (task[0]) {
//                    case "T":
//                        items.add(new Todo(task[2], task[1].equals("1")));
//                        break;
//
//                    case "E":
//                        items.add(new Event(task[2], task[1].equals("1"), LocalDateTime.parse(task[3], outputFormatter)));
//                        break;
//
//                    case "D":
//                        items.add(new Deadline(task[2], task[1].equals("1"), LocalDateTime.parse(task[3], outputFormatter)));
//                        break;
//
//                    default:
//                        break;
//                    }
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("Something went wrong: " + e.getMessage());
//        }
//    }


    public void run() {
        ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();

            } catch (DukeException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();

    }
}
