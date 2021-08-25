package yoyo.core;

import org.junit.jupiter.api.Test;
import yoyo.Yoyo;
import yoyo.command.*;
import yoyo.core.Parser;
import yoyo.exception.YoyoException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parserParse_matchingCommand_success() {
        String input1 = "bye";
        String input2 = "list";
        String input3 = "done 3";
        String input4 = "delete 3";
        String input5 = "todo eat";
        String input6 = "deadline /by 1999-11-11 1900";
        String input7 = "event /at 1999-11-11 1899";
        String[] inputTokens1 = {"bye"};
        String[] inputTokens2 = {"list"};
        String[] inputTokens3 = {"done", "3"};
        String[] inputTokens4 = {"delete", "3"};
        String[] inputTokens5 = {"todo", "eat"};
        String[] inputTokens6 = {"deadline", "/by 1999-11-11 1900"};
        String[] inputTokens7 = {"event", "/at 1999-11-11 1899"};
        
        try {
            assertEquals(new CommandBye(inputTokens1), Parser.parse(input1));
            assertEquals(new CommandList(inputTokens2), Parser.parse(input2));
            assertEquals(new CommandDone(inputTokens3), Parser.parse(input3));
            assertEquals(new CommandDelete(inputTokens4), Parser.parse(input4));
            assertEquals(new CommandTodo(inputTokens5), Parser.parse(input5));
            assertEquals(new CommandDeadline(inputTokens6), Parser.parse(input6));
            assertEquals(new CommandEvent(inputTokens7), Parser.parse(input7));
        } catch (YoyoException e) {
            fail();
        }
        
        
    }
}
