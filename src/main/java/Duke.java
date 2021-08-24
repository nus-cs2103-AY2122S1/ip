import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Command commands = new Command();
    private Storage storage;
    private TaskList tasks;
    private String file_path;

    /**
     * Constructor for Duke.
     *
     * @param file_path file_path to write records to.
     */
    public Duke(String file_path) {
        this.file_path = file_path;
        try {
            this.storage = new Storage(file_path);
            tasks = new TaskList(this.storage.getTasks());
        } catch (IOException e) {
            this.storage = new Storage(file_path);
            tasks = new TaskList(null);
        }
    }


    /**
     * Starts the bot.
     */
    public void start() {
        Ui.greeting();

        Scanner scanner = new Scanner(System.in);
        String input;
            while (!((input = scanner.next().toLowerCase()).equals("bye"))) {
                Keyword keyword = null;
                try {
                    keyword = Parser.parse(input);
                } catch (DukeException e) {
                    Ui.display(e.toString());
                }
                if (keyword != null) {
                    switch (keyword) {
                    case LIST:
                        Ui.display(commands.list(tasks));
                        break;
                    case TODO:
                        try {
                            String text = scanner.nextLine().trim();
                            Ui.display(commands.todo(text, tasks));
                        } catch (DukeException e) {
                            Ui.display(e.toString());
                        }
                        break;
                    case EVENT:
                        String[] text = scanner.nextLine().split("/at ");
                        try {
                            Ui.display(commands.event(text[0].trim(), text[1], tasks));
                        } catch (DukeException e) {
                            Ui.display(e.toString());
                        } catch (ArrayIndexOutOfBoundsException e) {
                            Ui.missingDate(keyword);
                        }
                        break;
                    case DEADLINE:
                        String[] txt = scanner.nextLine().split("/by ");
                        try {
                            Ui.display(commands.deadline(txt[0].trim(), txt[1], tasks));
                        } catch (DukeException e) {
                            Ui.display(e.toString());
                        } catch (ArrayIndexOutOfBoundsException e) {
                            Ui.missingDate(keyword);
                        }
                        break;
                    case DONE:
                        try {
                            int index = scanner.nextInt() - 1;
                            Ui.display(commands.done(index, tasks));
                        } catch (DukeException e) {
                            Ui.display(e.toString());
                        }
                        break;
                    case DELETE:
                        try {
                            int index = (scanner.nextInt()) - 1;
                            Ui.display(commands.delete(index, tasks));
                        } catch (DukeException e) {
                            Ui.display(e.toString());
                        }
                        break;
                    case FIND:
                        try {
                            String key = scanner.nextLine().trim();
                            Ui.display(commands.find(key, tasks));
                        } catch (DukeException e) {
                            Ui.display(e.toString());
                        }
                        break;
                    }
                }
            }

        try {
            storage.writeToFile(tasks);
        } catch (IOException e){
            System.out.println("Error in saving storage");
        }

        scanner.close();
        Ui.end();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke bot = new Duke("./data.txt");
        bot.start();
    }
}