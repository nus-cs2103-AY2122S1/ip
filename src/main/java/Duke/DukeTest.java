package Duke;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileInputStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void TestSaveFile(){
        try {
            //clear file first
            String FILE_PATH = "C:/Users/shuiz/OneDrive/Documents/GitHub/ip/src/" +
                    "main/java/Duke/Duke.txt";
            new FileWriter(FILE_PATH, false).close();
            FileInputStream in = new FileInputStream("C:/Users/shuiz/OneDrive/Documents/GitHub/ip/src/" +
                        "main/java/Duke/TestInput.txt");
            System.setIn(in);
            String[] args = {""};
            Duke.main(args);

            FileInputStream f1 = new FileInputStream("TestOutput.txt");
            FileInputStream f2 = new FileInputStream("Duke.txt");
            assertEquals(f1, f2);
        }catch (IOException io) {
            System.out.println(io.getLocalizedMessage());
        }
    }

    @Test
    public void testInvalidFormat() {

        try {
            //clear file first
            String FILE_PATH = "C:/Users/shuiz/OneDrive/Documents/GitHub/ip/src/" +
                    "main/java/Duke/Duke.txt";
            new FileWriter(FILE_PATH, false).close();
            FileInputStream in = new FileInputStream("C:/Users/shuiz/OneDrive/Documents/GitHub/ip/src/" +
                    "main/java/Duke/TestInput2.txt");
            System.setIn(in);
            String[] args = {""};
            Duke.main(args);

            FileInputStream f1 = new FileInputStream("TestOutput2.txt");
            FileInputStream f2 = new FileInputStream("Duke.txt");
            assertEquals(f1, f2);
        }catch (IOException io) {
            System.out.println(io.getLocalizedMessage());
        }
    }
}
