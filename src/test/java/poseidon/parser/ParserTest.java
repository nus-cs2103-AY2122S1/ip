package poseidon.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import poseidon.command.Command;
import poseidon.exception.PoseidonDateTimeParseException;

class ParserTest {

    private static final String CMD_1 = "todo finish work";
    private static final String CMD_2 = "deadline finish work /by 2021 09 30 2359";
    private static final String CMD_3 = "event finish work /from 2021 09 30 2000 to 2021 09 30 2200";
    private static final String CMD_4 = "bye";
    private static final String CMD_5 = "delete 10";
    private static final String CMD_6 = "done 10";
    private static final String CMD_7 = "find work";
    private static final String CMD_8 = "help";
    private static final String CMD_9 = "list";
    private static final String CMD_10 = "list -s";
    private static final String CMD_11 = "gibberish";

    @Test
    void parse_allCommands_correctCommandInstance() {
        Command command1 = Parser.parse(CMD_1);
        String commandType1 = "AddTodo";
        assertEquals(commandType1, command1.getClass().getSimpleName());

        Command command2 = Parser.parse(CMD_2);
        String commandType2 = "AddDeadline";
        assertEquals(commandType2, command2.getClass().getSimpleName());

        Command command3 = Parser.parse(CMD_3);
        String commandType3 = "AddEvent";
        assertEquals(commandType3, command3.getClass().getSimpleName());

        Command command4 = Parser.parse(CMD_4);
        String commandType4 = "Bye";
        assertEquals(commandType4, command4.getClass().getSimpleName());

        Command command5 = Parser.parse(CMD_5);
        String commandType5 = "Delete";
        assertEquals(commandType5, command5.getClass().getSimpleName());

        Command command6 = Parser.parse(CMD_6);
        String commandType6 = "Done";
        assertEquals(commandType6, command6.getClass().getSimpleName());

        Command command7 = Parser.parse(CMD_7);
        String commandType7 = "Find";
        assertEquals(commandType7, command7.getClass().getSimpleName());

        Command command8 = Parser.parse(CMD_8);
        String commandType8 = "Help";
        assertEquals(commandType8, command8.getClass().getSimpleName());

        Command command9 = Parser.parse(CMD_9);
        String commandType9 = "List";
        assertEquals(commandType9, command9.getClass().getSimpleName());

        Command command10 = Parser.parse(CMD_10);
        String commandType10 = "Sort";
        assertEquals(commandType10, command10.getClass().getSimpleName());

        Command command11 = Parser.parse(CMD_11);
        String commandType11 = "Fail";
        assertEquals(commandType11, command11.getClass().getSimpleName());
    }

    @Test
    public void parseDateTime_dateTimeString_correctDateTimeObject() {
        String dateTimeString = "2021 09 17 2359";
        String expectedDateTimeObjectToString = "2021-09-17T23:59";
        String localDateTimeToString = "";
        try {
            LocalDateTime localDateTime = Parser.parseDateTime(dateTimeString);
            localDateTimeToString = localDateTime.toString();
        } catch (PoseidonDateTimeParseException ex) {
            System.out.println(ex.getMessage());
            fail();
        }
        assertEquals(expectedDateTimeObjectToString, localDateTimeToString);
    }

    @Test
    public void parseIndex_intString_integer() {
        assertEquals(1, Parser.parseIndex("1"));
        assertEquals(23, Parser.parseIndex("23"));
        assertEquals(-10, Parser.parseIndex("-10"));
    }

    @Test
    public void isParsedBye_byeCommand() {
        String byeCommand1 = "bye";
        String byeCommand2 = "BYE";
        String byeCommand3 = "Bye      ";
        String byeCommand4 = "Not a bye";

        assertTrue(Parser.isParsedBye(byeCommand1));
        assertTrue(Parser.isParsedBye(byeCommand2));
        assertTrue(Parser.isParsedBye(byeCommand3));
        assertFalse(Parser.isParsedBye(byeCommand4));
    }
}
