import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class EqualRangeJavaTest {
    // only tests that the library can be called from Java, not corner cases

    @Before
    public void setUp() {
        xs = new ArrayList<>();
        xs.add(10);
        xs.add(30);
        xs.add(30);
        xs.add(40);
    }

    @Test
    public void existingInMiddle() {
        assertEquals(1, EqualRangeSTLKt.lowerBound(xs, 30));
        assertEquals(3, EqualRangeSTLKt.upperBound(xs, 30));
    }

    private List<Integer> xs;
}
