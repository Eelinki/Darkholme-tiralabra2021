package fi.eelij.Darkholme;

import fi.eelij.Darkholme.Domain.Generator;
import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratorTest {
    @Test
    public void correctPointCloud() {
        Generator g = new Generator(10, 10, 420);

        g.generate(10);

        String expectedMapString = "[[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]" +
                ", [0, 0, 0, 0, 0, 0, 0, 1, 0, 0]" +
                ", [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]" +
                ", [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]" +
                ", [0, 0, 0, 0, 0, 0, 0, 0, 0, 1]" +
                ", [0, 1, 0, 0, 0, 0, 0, 1, 0, 1]" +
                ", [0, 0, 0, 0, 1, 0, 0, 0, 0, 0]" +
                ", [0, 0, 1, 0, 0, 0, 0, 0, 0, 0]" +
                ", [0, 0, 0, 0, 1, 1, 0, 0, 0, 0]" +
                ", [0, 0, 0, 0, 0, 0, 1, 0, 0, 0]]";

        assertEquals(expectedMapString, g.mapToString());
    }
}
