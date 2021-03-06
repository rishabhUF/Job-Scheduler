# Job-Scheduler
DESIGN AND IMPLEMENTATION:
------->>> Job Scheduler <<<-------
1) I have made 3 classes for this solve this problem. Task is the class which consists of id, name, jobType, dependent List, expected end Time.
   Processor class depicts each processor which consists of name, taskAcquired. The main program consists of the execution of the program.

2) Here I am considering that each time processor can do one job only. So first I am making the graph (directed graph) so that I can know the
   way of implementing tasks according to its dependent tasks. Once I have made the tasks I make the list of the tasks according to the priority
   in which they should be implemented. Like all the tasks who have no dependent tasks should be implemented and so on.

3) Then I add all the tasks in the readyToGo priority queue. In this queue I have taken the priority as End or DeadLine time. According to
   my algorithm all the tasks should be done according to the deadline time order. The tasks with earlier deadline should be executed first.

4) Then after that I assign the task to the number of processor from the readyToGo queue.



Deployment Plan
1) I have added logs in the program so that I can monitor the running of the code.
   I have made a file named test.log in which logs are added. If the log file takes up too much storage then we can take 2 different approaches
   to maintain the log files. The first one would be to have a separate log file for every day. This will help in maintaining log file
   for everyday. The second approach would be to periodically back up the log file to any cloud service to free up space from the server or machine.
   This approach will help to decrease the space taken in the machine.

2) I have maintained the log level as INFO, SEVERE and WARNING. When ever I will get the logging details as Severe, I will generate an alert
   to be sent to the appropriate team because the code was not able to run properly and it stopped in between. It will also create a problem for the
   end user as he/she will be unable to access the service. I will keep monitoring the log with Info level, as that keeps me updated about whether or not
   my program is returning the expected value or not.

3) I will also make a graph of number of processors sitting idle versus time. So that I can understand the amount of time my processors and
   sitting idle and efficiency of my algorithm. I would want to create alerts if too many requests are failing from being served which informs
   me that something is wrong while scheduling the processes

Test Plan
1) Basically I will test the program with small input size. When I will get the results of the program then again
   I will deploy the program in the test application and use real world test input and monitor the results. If the results are as expected
   then I will deploy the application on one specific region, for example, Texas in USA. After successful
   deployment to one region I can start deployment for different regions.