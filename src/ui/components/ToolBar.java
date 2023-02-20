package ui.components;

import freshui.graphics.FPanel;
import freshui.graphics.FRect;
import freshui.interfaces.FreshComponent;

import java.io.Serializable;

public class ToolBar extends FPanel implements Serializable {

    FRect shape = new FRect(300,50);

    public ToolBar(){

        add(shape,0,0);

    }

}
