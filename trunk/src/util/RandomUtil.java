package util;

import java.util.List;
import java.util.Random;

public class RandomUtil {

    private RandomUtil() {
    }

    public static <T> T getRandomElement(List<T> objects) {
        Random random = new Random();

        int index = random.nextInt(objects.size());

        return objects.get(index);
    }
}
