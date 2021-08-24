package jarvis.parser;

import jarvis.command.CommandTypeEnum;
import jarvis.exception.UnknownCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void isUserInputEmpty_emptyInput_true() {
        boolean result = Parser.isUserInputEmpty("");
        assertTrue(result);
    }

    @Test
    public void getCommandTypeFromInput_listCommand_listEnum() throws UnknownCommandException {
        String input = "   list    ";
        CommandTypeEnum resultingEnum = Parser.getCommandTypeFromInput(input);
        assertEquals(CommandTypeEnum.LIST, resultingEnum);
    }
}
