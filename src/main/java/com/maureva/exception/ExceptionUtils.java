package com.maureva.exception;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.util.function.Function;

@UtilityClass
public class ExceptionUtils {

    /*
    * Creating my own function interface to enable throwing
    * */
    @FunctionalInterface
    public interface CheckedFunction<T, R> {
        R apply(T t) throws IOException;
    }

    public static <T, R> Function<T, R> safelyTry(CheckedFunction<T, R> checkedFunction) {
        return t -> {
            try {
                return checkedFunction.apply(t);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
