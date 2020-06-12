package com.dmmiralles.bowlingscorer.repository.impl;

import com.dmmiralles.bowlingscorer.model.Roll;
import com.dmmiralles.bowlingscorer.repository.RollRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;


/**
 * Concrete implementation for Repository Interface.
 *
 * @author Daniel Miralles.
 */
@AllArgsConstructor
public class RollRepositoryImpl implements RollRepository{

    private final List<Roll> rolls;

    @Override
    public void create(Roll value) {
        rolls.add(value);
    }

    public RollRepositoryImpl() {
        this.rolls = new ArrayList<>();
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    @Override
    public List<Roll> getByPlayerName(String playerName) {
        return rolls
                .stream()
                .filter(Objects::nonNull)
                .filter(x -> x.getPlayerName().equals(playerName))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllPlayerNames() {
        return rolls
                .stream()
                .filter(x -> x != null && x.getPlayerName() != null)
                .filter(distinctByKey(Roll::getPlayerName))
                .map(Roll::getPlayerName)
                .collect(Collectors.toList());
    }
}
