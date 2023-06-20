package Nested;

import Nested.employeeDomain.Employee;
import Nested.workerDomain.StoreWorker;
import Nested.workerDomain.Worker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RunMethods {
    public static void main(String[] args) {
        List<Employee> employee = new ArrayList<>(List.of(
                new Employee(1875,"Rarwin",2012),
                new Employee(1412,"Aapastapulos",2000),
                new Employee(1433,"Ceremay",2020),
                new Employee(1214,"Eduardo",1999),
                new Employee(1345,"Gascari",2002),
                new Employee(1106,"Balphonso",2009))
        );

        List<StoreWorker> storeWorkers = new ArrayList<>(List.of(
                new StoreWorker(1875,"Rarwin",2012,"Target"),
                new StoreWorker(1412,"Aapastapulos",2000,"Walmart"),
                new StoreWorker(1433,"Ceremay",2020,"Macys"),
                new StoreWorker(1214,"Eduardo",1999,"Walmart"),
                new StoreWorker(1345,"Gascari",2002,"Target"),
                new StoreWorker(1106,"Balphonso",2009,"Macys"))
        );


        var C1 = new Worker.WorkerComparator<StoreWorker>();  //uses static nested class
        var C2 = new StoreWorker().new StoreComparator<StoreWorker>();  //uses inner class

        class NameSort<T> implements Comparator<StoreWorker>{
            @Override
            public int compare(StoreWorker o1, StoreWorker o2) {
                return o1.getName().compareTo(o2.getName());
            }
        }
        var C3 = new NameSort<StoreWorker>();

        //Using Anonymous Class
        var C4 = new Comparator<StoreWorker>(){
            @Override
            public int compare(StoreWorker o1, StoreWorker o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };



        sortIt(storeWorkers,C1);
        sortIt(storeWorkers,C2);
        sortIt(storeWorkers,C3);

        //Anonymous Classes
        sortIt(storeWorkers,C4);

        //or we could use it like this
        sortIt(storeWorkers,new Comparator<StoreWorker>(){
            @Override
            public int compare(StoreWorker o1, StoreWorker o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

    public static <T> void sortIt(List<T> list, Comparator<? super T> comparator){
        System.out.println("Sorting with Comparator: " + comparator.toString());
        list.sort(comparator);
        for(var worker : list){
            System.out.println(worker);
        }
    }


}
