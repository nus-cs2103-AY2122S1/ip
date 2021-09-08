import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.Duke;
import duke.ui.Ui;
import stub.DukeStub;

public class UiTest {
    private final Ui ui;
    private final Duke duke;

    UiTest() throws IOException {
        ui = new Ui("Test user");
        duke = new DukeStub();
    }

    @Test
    void testBye() {
        Ui ui = new Ui("Test bye");
        ui.checkInput("bye", duke);
        Assertions.assertTrue(ui.willExit());
    }

}
