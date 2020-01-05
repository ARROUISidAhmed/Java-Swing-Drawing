/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.rangeslider;

/**
 *
 * @author SidAhmed
 */
public class Pair<T,G> {
    private T first;
    private G second;

    public Pair(T first, G second) {
        this.first = first;
        this.second = second;
    }

    
    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public G getSecond() {
        return second;
    }

    public void setSecond(G second) {
        this.second = second;
    }
    
}
