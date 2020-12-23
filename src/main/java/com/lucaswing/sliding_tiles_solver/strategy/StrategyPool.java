package com.lucaswing.sliding_tiles_solver.strategy;

import com.lucaswing.sliding_tiles_solver.strategy.impl.FillLeftStrategy;
import com.lucaswing.sliding_tiles_solver.strategy.impl.FillTopStrategy;
import com.lucaswing.sliding_tiles_solver.strategy.impl.Last4Strategy;
import com.lucaswing.sliding_tiles_solver.strategy.impl.Last6Strategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Holds a pool of Strategies to grab from. These function without
 * state, so there is no point in creating them every time.
 * 
 * @author Luke
 * @version 1.0
 */
public class StrategyPool {
    
    public static final String TOP_STRAT = "FillTopStrategy";
    public static final String LEFT_STRAT = "FillLeftStrategy";
    public static final String LAST6_STRAT = "Last6Strategy";
    public static final String LAST4_STRAT = "Last4Strategy";
    
    private static StrategyPool instance = null;
    private final Map<String, Strategy> mapOfStrategies = new HashMap<>();
    
    private StrategyPool() {
        mapOfStrategies.put(TOP_STRAT, new FillTopStrategy());
        mapOfStrategies.put(LEFT_STRAT,  new FillLeftStrategy());
        mapOfStrategies.put(LAST6_STRAT,  new Last6Strategy());
        mapOfStrategies.put(LAST4_STRAT,  new Last4Strategy());
    }
    
    public static StrategyPool getInstance() {
        if (instance == null) {
            instance = new StrategyPool(); 
        }
        return instance;
    }
    
    public Strategy getStrategy(String strategy) {
        return mapOfStrategies.get(strategy);
    }
    
    public List<Strategy> getAllStrategies() {
        return mapOfStrategies.values().stream()
                .collect(Collectors.toList());
    }
}
