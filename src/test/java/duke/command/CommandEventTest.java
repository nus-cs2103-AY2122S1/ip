package duke.command;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandEventTest {
    String word01 = "test01";
    String word02 = "/at";
    String word03 = "2022-10-04";
    String word04 = "18:00-19:00";
    String word05 = "asdasdasdasd";
    String word06 = "18:00";
    ArrayList<String> args = new ArrayList<>();

    @Test
    public void test01() {
        args.add(word01);
        args.add(word02);
        args.add(word03);
        args.add(word04);
        Command testCommandEvent = new CommandEvent(args);
        assertEquals(testCommandEvent.isValidArgument(), true);
        args.clear();
    }

    @Test
    //Missing time
    public void test02() {
        args.add(word01);
        args.add(word02);
        args.add(word03);
        Command testCommandEvent = new CommandEvent(args);
        assertEquals(testCommandEvent.isValidArgument(), false);
        args.clear();
    }

    @Test
    public void test03() {
        args.add(word01);
        Command testCommandEvent = new CommandEvent(args);
        assertEquals(testCommandEvent.isValidArgument(), false);
        args.clear();
    }

    @Test
    public void test04() {
        args.add(word01);
        args.add(word02);
        Command testCommandEvent = new CommandEvent(args);
        assertEquals(testCommandEvent.isValidArgument(), false);
        args.clear();
    }

    @Test
    public void test05() {
        args.add(word01);
        args.add(word02);
        args.add(word05);
        Command testCommandEvent = new CommandEvent(args);
        assertEquals(testCommandEvent.isValidArgument(), false);
        args.clear();
    }

    @Test
    public void test06() {
        args.add(word01);
        args.add(word02);
        args.add(word03);
        args.add(word05);
        Command testCommandEvent = new CommandEvent(args);
        assertEquals(testCommandEvent.isValidArgument(), false);
        args.clear();
    }

    @Test
    public void test07() {
        args.add(word01);
        args.add(word02);
        args.add(word03);
        args.add(word06);
        Command testCommandEvent = new CommandEvent(args);
        assertEquals(testCommandEvent.isValidArgument(), false);
        args.clear();
    }



}
