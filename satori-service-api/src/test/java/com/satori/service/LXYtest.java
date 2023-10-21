package com.satori.service;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @auth YanFuYou
 * @date 25/09/23 下午 09:43
 */
public class LXYtest {


    @Test
    public void exec(){
//        random(1,100,100);

        String calculate = calculate("1.5+1/4;2*3/7-1*6+4");
        System.out.println(calculate);
    }



    public String calculate(String args){
        final Set<Character> operChars = new HashSet<>(Arrays.asList('+','-','*','/'));
        String[] expressions = args.split(";");
        Map<String,Double> resultMap = new HashMap<>();
        Stack<Double> vals = new Stack<>();
        Stack<Character> opers = new Stack<>();
        //可以抽一个单独的方法
        for (String expression : expressions) {
            for (int i = 0; i < expression.length(); i++) {
                char ch = expression.charAt(i);
                if (Character.isDigit(ch)){
                    StringBuilder builder = new StringBuilder();
                    while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || '.' == expression.charAt(i) )){
                        builder.append(expression.charAt(i));
                        i++;
                    }
                    i--;
                    vals.push(Double.parseDouble(builder.toString()));
                }else if (operChars.contains(ch)){
                    while (!opers.empty() && !((ch == '*' || ch == '/') && (opers.peek() == '+' || opers.peek() == '-'))){
                        vals.push(calcVal(opers.pop(),vals.pop(),vals.pop()));
                    }
                    opers.push(ch);
                }
            }
            while (!opers.empty()){
                vals.push(calcVal(opers.pop(),vals.pop(),vals.pop()));
            }
            resultMap.put(expression,vals.pop());
        }
        StringBuilder resultBuilder = new StringBuilder();
        for (String key : resultMap.keySet()) {
            resultBuilder.append(key).append("=").append(String.format("%.2f",resultMap.get(key))).append("\n");
        }

        return resultBuilder.toString();
    }


    public double calcVal(char operator, double b, double a) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("除零");
                }
                return a / b;
        }
        return 0;
    }


    public void random(Integer min,Integer max,Integer n){
        List<Integer> result = new ArrayList<>(n);
        Random random = new Random();
        int r = -1;
        do{
            r = random.nextInt(max - min + 1) + min;
            if (!result.contains(r)){
                result.add(r);
            }
        } while (result.size() < n);
        System.out.println(result);
    }

}
