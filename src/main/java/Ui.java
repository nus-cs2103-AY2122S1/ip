import java.lang.reflect.Array;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ui {
    Ui() {
    }

    public static void run(Storage storage, TaskList tasklist) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String linebreakstart = "~~*********___\\(owo)/___\\(owo)/___*********~~\n";
        String linebreakend = "~~*********___\\(owo)/___\\(owo)/___*********~~";

        System.out.println(linebreakstart
                + "\nHewwo!! From:\n"
                + logo
                + "How mayw Iw hewlp youw, Mastwer? Pwease type 'help' for a list of commandws uwu\n");

        Scanner in = new Scanner(System.in);
        Parser parser = new Parser();

        System.out.println(linebreakend);

        boolean continueLoop = true;
        // for checking loop

        while (continueLoop) {
            // initiates loop.
            try {
                String str = in.nextLine();
                String[] strparse = str.split(" ");
                // splits input to parse for keywords.

                switch (parser.parseCommand(strparse[0])) {
                case "bye":
                    // breaks loop, closes chatbot.
                    try {
                        if (strparse.length > 1) {
                            throw new IncorrectInputException("bye", "'bye'");
                        }
                        continueLoop = false;
                    } catch (DukeException e) {
                        System.out.println(linebreakstart);
                        System.out.println(e.getMessage());
                        System.out.println(linebreakend);
                    }
                    break;
                case "help":
                    // lists command list
                    try {
                        if (strparse.length > 1) {
                            throw new IncorrectInputException("help", "'help'");
                        }
                        System.out.println(linebreakstart);
                        System.out.println("Commandws supported:\n");
                        System.out.println("- bye\n- help\n- list\n- todo\n- event\n- deadline\n");
                        System.out.println(linebreakend);
                    } catch (DukeException e) {
                        System.out.println(linebreakstart);
                        System.out.println(e.getMessage());
                        System.out.println(linebreakend);
                    }


                    break;
                case "list":
                    // lists history of current tasks.
                    try {
                        if (strparse.length > 1) {
                            throw new IncorrectInputException("list", "'list'");
                        }
                        System.out.println(linebreakstart);
                        System.out.println("Uwu! Herw arw yourw taskws:\n");

                        if (tasklist.getTaskCounter() == 0) {
                            System.out.println("Itw seewsm like youw wist is emptwy! Congwats!\n");
                        } else {
                            System.out.println(tasklist.displayList());
                        }
                        System.out.println(linebreakend);
                    } catch (DukeException e) {
                        System.out.println(linebreakstart);
                        System.out.println(e.getMessage());
                        System.out.println(linebreakend);
                    }
                    break;
                case "todo":
                    // adds a todo task to the list.
                    try {
                        tasklist.addTodo(strparse);
                        storage.saveData(tasklist);

                        System.out.println(linebreakstart);
                        System.out.println("Uwu! Addewd yourw taskws:\n");
                        System.out.println(tasklist.lastAddedTask() + '\n');
                        System.out.println("Youw noww havew "
                                + (tasklist.getTaskCounter())
                                + " taskw(s) inw thew wist! uwu\n");
                        System.out.println(linebreakend);
                    } catch (DukeException e) {
                        System.out.println(linebreakstart);
                        System.out.println(e.getMessage());
                        System.out.println(linebreakend);
                    }
                    break;
                case "deadline":
                    // adds a deadline task to the list.
                    try {
                        tasklist.addDeadline(strparse);
                        storage.saveData(tasklist);

                        System.out.println(linebreakstart);
                        System.out.println("Uwu! Addewd yourw taskws:\n");
                        System.out.println(tasklist.lastAddedTask() + '\n');
                        System.out.println("Youw noww havew "
                                + (tasklist.getTaskCounter())
                                + " taskw(s) inw thew wist! uwu\n");
                        System.out.println(linebreakend);
                    } catch (DukeException e) {
                        System.out.println(linebreakstart);
                        System.out.println(e.getMessage());
                        System.out.println(linebreakend);
                    }
                    break;
                case "event":
                    // adds an event to the list. pretty much like deadline.
                    try {
                        tasklist.addEvent(strparse);
                        storage.saveData(tasklist);

                        System.out.println(linebreakstart);
                        System.out.println("Uwu! Addewd yourw taskws:\n");
                        System.out.println(tasklist.lastAddedTask() + '\n');
                        System.out.println("Youw noww havew "
                                + (tasklist.getTaskCounter())
                                + " taskw(s) inw thew wist! uwu\n");
                        System.out.println(linebreakend);
                    } catch (DukeException e) {
                        System.out.println(linebreakstart);
                        System.out.println(e.getMessage());
                        System.out.println(linebreakend);
                    }
                    break;
                case "done":
                    // marks a task as done.
                    try {
                        int i = tasklist.markDone(strparse);
                        System.out.println("Task done is " + i);
                        storage.saveData(tasklist);

                        System.out.println(linebreakstart);
                        System.out.println("Thanwk youw forw youwr serwwice! Thwis taskw isw downe:\n");
                        System.out.println("   "
                                + tasklist.getTaskDescr(i)
                                + '\n');
                        System.out.println(linebreakend);

                    } catch (DukeException e) {
                        System.out.println(linebreakstart);
                        System.out.println(e.getMessage());
                        System.out.println(linebreakend);
                    }
                    break;
                case "delete":
                    // deletes corresponding task on list.
                    try {
                        Task t = tasklist.delete(strparse);
                        storage.saveData(tasklist);

                        System.out.println(linebreakstart);
                        System.out.println("Thanwk youw forw youwr serwwice! Thwis taskw hasw beenw deweted:\n");
                        System.out.println("   "
                                + t.toString()
                                + '\n');
                        System.out.println("Youw noww havew "
                                + (tasklist.getTaskCounter())
                                + " taskw(s) inw thew wist! uwu\n");
                        System.out.println(linebreakend);
                    } catch (DukeException e) {
                        System.out.println(linebreakstart);
                        System.out.println(e.getMessage());
                        System.out.println(linebreakend);
                    }
                    break;
                default:
                    throw new InvalidInputException();
                }
            } catch (DukeException e) {
                System.out.println(linebreakstart);
                System.out.println(e.getMessage());
                System.out.println(linebreakend);
            }
        }
        System.out.println(linebreakstart
                + "\nGoodbywe, Mastwer! Seew youw soown!\n\n"
                + linebreakend);
        // closing lines
    }

}
