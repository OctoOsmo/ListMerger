import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class MergerTest {

    private static final int RANDOM_NUMBER_ORIGIN = 1;
    private static final int RANDOM_NUMBER_BOUND = 999;
    private static final int FIRST_MAX_SIZE = 10;
    private static final int SECOND_MAX_SIZE = 500;
    private static List<Integer> first;
    private static List<Integer> second;
    private static List<Integer> merged;
    private static final Logger LOG = Logger.getLogger(MergerTest.class.getName());

    @BeforeClass
    public static void init() {
        first  = new Random().ints(RANDOM_NUMBER_ORIGIN, RANDOM_NUMBER_BOUND)
                .boxed()
                .limit(FIRST_MAX_SIZE)
                .distinct()
                .collect(Collectors.toList());
        second  = new Random().ints(RANDOM_NUMBER_ORIGIN, RANDOM_NUMBER_BOUND)
                .boxed()
                .limit(SECOND_MAX_SIZE)
                .distinct()
                .collect(Collectors.toList());
        Set<Integer> mergedSet = new HashSet<>(first);
        mergedSet.retainAll(new HashSet<>(second));
        merged = new ArrayList<>();
        merged.addAll(mergedSet);
        LOG.info(first.toString());
        LOG.info(second.toString());
    }

    @Test
    public void hashJoinTest() {
        ListMerger<Integer> merger = new HashListMerger<>();
        List<Integer> merged = merger.merge(first, second);
        LOG.info(merged.toString());
        Assert.assertTrue(merged.containsAll(MergerTest.merged));
    }

    @Test
    public void nestedLoopsTest(){
        ListMerger<Integer> merger = new NestedLoopsMerger<>();
        List<Integer> merged = merger.merge(first, second);
        LOG.info(merged.toString());
        Assert.assertTrue(merged.containsAll(MergerTest.merged));
    }

    @Test
    public void mergeJoinTest(){
        ListMerger<Integer> merger = new MergeJoinManager<>();
        List<Integer> firstSorted = first;
        List<Integer> secondSorted = second;
        firstSorted.sort(Integer::compareTo);
        secondSorted.sort(Integer::compareTo);

        List<Integer> merged = merger.merge(firstSorted, secondSorted);
        LOG.info(merged.toString());
        Assert.assertTrue(merged.containsAll(MergerTest.merged));
    }
}
