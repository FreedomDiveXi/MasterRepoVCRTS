import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Job {
    private String jobOwnerName;
    private int jobID;
    private LocalTime jobDurationTime;
    private LocalDate jobDeadline;

    public Job(String jobOwnerName, int jobID, LocalTime jobDurationTime, LocalDate jobDeadline) {
        this.jobOwnerName = jobOwnerName;
        this.jobID = jobID;
        this.jobDurationTime = jobDurationTime;
        this.jobDeadline = jobDeadline;
    }
}
