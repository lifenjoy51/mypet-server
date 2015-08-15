package me.lifenjoy51.mypet.server.domain;

import me.lifenjoy51.mypet.server.domain.id.PetId;
import me.lifenjoy51.mypet.server.service.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by lifenjoy51 on 2015-08-15.
 */
@Component
public class NormalPark implements Park {
    
    Map<PetId, Pet> petMap;
    
    public NormalPark(){
        petMap = new HashMap<>();
    }
    
    @Override
    public List<AnothersPet> findAnothersPets(User user) {
        List<AnothersPet> anothersPets = 
                petMap.entrySet().stream()
                        .filter(e -> user.getPets().stream().allMatch(pet -> pet.getId() != e.getKey()))
                        .map(Map.Entry::getValue).collect(Collectors.toList());
        return anothersPets;
    }

    @Override
    public void registerPet(Pet pet) {
        petMap.put(pet.getId(), pet);
    }
}
