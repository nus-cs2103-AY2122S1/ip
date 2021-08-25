package duke.ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    public void getCommandFormat_randomString_success() {
        assertEquals("Please follow the format:\nrandom task <task description> time",
                Ui.getCommandFormat("random task", "time"));
    }

    @Test
    public void getCommandFormat_emptyString_success() {
        assertEquals("Please follow the format:\n <task description> ",
                Ui.getCommandFormat("", ""));
    }

    @Test
    public void getCommandFormat_edgeCase_success() {
        assertEquals("Please follow the format:\n\n <task description> time",
                Ui.getCommandFormat("\n", "time"));
    }
}
