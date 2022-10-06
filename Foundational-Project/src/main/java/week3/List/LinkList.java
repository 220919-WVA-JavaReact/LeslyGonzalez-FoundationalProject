package week3.List;

import java.util.LinkedList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
public class LinkList {

    public static void main(String[] args) {

        // 1. Write a Java program to append the specified element to the end of a linked list. Add several values.

        LinkedList<String> food = new LinkedList<>();
        food.add("Tacos");
        food.add("Hot Wings");
        food.add("Viet food");

        System.out.println(food);

        food.addLast("Thai food");

        System.out.println("Added Thai food at the end: " + food);


        // 2. Write a Java program to iterate through all elements in a linked list.

        for(String foodName: food){
            System.out.println(foodName);
        }

        // 3. Write a Java program to iterate a linked list in reverse order
        Iterator<String> foodIterator = food.descendingIterator();
        while(foodIterator.hasNext()){
            String backwardFood = foodIterator.next();
            System.out.println(backwardFood);
        }

        // 4.  Write a Java program to insert the specified element at the front of a linked list

        food.addFirst("Steak");
        System.out.println(food);

        // 5. Write a Java program to insert some elements at the specified position into a linked list.
        food.add(4, "Tamales");
        System.out.println(food);
        // 6. Write a Java program to get the first and last occurrence of the specified elements in a linked list.

        // 7. Write a Java program to check if a particular element exists in a linked list.

        // 8. Write a Java program to convert a linked list to array list.


    }

}
