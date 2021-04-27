/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class StatLivreurController implements Initializable {


    @FXML
    private BarChart<?, ?> LivChart;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       XYChart.Series set1 = new XYChart.Series<>();
       set1.getData().add(new XYChart.Data("tunis",50));
       set1.getData().add(new XYChart.Data("kairouan",150));
       set1.getData().add(new XYChart.Data("beja",33));
       set1.getData().add(new XYChart.Data("hamamet",70));
       set1.getData().add(new XYChart.Data("bizerte",40));
       
       
       LivChart.getData().addAll(set1);
       
    }    
}
