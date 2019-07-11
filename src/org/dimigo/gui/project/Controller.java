package org.dimigo.gui.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TextField textField;
    @FXML
    private TextField textField2;
    private String value;
    private String value2;
    private String ID;

    @FXML
    private ScrollPane sp1;
    @FXML
    private ScrollPane sp2;


    private String jjaryo="";
    private String recipe="";

    Class1 class1=new Class1();
    Class2 class2=new Class2();
    Class3 class3=new Class3();


    Label lb1=new Label();


    @FXML
    private void buttonAction(ActionEvent event){
        value=textField.getText();
        ID=class1.gibonn(value);
        jjaryo=class2.jaryo(ID);
        recipe=class3.gwajung(ID);
        if(ID=="none"){
            jjaryo="검색어를 입력하세요";
            recipe="검색어를 입력하세요";
        }
        sp1.setContent(new Label(jjaryo));
        sp2.setContent(new Label(recipe));
    }
    @FXML
    private void buttonAction2(ActionEvent event){
        FileChooser fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TEXT files(*.txt)","*.txt"));
        File file=fileChooser.showSaveDialog(null);
        try{
            FileWriter writer=null;
            if(file!=null){
                writer=new FileWriter(file);
                writer.write(jjaryo.replace("\n","\r\n")+"\r\n\r\n"+recipe.replace("\n","\r\n"));
                writer.close();
            }
        }catch(Exception e){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("저장할 수 없음");
            alert.setContentText("파일을 저장할 수 없습니다");
            alert.showAndWait();
        }
    }

    private void warning(){
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("검색할 수 없음");
        alert.setContentText("해당 검색어를 검색할 수 없습니다");
        alert.showAndWait();
    }

    @FXML
    private void naver(ActionEvent event){
        value2=textField2.getText();
        try {
            Desktop.getDesktop().browse(new URI("https://search.shopping.naver.com/search/all.nhn?query="+value2+"&cat_id=&frm=NVSHATC"));
        } catch (Exception e) {
            warning();
        }
    }

    @FXML
    private void coupang(ActionEvent event){
        value2= textField2.getText();
        try {
            Desktop.getDesktop().browse(new URI("https://www.coupang.com/np/search?component=&q="+value2+"&channel=user"));
        } catch (Exception e) {
            warning();
        }
    }

    @FXML
    private void tmon(ActionEvent event){
        value2=textField2.getText();
        try{
            Desktop.getDesktop().browse(new URI("http://search.tmon.co.kr/search/?keyword="+value2+"&thr=ts"));
        }catch(Exception e){
            warning();
        }
    }

    @FXML
    private void we(ActionEvent event){
        value2=textField2.getText();
        try{
            Desktop.getDesktop().browse(new URI("https://search.wemakeprice.com/search?keyword="+value2+"&isRec=1"));
        }catch(Exception e){
            warning();
        }
    }

    @FXML
    private void printTab1(){
        sp1.setContent(new Label(jjaryo.replaceAll("\n","\n")));
    }
    @FXML
    private void printTab2(){
        sp2.setContent(new Label(recipe));
    }




    public void initialize(URL location, ResourceBundle resources){

    }
}
