package org.nda.hiber;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class RaccoonFacade {

    private final RaccoonHandler raccoonHandler;

    public void buyAndFeedRaccoon(final String raccoonName) {
        boolean b = raccoonHandler.buyRaccoon(raccoonName);
        if (!b) {
            raccoonHandler.feedRaccoon(raccoonName);
        }
    }

}
