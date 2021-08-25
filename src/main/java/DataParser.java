import java.time.LocalDate;

public class DataParser {

    public static Task readData(String str) {
        String[] splitArray = str.split("\\|");
        switch (splitArray[0]) {
        case "E":
            Task event = new Events(splitArray[2], LocalDateParser.parse(splitArray[3]));
            if (splitArray[1].equals("1")) { event.setCompleted(); }
            return event;
            //no need for break, as function has terminated at the return statement
        case "D":
            Task deadline = new Deadlines(splitArray[2], LocalDateParser.parse(splitArray[3]));
            if (splitArray[1].equals("1")) { deadline.setCompleted(); }
            return deadline;
        //no need for break, as function has terminated at the return statement
        //last case will always be "T"
        default:
            Task todo = new Todos(splitArray[2]);
            if (splitArray[1].equals("1")) { todo.setCompleted(); }
            return todo;
        }
    }

}
