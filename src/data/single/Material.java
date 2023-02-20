package data.single;

import java.awt.*;
import java.io.Serializable;

public class Material implements Serializable {

    public Color color, outlineColor;
    public String name;
    public boolean outlineVisible;

    public Material(Color color, String name){
        this.color = color;
        this.name = name;
        this.outlineColor = Color.BLACK;
        this.outlineVisible = true;
    }

    public Material(Color color, Color outlineColor, boolean outlineVisible,String name){
        this.color = color;
        this.name = name;
        this.outlineColor = outlineColor;
        this.outlineVisible = outlineVisible;
    }

    public String toString(){
        return name;
    }

}
