import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class MergeJoinManager<T extends Comparable<T>> extends AbstractListMerger<T> {

    @Override
    public List<T> mergeNotEmptyLists(List<T> first, List<T> second) {
        List<T> mergedList = new ArrayList<>();
        ListIterator<T> firstItr = first.listIterator();
        ListIterator<T> secondItr = second.listIterator();
        T firstVal = firstItr.next();
        T secondVal = secondItr.next();
        while ((firstItr.hasNext() || (firstVal.compareTo(secondVal) > 0)) &&
                (secondItr.hasNext() || (firstVal.compareTo(secondVal) < 0))) {
            if (firstVal.compareTo(secondVal) < 0) {
                firstVal = firstItr.next();
            } else if (firstVal.compareTo(secondVal) == 0) {
                mergedList.add(firstVal);
                secondVal = secondItr.next();
            } else if (firstVal.compareTo(secondVal) > 0) {
                secondVal = secondItr.next();
            }
        }
        if (firstVal.equals(secondVal)) {
            mergedList.add(firstVal);
        }
        return mergedList;
    }

}
