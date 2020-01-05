/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.javabeans;

import java.util.EventListener;

/**
 *
 * @author arrouisa
 */
public interface ShapeLabelListener extends EventListener{
    public void shapeLabelClicked(ShapeLabelEvent event);
}
