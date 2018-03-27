import com.sun.deploy.util.ArrayUtil;

import java.io.IOException;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {

    public static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void dfs(int n, int root, ArrayList[] graph, ArrayList[] listGraph) {
        Iterator<Integer> i = graph[n].listIterator();
        while (i.hasNext()) {
            int h = i.next()-1;
            if (!listGraph[root].contains(h+1)){
                listGraph[root].add(h+1);
            }
            dfs(h, root, graph,listGraph);
        }
    }










    public static void main(String[] args) throws IOException {
        FileHandler fp;
        fp = new FileHandler("test.log");
        logger.addHandler(fp);
        SimpleFormatter formatter = new SimpleFormatter();
        fp.setFormatter(formatter);

        // Number of processors
        int numProcesser = 3;
        //
        Task t6 = new Task(6,"F", 30,null);
        // TASK T4
        ArrayList<Task> dt4 = new ArrayList<>();
        dt4.add(t6);
        Task t4 = new Task(4,"D", 28, dt4);
        // TASK T5
        ArrayList<Task> dt5 = new ArrayList<>();
        dt5.add(t6);
        Task t5 = new Task(5,"E",26,dt5);
        // TASK T7
        ArrayList<Task> dt7= new ArrayList<>();
        dt7.add(t5);
        Task t7 = new Task(7,"G",24,dt7);
        // TASK T3
        ArrayList<Task> dt3= new ArrayList<>();
        dt3.add(t7);
        dt3.add(t4);
        Task t3 = new Task(3,"C", 42, dt3);
        // TASK T2
        ArrayList<Task> dt2= new ArrayList<>();
        dt2.add(t3);
        Task t2 = new Task(2,"B", 75, dt2);
        // TASK T1
        ArrayList<Task> dt1= new ArrayList<>();
        dt1.add(t2);
        Task t1 = new Task(1,"A",100, dt1);
        // List of all the tasks
        List<Task> allTasks = new ArrayList<>();
        allTasks.add(t1);
        allTasks.add(t2);
        allTasks.add(t3);
        allTasks.add(t4);
        allTasks.add(t5);
        allTasks.add(t6);
        allTasks.add(t7);
        // Making the adjacency List.
        ArrayList<Integer>[] graph = new ArrayList[allTasks.size()];
        for(int i=0;i<allTasks.size();i++)
        {
            graph[i] = new ArrayList();
            if(allTasks.get(i).getDependentTask() == null)
                continue;
            for(int j=0; j < allTasks.get(i).getDependentTask().size();j++)
            {
                graph[i].add(allTasks.get(i).getDependentTask().get(j).getName());
            }
        }
        logger.log(Level.INFO, "graph for the tasks generated");
        ArrayList[] listGraph = new ArrayList[allTasks.size()];
        for(int i=0;i<allTasks.size();i++)
        {
            listGraph[i] = new ArrayList();
        }

        for(int i=0;i<allTasks.size();i++)
        {
            Integer root = i;
            dfs(i,root,graph,listGraph);
        }
        logger.log(Level.INFO, "Level for execution of the tasks generated");

        PriorityQueue<Task> taskQueue = new PriorityQueue<>(allTasks.size(), new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                if(o1.getDeadLineTime() > o2.getDeadLineTime())
                    return -1;
                else if(o1.getDeadLineTime() < o2.getDeadLineTime())
                    return 1;
                else
                    if(o1.getExpectedEndTime() > o2.getExpectedEndTime())
                        return -1;
                    else if(o1.getExpectedEndTime() > o2.getExpectedEndTime())
                        return 1;
                    else
                        return 0;
            }
        });



        List<Integer> readyToGo = new ArrayList<>();
        for(int i=0;i<allTasks.size();i++)
        {
            if(listGraph[i].size() == 0) 
            {
                taskQueue.add(allTasks.get(i));
            }
        }

        if(taskQueue.size() == 0)
        {
            logger.log(Level.INFO, "No process can be processed");
            System.out.println("No process can be processed");
            return;
        }

        PriorityQueue<Task> processorQueue = new PriorityQueue<>(numProcesser, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                if(o1.getExpectedEndTime() < o2.getExpectedEndTime())
                    return 1;
                else if(o1.getExpectedEndTime() > o2.getExpectedEndTime())
                    return -1;
                else
                    return 0;
            }
        });

        int currentTime = 0;

        while(taskQueue.size()!=0 || processorQueue.size()!=0 )
        {
            while (taskQueue.size()!=0 && processorQueue.size() <= numProcesser)
            {
                Task temp = taskQueue.poll();
                if(temp.getExpectedEndTime() + currentTime <= temp.getDeadLineTime())
                {
                    processorQueue.add(temp);
                }
                else
                {
                    logger.log(Level.WARNING, temp.getName() + " Unable to process task");
                    System.out.println("Unable to process" + temp.getName());
                }
            }
            if(processorQueue.size()>0)
            {
                Task temp = processorQueue.poll();
                currentTime = temp.getExpectedEndTime();

                for(int i=0;i<listGraph.length;i++)
                {
                    if(listGraph[i].contains(temp.getName()))
                    {
                        logger.log(Level.INFO, listGraph[i].get(listGraph[i].indexOf(temp.getName())) + " Task completed");
                        listGraph[i].remove(listGraph[i].indexOf(temp.getName()));
                        if(listGraph[i].size() == 0)
                        {
                            taskQueue.add(allTasks.get(i));
                        }
                    }
                }
            }
        }

        for(int i = 0; i<readyToGo.size();i++)
        {
            System.out.println(readyToGo.get(i));
        }

        for(int i=0;i<allTasks.size();i++)
        {
            System.out.println(i + " " + listGraph[i]);
        }
    }
}
