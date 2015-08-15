package me.lifenjoy51.mypet.server.domain;

import lombok.Data;
import me.lifenjoy51.mypet.server.service.Pet;

/**
 * Created by lifenjoy51 on 2015-08-15.
 */
@Data
public class NormalPet extends Pet {

    int id;

    String name;

    NormalPet() {
    }

    public NormalPet(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
