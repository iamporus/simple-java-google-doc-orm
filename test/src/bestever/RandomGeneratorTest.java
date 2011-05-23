package bestever;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import util.RandomUtil;

public class RandomGeneratorTest {
    
    @Test
    public void testRandom() {

        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        for (int i = 0; i < 20; i++) {
         
            Integer temp = RandomUtil.getRandomElement(ints);
            
            Assert.assertNotNull(temp);

            System.out.println(temp);
        }
    }

}
