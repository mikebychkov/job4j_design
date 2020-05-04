package ru.job4j;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 mvn archetype:generate -DgroupId=ru.job4j -DartifactId=job4j_design -DarchetypeArtifactId=maven-archetype-simple -DarchetypeVersion=1.4 -DinteractiveMode=false
 mvn install
*/

public class App {

    private static class OutClass {

        InClass[] data;

        public OutClass(int size) {
            data = new InClass[size];
        }

        private class InClass {
            Object key;
            Object val;

            public InClass(Object key, Object val) {
                this.key = key;
                this.val = val;
            }
        }

        private class InClass2<K, V> {
            K[] key;
            V[] val;

            public InClass2() {
                this.key = (K[]) new Object[47];
                this.val = (V[]) new Object[47];
            }
        }

        private static class SuperInClass3<T extends Number> {
            T val;
        }

        private static class InClass3<T extends Number> extends SuperInClass3<T> {

            public void printClass() {
                Type param = (
                        (ParameterizedType) getClass().getGenericSuperclass()
                ).getActualTypeArguments()[0];
                System.out.println(
                    param
                );
            }
        }
    }

    public static class MyClass<A, B, C> {

        Class<A> aType;

        Class<B> bType;

        Class<C> cType;


        public MyClass() {
            this.aType = (Class<A>) getGenericClassType(0);
            this.bType = (Class<B>) getGenericClassType(1);
            this.cType = (Class<C>) getGenericClassType(2);
        }

        /**
         * Returns a {@link Type} object to identify generic types
         * @return type
         */
        Type getGenericClassType(int index) {

            Type type = getClass().getGenericSuperclass();

            while (!(type instanceof ParameterizedType)) {
                if (type instanceof ParameterizedType) {
                    type = ((Class<?>) ((ParameterizedType) type).getRawType()).getGenericSuperclass();
                } else {
                    type = ((Class<?>) type).getGenericSuperclass();
                }
            }

            return ((ParameterizedType) type).getActualTypeArguments()[index];
        }
    }

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
        ArrayList<OutClass> list = new ArrayList<>();
        list.add(new OutClass(7));
        System.out.println(list);

        List<String> list2 = new ArrayList<>();
        list2.add("computer");
        System.out.println(list2.get(0).getClass());

        System.out.println(list.get(0).getClass());

        ArrayList<String> list3 = new ArrayList<>();
        System.out.println(
                //list3.getClass().getGenericSignature0().getClass()
        );

        Map<Integer, String> map = new HashMap<>();

        OutClass.InClass3<Integer> ic3 = new OutClass.InClass3<>();
        ic3.printClass();

//        MyClass<String, Integer, MyClass> mc = new MyClass<>();
//        System.out.println(mc.aType);
    }
}
