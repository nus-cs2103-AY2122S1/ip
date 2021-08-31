package duke;

import java.io.File;
import java.io.IOException;

public class Main {
    /** Runs Duke
     */
    public static void main(String[] args) {
        try {
            File note = new File("duke.txt");
            if (note.createNewFile()) {
                System.out.println("created");
            }
            Duke duke = new Duke(note);
            duke.run();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
