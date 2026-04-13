package Vehicles;

import java.time.LocalDateTime;

public class Report {
    private LocalDateTime entryDate;
    private LocalDateTime exitDate;

    public Report() {
        this.entryDate = LocalDateTime.now();
        this.exitDate = null;
    }

    public void reportExit() {
        this.exitDate = LocalDateTime.now();
    }

    public int calculateMinutes() {
        return exitDate.getMinute() - entryDate.getMinute();
    }
}
