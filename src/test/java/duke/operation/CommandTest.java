package duke.operation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {
	@Test
	public void getCommandWordFromStringTest(){
		assertEquals(Command.TODO, Command.getCommandWordFromString("todo"));
	}
}
