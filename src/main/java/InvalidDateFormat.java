public class InvalidDateFormat extends DukeException{

    @Override
    public String toString() {
        return "Invalid format, please use yyyy-MM-dd for date and HH:mm for time in 24hr format.";
    }
}