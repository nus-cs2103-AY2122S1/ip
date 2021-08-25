import bobbybot.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    Parser parser = new Parser();
    TaskList tasks = new TaskList(new ArrayList<>());
    Ui ui = new Ui();

    @Test
    public void parse_bye_exitsProgram() throws InvalidArgumentException, TooManyArgumentsException {
        parser.parseCommand("bye", tasks, ui);
        //program should not leave here
        fail();
    }
}
