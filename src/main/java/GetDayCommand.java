public class GetDayCommand extends Command{
    private String next;

    public GetDayCommand(String next) {
        this.next = next;
    }
    @Override
    public void execute() {
        if (next.length() == 14) {
            try {
                TaskList.getOnADay(next.substring(4));
            } catch (DukeException e) {
                Ui.showError(e);
            }
        } else {
            Ui.myPrint("☹ OOPS!!! Please enter a valid date, such as get dd/MM/yyyy");
        }
    }
}
