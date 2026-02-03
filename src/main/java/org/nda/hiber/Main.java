package org.nda.hiber;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootApplication
public class Main {

    public static void main(String... args) {
        Integer[] arr = {1};
        for(int i = 0; i<1000; i++) {
            try {
                Integer value = arr[3];
                assertEquals(0, value);
            } catch(ArrayIndexOutOfBoundsException e) {
                log.error("error", e);
            }
        }
    }
}
