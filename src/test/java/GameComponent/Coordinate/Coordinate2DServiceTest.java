package GameComponent.Coordinate;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class Coordinate2DServiceTest {

    private Coordinate2DService coordinate2DService;

    @Before
    public void setUp(){
        coordinate2DService = new Coordinate2DService();
    }

    @Test
    public void isCoordinate2DTest() {
        assertThat(coordinate2DService.isCorrectFormat("1,2"),equalTo(true));
        assertThat(coordinate2DService.isCorrectFormat("a,2"),equalTo(false));
        assertThat(coordinate2DService.isCorrectFormat("12"),equalTo(false));
    }

    @Test
    public void convertToCoordinate2D() {
        Coordinate2D actualCoordinate1 = (Coordinate2D) coordinate2DService.convertToCoordinate("1,2");
        assertThat(actualCoordinate1,equalTo(new Coordinate2D(0,1)));

        Coordinate2D actualCoordinate2 = (Coordinate2D) coordinate2DService.convertToCoordinate("11,2");
        assertThat(actualCoordinate2,equalTo(new Coordinate2D(10,1)));
    }
}