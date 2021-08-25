package gnosis;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;


public class GnosisControllerTest {
    GnosisController gc;
    GnosisUI view;

    @Test
    public void viewNotCreated_null_exceptionThrown() {
        try{
            gc = new GnosisController(view);
            Scanner sc = new Scanner(System.in);
            gc.startGnosis(null);
        } catch (NullPointerException e) {
            assertTrue(true);
        }

    }
}
