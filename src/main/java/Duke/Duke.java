package Duke;

import javafx.application.Application;
import javafx.stage.Stage;
import Duke.task.TaskList;

import java.io.IOException;
import java.util.Scanner;

public class Duke extends Application {
    private Command commands = new Command();
    private Storage storage;
    private TaskList tasks;
    private String file_path;

    /**
     * Constructor for Duke.Duke.
     *
     */
    public Duke() {
        this.file_path = "./data.txt";
        try {
            this.storage = new Storage(file_path);
            tasks = new TaskList(this.storage.getTasks());
        } catch (IOException e) {
            this.storage = new Storage(file_path);
            tasks = new TaskList(null);
        }
    }

    /**
     * Returns the response from Duke given the user input to be shown on the GUI.
     */
    public String getResponse(String input) {
        String response;
            if (input.equals("bye")) {
                return "Bye bye!";
            } else if (input.startsWith("list")) {
                response = commands.list(tasks);
            } else if (input.startsWith("find")) {
                String key;
                if (input.trim().equals("find")) {
                    key = "";
                } else {
                    key = input.split("find")[1].trim();
                }
                try {
                    response = commands.find(key, tasks);
                } catch (DukeException e) {
                    return e.toString();
                }

            } else if (input.startsWith("done")) {
                String text;
                if (input.trim().equals("done")) {
                    text = " ";
                } else {
                    text = input.split("done")[1].trim();
                }
                try {
                    int index = Integer.parseInt(text);
                    response = commands.done(index, tasks);
                } catch (DukeException e) {
                    return e.toString();
                } catch (NumberFormatException err) {
                    response = "Please provide a task number!!";
                }
            } else if (input.startsWith("delete")) {
                String text;
                if (input.trim().equals("delete")) {
                    text = " ";
                } else {
                    text = input.split("delete")[1].trim();
                }
                try {
                    int index = Integer.parseInt(text);
                    response = commands.delete(index, tasks);
                } catch (DukeException e) {
                    return e.toString();
                } catch (NumberFormatException err) {
                    response = "Please provide a task number!!";
                }
            } else if (input.startsWith("todo")) {
                String text;
                if (input.trim().equals("todo")) {
                    text = "";
                } else {
                    text = input.split("todo")[1].trim();
                }
                try {
                    response = commands.todo(text, tasks);
                } catch (DukeException e) {
                    return e.toString();
                }
            } else if (input.startsWith("deadline")) {
                String text;
                if (input.trim().equals("deadline") || !input.contains("/by ")) {
                    text = " /by ";
                } else {
                    text = input.split("deadline")[1];
                }
                String[] txt = text.split("/by ");
                try {
                    response = commands.deadline(txt[0].trim(), txt[1].trim(), tasks);
                } catch (DukeException e) {
                    return e.toString();
                }
            } else if (input.startsWith("event")) {
                String text;
                if (input.trim().equals("event") || !input.contains("/at ")) {
                    text = " /at ";
                } else {
                    text = input.split("event")[1];
                }
                String[] txt = text.split("/at ");
                try {
                    response = commands.deadline(txt[0].trim(), txt[1], tasks);
                } catch (DukeException e) {
                    return e.toString();
                }
            } else {
                response = "I don't understand that command!";
            }

        return response;
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
            System.out.println("Error in saving data to storage");
        }

        scanner.close();
        Ui.end();
    }

    @Override
    public void start(Stage stage) {
        Duke duke = new Duke();
        GraphicalUserInterface gui = new GraphicalUserInterface(stage, duke.tasks, duke.storage);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke bot = new Duke();
        bot.start();
    }
}