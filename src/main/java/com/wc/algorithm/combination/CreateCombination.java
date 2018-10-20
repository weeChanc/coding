package com.wc.algorithm.combination;

import java.util.*;

public class CreateCombination {

    static class Combination {
        List<Character> raw = new LinkedList<>();

        public void start(){
            raw.addAll(Arrays.asList('a','b','c','d','e','f','g','h','i'));
            List<List<Character>> to = new LinkedList<>();
            to.add(new LinkedList<Character>(Collections.singleton('a')));

            for (int i = 1; i < raw.size(); i++) {
                List<List<Character>> tempTo = new LinkedList<>();
                for (int j = 0; j < to.size(); j++) {
                    tempTo.addAll(insertAll(raw.get(i),to.get(j)));
                }
                to = tempTo;
            }
            System.out.println(to.size());

        }

        public List<List<Character>> insertAll(char insert , List<Character> to ){
            List<List<Character>> result = new LinkedList<>();
            for (int i = 0; i <= to.size(); i++) {
                List<Character> copy = new LinkedList<>();
                copy.addAll(to);
                copy.add(i,insert);
                result.add(copy);
            }
            return result;
        }
    }

    public static void main(String[] args){
        new Combination().start();
    }
}
