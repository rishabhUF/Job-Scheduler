import java.util.HashMap;

public class Processor
{
    String name;
    String taskAcquired;
    // to keep the number of process equal to N.


    public String getTaskAcquired() {
        return taskAcquired;
    }

    public void setTaskAcquired(String taskAcquired) {
        this.taskAcquired = taskAcquired;
    }

    public Processor(String name,int capacity)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
