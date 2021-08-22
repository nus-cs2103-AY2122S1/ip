package duke;

import duke.commands.Command;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Duke {
    static final String LOGO = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
    LinkedList<Item> itemList = new LinkedList<>();
    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        System.out.println("Hello from\n" + LOGO);
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        String currLine;
        while (!isExit) {
            try {
                currLine = sc.nextLine();
                Command currCommand = Parser.parse(currLine);
                currCommand.execute(this.itemList);
                isExit = currCommand.isExit();
            } catch (DukeException e) {
                System.out.println(styleResponse(e.toString()));
            }
        }
        sc.close();
    }

    public static String styleResponse(String inputString) {
        return "     --------------------\n     " + inputString + "\n     --------------------";
    }

    /**
     * Uses a line-wise input array of Strings and formats it for output.
     * @param inputStrings
     * @return String of output.
     */
    public static String styleResponse(ArrayList<String> inputStrings) {
        String returnBuffer = "     --------------------\n";
        for (String line : inputStrings) {
            returnBuffer = returnBuffer.concat("     " + line + "\n");
        }
        return returnBuffer + "     --------------------";
    }
}
