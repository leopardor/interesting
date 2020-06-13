package com.shu.memoryverflow;

import com.shu.pojo.Person;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author arlen
 * @since 2020-04-28 21:16
 */
public class HeapOverflowTest {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        List<Person> resultList = new ArrayList<>();
        int j = 0;
        while (j++ < 1000000000) {
            Person person = new Person();
            System.out.println(j);
            for (int i = 1; i <= 76; ++i ) {
                setFieldValue(person, "a" + i, i + "");
            }
            resultList.add(person);
            System.out.println(resultList.toString());
        }




    }

    private static void setFieldValue(Object obj, String fieldName, String value) throws NoSuchFieldException, IllegalAccessException {
        Class c = obj.getClass();
        Field declaredField = c.getDeclaredField(fieldName);
        declaredField.setAccessible(true);
        declaredField.set(obj, value);
//        declaredField = null;
    }
}
