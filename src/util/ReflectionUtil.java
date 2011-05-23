package util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtil {

    private ReflectionUtil() {
    }

    public static <T, W> void setMember(T object, String memberName, W value) {

        try {

            Field field = object.getClass().getDeclaredField(memberName);

            PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), object.getClass());

            Method method = descriptor.getWriteMethod();

            method.invoke(object, value);

        } catch (NoSuchFieldException e) {
            throw new IllegalStateException(e);
        } catch (IntrospectionException e) {
            throw new IllegalStateException(e);
        } catch (InvocationTargetException e) {
            throw new IllegalStateException(e);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

}
