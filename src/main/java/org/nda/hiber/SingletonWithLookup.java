package org.nda.hiber;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SingletonWithLookup implements ApplicationContextAware {

    private ApplicationContext ctx;

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    @Lookup
    public BeanForLookup beanForLookup() {
        return null;
    }

    public void severalCallsInOneThreadUsingLookup() {
        BeanForLookup beanForLookup = beanForLookup();
        for(int i = 1; i <=3; i++) {
            beanForLookup.showMyId();
        }
    }

    public void severalCallsInOneThreadUsingContext() {
        BeanForLookup beanForLookup = ctx.getBean(BeanForLookup.class);
        for(int i = 1; i <=3; i++) {
            beanForLookup.showMyId();
        }
    }


}
