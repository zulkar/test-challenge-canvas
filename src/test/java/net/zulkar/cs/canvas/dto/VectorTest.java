package net.zulkar.cs.canvas.dto;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class VectorTest {

    @Test
    public void forEachShouldGoRowByRow() {
        List<String> recorded = new ArrayList<>();

        new Vector(3, 3).forEachDot((i, j) -> {
            recorded.add(i + "-" + j);
        });

        assertEquals(Arrays.asList(
                "0-0", "1-0", "2-0",
                "0-1", "1-1", "2-1",
                "0-2", "1-2", "2-2"
        ), recorded);
    }

}