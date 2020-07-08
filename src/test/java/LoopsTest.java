import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoopsTest {

    @Test
    public void loopPyramid(){
        for (int i=1; i<5; i++){
            System.out.println();

            for (int j=1;j<=i;j++){
                System.out.print(j);
            }
        }
    }

}
