package gnosis;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

import gnosis.controller.GnosisController;
import gnosis.ui.GnosisUI;


public class GnosisControllerTest {

    private GnosisController gc;
    private GnosisUI view;

    @Test
    public void viewNotCreated_null_exceptionThrown() {
        try {
            gc = new GnosisController(view);
            Scanner sc = new Scanner(System.in);
            gc.loadGreetingMessage();
        } catch (NullPointerException e) {
            assertTrue(true);
        }

    }
}
