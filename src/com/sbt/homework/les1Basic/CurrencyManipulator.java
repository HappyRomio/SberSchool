package com.sbt.homework.les1Basic;

import com.sbt.homework.les1Basic.exception.NotEnoughMoneyException;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class CurrencyManipulator {

    private String currencyCode;
    private Map<Integer, Integer> denominations;

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        denominations = new TreeMap<>(Collections.reverseOrder());
    }

    public void addAmount(int denomination, int count){
        denominations.merge(denomination,count,(prev, count2)-> count2+count);
        denominations.get(denomination);
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public int getTotalAmount(){
        int sum =0;

        for(Map.Entry<Integer,Integer> entry : denominations.entrySet()){
            sum += entry.getKey()*entry.getValue();
        }
        return sum;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        int resultSum = 0;
        Map<Integer,Integer> denominationsCopy = new TreeMap(Collections.reverseOrder());
        denominationsCopy.putAll(denominations);
        Map<Integer,Integer> result = new TreeMap<>(Collections.reverseOrder());
        for(int j = 0; j < denominations.size(); j++) {
            for (Map.Entry<Integer, Integer> entry : denominationsCopy.entrySet()) {
                if(entry.getKey() > expectedAmount)
                    continue;
                for (int i = 1; i <= entry.getValue(); i++) {
                    resultSum += entry.getKey();
                    if (resultSum == expectedAmount) {
                        result.put(entry.getKey(), i);
                        break;
                    }
                    if (resultSum > expectedAmount && i != 1) {
                        resultSum-=entry.getKey();
                        result.put(entry.getKey(), i-1);
                        break;
                    }

                    if (resultSum > expectedAmount && i == 1) {
                        resultSum-=entry.getKey();
                        break;
                    }

                    if(resultSum !=expectedAmount && i==entry.getValue()){
                        result.put(entry.getKey(), i);
                    }
                }
                if(resultSum==expectedAmount){
                    break;
                }
            }
            if(resultSum==expectedAmount){
                break;
            } else {
                result.clear();
            }
        }
        if(resultSum!=expectedAmount){
            throw new NotEnoughMoneyException();
        }

        for(Map.Entry<Integer,Integer> entry : result.entrySet()){
            Integer value = denominations.get(entry.getKey());
            value -=entry.getValue();
            if(value==0){
                denominations.remove(entry.getKey());
            } else {
                denominations.put(entry.getKey(),value);
            }
        }

        return result;
    }

    public boolean isAmountAvailable(int expectedAmount){
        return expectedAmount<=getTotalAmount();
    }
    public boolean hasMoney(){
        return denominations.size()==0 ? false : true;
    }
}
