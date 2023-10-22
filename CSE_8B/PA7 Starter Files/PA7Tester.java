///////////////////////////////////////////////////////////////////////////////
// Main Class File:    BarChartRacer.java
// File:               PA7Tester.java
// Quarter:            CSE 8B Winter 2022
//
//
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * Sample tests to check if other classes work as intended
 *
 * Bugs: None known
 *
 * @author Rocky Hankin
 */

import java.util.*;

public class PA7Tester {
    // sample client
    public static void main(String[] args) {

        // do not modify these two lines
        StdDraw.setCanvasSize(1000, 700);
        StdDraw.enableDoubleBuffering();

        //bar tests
        Bar fooBar = new Bar("Steven", 2, "Human?");
        Bar barFoo = new Bar("bar fu", 7, "martial art");
        Bar subBar = new Bar("Sub par bar", 2, "lame joke");
        
        System.out.println("First bar data");
        System.out.println(fooBar.getName() + ", " + fooBar.getValue() + ", " 
                            + fooBar.getCategory());
        System.out.println();
        System.out.println("Second bar data");
        System.out.println(barFoo.getName() + ", " + barFoo.getValue() + ", " 
                            + barFoo.getCategory());
        System.out.println();
        System.out.println("Third bar data");
        System.out.println(subBar.getName() + ", " + subBar.getValue() + ", " 
                            + subBar.getCategory());
        //compareTo tests
        System.out.println();
        System.out.println("Comparing bar 1 and bar 2:");
        System.out.println(fooBar.compareTo(barFoo)); // expect -1
        System.out.println("Comparing bar 2 and bar 3");
        System.out.println(barFoo.compareTo(subBar)); //expect 1
        System.out.println("Comparing bar 1 and bar 3"); //expect 0
        System.out.println(fooBar.compareTo(subBar));
        
        //BarChart.java tests
        BarChart chart = new BarChart("Basic chart", "some kind of value", "dude trust me");
        chart.setCaption("I'm out. That's all the jokes you're getting");
        chart.add(fooBar.getName(), fooBar.getValue(), 2);
        chart.add(barFoo.getName(), barFoo.getValue(), 3);
        chart.add(subBar.getName(), subBar.getValue(), 4);

        
        // TEST 1 
        // do not modify these lines, otherwise drawing behavior will be weird

        chart.draw(); // here chart is your BarChart object
        StdDraw.show(); // you can also use Std.show(1000)/StdDraw.pause() to show for a specified interval
        chart.reset(); // here chart is your BarChart object

        Bar idk = new Bar("a new bar", 12, "bar");
        Bar onceMore = new Bar("another bar", 13, "sure is a bar");
        Bar runItBack = new Bar("You guessed it, a bar", 8, "man look at that bar");

        chart.add(idk.getName(), idk.getValue(), 2);
        chart.add(onceMore.getName(), onceMore.getValue(), 3);
        chart.add(runItBack.getName(), runItBack.getValue(), 4);        
        // TEST 2
        // do not modify these lines, otherwise drawing behavior will be weird
        chart.draw(); // here chart is your BarChart object
        StdDraw.show(); // you can also use Std.show(1000)/StdDraw.pause() to show for a specified interval
        chart.reset(); // here chart is your BarChart object

        // BarChartRacer.java tests can directly be run in it's own main method
        // once Bar and BarChart work correctly.
        
    }
}
