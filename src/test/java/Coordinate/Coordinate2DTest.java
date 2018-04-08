package Coordinate;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class Coordinate2DTest {

    @Test
    public void isCoordinate2DTest() {
        assertThat(Coordinate2D.isCoordinate2D("1,2"),equalTo(true));
        assertThat(Coordinate2D.isCoordinate2D("a,2"),equalTo(false));
        assertThat(Coordinate2D.isCoordinate2D("12"),equalTo(false));
    }

    @Test
    public void convertToCoordinate2D() {
        assertThat(Coordinate2D.convertToCoordinate2D("1,2"),equalTo(new Coordinate2D(1,2)));
        assertThat(Coordinate2D.convertToCoordinate2D("11,2"),equalTo(new Coordinate2D(11,2)));
    }
}