package me.lifenjoy51.mypet.server.domain;

import lombok.extern.apachecommons.CommonsLog;
import me.lifenjoy51.mypet.server.service.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by lifenjoy51 on 2015-08-15.
 */
@Component
public class NormalPark implements Park {
    
    Map<Integer, Pet> petMap;
    
    public NormalPark(){
        petMap = new HashMap<>();
    }
    
    @Override
    public List<AnothersPet> findAnothersPets(User parameter) {
        List<AnothersPet> anothersPets = 
                petMap.entrySet().stream()
                        .map(Map.Entry::getValue).collect(Collectors.toList());
        return anothersPets;
    }

    @Override
    public void registerPet(Pet pet) {
        petMap.put(pet.getId(), pet);
    }

    @Override
    public Story getAnothersStory(AnothersPet anothersPet) {
        return null;
    }
}
