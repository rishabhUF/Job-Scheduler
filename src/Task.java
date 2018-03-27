import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class Task
{
    private String id;
    private int name;
    private String jobType;
    private int deadLineTime;
    private List<Task> dependentTask;
    private int expectedEndTime;

    public int getName() {
        return name;
    }
    
    public String getId() {
        return id;
    }

    public String getJobType() {
        return jobType;
    }

    public int getDeadLineTime() {
        return deadLineTime;
    }

    public List<Task> getDependentTask() {
        return dependentTask;
    }

    public int getExpectedEndTime() {
        return expectedEndTime;
    }

    // constructor
    public Task(int name,String jobType, int deadLineTime, List<Task> dependentTask)
    {
        this.name = name;
        this.jobType = jobType;
        this.id = UUID.randomUUID().toString();
        this.deadLineTime = deadLineTime;
        this.dependentTask = dependentTask;
        switch(jobType)
        {
            case "A":
            {
                expectedEndTime = 10;
            }
            case "B":
            {
                expectedEndTime = 20;
            }
            case "C":
            {
                expectedEndTime = 30;
            }
        }
    }

}
