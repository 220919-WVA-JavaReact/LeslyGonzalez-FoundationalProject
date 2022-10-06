package week3.List;

import java.util.Comparator;
import java.util.List;

public class ArrayList {

    /*
    Below is a list of exercises to help you get familiar with working with the ArrayList Collection
     */
    public static void main(String[] args) {

        // 1. Write a Java program to create a new array list, add some colors (string) and print out the collection.
        List<String> rainbow = new java.util.ArrayList<>();

        rainbow.add("Red");
        rainbow.add("Orange");
        rainbow.add("Yellow");
        rainbow.add("Green");
        rainbow.add("Blue");
        rainbow.add("Indigo");
        rainbow.add("Violet");

        System.out.println(rainbow);


        // 2. Write a Java program to iterate through all elements in an array list
        rainbow.forEach(colors -> {
            System.out.println(colors);
        });


        // 3. Write a Java program to insert an element into the array list at the first position
        rainbow.add(0,"Pink");
        System.out.println(rainbow);


        // 4. Write a Java program to retrieve an element (at a specified index) from a given array list

        String favoriteColor = rainbow.get(0);
        System.out.println("favorite color: " + favoriteColor);


        // 5. Write a Java program to remove the third element from an array list.
        rainbow.remove(2);
        System.out.println(rainbow);



        // 6. Write a Java program to search an element in an array list.
        System.out.println(rainbow.indexOf("Yellow"));


        // 7. Write a Java program to sort a given array list.

        System.out.println("Before: " + rainbow);

        rainbow.sort(Comparator.naturalOrder());

        System.out.println("Sorted Cloros: " + rainbow);

    }


}
