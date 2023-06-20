package Nested;

import Nested.employeeDomain.Employee;
import Nested.employeeDomain.EmployeeComparator;
import Nested.workerDomain.StoreWorker;
import Nested.workerDomain.Worker;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>(List.of(
                new Employee(1875,"Darwin",2012),
                new Employee(1412,"Papastapulos",2000),
                new Employee(1433,"Jeremay",2020),
                new Employee(1214,"Eduardo",1999),
                new Employee(1345,"Pascari",2002),
                new Employee(1106,"Alphonso",2009))
        );

        var comparator = new EmployeeComparator<>();
        employees.sort(comparator);
        for(Employee e : employees){
            System.out.println(e);
        }

        System.out.println("-".repeat(50));
        //

        List<Worker> workers = new ArrayList<>(List.of(
                new Worker(1875,"Rarwin",2012),
                new Worker(1412,"Aapastapulos",2000),
                new Worker(1433,"Ceremay",2020),
                new Worker(1214,"Eduardo",1999),
                new Worker(1345,"Gascari",2002),
                new Worker(1106,"Balphonso",2009))
        );
        //Static Nested Class -> WorkerComparator<>()
        //it Will sort for names because name is the default sort type
        workers.sort(new Worker.WorkerComparator<>());
        for(Worker e : workers){
            System.out.println(e);
        }
        System.out.println("-".repeat(50));
        //it Will sort for yearStarted
        workers.sort(new Worker.WorkerComparator<>("yearStarted"));
        for(Worker e : workers){
            System.out.println(e);
        }

        System.out.println("-".repeat(50));
        //it Will sort for workerID reverse
        workers.sort(new Worker.WorkerComparator<>("workerID").reversed());
        for(Worker e : workers){
            System.out.println(e);
        }
        System.out.println("-".repeat(50));
        System.out.println("Store Members");
        List<StoreWorker> storeWorkers = new ArrayList<>(List.of(
                new StoreWorker(1875,"Rarwin",2012,"Target"),
                new StoreWorker(1412,"Aapastapulos",2000,"Walmart"),
                new StoreWorker(1433,"Ceremay",2020,"Macys"),
                new StoreWorker(1214,"Eduardo",1999,"Walmart"),
                new StoreWorker(1345,"Gascari",2002,"Target"),
                new StoreWorker(1106,"Balphonso",2009,"Macys"))
        );
        //1_ we will get an compiler error that will say storecomparator is not static /enclosing class:Nested.workerDomain.StoreWorker' is not an enclosing class
        //var comparator3 =new  StoreWorker.StoreComparator();

        //2_we can solve this issue here
        var genericWorker = new StoreWorker();
        var comparator2 =  genericWorker.new StoreComparator<>();

        //3_ or like this
        //var comparator3 = new StoreWorker().new StoreComparator<>();
        // Inner Class (not Static) -> StoreComparator<>()

        storeWorkers.sort(comparator2);
        for(StoreWorker e : storeWorkers){
            System.out.println(e);
        }

        //To create an instance of an inner class you first must have an instance of the Enclosing class:
        //From that instance you call .new , followed by the inner class name and the parentheses taking any constructor arguments


        System.out.println("-".repeat(50));
        System.out.println("Decorated Store Workers");
        //Using a method with a local class by using the StoreWorker list we created before
        addPigLatinName(storeWorkers);
    }


    public static void addPigLatinName(List<? extends StoreWorker> list){
        String lastName = "Piggy";


        //Local Class (Modifiers are not allowed(f.e public) (they can implement interfaces)
        class DecoratedWorker extends StoreWorker implements Comparable<DecoratedWorker>{
            private String pigLatinName;
            private Worker originalInstance;

            public DecoratedWorker(String pigLatinName, Worker originalInstance) {
                this.pigLatinName = pigLatinName + " " +lastName;
                this.originalInstance = originalInstance;
            }

            @Override
            public String toString() {
                return originalInstance.toString()+ " "+ pigLatinName;
            }

            @Override
            public int compareTo(DecoratedWorker o) {
                return pigLatinName.compareTo(o.pigLatinName);
            }
        }

        List<DecoratedWorker> newList = new ArrayList<>();
        for(var worker : list){
            String name= worker.getName();
            String pigLatin = name.substring(1) + name.charAt(0) + "ay";
            newList.add(new DecoratedWorker(pigLatin,worker));
        }
        newList.sort(null); //if we pass null the method will use comparable compare to
        for(var decWorker : newList){
            System.out.println(decWorker);
        }
        // if we would change last name value we would get an error they should be final
        // lastName = "dan"; ----> Variable 'lastName' is accessed from within inner class, needs to be final or effectively final
        System.out.println("-".repeat(50));
        for(var decWorker : newList){
            System.out.println(decWorker.originalInstance.getName() + " " + decWorker.pigLatinName );
        }
    }
}
