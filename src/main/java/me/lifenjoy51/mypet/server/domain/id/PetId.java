package me.lifenjoy51.mypet.server.domain.id;

import lombok.Data;

/**
 * Created by lifenjoy51 on 2015-08-16.
 */
@Data
public class PetId {
    
    int id;
    
    public PetId(int id){
        this.id=id;
    }
}
