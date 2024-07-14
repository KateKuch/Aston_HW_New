package org.array;

public class Test {

    public static void main(String[] args) {
        CustomArrayList<Integer> testList = new CustomArrayList<>();

        testList.add(0, 5);
        testList.add(1, 3);
        testList.add(2, 8);
        testList.add(1, 7);
        testList.add(4, 4);
        testList.add(5, 10);
        testList.add(4, 13);
        testList.add(6, 1);

        System.out.println("List: ");
        for (int i = 0; i < testList.getSize(); i++) {
            System.out.print(testList.get(i) + " ");
        }

        testList.sort(Integer::compareTo);
        System.out.println("\nSorted List: ");
        for (int i = 0; i < testList.getSize(); i++) {
            System.out.print(testList.get(i) + " ");
        }
    }
}