package myjournal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Scanner;

public class Date {
    LocalDate date;

    Date(LocalDate date) {
        this.date = date;
    }

    public int getYear() {
        return this.date.getYear();
    }

    public Month getMonth() {
        return this.date.getMonth();
    }
}