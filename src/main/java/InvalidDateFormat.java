public class InvalidDateFormat extends DukeException{

    @Override
    public String toString() {
        return "Invalid format, please use dd/MM/yyyy for date and HHmm for time";
    }
}