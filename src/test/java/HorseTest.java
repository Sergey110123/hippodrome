import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class HorseTest {

    @Test
    void whenHorseConstructorNameNullException() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 0, 0));
        assertEquals("Name cannot be null.", exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {"", "\s", "\t", "\r", "\n", "\f", "\u000B"})
    void whenHorseConstructorNameBlankException(String strings) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(strings, 0, 0));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void whenHorseConstructorSpeedIsNegative() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Plotva", -1, 0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }
    @Test
    void whenHorseConstructorDistanceIsNegative() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Plotva", 0, -1));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }
    @Test
    void whenHorseGetName() {
        Horse horse = new Horse("Plotva", 10, 100);
        assertEquals("Plotva", horse.getName());
    }
    @Test
    void whenHorseGetSpeed() {
        Horse horse = new Horse("Plotva", 10, 100);
        assertEquals(10, horse.getSpeed());
    }
    @Test
    void whenHorseGetDistance() {
        Horse horse = new Horse("Plotva", 10, 100);
        assertEquals(100, horse.getDistance());
        Horse horse2 = new Horse("Plotva", 10);
        assertEquals(0, horse2.getDistance());
    }
    @Test
    void whenHorseMoveRandomDouble(){

        try (MockedStatic<Horse> horses = mockStatic(Horse.class)) {
            Horse horse = new Horse("Plotva", 10);
            horse.move();
            horses.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8})
    void whenHorseMoveFormula(Double doubles){

        try (MockedStatic<Horse> horses2 = mockStatic(Horse.class)) {
            Horse horse = new Horse("Plotva", 10);
            horses2.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(doubles);
            double distance = 0;
            distance += 10 * doubles;
            horse.move();
            assertEquals(distance, horse.getDistance());
        }
    }
}