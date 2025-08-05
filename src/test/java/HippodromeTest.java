import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    public void constructor_NullListParamPassed_ThrowsIllegalArgumentException() {
        List<Horse> horses = null;
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void constructor_EmptyListParamPassed_ThrowsIllegalArgumentException() {
        List<Horse> horses = new ArrayList<>();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorses() {
        List<Horse>horses=new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse"+i,i,i));
        }
        Hippodrome hippodrome=new Hippodrome(horses);

        assertNotNull(hippodrome.getHorses());
        assertEquals(30,hippodrome.getHorses().size());
        assertEquals("Horse0",hippodrome.getHorses().get(0).getName());
        assertEquals("Horse10",hippodrome.getHorses().get(10).getName());
        assertEquals("Horse20",hippodrome.getHorses().get(20).getName());
    }

    @Test
    void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome=new Hippodrome(horses);

        hippodrome.move();

        for (Horse hors : horses) {
            Mockito.verify(hors,Mockito.times(1)).move();
        }
    }

    @Test
    void getWinner() {
        Hippodrome hippodrome=new Hippodrome(List.of(
                new Horse("Horse1",13,19),
                new Horse("Horse2",15,18),
                new Horse("Horse3",10,20)
        ));
        assertEquals("Horse3",hippodrome.getWinner().getName());
    }
}