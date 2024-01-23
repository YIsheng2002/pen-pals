package com.penpals.view;

import javax.swing.JPanel;  
import javax.swing.SwingUtilities;  
  
import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.plot.PlotOrientation;  
import org.jfree.data.category.CategoryDataset;  
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.axis.NumberAxis;
import java.util.HashMap;
import java.awt.Color;
  
public class RatingGraphGui extends JPanel {  
  
  private static final long serialVersionUID = 1L;
  private ChartPanel panel;  
  
  public RatingGraphGui(double[] ratingCount) {   
    // Create Dataset  
    CategoryDataset dataset = createDataset(ratingCount);  
      
    //Create chart  
    JFreeChart chart=ChartFactory.createBarChart(  
        "Rating", //Chart Title  
        "Rating", // Category axis  
        "Percentage", // Value axis  
        dataset,  
        PlotOrientation.HORIZONTAL,  
        false,false,false  
       );  
       chart.setBackgroundPaint(java.awt.Color.white);
       chart.setBorderVisible(true);
       final CategoryPlot plot = (CategoryPlot) chart.getPlot();
       NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
       rangeAxis.setVisible(false);
       final CategoryItemRenderer renderer = plot.getRenderer();
       renderer.setOutlinePaint(java.awt.Color.white);
       

       plot.setOutlinePaint(java.awt.Color.BLACK);
       plot.setRangeCrosshairPaint(java.awt.Color.white);
       //plot.setInsets(new RectangleInsets(0, 0, 2, 0), true);
       renderer.setBaseOutlinePaint(java.awt.Color.white);
       plot.setRangeGridlinesVisible(false);
       plot.setRenderer(renderer);

    panel=new ChartPanel(chart);  
  }  
  
  private CategoryDataset createDataset(double[] ratingCount) {  
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
  
    double maxCount = 0;
    for(int i = 0; i < ratingCount.length; i++)
    {
        if(ratingCount[i] > maxCount)
        {
            maxCount = ratingCount[i];
        }
    }

    dataset.addValue((ratingCount[5]/maxCount * 100), "rating", "5");
    dataset.addValue((ratingCount[4]/maxCount * 100), "rating", "4");
    dataset.addValue((ratingCount[3]/maxCount * 100), "rating", "3");
    dataset.addValue((ratingCount[2]/maxCount * 100), "rating", "2");
    dataset.addValue((ratingCount[1]/maxCount * 100), "rating", "1");
    dataset.addValue((ratingCount[0]/maxCount * 100), "rating", "0");
    
    return dataset;  
  }  

    public ChartPanel getChartPanel() {  
        return panel;  
    }
}