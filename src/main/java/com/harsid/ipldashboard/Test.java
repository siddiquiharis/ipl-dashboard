package com.harsid.ipldashboard;

import org.springframework.boot.SpringApplication;

public class Test {

    public static void main(String[] args) {

        try {
            if(args.length == 0){
                throw new IllegalArgumentException();
            }
        } catch (NullPointerException e) {
            System.out.print("NPE ");
            throw e;
        } catch (IllegalArgumentException e) {
            System.out.print("IAE ");
            throw e;
        }catch (Throwable t){
            System.out.print("T ");
            throw t;
        } finally {
            System.out.print("F ");
        }


    }

}
