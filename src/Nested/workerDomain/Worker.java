package Nested.workerDomain;

import java.util.Comparator;

public class Worker {

    //Static Nested Class
    public static class WorkerComparator <T extends Worker> implements Comparator<Worker> {
        private String sortType;
        public WorkerComparator() {
            this("name");
        }

        public WorkerComparator(String sortType) {
            this.sortType = sortType;
        }

        @Override
        public int compare(Worker o1, Worker o2) {
            if(sortType.equalsIgnoreCase("yearStarted")){
                return o1.yearStarted - o2.yearStarted;
            }
            else if(sortType.equalsIgnoreCase("name")){
                return o1.name.compareTo(o2.name);
            }
            else if(sortType.equalsIgnoreCase("workerID")){
                return o1.workerID - o2.workerID;
            }
            else{
                return -1;
            }
            //now we can acces to the private members of the class directly
        }
    }
    private int workerID;
    private String name;
    private int yearStarted;

    public Worker(){}
    public Worker(int workerID, String name, int yearStarted) {
        this.workerID = workerID;
        this.name = name;
        this.yearStarted = yearStarted;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "%d %-8s %d".formatted(workerID,name,yearStarted);
    }
}
