package com.doordash;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidateOrdersPath {
    public boolean isValid(List<String> orders) {
        //using arr[] to track status of order
        int[] arr = new int[orders.size()+1];
        for(String order: orders) {
            int number = Integer.parseInt(order.substring(1));

            if(order.charAt(0) == 'P') {
                if(arr[number] !=0 ) //only previously unpicked orders will be picked, else return false
                    return false;
                arr[number]++;
            } else {
                if(arr[number] !=1 ) //only order which has already been picked (value = 1) will be allowed to be delivered, else return false
                    return false;
                arr[number]++;
            }
        }
        for(int i=0; i<arr.length; i++){
            if(arr[i]==1 || arr[i]>2) //if value at any index is 1, i.e. it was not delivered. And if value >2 then it was delivered multiple times
                return false;
        }
        return true;
    }


    public static void main(String[] args) {

        ValidateOrdersPath obj = new ValidateOrdersPath();
        System.out.println(obj.isValid(Arrays.asList("P1", "P2", "D1", "D2")));
        System.out.println(obj.isValid(Arrays.asList("P1", "D1", "P2", "D2")));
        System.out.println(obj.isValid(Arrays.asList("P1", "D2", "D1", "P2")));
        System.out.println(obj.isValid(Arrays.asList("P1", "D2")));
        System.out.println(obj.isValid(Arrays.asList("P1", "P2")));
        System.out.println(obj.isValid(Arrays.asList("P1", "D1", "D1")));
        System.out.println(obj.isValid(Arrays.asList()));
        System.out.println(obj.isValid(Arrays.asList("P1", "P1", "D1")));
        System.out.println(obj.isValid(Arrays.asList("P1", "P1", "D1", "D1")));
        System.out.println(obj.isValid(Arrays.asList("P1", "D1", "P1")));
        System.out.println(obj.isValid(Arrays.asList("P1", "D1", "P1", "D1")));
    }
}
