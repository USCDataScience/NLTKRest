package edu.usc.ir.visualization;

import java.util.ArrayList;
import edu.usc.ir.visualization.Series;

public class Labels{
    ArrayList<String> labels;
    Series[] series;
    public Labels(ArrayList<String> labels, Series[] series) {
        this.labels = labels;
        this.series = series;
    }
    public ArrayList<String> getLabels() {
        return labels;
    }
    public void setLabels(ArrayList<String> labels) {
        this.labels = labels;
    }
    public Series[] getSeries() {
        return series;
    }
    public void setSeries(Series[] series) {
        this.series = series;
    }
}