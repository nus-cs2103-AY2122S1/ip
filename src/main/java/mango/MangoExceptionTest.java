package mango;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MangoExceptionTest {
    @Test
    public void testException() {
        assertEquals("hello", new MangoException("hello").getMessage());
    }
}
