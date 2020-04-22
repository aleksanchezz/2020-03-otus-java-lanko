package ru.otus.collection;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class DIYArrayListTest {

    @Test
    public void testContains() {
        List<Integer> diyArrayList = new DIYArrayList<>();
        diyArrayList.add(12);
        assertThat(diyArrayList.contains(12)).isTrue();
        assertThat(diyArrayList.contains(null)).isFalse();
    }

    @Test
    public void testIndexSearch() {
        List<Integer> diyArrayList = new DIYArrayList<>();
        diyArrayList.add(12);
        diyArrayList.add(15);
        diyArrayList.add(16);
        assertThat(diyArrayList.indexOf(15)).isEqualTo(1);
        assertThat(diyArrayList.indexOf(263)).isEqualTo(-1);
    }

    @Test
    public void testListIterator() {
        List<Integer> diyArrayList = new DIYArrayList<>();
        diyArrayList.add(15);
        diyArrayList.add(16);
        ListIterator<Integer> iterator = diyArrayList.listIterator();
        // First iteration
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.hasPrevious()).isFalse();
        assertThat(iterator.nextIndex()).isEqualTo(0);
        assertThat(iterator.next()).isEqualTo(15);
        // Second iteration
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.hasPrevious()).isTrue();
        assertThat(iterator.nextIndex()).isEqualTo(1);
        assertThat(iterator.next()).isEqualTo(16);
        // Third iteration
        assertThat(iterator.hasNext()).isFalse();
        assertThat(iterator.hasPrevious()).isTrue();
    }

    @Test
    public void testIterator() {
        List<Integer> diyArrayList = new DIYArrayList<>();
        diyArrayList.add(1);
        diyArrayList.add(2);
        diyArrayList.add(3);
        for (Integer value: diyArrayList) {
            System.out.println(value);
        }
    }

    @Test
    public void testAddAllFunctionality() {
        List<Integer> diyArrayList = new DIYArrayList<>();
        IntStream.range(3000, 5000).forEach(diyArrayList::add);
        Collections.addAll(diyArrayList, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        assertThat(diyArrayList.size()).isEqualTo(2010);
    }

    @Test
    public void testCopyFunctionality() {
        List<Integer> diyArrayList = new DIYArrayList<>();
        IntStream.range(300, 500).forEach(diyArrayList::add);
        List<Integer> copyList = Arrays.asList(new Integer[diyArrayList.size()]);
        Collections.copy(copyList, diyArrayList);
        assertThat(diyArrayList).containsExactlyElementsOf(copyList);
    }

    @Test
    public void testRandomSortFunctionality() {
        Random random = new Random();
        List<Integer> diyArrayList = new DIYArrayList<>();
        IntStream.range(1000, 2000).forEach(i -> diyArrayList.add(random.nextInt()));
        Collections.sort(diyArrayList);
        assertThat(diyArrayList).isSorted();
    }

    @Test
    public void testControlledSortFunctionality() {
        List<Integer> diyArrayList = new DIYArrayList<>();
        IntStream.range(3000, 5000).forEach(diyArrayList::add);
        Collections.addAll(diyArrayList, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Collections.sort(diyArrayList);
        assertThat(diyArrayList).isSorted();
    }

    @Test
    public void testSortingWithComparatorFunctionality() {
        Random random = new Random();
        List<Integer> diyArrayList = new DIYArrayList<>();
        IntStream.range(1000, 2000).forEach(i -> diyArrayList.add(random.nextInt()));
        Collections.sort(diyArrayList, Comparator.naturalOrder());
        assertThat(diyArrayList).isSorted();
    }
}
