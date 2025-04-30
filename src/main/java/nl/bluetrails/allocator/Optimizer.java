package nl.bluetrails.allocator;

import java.util.*;
import java.util.stream.Collectors;

public class Optimizer {

    Map<String,Double> prices;
    Map<String,Integer> positions;
    Map<String,Integer> sortedpos;
    LinkedHashMap<String, Integer> sortedPositions;


    public Optimizer(){
        System.out.println("optimizing...");
        prices = new HashMap<>();
        prices.put("apples",1.0);
        prices.put("bananas",1.0);
        prices.put("oranges",11.0);

        positions = new HashMap<>();
        positions.put("apples",100);
        positions.put("bananas",50);
        positions.put("oranges",125);

        // Sort the map by values in descending order
        sortedpos = positions.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, // Merge function (not used here)
                        LinkedHashMap::new // Maintain insertion order
                ));
    }

public Double getPrice (String fruit){
        return prices.get(fruit);
}

public Map<String,Integer> OptimizeMe(){
    Double targetValue = 130.0;
    Map<String,Integer> results= new HashMap<>();
    System.out.println("optimizing. target is "+targetValue);

    Double currentFulfillValue = 0.0;

    for (Map.Entry<String, Integer> entry : sortedpos.entrySet()) {
        String currentFruit = entry.getKey();
        System.out.println("checking "+currentFruit);
        Double needed = targetValue-currentFulfillValue;
        Double currentFruitStockValue = entry.getValue()*getPrice(currentFruit);

        if(needed<=currentFruitStockValue){
            results.put(currentFruit,needed.intValue());
            currentFulfillValue+=needed.intValue()*getPrice(currentFruit);
            System.out.println("allocated "+currentFruit+" : "+needed.intValue()*getPrice(currentFruit));
        } else {
            results.put(currentFruit,entry.getValue());
            currentFulfillValue+=entry.getValue()*getPrice(currentFruit);
            System.out.println("allocated "+currentFruit+" : "+entry.getValue()*getPrice(currentFruit));
        }


        System.out.println("============================================");
        System.out.println("targetValue = "+targetValue);
        System.out.println("current fulfill = "+currentFulfillValue);
        System.out.println("============================================");


        if(currentFulfillValue >= targetValue){
            System.out.println("results allocated:");
            results.forEach((key, value) -> System.out.println(key + ": " + value));
            return results;
        }
    }

    System.out.println("results not finished : currently allocated");
    results.forEach((key, value) -> System.out.println(key + ": " + value));
    System.out.println("but not finished");
    return results;

}

}
