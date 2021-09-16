package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.command.CommandKeyword;
import duke.exception.InvalidCommandException;
import javafx.scene.layout.VBox;

public class UiTest {
    private Ui ui;

    @BeforeEach
    void setUp() {
        this.ui = new Ui(new VBox());
        HashMap<String, CommandKeyword> listOfCommands = new HashMap<>();
        listOfCommands.put("LIST", CommandKeyword.LIST);
        this.ui.setListOfCommands(listOfCommands);
    }

    @Test
    void testInvalidReadCommand() {
        assertThrows(InvalidCommandException.class, () -> {
            this.ui.readCommand("Invalid command");
        });
    }

    @Test
    void testValidReadCommand() {
        try {
            Command command = this.ui.readCommand("list");
            assertEquals(new Command(CommandKeyword.LIST, ""), command);
        } catch (InvalidCommandException e) {
            assertTrue(false);
        }
    }
}
