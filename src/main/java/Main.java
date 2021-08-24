import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
                File note = new File("duke.txt");
                if (note.createNewFile()) {
                    System.out.println("created");
                }
            Duke duke = new Duke(note);
            duke.Run();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
