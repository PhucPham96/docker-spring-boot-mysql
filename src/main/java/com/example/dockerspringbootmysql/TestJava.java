package com.example.dockerspringbootmysql;

public class TestJava {

    public static void main(String[] args) {
        String a = "a";
        try {
            switch (a) {
                case "a":
                    System.out.println("a");
                    break;
                case "b":
                    System.out.println("b");
                    break;
                default:
                    System.out.println("default");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
