import java.util.Scanner;
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

        // checks loop
        boolean continueloop = true;

        while (continueloop) {
            String str = in.nextLine();

            if (str.equalsIgnoreCase("bye")) {
                // breaks loop
                continueloop = false;
            } else {
                System.out.println(linebreak
                        +  '\n'
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

