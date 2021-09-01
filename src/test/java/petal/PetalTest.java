package petal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class PetalTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private Petal petal;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void startProperly_noInput_noOutput() {
        petal = new Petal();
        assertEquals("", outputStream.toString().trim());
    }



}
