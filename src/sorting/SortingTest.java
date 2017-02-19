package sorting;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SortingTest {
    public static void main(String[] args) {
        Random r = new Random();
        Integer[] arr = Stream.generate(r::nextInt).limit(10000000).toArray(Integer[]::new);

//        testSorting(new SelectionSorting(), Arrays.copyOf(arr, arr.length));
//        testSorting(new BubbleSorting(),     Arrays.copyOf(arr, arr.length));
//        testSorting(new InsertionSorting(), Arrays.copyOf(arr, arr.length));

//        testSorting(new ShellSorting(),     Arrays.copyOf(arr, arr.length));
        testSorting(new MergeSorting(),     Arrays.copyOf(arr, arr.length));
        testSorting(new BottomUpMergeSorting(),     Arrays.copyOf(arr, arr.length));
        testSorting(new QuickSorting(),     Arrays.copyOf(arr, arr.length));


        Integer[] ordered = Stream.iterate(1, (i)->i+1).limit(100).toArray(Integer[]::new);
        KnuthShuffle.shuffle(ordered);
        System.out.println(Arrays.stream(ordered).map(Object::toString).collect(Collectors.joining(",")));

    }

    private static void testSorting(Sorting s, Integer[] arr) {
        System.out.println(s.toString());
        s.sort(arr);
//        System.out.println(Arrays.stream(arr).map(Object::toString).collect(Collectors.joining(",")));
        s.printStats();

        System.out.println();
    }
}
