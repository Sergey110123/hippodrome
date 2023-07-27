import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;



class MainTest {
@Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    void whenMain(){
    try {
        Main.main(new String[]{""});
    } catch (Exception e) {
        throw new RuntimeException(e);
    }

}
}