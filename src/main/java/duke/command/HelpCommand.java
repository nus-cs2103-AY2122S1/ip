package duke.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HelpCommand extends Command {

    public HelpCommand() {
    }

    @Override
    public String reply() {
        StringBuilder builder = new StringBuilder();
        try {
            File file = new File("taskFile/instructions.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                builder.append(sc.nextLine()).append("\n");
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
