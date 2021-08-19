import java.util.*;
import java.lang.*;

public class Duke {
    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n";
        String linebreak = "~~*********___\\(owo)/___\\(owo)/___*********~~\n\n";
        System.out.println(linebreak
                + "Hewwo!! From:\n\n"
                + logo
                + "How mayw Iw hewlp youw, Mastwer? uwu\n\n"
                + linebreak);

        Scanner in = new Scanner(System.in);
        Task[] taskarr = new Task[100];
        int arrcounter = 0;
        // adds array for 'list' command
        boolean continueloop = true;
        // checks loop

        while (continueloop) {
            String str = in.nextLine();
            String[] strparse = str.split(" ");
            // split it later to parse for 'done' and 'integer afterwards'

            if (str.equalsIgnoreCase("bye")) {
                // breaks loop
                continueloop = false;
            } else if (str.equalsIgnoreCase("list")) {
                // lists history of past tasks
                System.out.println(linebreak);
                System.out.println("Uwu! Herw arw yourw taskws:\n\n");
                int counter = 0;
                while (taskarr[counter] != null || counter >= 100) {
                    System.out.println(counter
                            + ". "
                            + taskarr[counter].toString()
                            + '\n');
                    counter++;
                }
                System.out.println(linebreak);
            } else if (strparse.length > 0 && strparse[0].equalsIgnoreCase("todo")) {
                // adds a todo task to the list
                try {
                    StringBuilder taskb = new StringBuilder();
                    if (strparse.length == 1) {
                        throw new DukeException("Ohww noww! Thew descwiption of todow cannyotw bew emptwpy.\n\n");
                    }
                    for (int i = 1; i < strparse.length; i++) {
                        taskb.append(strparse[i]);
                        if (i != strparse.length - 1) {
                            taskb.append(" ");
                        }
                    }
                    taskarr[arrcounter] = new Todo(taskb.toString());
                    arrcounter++;

                    System.out.println(linebreak);
                    System.out.println("Uwu! Addewd yourw taskws:\n\n");
                    System.out.println("   " + taskarr[arrcounter - 1].toString());
                    System.out.println("Youw noww havew "
                            + (arrcounter)
                            + " taskw(s) inw thew wist! uwu\n\n");
                    System.out.println(linebreak);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (strparse[0].equalsIgnoreCase("deadline")) {
                // adds a deadline task to the list. now with no deadline assumption
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
                taskarr[arrcounter] = new Deadline(taskb.toString(), deadlineb.toString());
                arrcounter++;

                System.out.println(linebreak);
                System.out.println("Uwu! Addewd yourw taskws:\n\n");
                System.out.println("   " + taskarr[arrcounter - 1].toString());
                System.out.println("Youw noww havew "
                        + (arrcounter)
                        + " taskw(s) inw thew wist! uwu\n\n");
                System.out.println(linebreak);
            } else if (strparse[0].equalsIgnoreCase("event")) {
                // adds an event to the list. pretty much like deadline.
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
                taskarr[arrcounter] = new Event(taskb.toString(), atb.toString());
                arrcounter++;

                System.out.println(linebreak);
                System.out.println("Uwu! Addewd yourw taskws:\n\n");
                System.out.println("   " + taskarr[arrcounter - 1].toString());
                System.out.println("Youw noww havew "
                        + (arrcounter)
                        + " taskw(s) inw thew wist! uwu\n\n");
                System.out.println(linebreak);
            } else if (strparse.length == 2 && strparse[0].equalsIgnoreCase("done")) {
                // checks for 'done' and integer keywords
                // checks corresponding task as done
                boolean passallcheck = false;
                try {
                    int i = Integer.parseInt(strparse[1]);
                    if (i >= 0 && i <= 100 && taskarr[i] != null) {
                        passallcheck = true;
                    } else {
                        // do nothing
                    }
                } catch (NumberFormatException nfe) {
                    // do nothing
                }
                if (passallcheck) {
                    int i = Integer.parseInt(strparse[1]);
                    taskarr[i].markAsDone();
                    System.out.println(linebreak);
                    System.out.println("Thanwk youw forw youwr serwwice! Thwis taskw isw downe:\n\n"
                            + "   "
                            + i
                            + ". "
                            + taskarr[i].toString()
                            + '\n');
                    System.out.println(linebreak);
                } else {
                    taskarr[arrcounter] = new Task(str);
                    arrcounter++;
                    System.out.println(linebreak
                            + "Addwed!: "
                            + str
                            + "\n\n"
                            + linebreak);
                }
            } else {
                try {
                    throw new DukeException("Sowwy, thiws commandw iswn't supporwted TwT\n\n");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }


        // closing lines
        System.out.println(linebreak
                + "Goodbywe, Mastwer! Seew youw soown!\n\n"
                + linebreak);
    }
}

