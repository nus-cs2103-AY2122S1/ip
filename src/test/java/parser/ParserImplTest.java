package parser;

import logic.CommandLogicUnitStub;
import logic.ICommandLogicUnit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.IUi;
import ui.UiStub;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ParserImplTest {
    private static ParserImpl parserImpl;
    
    @BeforeAll
    public static void setUpEnvironment() {
        ICommandLogicUnit commandLogicUnit = new CommandLogicUnitStub();
        IUi ui = new UiStub();
        parserImpl = new ParserImpl(commandLogicUnit, ui);
    }
    
    @Test
    @DisplayName("Parser should be able to take valid input from user")
    public void processInput_validCommand_parsedSuccessfully() {
        // @author McDowell
        // Reused from http://www.javased.com/?post=1647907
        // with minor modifications
        
        String userInput = "man \r\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
            System.out.println(input);
            assertDoesNotThrow(() -> parserImpl.processInput(input));
        }
    }
    
    @Test
    @DisplayName("Parser should be able to take rubbish input from user")
    public void processInput_invalidCommand_handledSuccessfully() {
        String userInput = "this is some rubbish input!\r\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
            System.out.println(input);
            assertDoesNotThrow(() -> parserImpl.processInput(input));
        }
    }
}
