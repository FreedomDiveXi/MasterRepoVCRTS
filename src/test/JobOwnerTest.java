import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class JobOwnerTest {

    private JobOwner jobOwner;
    private Job job1;
    private Job job2;

    @BeforeEach
    public void setup() {
        jobOwner = new JobOwner("JohnDoe", "password123", "DoeCo", "555-555-5555");
        job1 = new Job("JohnDoe", 101, 5);
        job2 = new Job("JohnDoe", 102, 8, "2023-12-31");
    }

    @Test
    public void testJobOwnerConstructor() {
        assertEquals("JohnDoe", jobOwner.getUsername()); // Assuming there's a getUsername method in User class
        assertEquals("DoeCo", jobOwner.getCompanyName());
        assertEquals("555-555-5555", jobOwner.getContactNumber());
    }

    @Test
    public void testAddJob() {
        assertTrue(jobOwner.getOwnedJobs().isEmpty()); // Assuming a getter for ownedJobs or you can modify the class to provide it
        jobOwner.addJob(job1);
        assertEquals(1, jobOwner.getOwnedJobs().size());
        assertTrue(jobOwner.getOwnedJobs().contains(job1));
    }

    @Test
    public void testRemoveJob() {
        jobOwner.addJob(job1);
        jobOwner.addJob(job2);
        assertEquals(2, jobOwner.getOwnedJobs().size());

        boolean result = jobOwner.removeJob(job1);
        assertTrue(result);
        assertEquals(1, jobOwner.getOwnedJobs().size());
        assertFalse(jobOwner.getOwnedJobs().contains(job1));
        assertTrue(jobOwner.getOwnedJobs().contains(job2));
   

    // Add more tests as required.
}
