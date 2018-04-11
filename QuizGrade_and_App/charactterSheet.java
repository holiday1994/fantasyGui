/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuizGrade_and_App.QuizGrade_and_App;

import java.util.ArrayList;
import java.util.Random;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author huntw
 */
public class charactterSheet extends Application {

    Random rand = new Random();
    ArrayList<Races> raceList = new ArrayList();

    @Override
    public void start(Stage primaryStage) {

        GridPane pane = new GridPane();

        Label cName = new Label("Character Name: ");
        TextField cNameTxt = new TextField();

        Label desc = new Label("Character Description: ");
        TextArea descText = new TextArea();

        Label str = new Label("Strength: ");
        TextField strTxt = new TextField();
        Label intt = new Label("Intelligenec: ");
        TextField inttTxt = new TextField();
        Label agl = new Label("Agility: ");
        TextField aglTxt = new TextField();

        ObservableList races = FXCollections.observableArrayList();
        Button addRace = new Button("Add New Race ->");
        ComboBox box = new ComboBox(races);

        pane.add(cName, 0, 0);
        pane.add(cNameTxt, 1, 0);
        pane.add(desc, 0, 1);
        pane.add(descText, 1, 1);
        pane.add(str, 0, 2);
        pane.add(strTxt, 1, 2);
        pane.add(intt, 0, 3);
        pane.add(inttTxt, 1, 3);
        pane.add(agl, 0, 4);
        pane.add(aglTxt, 1, 4);
        pane.add(box, 2, 3);
        pane.add(addRace, 2, 2);

        Button rollStats = new Button("Roll Stats ->");

        pane.add(rollStats, 0, 5);

        addRace.setOnAction(e -> {
            addNewRace(races);
        });

        rollStats.setOnAction(e -> {
            rollStats(strTxt, inttTxt, aglTxt);
        });
        
        Button setRace = new Button("Set Race ->");
        
        setRace.setOnAction(e -> {
            if(raceList.contains(box.getSelectionModel().getSelectedItem()));
            {
                int number = raceList.indexOf(box.getSelectionModel().getSelectedIndex());
                inttTxt.setText(inttTxt.getText() + raceList.get(number).intt + "");
            }
        });
        
        pane.add(setRace, 3, 2);

        Scene scene = new Scene(pane, 800, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        charactterSheet sheet = new charactterSheet();
        System.out.println(sheet.getRoll(20, 5));

    }

    public ArrayList<Integer> getMax(ArrayList<Integer> list) {
        for (int x = 0; x < list.size() - 1; x++) {
            if (list.get(x) < list.get(x + 1)) {
                int temp = list.get(x + 1);
                list.set(x + 1, list.get(x));
                list.set(x, temp);
            }

        }
        return list;
    }

    public int getRoll(int x, int y) {
        int number = x + 1;
        while (number >= x) {
            number = rand.nextInt(x) + y;
        }
        return number;
    }

    public void rollStats(TextField strTxt, TextField inttTxt, TextField aglTxt) {
        int num1 = 0, num2 = 0, num3 = 0;
        ArrayList<Integer> list = new ArrayList();

        for (int i = 0; i < 3; i++) {
            for (int x = 0; x < 3; x++) {
                list.add(x, getRoll(20, 5));
            }
            list = getMax(list);
            if (i == 0) {
                num1 = list.get(0);
            }
            if (i == 1) {
                num2 = list.get(0);
            }
            if (i == 2) {
                num3 = list.get(0);
            }
        }

        strTxt.setText(num1 + "");
        inttTxt.setText(num2 + "");
        aglTxt.setText(num3 + "");
    }

    public void addNewRace(ObservableList races) {
        Stage raceStage = new Stage();

        GridPane pane = new GridPane();

        TextField raceTxt = new TextField();
        Label strLbl = new Label("Enter Races Strength Bonus");
        TextField strTxt = new TextField("5");
        Label inttLbl = new Label("Enter Races Intelligence Bonus");
        TextField inttText = new TextField("0");
        Label aglLbl = new Label("Enter Races Agility Bonus");
        TextField aglText = new TextField("0");
        Button AddRace = new Button("Add Race->");

        pane.add(raceTxt, 0, 0);
        pane.add(strLbl, 0, 1);
        pane.add(strTxt, 1, 1);
        pane.add(inttLbl, 0, 2);
        pane.add(inttText, 1, 2);
        pane.add(aglLbl, 0, 3);
        pane.add(aglText, 1, 3);
        pane.add(AddRace, 0, 4);
       

        
        AddRace.setOnAction(e -> {
           Races race = new Races(raceTxt.getText(), Integer.parseInt(strTxt.getText()), Integer.parseInt(inttText.getText()), Integer.parseInt(aglText.getText()));
           raceList.add(race);
           for(Races r:raceList){
               races.add(r.getName());
           }
        });
        
        
//        races.add(race);

//        races.add(0, pane);
        pane.setAlignment(Pos.BASELINE_LEFT);
        Scene raceScene = new Scene(pane, 400, 200);
        raceStage.setScene(raceScene);
        raceStage.setTitle("Race Creator");
        raceStage.show();

    }
}
