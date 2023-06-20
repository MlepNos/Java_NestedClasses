package Nested.burger;

public class Store  {
    public static void main(String[] args) {
        Meal regularMeal = new Meal();
        System.out.println(regularMeal);

        Meal USRegularMeal = new Meal(0.74);
        System.out.println(USRegularMeal);
    }
}
