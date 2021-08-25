import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Duke {
    private static final String SEPARATOR =
            "-------------------------------------------------------";
    private static final File SAVE_FILE = new File("data/duke.txt");
    private static final taskList inputs = new taskList();

    public static void main(String[] args) {
        loadFromFile();

        Scanner s = new Scanner(System.in);

        printMsg("Hello! I'm Duke\n    I am your personal to-do list!");

        String input;

        while (true) {
            System.out.print(">>> ");
            input = s.nextLine().trim().replaceAll("~", "");

            try {
                if (input.length() == 0) {
                    throw new DukeException("Input cannot be blank");
                } else if (input.equals("bye")) {
                    printMsg("Bye! Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    printMsg(inputs.getList());
                } else if (input.matches("done (\\d+)")) {
                    printMsg(inputs.markAsDone(Integer.parseInt(input.replaceAll("[^0-9]",
                            ""))));
                    saveFile();
                } else if (input.matches("delete (\\d+)")) {
                    printMsg(inputs.deleteTask(Integer.parseInt(input.replaceAll("[^0-9]",
                            ""))));
                    saveFile();
                } else {
                    printMsg(inputs.addTask(input));
                    saveFile();
                }
            } catch (DukeException e) {
                printMsg(e.getMessage());
            }
        }
        s.close();
    }

    private static void printMsg(String... msgs) {
        System.out.println(SEPARATOR);
        for (String msg:msgs) {
            System.out.println(msg);
        }
        System.out.println(SEPARATOR);
    }

    private static void saveFile() {
        File saveFileDir = SAVE_FILE.getAbsoluteFile().getParentFile();

        if (!saveFileDir.exists()) {
            saveFileDir.mkdir();
        }

        try {
            FileWriter fw = new FileWriter(SAVE_FILE);
            fw.write(inputs.getSaveData());
            fw.close();
        } catch (Exception e) {
            throw new DukeException("Error saving data: " + e.getMessage());
        }
    }

    private static void loadFromFile() {
        if (SAVE_FILE.exists()) {
            try {
                Scanner read = new Scanner(SAVE_FILE);
                read.useDelimiter("~");

                while (read.hasNext()) {
                    String type = read.next();

                    switch (type) {
                        case "T":
                            inputs.addToList(new ToDos(read.next().equals("1"), read.next()));
                            break;
                        case "D":
                            inputs.addToList(new Deadlines(read.next().equals("1"), read.next(), read.next()));
                            break;
                        case "E":
                            inputs.addToList(new Events(read.next().equals("1"), read.next(), read.next()));
                            break;
                    }
                }
                read.close();
            } catch (Exception e) {
                throw new DukeException(e.getMessage());
            }
        }
    }
}
