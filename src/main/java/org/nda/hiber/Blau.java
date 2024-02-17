package org.nda.hiber;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Blau {

    private final Mallow mallow;

    @GetMapping("/xxx")
    public String xxx() throws Throwable {
        mallow.mallow();
        return "OK";
    }

}
