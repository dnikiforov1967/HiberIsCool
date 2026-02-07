package org.nda.hiber;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LookupTest {

    @Autowired
    private SingletonWithLookup bean;

    @Test
    void lookupTest() {

        System.out.println("**** new lookup call ****");
        bean.severalCallsInOneThreadUsingLookup();
        System.out.println("**** new lookup call ****");
        bean.severalCallsInOneThreadUsingLookup();
    }

    @Test
    void contextTest() {

        System.out.println("**** new context call ****");
        bean.severalCallsInOneThreadUsingContext();
        System.out.println("**** new context call ****");
        bean.severalCallsInOneThreadUsingContext();
    }

}
