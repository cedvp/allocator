package nl.bluetrails.allocator;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("blabla");
        Optimizer opt = new Optimizer();
        Map<String,Integer> resultsoptim = opt.OptimizeMe();
        for (Map.Entry<String, Integer> entry : resultsoptim.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

    }
}


