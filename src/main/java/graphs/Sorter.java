package graphs;

import java.util.*;

public class Sorter<E> {

    /**
     * Sorts all items by insertion sort using the provided comparator
     * for deciding relative ordening of two items.
     * Items are sorted 'in place' without use of an auxiliary list or array
     *
     * @param items
     * @param comparator
     * @return the items sorted in place
     *
     **/
    public List<E> insertionSort(List<E> items, Comparator<E> comparator) {
        // TODO implement selection sort or insertion sort or bubble sort

         int n = items.size();
         for (int i = 1;  i < n; i++) {
             E k = items.get(i);
             int j = i - 1;

             while (j >= 0 && comparator.compare(items.get(j), k) > 0) {
                 items.set(j + 1, items.get(j));
                 j--;
             }

             items.set(j+1, k);
         }

        return items;   // replace as you find appropriate

    }

    /**
     * Sorts all items by quick sort using the provided comparator
     * for deciding relative ordening of two items.
     * Items are sorted 'in place' without use of an auxiliary list or array
     *
     * @param items
     * @param comparator
     * @return the items sorted in place
     */
    public List<E> quickSort(List<E> items, Comparator<E> comparator) {
        // TODO provide a recursive quickSort implementation,
        //  that is different from the example given in the lecture

        if (items.size() <= 1) {
            return items;
        }

        int mid = items.size() / 2;
        E first = items.get(0);
        E middle = items.get(mid);
        E last = items.get(items.size() - 1);

        E pivot = medianOfThree(first, middle, last, comparator);

        List<E> from = new ArrayList<>();
        List<E> to = new ArrayList<>();

        for (E item : items) {
            if (item != pivot) {  // Zorgt ervoor dat we de pivot niet dubbel toevoegen
                if (comparator.compare(item, pivot) < 0) {
                    from.add(item);
                } else {
                    to.add(item);
                }
            }
        }

        List<E> sortedLeft = quickSort(from, comparator);
        List<E> sortedRight = quickSort(to, comparator);

        items.clear();
        items.addAll(sortedLeft);
        items.add(pivot);
        items.addAll(sortedRight);

        return items;   // replace as you find appropriate
    }
    private E medianOfThree(E a, E b, E c, Comparator<E> comparator) {
        if (comparator.compare(a, b) > 0) {
            if (comparator.compare(b, c) > 0) return b;  // a > b > c → b is mediaan
            return comparator.compare(a, c) > 0 ? c : a;  // a > c > b → c of a is mediaan
        } else {
            if (comparator.compare(a, c) > 0) return a;  // b > a > c → a is mediaan
            return comparator.compare(b, c) > 0 ? c : b;  // b > c > a → c of b is mediaan
        }
    }
}
