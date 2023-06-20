package Nested.workerDomain;

import java.util.Comparator;

public class StoreWorker extends Worker{
    private String store;

    public StoreWorker() {
    }

    public StoreWorker(int workerID, String name, int yearStarted, String store) {
        super(workerID, name, yearStarted);
        this.store = store;
    }

    @Override
    public String toString() {
        return "%-8s%s".formatted(store,super.toString());
    }

    public class StoreComparator <T extends StoreWorker> implements Comparator<StoreWorker>{

        @Override
        public int compare(StoreWorker o1, StoreWorker o2) {
            int result = o1.store.compareTo(o2.store);
            if(result ==0){
                return new Worker.WorkerComparator<>("yearStarted").compare(o1,o2);
            }
            return result;
        }
    }
}
