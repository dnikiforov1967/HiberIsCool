package org.nda.hiber;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class BeanForLookup {

    private final UUID beanId = UUID.randomUUID();

    public void showMyId() {
        System.out.println("My id is "+beanId);
    }
}
