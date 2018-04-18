package Game.Service.Coordinate;

import Game.Model.Coordinate.Coordinate2D;
import Game.Service.Coordinate.Coordinate2DConverter;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class Coordinate2DConverterTest {

    private Coordinate2DConverter coordinate2DConverter;

    @Before
    public void setUp(){
        coordinate2DConverter = new Coordinate2DConverter();
    }

    @Test
    public void isCoordinate2DTest() {
        assertThat(coordinate2DConverter.isCorrectFormat("1,2"),equalTo(true));
        assertThat(coordinate2DConverter.isCorrectFormat("a,2"),equalTo(false));
        assertThat(coordinate2DConverter.isCorrectFormat("12"),equalTo(false));
    }

    @Test
    public void convertToCoordinate2D() {
        Coordinate2D actualCoordinate1 = (Coordinate2D) coordinate2DConverter.convert("1,2");
        assertThat(actualCoordinate1,equalTo(new Coordinate2D(0,1)));

        Coordinate2D actualCoordinate2 = (Coordinate2D) coordinate2DConverter.convert("11,2");
        assertThat(actualCoordinate2,equalTo(new Coordinate2D(10,1)));
    }
}