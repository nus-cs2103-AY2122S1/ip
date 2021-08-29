import duke.Ui;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    public void showErrorTest() {
        Ui u = new Ui();
        assertEquals("There has been a loading error", u.showLoadingError());
    }
}
