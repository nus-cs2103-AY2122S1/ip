import Duke.Duke;
import Duke.Storage;
import Duke.TaskList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class DukeTest {
    @TempDir
    Path tempPath;

    @Test
    public void PreexistingTaskTest() {
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("My string".getBytes());
        System.setIn(in);
        Duke testDuke = new Duke("testInput/duke.txt");
    }
}