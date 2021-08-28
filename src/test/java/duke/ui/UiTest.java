package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class UiTest {
    @Test
    public void emptyDescriptionTest_success() {
        assertEquals("    OOPS!!! The description of an event cannot be empty.", Ui.emptyDescription("event"));
    }
}
