package duke;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Duke is the main class of the bot.
 *
 * @author meerian
 */
public class Duke {
    /**
     * Represents the storage to read/write files and the list to
     * hold any tasks created.
     */
    Storage storage;
    TaskList list;

    /**
     * Creates a duke with storage pointing to the right file.
     *
     * @param file the file to write/read the list to.
     */
    public Duke(File file) {
        this.storage = new Storage(file);
        this.list = new TaskList();

    }

    /**
     * The main program. Keeps on looping until the user inputs "bye",
     * which will result in the bot saving the current task list into the file
     * provided before shutting down.
     */
    public void Run() {
        Scanner scanner = new Scanner(System.in);

        Ui.Logo();
        storage.read(list);
        Ui.WelcomeMessage();

        String input = scanner.next();

        while (!Objects.equals(input, "bye")) {
            switch (input) {
                case "list":
                    Ui.Border();
                    list.display();
                    Ui.Border();
                    input = scanner.next();
                    break;

                case "done":
                    try {
                        int temp = scanner.nextInt();

                        Task cur = list.get(temp - 1);
                        cur.Done();
                        Ui.Border();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(cur);
                        Ui.Border();
                        input = scanner.next();
                        break;
                    } catch (InputMismatchException | DukeException e) {
                        System.out.println(e.getMessage());
                        Ui.Border();
                        input = scanner.next();
                        break;
                    }

                case "todo":
                    String tdLabel = scanner.nextLine();
                    Ui.Border();
                    try {
                        Todo todo = new Todo(tdLabel);
                        list.add(todo);
                        Ui.Border();
                        input = scanner.next();
                        break;
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                        Ui.Border();
                        input = scanner.next();
                        break;
                    }

                case "deadline":
                    try {
                        String dlLabel = scanner.nextLine();
                        Ui.Border();
                        Task deadline = Parser.check(dlLabel, "/by ");
                        list.add(deadline);
                        Ui.Border();
                        input = scanner.next();
                        break;
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                        Ui.Border();
                        input = scanner.next();
                        break;
                    }

                case "event":
                    try {
                        String eLabel = scanner.nextLine();
                        Ui.Border();
                        Task event = Parser.check(eLabel, "/at ");
                        list.add(event);
                        Ui.Border();
                        input = scanner.next();
                        break;
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                        Ui.Border();
                        input = scanner.next();
                        break;
                    }

                case "delete":
                    try {
                        Ui.Border();
                        int temp = scanner.nextInt();
                        list.delete(temp);
                        Ui.Border();
                        input = scanner.next();
                        break;
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                        Ui.Border();
                        input = scanner.next();
                        break;
                    }

                case "find":
                    try {
                        String find = scanner.nextLine();
                        Ui.Border();
                        list.find(find);
                        Ui.Border();
                        input = scanner.next();
                        break;

                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                        Ui.Border();
                        input = scanner.next();
                        break;
                    }

                default:
                    scanner.nextLine();
                    Ui.UnknownCommand();
                    input = scanner.next();
                    break;
            }
        }

        Ui.Border();
        storage.write(list);
        Ui.Goodbye();
    }
}