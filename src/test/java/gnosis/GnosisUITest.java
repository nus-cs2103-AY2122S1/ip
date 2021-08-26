package gnosis;

import org.junit.jupiter.api.Test;
import gnosis.ui.GnosisUI;

import static org.junit.jupiter.api.Assertions.*;

public class GnosisUITest {
    GnosisUI view = new GnosisUI();

    @Test
    public void isUIListening_isListening_success() {
        assertFalse(view.isStillListeningInput());
    }


}
