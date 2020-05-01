package ru.job4j;

import java.util.Arrays;
import java.util.Objects;

/**
 mvn archetype:generate -DgroupId=ru.job4j -DartifactId=job4j_design -DarchetypeArtifactId=maven-archetype-simple -DarchetypeVersion=1.4 -DinteractiveMode=false
 mvn install
*/

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println(Integer.hashCode(17));
        System.out.println(Double.hashCode(17.6));
        System.out.println((int) 'h');
        Integer[] ar = {1, 2, 3, 4, 5};
        System.out.println(Arrays.hashCode(ar));
        //System.out.println(ar[5]);
        //Objects.checkIndex(2, 2);
        int index = 2;
        int len = ar.length - 1 - index;
        System.arraycopy(ar, index + 1, ar, index, len);
        for (Integer a : ar) {
            System.out.print(a + " ");
        }
    }
}
