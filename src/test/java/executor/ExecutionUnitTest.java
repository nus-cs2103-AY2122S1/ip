package executor;

import exception.CommandArityException;
import exception.LogicException;
import executor.parser.StorageParser;
import model.task.Task;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExecutionUnitTest {
    @Test
    public void testCorrectInit() {
        assertDoesNotThrow(() -> {
            ExecutionUnit executor = new ExecutionUnit("resource/taskList1.txt");
            executor.initStorage();
        });
    }

    @Test
    public void testIncorrectFilename() {
        assertThrows(IOException.class, () -> {
            ExecutionUnit executor = new ExecutionUnit("resource/taskList.txt");
            executor.initStorage();
        });
    }

    @Test
    public void testTodoCreation() {
        String todoQuery = "todo eat breakfast";
        String[] todoExpected = new String[]{"Got it. I've added this task:",
                                            "  [T][ ] eat breakfast",
                                            "Now you have 1 tasks in the list"};

        assertDoesNotThrow(() -> {
            ExecutionUnit executor = new ExecutionUnit("resource/taskList1.txt");
            executor.initStorage();
            String[] todoActual = executor.executeCommand(todoQuery);
            todoExpected[2] = todoActual[2]; // Hack to avoid dependency on actual content of taskList1.txt
            assertArrayEquals(todoExpected, todoActual);
        });
    }

    @Test
    public void testCommandArity() {
        String deadlineQuery = "deadline eat breakfast";
        String eventQuery = "event eat breakfast";
        String todoQuery = "todo";

        assertDoesNotThrow(() -> {
            ExecutionUnit executor = new ExecutionUnit("resource/taskList2.txt");
            executor.initStorage();
            assertAll("Throws Command Arity Exceptions",
                    () -> assertThrows(CommandArityException.class, () -> executor.executeCommand(deadlineQuery)),
                    () -> assertThrows(CommandArityException.class, () -> executor.executeCommand(eventQuery)),
                    () -> assertThrows(CommandArityException.class, () -> executor.executeCommand(todoQuery)));
        });
    }

    @Test
    public void testSaveFile() {
        String deadlineQuery = "deadline eat breakfast /by 2021-01-31";
        String eventQuery = "event eat breakfast /at 2020-10-03";
        String todoQuery = "todo eat breakfast";

        assertDoesNotThrow(() -> {
            ExecutionUnit executor = new ExecutionUnit("resource/taskList2.txt");
            executor.initStorage();
            executor.executeCommand(todoQuery);
            executor.executeCommand(eventQuery);
            executor.executeCommand(deadlineQuery);

            Path path = Paths.get("resource/taskList2.txt");
            List<String> read = Files.readAllLines(path);
            int n = read.size();

            assertEquals(read.get(n - 1), "[D][ ] eat breakfast (by: 31 Jan 2021)");
            assertEquals(read.get(n - 2), "[E][ ] eat breakfast (at: 31 Jan 2021)");
            assertEquals(read.get(n - 3), "[T][ ] eat breakfast");
        });
    }
}
