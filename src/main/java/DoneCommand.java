import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DoneCommand extends Command {
    private int doneIndex;
    public DoneCommand(String desc, int doneIndex) {
        super(desc);
        this.doneIndex = doneIndex;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Storage storage) {
        tasks.get(doneIndex - 1).markAsDone();
        System.out.println("-------------------------------------");
        System.out.println("Very well, Master Wayne. This task has been marked as per your request.");
        System.out.println((doneIndex) + ". " + tasks.get(doneIndex - 1)); //actual index is index - 1
        System.out.println("-------------------------------------");

        try {
            List<String> fileContent = new ArrayList<>(Files.readAllLines(Path.of("data/tasks.txt"), StandardCharsets.UTF_8));

            String oldLine = fileContent.get(doneIndex - 1);
            StringBuilder newLine = new StringBuilder(oldLine);
            newLine.setCharAt(4, '1');

            fileContent.set(doneIndex - 1, newLine.toString());
            Files.write(Path.of("data/tasks.txt"), fileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
