package Duke;

import Duke.task.TaskList;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Command commands = new Command();
    private Storage storage;
    private TaskList tasks;
    private String file_path;
    private final String BYE_MESSAGE = "No time to say 'hello, goodbye'. I'm late, I'm late, I'm late!";
    private final String STORAGE_ERROR_MESSAGE = "Error in saving data to storage";

    /**
     * Constructor for Duke.Duke.
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
        final String errorMessage = "Not a valid command!!";
        String response = null;
        if (input.equals("bye")) {
            return BYE_MESSAGE;
        } else {
            Keyword keyword = null;
            try {
                keyword = Parser.parseForGui(input);
            } catch (DukeException e) {
                keyword = null;
                return e.toString();
            }
            switch (keyword) {
            case LIST:
                response = commands.list(tasks);
                break;
            case FIND:
                String key = Parser.getInfo(input, keyword);
                try {
                    response = commands.find(key, tasks);
                } catch (DukeException e) {
                    return e.toString();
                }
                break;
            case DONE:
                String number = Parser.getInfo(input, keyword);
                try {
                    int index = Integer.parseInt(number);
                    response = commands.done(index, tasks);
                } catch (NumberFormatException err) {
                    response = "Please provide a task number!!";
                } catch (DukeException e) {
                    return e.toString();
                }
                break;
            case DELETE:
                String toDelete = Parser.getInfo(input, keyword);
                try {
                    int index = Integer.parseInt(toDelete);
                    response = commands.delete(index, tasks);
                } catch (NumberFormatException err) {
                    response = "Please provide a task number!!";
                } catch (DukeException e) {
                    return e.toString();
                }
                break;
            case TODO:
                String todo = Parser.getInfo(input, keyword);
                try {
                    response = commands.todo(todo, tasks);
                } catch (DukeException e) {
                    return e.toString();
                }
                break;
            case DEADLINE:
                String deadline = Parser.getInfo(input, keyword);
                String[] txt = deadline.split("/by ");
                try {
                    response = commands.deadline(txt[0].trim(), txt[1].trim(), tasks);
                } catch (DukeException e) {
                    return e.toString();
                }
                break;
            case EVENT:
                String event = Parser.getInfo(input, keyword);
                String[] text = event.split("/at ");
                try {
                    response = commands.event(text[0].trim(), text[1], tasks);
                } catch (DukeException e) {
                    return e.toString();
                }
                break;
            default:
                return errorMessage;
            }
        }

        try {
            storage.writeToFile(tasks);
        } catch (IOException e) {
            return STORAGE_ERROR_MESSAGE;
        }
        return response;
    }


    /**
     * Starts the bot.
     */
    public void start() {
        Ui.greet();

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
                final String errorMessage = "Not a valid command!!";
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
                    int doneIndex = scanner.nextInt();
                    try {
                        Ui.display(commands.done(doneIndex, tasks));
                    } catch (DukeException e) {
                        Ui.display(e.toString());
                    }
                    break;
                case DELETE:
                    int deleteIndex = (scanner.nextInt());
                    try {
                        Ui.display(commands.delete(deleteIndex, tasks));
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
                default:
                    Ui.display(errorMessage);
                }
            }
        }

        try {
            storage.writeToFile(tasks);
        } catch (IOException e) {
            System.out.println(STORAGE_ERROR_MESSAGE);
        }

        scanner.close();
        Ui.end();
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.start();
    }
}