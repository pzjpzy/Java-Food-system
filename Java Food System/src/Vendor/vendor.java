/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vendor;

import Customer.user;

/**
 *
 * @author pangz
 */
public class vendor extends user{
    @Override
     public void setOrderID(String ID){
         this.orderID = ID;
     }
     @Override
     public String getOrderID(){
         return orderID;
     }    
}
