import org.junit.jupiter.api.Test;
import util.commands.DelCommand;
import util.commands.ExitCommand;
import util.parser.Parser;
import util.tasks.DateTaskTable;
import util.tasks.Deadline;
import util.tasks.DukeException;
import util.tasks.DatedTask;
import util.tasks.TaskList;
import util.ui.Ui;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DukeTester {

    @Test
    public void testDatedTaskTable() {
        try {
            DateTaskTable t = new DateTaskTable();
            DatedTask[] arr = new DatedTask[3];
            arr[0] = Deadline.of("Working by life /by 2020-08-20");
            arr[1] = Deadline.of("Sleeping in life /by 2020-08-20");
            arr[2] = Deadline.of("kdsfmekfsdf /by 2020-08-20");


            t.add(arr[0]);
            t.add(arr[1]);
            t.add(arr[2]);


            ArrayList<DatedTask> ls = t.get(Parser.dateParse("2020-08-20"));
            assertEquals( 3, ls.size());
            for (int i = 0; i < ls.size(); i++) {
                assertEquals(arr[i], t.get(Parser.dateParse("2020-08-20")).get(i));
            }
        } catch (DukeException e) {
            System.err.println(e.getMessage());

        }
    }

    @Test
    public void testDeleteCommand() {
        try {
            DateTaskTable t = new DateTaskTable();
            TaskList tasklist = new TaskList();
            t.add(Deadline.of("Driving a car /by 2020-08-20"));
            t.add(Deadline.of("Driving a traing/by2020-08-20"));
            t.add(Deadline.of("Building a car/by 2020-08-20"));
            tasklist.add(Deadline.of("Driving a car /by 2020-08-20"));
            tasklist.add(Deadline.of("Driving a traing/by2020-08-20"));
            tasklist.add(Deadline.of("Building a car/by 2020-08-20"));
            DelCommand removeDrCar = new DelCommand(1, tasklist, t);
            removeDrCar.execute();
            assertEquals(Deadline.of("Driving a traing/by2020-08-20"), tasklist.get(0));
            assertEquals(Deadline.of("Driving a traing/by2020-08-20"), t.get(Parser.dateParse("2020-08-20")).get(0));
        } catch (DukeException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testParser() {
        DateTaskTable t = new DateTaskTable();
        TaskList tasklist = new TaskList();
        Parser p = new Parser(new Ui(), tasklist, t);
        try {
            p.inputsParser("deadline fly a car /by 2020-08-01").executeAll();
            p.inputsParser("todo Build the empire states building").executeAll();
            assertEquals(2, tasklist.size());
            assertEquals( t.get(Parser.dateParse("2020-08-01")).get(0), tasklist.get(0));
            p.inputsParser("bye").executeAll();
            assertEquals(true, ExitCommand.isExit);


        } catch (DukeException e) {
            System.err.println(e.getMessage());
        }


    }



}
