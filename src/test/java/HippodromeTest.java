import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {
    @Test
    void whenHippodromeConstructorNullParameter(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }
    @Test
    void whenHippodromeConstructorEmptyParameter(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void whenGetHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <=30; i++) {
            horses.add(new Horse("Horse" + i, Math.random() + 2.0));
        }
//        for (Horse s: horses) {
//            System.out.println(s.getName() + " " + s.getSpeed());
//        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }
    @Test
    void whenMove() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse s : horses) {
            Mockito.verify(s).move();
        }
    }

    @Test
    void whenGetWinner() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <=10; i++) {
            horses.add(new Horse("Horse" + i, 0, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals("Horse10", hippodrome.getWinner().getName());
    }
}