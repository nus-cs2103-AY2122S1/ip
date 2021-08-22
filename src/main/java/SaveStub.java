import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SaveStub {

    public static void main(String[] args) {
        Path data = Path.of("data");
        Path saveFile = data.resolve("duke.txt");
        if (Files.notExists(data)) {
            try {
                Files.createDirectory(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            String userDirectory = Path.of("")
                    .toAbsolutePath()
                    .toString();
            System.out.println("Alr exists" + data);
        }
        if (Files.notExists(saveFile)) {
            try {
                Files.createFile(saveFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            String userDirectory = Path.of("")
                    .toAbsolutePath()
                    .toString();
            System.out.println("Alr exists" + saveFile);
        }
        try {
            Files.writeString(saveFile, "HI THEREskadsadla");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
