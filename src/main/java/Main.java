import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Duke duke = new Duke("data/data.txt");
        duke.start();
    }
}