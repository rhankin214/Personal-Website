///////////////////////////////////////////////////////////////////////////////
// Main Class File:    BarChartRacer.java
// File:               BarChart.java
// Quarter:            CSE 8B Winter 2022
//
//
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * Defines a barchart given a title, x axis, and source of data
 * Has 3 member ArrayLists to keep track of an entry's name, value, and color
 * Draws one image per chunk of data (a given date)
 *
 * Bugs: None known
 *
 * @author you name
 */

import java.awt.Color;
import java.awt.*;
import java.util.ArrayList;

public class BarChart {

    // color palette for bars
    private static final Color[] PALETTE = StdDraw.initColors();
    private String title; // bar chart title
    private String xAxisLabel; // x-axis label
    private String dataSource; // data source
    private String caption; // caption
    private ArrayList<String> names = new ArrayList<>(); // list of bar names
    private ArrayList<Integer> values = new ArrayList<>(); // list of bar values
    private ArrayList<Color> colors = new ArrayList<>(); // list of bar colors

    /**
     * Basic constructror that assigns parameters to member variables
     * @param title the String to assign to title
     * @param xAxisLabel the String to assign to xAxisLabel
     * @param dataSource the String to assign to dataSource
     */
    public BarChart(String title, String xAxisLabel, String dataSource) {
        this.title = title;
        this.xAxisLabel = xAxisLabel;
        this.dataSource = dataSource;
    }

    /**
     * Sets the caption of the bar chart
     * @param caption the new caption
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * Adds the parameters to corresponding ArrayList member variables
     * @param name a String to be added to names
     * @param value an int to be added to values
     * @param paletteIndex the index of the color that will be added to colors
     */
    public void add(String name, int value, int paletteIndex) {
        this.names.add(name);
        this.values.add(value);
        this.colors.add(PALETTE[paletteIndex]);
    }

    /**
     * Removes all entries from the four ArrayList member variables
     */
    public void reset() {
        this.names.clear();
        this.values.clear();
        this.colors.clear();
    }

    /*
     * #############################################################################
     * ###########
     * Do not modify methods below this line.
     * #############################################################################
     * ###########
     */

    /**
     * compute units (multiple of 1, 2, 5, 10, 20, 50, 100, 200, 500, 1000, ...)
     * so that between 4 and 8 axes labels
     * there is no need to modify this method, it is used with draw()
     */
    private static int getUnits(double xmax) {
        int units = 1;
        while (Math.floor(xmax / units) >= 8) {
            // hack to identify 20, 200, 2000, ...
            if (units % 9 == 2)
                units = units * 5 / 2;
            else
                units = units * 2;
        }
        return units;
    }

    /**
     * Draws this bar chart to standard draw.
     * Do not touch this method, it should draw
     */
    public void draw() {
        StdDraw.clear();
        // nothing to draw
        if (this.names.isEmpty())
            return;

        // leave room for at least 8 bars
        int numberOfBars = Math.max(8, this.names.size());

        // set the scale of the coordinate axes
        double xmax = Double.NEGATIVE_INFINITY;
        for (int value : this.values) {
            if (value > xmax)
                xmax = value;
        }

        StdDraw.setXscale(-0.01 * xmax, 1.2 * xmax);
        StdDraw.setYscale(-0.01 * numberOfBars, numberOfBars * 1.25);

        // draw title
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setFont(new Font("SansSerif", Font.BOLD, 24));
        StdDraw.text(0.6 * xmax, numberOfBars * 1.2, this.title);

        // draw x-axis label
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 16));
        StdDraw.textLeft(0, numberOfBars * 1.10, this.xAxisLabel);

        // draw axes
        int units = this.getUnits(xmax);
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
        for (int unit = 0; unit <= xmax; unit += units) {
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.text(unit, numberOfBars * 1.02, String.format("%,d", unit));
            StdDraw.setPenColor(new Color(230, 230, 230));
            StdDraw.line(unit, 0.1, unit, numberOfBars * 1.0);
        }

        // draw caption
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        if (this.caption.length() <= 4)
            StdDraw.setFont(new Font("SansSerif", Font.BOLD, 100));
        else if (this.caption.length() <= 8)
            StdDraw.setFont(new Font("SansSerif", Font.BOLD, 60));
        else
            StdDraw.setFont(new Font("SansSerif", Font.BOLD, 40));
        StdDraw.textRight(1.15 * xmax, 0.2 * numberOfBars, this.caption);

        // draw data source acknowledgment
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 14));
        StdDraw.textRight(1.14 * xmax, 0.1 * numberOfBars, this.dataSource);

        // draw bars
        for (int i = 0; i < this.names.size(); i++) {
            String name = this.names.get(i);
            int value = this.values.get(i);
            Color color = this.colors.get(i);
            StdDraw.setPenColor(color);
            StdDraw.filledRectangle(0.5 * value, numberOfBars - i - 0.5, 0.5 * value, 0.4);
            StdDraw.setPenColor(StdDraw.BLACK);
            int fontSize = (int) Math.ceil(14 * 10.0 / numberOfBars);
            StdDraw.setFont(new Font("SansSerif", Font.BOLD, fontSize));
            StdDraw.textRight(value - 0.01 * xmax, numberOfBars - i - 0.5, name);
            StdDraw.setFont(new Font("SansSerif", Font.PLAIN, fontSize));
            StdDraw.setPenColor(StdDraw.DARK_GRAY);
            StdDraw.textLeft(value + 0.01 * xmax, numberOfBars - i - 0.5, String.format("%,d", value));
        }
    }
}
