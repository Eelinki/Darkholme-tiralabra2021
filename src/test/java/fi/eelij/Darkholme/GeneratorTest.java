package fi.eelij.Darkholme;

import fi.eelij.Darkholme.Domain.Generator;
import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratorTest {
    @Test
    public void correctPointCloud() {
        Generator g = new Generator(200, 200, 21983321);

        g.generate(50);

        String expectedPointsString = "[72.0, 140.0, 72.0, 95.0, 16.0, 7.0, 97.0, 139.0, 122.0, 13.0, 44.0, 53.0, 93.0, 119.0, 143.0, 28.0, 59.0, 9.0, 86.0, 124.0, 92.0, 159.0, 46.0, 161.0, 15.0, 184.0, 111.0, 64.0, 191.0, 44.0, 175.0, 10.0, 147.0, 97.0, 13.0, 42.0, 70.0, 131.0, 95.0, 106.0, 197.0, 120.0, 29.0, 81.0, 143.0, 96.0, 73.0, 123.0, 138.0, 121.0, 63.0, 42.0, 130.0, 1.0, 86.0, 37.0, 98.0, 100.0, 166.0, 129.0, 18.0, 27.0, 162.0, 140.0, 4.0, 151.0, 1.0, 168.0, 53.0, 144.0, 120.0, 38.0, 91.0, 160.0, 94.0, 130.0, 169.0, 43.0, 33.0, 47.0, 186.0, 46.0, 49.0, 25.0, 118.0, 140.0, 86.0, 27.0, 139.0, 124.0, 95.0, 81.0, 87.0, 59.0, 174.0, 92.0, 106.0, 100.0, 90.0, 145.0]";

        assertEquals(expectedPointsString, g.pointsToString());
    }
}
