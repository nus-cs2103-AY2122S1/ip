import java.lang.reflect.Array;
import java.util.*;
import java.lang.*;

public class Duke {
    public static void main(String[] args) throws DukeException {
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
                + "How mayw Iw hewlp youw, Mastwer? uwu\n\n"
                + linebreakend);

        Scanner in = new Scanner(System.in);
        List<Task> taskarr = new ArrayList<>(100);
        int arrcounter = 0;
        // adds array for 'list' command
        boolean continueloop = true;
        // checks loop

        while (continueloop) {
            String str = in.nextLine();
            String[] strparse = str.split(" ");
            // split it later to parse for keywords

            if (strparse[0].equalsIgnoreCase("bye")) {
                // breaks loop
                try {
                    if (strparse.length > 1) {
                        throw new IncorrectInputException("bye", "'bye'");
                    }
                    continueloop = false;
                } catch (DukeException e) {
                    System.out.println(linebreakstart);
                    System.out.println(e.getMessage());
                    System.out.println(linebreakend);
                }
            } else if (strparse[0].equalsIgnoreCase("list")) {
                // lists history of past tasks
                try {
                    if (strparse.length > 1) {
                        throw new IncorrectInputException("list", "'list'");
                    }
                    System.out.println(linebreakstart);
                    System.out.println("Uwu! Herw arw yourw taskws:\n");
                    if (arrcounter == 0) {
                        System.out.println("Itw seewsm like youw wist is emptwy! Congwats!\n");
                    } else {
                        for (int i = 0; i < arrcounter; i++) {
                            System.out.println((i + 1)
                                    + ". "
                                    + taskarr.get(i).toString()
                                    + '\n');
                        }
                    }
                    System.out.println(linebreakend);
                } catch (DukeException e) {
                    System.out.println(linebreakstart);
                    System.out.println(e.getMessage());
                    System.out.println(linebreakend);
                }
            } else if (strparse[0].equalsIgnoreCase("todo")) {
                // adds a todo task to the list
                try {
                    StringBuilder taskb = new StringBuilder();
                    if (strparse.length == 1) {
                        throw new IncorrectInputException("todo", "using 'todo (taskw)'");
                    }
                    for (int i = 1; i < strparse.length; i++) {
                        taskb.append(strparse[i]);
                        if (i != strparse.length - 1) {
                            taskb.append(" ");
                        }
                    }
                    taskarr.add(new Todo(taskb.toString()));
                    arrcounter++;
                    System.out.println(linebreakstart);
                    System.out.println("Uwu! Addewd yourw taskws:\n");
                    System.out.println("   " + taskarr.get(arrcounter - 1).toString() + '\n');
                    System.out.println("Youw noww havew "
                            + (arrcounter)
                            + " taskw(s) inw thew wist! uwu\n");
                    System.out.println(linebreakend);
                } catch (DukeException e) {
                    System.out.println(linebreakstart);
                    System.out.println(e.getMessage());
                    System.out.println(linebreakend);
                }
            } else if (strparse[0].equalsIgnoreCase("deadline")) {
                // adds a deadline task to the list. now with no deadline assumption
                try {
                    if (strparse.length == 1) {
                        throw new IncompleteInputException("deadline");
                    }
                    StringBuilder taskb = new StringBuilder();
                    StringBuilder deadlineb = new StringBuilder();
                    int i = 1;
                    while (i < strparse.length
                            && !strparse[i].equalsIgnoreCase("/by")) {
                        taskb.append(strparse[i]);
                        if (i != strparse.length - 1) {
                            taskb.append(" ");
                        }
                        i++;
                    }
                    i++;
                    while (i < strparse.length) {
                        deadlineb.append(strparse[i]);
                        if (i != strparse.length - 1) {
                            deadlineb.append(" ");
                        }
                        i++;
                    }
                    if (taskb.toString().equals("") || deadlineb.toString().equals("")) {
                        throw new IncorrectInputException("deadline", "using 'deadline (task) /by (date)'");
                    }
                    taskarr.add(new Deadline(taskb.toString(), deadlineb.toString()));
                    arrcounter++;

                    System.out.println(linebreakstart);
                    System.out.println("Uwu! Addewd yourw taskws:\n");
                    System.out.println("   " + taskarr.get(arrcounter - 1).toString() + '\n');
                    System.out.println("Youw noww havew "
                            + (arrcounter)
                            + " taskw(s) inw thew wist! uwu\n");
                    System.out.println(linebreakend);
                } catch (DukeException e) {
                    System.out.println(linebreakstart);
                    System.out.println(e.getMessage());
                    System.out.println(linebreakend);
                }
            } else if (strparse[0].equalsIgnoreCase("event")) {
                // adds an event to the list. pretty much like deadline.
                try {
                    if (strparse.length == 1) {
                        throw new IncompleteInputException("event");
                    }
                    StringBuilder taskb = new StringBuilder();
                    StringBuilder atb = new StringBuilder();
                    int i = 1;
                    while (i < strparse.length
                            && !strparse[i].equalsIgnoreCase("/at")) {
                        taskb.append(strparse[i]);
                        if (i != strparse.length - 1) {
                            taskb.append(" ");
                        }
                        i++;
                    }
                    i++;
                    while (i < strparse.length) {
                        atb.append(strparse[i]);
                        if (i != strparse.length - 1) {
                            atb.append(" ");
                        }
                        i++;
                    }
                    if (taskb.toString().equals("") || atb.toString().equals("")) {
                        throw new IncorrectInputException("event", "'event (event) /at (date)'");
                    }
                    taskarr.add(new Event(taskb.toString(), atb.toString()));
                    arrcounter++;
                    System.out.println(linebreakstart);
                    System.out.println("Uwu! Addewd yourw taskws:\n");
                    System.out.println("   " + taskarr.get(arrcounter - 1).toString() + '\n');
                    System.out.println("Youw noww havew "
                            + (arrcounter)
                            + " taskw(s) inw thew wist! uwu\n");
                    System.out.println(linebreakend);
                } catch (DukeException e){
                    System.out.println(linebreakstart);
                    System.out.println(e.getMessage());
                    System.out.println(linebreakend);
                }
            } else if (strparse[0].equalsIgnoreCase("done")) {
                // checks for 'done' and integer keywords
                // checks corresponding task as done
                try {
                    if (strparse.length == 1) {
                        throw new IncompleteInputException("done");
                    }
                    try {
                        int i = Integer.parseInt(strparse[1]) - 1;
                    } catch (NumberFormatException e) {
                        throw new MissingNoException("done");
                    }
                    int i = Integer.parseInt(strparse[1]) - 1;
                    if (i >= arrcounter || i < 0) {
                        throw new MissingNoException("done");
                    }
                    taskarr.get(i).markAsDone();
                    System.out.println(linebreakstart);
                    System.out.println("Thanwk youw forw youwr serwwice! Thwis taskw isw downe:\n");
                    System.out.println("   "
                            + taskarr.get(i).toString()
                            + '\n');
                    System.out.println(linebreakend);
                } catch (DukeException e) {
                    System.out.println(linebreakstart);
                    System.out.println(e.getMessage());
                    System.out.println(linebreakend);
                }
            } else if (strparse[0].equalsIgnoreCase("delete")) {
                // checks for 'delete' and integer keywords
                // deletes corresponding task
                try {
                    if (strparse.length == 1) {
                        throw new IncompleteInputException("delete");
                    }
                    try {
                        int i = Integer.parseInt(strparse[1]) - 1;
                    } catch (NumberFormatException e) {
                        throw new MissingNoException("delete");
                    }
                    int i = Integer.parseInt(strparse[1]) - 1;
                    if (i >= arrcounter || i < 0) {
                        throw new MissingNoException("delete");
                    }
                    Task t = taskarr.get(i);
                    System.out.println(linebreakstart);
                    System.out.println("Thanwk youw forw youwr serwwice! Thwis taskw hasw beenw deweted:\n");
                    System.out.println("   "
                            + t.toString()
                            + '\n');
                    arrcounter--;
                    taskarr.remove(i);
                    System.out.println("Youw noww havew "
                            + (arrcounter)
                            + " taskw(s) inw thew wist! uwu\n");
                    System.out.println(linebreakend);
                } catch (DukeException e) {
                    System.out.println(linebreakstart);
                    System.out.println(e.getMessage());
                    System.out.println(linebreakend);
                }
            } else {
                try {
                    throw new InvalidInputException();
                } catch (DukeException e) {
                    System.out.println(linebreakstart);
                    System.out.println(e.getMessage());
                    System.out.println(linebreakend);
                }
            }
        }
        System.out.println(linebreakstart
                + "\nGoodbywe, Mastwer! Seew youw soown!\n\n"
                + linebreakend);
        // closing lines
    }
}