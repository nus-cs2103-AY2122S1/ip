package dialog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dialog.exceptions.DialogException;

public class DialogTest {
    @Test
    public void generateTest() throws DialogException {
        String expected =
            "    ____________________________________________________________\n"
                + "    test1\n"
                + "    test2\n"
                + "    ____________________________________________________________";
        Dialog testDialog = Dialog.generate("generateTest");
        testDialog.add("test1");
        testDialog.add("test2");
        assertEquals(testDialog.toString(), expected);
    }

    @Test
    public void generateTest2() {
        Exception exception = assertThrows(DialogException.class, () -> {
            Dialog testDialog1 = Dialog.generate("generateTest2");
            Dialog testDialog2 = Dialog.generate("generateTest2");
        });

        String expectedMessage = "generateTest2 already exists";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void addTest() throws DialogException {
        Dialog testDialog = Dialog.generate("addTest");
        testDialog.add("sentences");
        assertTrue(testDialog.sentences.contains("sentences"));
    }

    @Test
    public void getTest() throws DialogException {
        Dialog testDialog = Dialog.generate("getTest");
        testDialog.add("sentences");

        String expectedMessage =
            "    ____________________________________________________________\n"
                + "    sentences\n"
                + "    ____________________________________________________________";

        assertEquals(Dialog.get("getTest").toString(), expectedMessage);
    }

    @Test
    public void containsIdTest() throws DialogException {
        Dialog testDialog = Dialog.generate("containsIdTest");
        assertTrue(Dialog.containsId("containsIdTest"));
    }

}
