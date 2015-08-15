package me.lifenjoy51.mypet.server.domain;

import me.lifenjoy51.mypet.server.domain.id.PetId;
import me.lifenjoy51.mypet.server.service.Admin;
import me.lifenjoy51.mypet.server.service.MyPet;
import me.lifenjoy51.mypet.server.service.Park;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by lifenjoy51 on 2015-08-15.
 */
@Component
@Qualifier("NormalAdmin")
public class NormalAdmin implements Admin {
    
    @Autowired
    Park park;
    
    @Override
    public Park findPark(PetId petId) {
        //FIXME 지금은 그냥... 공원 하나만 제공한다.
        return park;
    }
}
