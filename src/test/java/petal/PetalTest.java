package petal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;

import org.junit.jupiter.api.Test;



public class PetalTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Test
    public void startProperly_noInput_noOutput() {
        Petal petal = new Petal();
        assertEquals("", outputStream.toString().trim());
    }

}
