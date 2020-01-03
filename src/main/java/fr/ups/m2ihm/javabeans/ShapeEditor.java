/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.javabeans;

import java.beans.PropertyEditorSupport;

/**
 *
 * @author arrouisa
 */
public class ShapeEditor extends PropertyEditorSupport{
     private int myShape;
 private static final String RECTANGLE_TEXT_VALUE = "rectangle";
 private static final String OVALE_TEXT_VALUE = "ovale";
 private static final String[] TAGS = {OVALE_TEXT_VALUE, RECTANGLE_TEXT_VALUE};
    private String RECTANGLRROUND_TEXT_VALUE;

 @Override
 public void setValue(Object obj) {
     myShape = ((Integer) obj);
 }

 @Override
 public Object getValue() {
     return myShape;
 }

 @Override
 public String getJavaInitializationString() {
     return Integer.toString(myShape);
 }
@Override
 public String[] getTags() {
     return TAGS;
 }
 
  @Override
 public String getAsText() {
     switch (myShape) {
         case Shape.RECT:
             return RECTANGLE_TEXT_VALUE;
         default:
             return OVALE_TEXT_VALUE;
     }
 }
 
 @Override
public void setAsText(String str) {
    if (str.equals(RECTANGLE_TEXT_VALUE)) {
        myShape = Shape.RECT;
    } else {
        myShape = Shape.OVAL;
    }
    firePropertyChange();
}



}
