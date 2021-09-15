package duke.command;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandDeadlineTest {
    String word01 = "test01";
    String word02 = "/by";
    String word03 = "2022-10-04";
    String word04 = "18:00";
    String word05 = "asdasdasdasd";
    ArrayList<String> args = new ArrayList<>();

    @Test
    public void test01() {
        args.add(word01);
        args.add(word02);
        args.add(word03);
        Command testCommandDeadline = new CommandDeadline(args);
        assertEquals(testCommandDeadline.isValidArgument(), true);
        args.clear();
    }

    @Test
    public void test02() {
        args.add(word01);
        args.add(word02);
        args.add(word03);
        args.add(word04);
        Command testCommandDeadline = new CommandDeadline(args);
        assertEquals(testCommandDeadline.isValidArgument(), true);
        args.clear();
    }

    @Test
    public void test03() {
        args.add(word01);
        Command testCommandDeadline = new CommandDeadline(args);
        assertEquals(testCommandDeadline.isValidArgument(), false);
        args.clear();
    }

    @Test
    public void test04() {
        args.add(word01);
        args.add(word02);
        Command testCommandDeadline = new CommandDeadline(args);
        assertEquals(testCommandDeadline.isValidArgument(), false);
        args.clear();
    }

    @Test
    public void test05() {
        args.add(word01);
        args.add(word02);
        args.add(word05);
        Command testCommandDeadline = new CommandDeadline(args);
        assertEquals(testCommandDeadline.isValidArgument(), false);
        args.clear();
    }

    @Test
    public void test06() {
        args.add(word01);
        args.add(word02);
        args.add(word03);
        args.add(word05);
        Command testCommandDeadline = new CommandDeadline(args);
        assertEquals(testCommandDeadline.isValidArgument(), false);
        args.clear();
    }



}
