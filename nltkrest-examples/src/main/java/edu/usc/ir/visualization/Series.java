package edu.usc.ir.visualization;

import java.util.ArrayList;

public class Series {
	String name;
    ArrayList<Integer> value;
    Series(String name, ArrayList<Integer> value ){
        this.name=name;
        this.value=value;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Integer> getValue() {
        return value;
    }
    public void setValue(ArrayList<Integer> value) {
        this.value = value;
    }
}
