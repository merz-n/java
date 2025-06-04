package framework.assertions;

import java.util.List;

import java.lang.reflect.Array;
import java.util.Set;

public class Assertions {
    public static <T> void equals(T expected, T actual) throws AssertException {
        boolean success;
        if (expected == null) {
            success = actual == null;
        } else {
            success = expected.equals(actual);
        }

        throw new AssertException(new AssertResult<>(success, expected, actual));
    }

    public static void contains(String input, String toCheck) {
        boolean success = input != null && toCheck != null && input.contains(toCheck);
        throw new AssertException(new ContainsTestResult(success, toCheck, input));
    }

    public static void equalRecursively(Object expected, Object actual){
        if(expected == null && actual == null){
            return;
        }
        if(expected == null || actual == null){
            throw new AssertException(new AssertResult<>(false,expected,actual));
        }
        if(expected.getClass().isArray() && actual.getClass().isArray()){
            int lengthex = Array.getLength(expected);
            int lengthac = Array.getLength(actual);
            if(lengthac!=lengthex){
                throw new AssertException(new AssertResult<>(false,expected,actual));
            }
            for(int i = 0;i< lengthex;i++){
                Object valueex = Array.get(expected, i);
                Object valueac = Array.get(actual, i);
                equalRecursively(valueex,valueac);
            }
            return;
        }
        if (expected instanceof List && actual instanceof List){
            List<?> listex = (List<?>) expected;
            List<?> listac = (List<?>) actual;
            if(listex.size() != listac.size()){
                throw new AssertException(new AssertResult<>(false,expected,actual));
            }
            for(int i = 0;i< listex.size();i++){
                Object elementex = listex.get(i);
                Object elementac = listac.get(i);
                equalRecursively(elementex,elementac);
            }
            return;
        }
        if (expected instanceof Set && actual instanceof Set){
            Set<?> setEx = (Set<?>) expected;
            Set<?> setAc = (Set<?>) actual;
            if(setEx.size() != setAc.size() || !setEx.containsAll(setAc) || !setAc.containsAll(setEx)){
                throw new AssertException(new AssertResult<>(false,expected,actual));
            }
            return;
        }
        if(!expected.equals(actual)){
            throw new AssertException(new AssertResult<>(false,expected,actual));
        }
    }
    public static <T> void contain(T[] current, T[] toContain){
        if (current == null || toContain == null || toContain.length > current.length) {
            throw new AssertException(new AssertResult<>(false, toContain, current));
        }
        boolean found = false;
        for(int i = 0; i <= current.length -toContain.length; i++){
            boolean match = true;
            for (int j = 0; j < toContain.length; j++) {
                if (!current[i + j].equals(toContain[j])) {
                    match = false;
                    break;
                }
            }
            if(match) {
                found = true;
                break;
            }
            if(!found){
                throw new AssertException(new AssertResult<>(false, toContain, current));
            }

        }

    }
}
