import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {

    @Test
    public void constructor_NullNameParamPassed_ThrowsIllegalArgumentException() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "  ", "\n", "\n\n", "\t", "\t\t", "\t \t"})
    public void constructor_EmptyNameParamPassed_ThrowsIllegalArgumentException(String name) {
        String expectedMessage = "Name cannot be blank.";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void constructor_NegativeSpeedParamPassed_ThrowsIllegalArgumentException() {
        String expectedMessage = "Speed cannot be negative.";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("name", -1, 1));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void constructor_NegativeDistanceParamPassed_ThrowsIllegalArgumentException() {
        String expectedMessage = "Distance cannot be negative.";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("name", 1, -1));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getName() {
        String staticName = "L";
        double staticSpeed = 1;
        double staticDistance = 77;

        Horse horse = new Horse(staticName, staticSpeed, staticDistance);

        String name = horse.getName();

        assertEquals(staticName, name);
    }

    @Test
    void getSpeed() {
        String staticName = "L";
        double staticSpeed = 11;
        double staticDistance = 77;

        Horse horse = new Horse(staticName, staticSpeed, staticDistance);

        double speed = horse.getSpeed();

        assertEquals(staticSpeed, speed);
    }

    @Test
    void getDistance() {
        String staticName = "L";
        double staticSpeed = 1;
        double staticDistance = 772;

        Horse horse = new Horse(staticName, staticSpeed, staticDistance);

        double distance = horse.getDistance();

        assertEquals(staticDistance, distance);
    }

    @Test
    void move() {
        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("u", 21, 32);

            horse.move();

            horseMockedStatic.verify(()->Horse.getRandomDouble(0.2,0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2,0.4,0.7,0.9,0.6})
    public void move_Calculates(double value){
double min=0.2;
double max=0.9;
double speed=2.9;
double distance=500;
String name="k";
double expectedDistance=distance+speed*value;

Horse horse=new Horse(name,speed,distance);

        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            horseMockedStatic.when(()->Horse.getRandomDouble(min,max)).thenReturn(value);

            horse.move();

            assertEquals(expectedDistance,horse.getDistance());
        }
    }
}