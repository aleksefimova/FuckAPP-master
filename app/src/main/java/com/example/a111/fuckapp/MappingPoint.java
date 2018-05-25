package com.example.a111.fuckapp;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MappingPoint {

    private LatLng latlng;
    private String PointTitle;
    private String Metadata; //just symbolic, add the attributes you need for the Metadata

    //Constructor also add the Metadata attributes here
    public MappingPoint(LatLng latlng, String pointTitle){
        this.latlng = latlng;
        this.PointTitle = pointTitle;
    }

    //converts the MappingPoint to a MarkerOption
    public MarkerOptions toMarkerOptions(){
        return new MarkerOptions().position(latlng).title(PointTitle);
    }
}
