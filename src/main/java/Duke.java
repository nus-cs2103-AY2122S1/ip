import java.util.*;
import java.lang.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String linebreak = "~~*********___\\(owo)/___\\(owo)/___*********~~\n";

        System.out.println(linebreak
                + "Hewwo!! From:\n"
                + logo
                + "How mayw Iw hewlp youw, Mastwer? uwu\n"
                + linebreak);

        Scanner in = new Scanner(System.in);

        // adds array for 'list' command
        Task[] taskarr = new Task[100];
        int arrcounter = 0;

        // checks loop
        boolean continueloop = true;

        while (continueloop) {
            String str = in.nextLine();
            // split it later to parse for 'done' and 'integer afterwards'
            String[] strparse = str.split(" ");

            if (str.equalsIgnoreCase("bye")) {
                // breaks loop
                continueloop = false;
            } else if (str.equalsIgnoreCase("list")) {
                // checks for list keyword
                // lists history of past tasks
                System.out.println(linebreak);
                System.out.println("Uwu! Herw arw yourw taskws: \n");
                int counter = 0;
                while (taskarr[counter] != null || counter >= 100) {
                    System.out.println(counter
                            + ". ["
                            + taskarr[counter].getTaskStatus()
                            + "] "
                            + taskarr[counter].getTask()
                            + '\n');
                    counter++;
                }
                System.out.println(linebreak);
                taskarr[arrcounter] = new Task(str);
                arrcounter++;
            } else if (strparse.length == 2 && strparse[0].equals("done")) {
                // checks for 'done' and integer keywords
                // checks corresponding task as done
                try {
                    int i = Integer.parseInt(strparse[1]);
                    taskarr[i].markAsDone();
                    System.out.println(linebreak);
                    System.out.println("Thanwk youw forw youwr serwwice! Thwis taskw isw downe: \n"
                            + i
                            + ". ["
                            + taskarr[i].getTaskStatus()
                            + "] "
                            + taskarr[i].getTask()
                            + '\n');
                    System.out.println(linebreak);
                    taskarr[arrcounter] = new Task(str);
                    arrcounter++;

                } catch (NumberFormatException nfe) {
                    // not a 'done' task
                    taskarr[arrcounter] = new Task(str);
                    arrcounter++;
                    System.out.println(linebreak
                            +  '\n'
                            + "Addwed!: "
                            + str
                            + '\n'
                            + '\n'
                            + linebreak);
                }
            } else {
                taskarr[arrcounter] = new Task(str);
                arrcounter++;
                System.out.println(linebreak
                        +  '\n'
                        + "Addwed!: "
                        + str
                        + '\n'
                        + '\n'
                        + linebreak);
            }
        }


        // closing lines
        System.out.println(linebreak
                + '\n'
                + "Goodbywe, Mastwer! Seew youw soown!"
                + '\n'
                + '\n'
                + linebreak);
    }
}

