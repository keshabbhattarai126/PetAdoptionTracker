/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petadoptiontracker.controller;

import petadoptiontracker.view.ListingView;

/**
 *
 * @author LeathLOQ
 */
public class ListingController {
    private final ListingView listingView;
    public ListingController(ListingView listingView){
        this.listingView=listingView;
    }
    public void open(){
        this.listingView.setVisible(true);
    }
    public void close(){
        this.listingView.dispose();
    }
}
