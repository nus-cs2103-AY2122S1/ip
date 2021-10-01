package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Class that handles testing of some of the methods in Ui class */
public class UiTest {

    /**Ensuring that the error message is displayed accurately as expected */
    @Test
    public void showErrorTest() {
        Ui u = new Ui();
        assertEquals("There has been a loading error", u.showLoadingError());
    }
}
