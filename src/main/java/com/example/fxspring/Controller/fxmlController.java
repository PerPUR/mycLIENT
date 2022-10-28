package com.example.fxspring.Controller;

import com.example.fxspring.Service.ImService.ServiceUser;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import com.example.fxspring.entity.phoneUser;

@Component
@FXMLController
public class fxmlController implements Initializable {

    @Autowired
    private ServiceUser serviceUser;

    private FxmlNewWin win = new FxmlNewWin();

    private String init = "姓名"+"\t \t"+"电话号码"+"\t" + "\n";

    public String SetCahrs(String str){
        if(str.length()<3){
            str = str+"\t";
        }
        return str;
    }

    public void  SetTextArea(){
            textArea.clear();
            textArea.setEditable(false);
            textArea.setWrapText(true);
            textArea.appendText(init);
            List<phoneUser> users = serviceUser.getAllUsers();
            for(phoneUser user: users){
            String text =SetCahrs(user.getUsername())+"\t"+user.getUphone()+"\t"+"\n";
            textArea.appendText(text);
        }
    }
    public void SetSearchMassage(List<phoneUser> users){
            textArea.clear();
            textArea.appendText(init);
            for(phoneUser user: users){
            String text =SetCahrs(user.getUsername())+"\t"+user.getUphone()+"\t"+"\n";
            System.out.println(text);
            textArea.appendText(text);
        }
    }
    public void  SetSingalMassage(phoneUser user){
        textArea.clear();
        textArea.appendText(init);
        String text =SetCahrs(user.getUsername())+"\t"+user.getUphone()+"\t"+"\n";
        System.out.println("123");
        textArea.appendText(text);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    /**
     * Sample Skeleton for 'hello-view.fxml' Controller Class
     */
        @FXML
        private Label dialog;

        @FXML // ResourceBundle that was given to the FXMLLoader
        private ResourceBundle resources;

        @FXML // URL location of the FXML file that was given to the FXMLLoader
        private URL location;

        @FXML // fx:id="btnAlter"
        private Button btnAlter; // Value injected by FXMLLoader

        @FXML // fx:id="btnNo"
        private Button btnNo; // Value injected by FXMLLoader

        @FXML // fx:id="itemadd"
        private MenuItem itemadd; // Value injected by FXMLLoader

        @FXML // fx:id="itemalter"
        private MenuItem itemalter; // Value injected by FXMLLoader

        @FXML // fx:id="itemdelete"
        private MenuItem itemdelete; // Value injected by FXMLLoader

        @FXML // fx:id="itemone02"
       private MenuItem itemone02; // Value injected by FXMLLoader

        @FXML // fx:id="itemone03"
        private MenuItem itemone03; // Value injected by FXMLLoader

        @FXML // fx:id="itemsearch"
        private MenuItem itemsearch; // Value injected by FXMLLoader

        @FXML // fx:id="itemthrre03"
        private MenuItem itemthrre03; // Value injected by FXMLLoader

        @FXML // fx:id="itemtwo02"
        private MenuItem itemtwo02; // Value injected by FXMLLoader

         @FXML
         void OnItemTwo(ActionEvent event) {
             selectMenu02.setText(itemone02.getText());
        }

        @FXML // fx:id="itemtwo03"
        private MenuItem itemtwo03; // Value injected by FXMLLoader

        @FXML // fx:id="selectMenu01"
        private MenuButton selectMenu01; // Value injected by FXMLLoader

        @FXML // fx:id="selectMenu02"
        private MenuButton selectMenu02; // Value injected by FXMLLoader

        @FXML // fx:id="selectMenu03"
        private MenuButton selectMenu03; // Value injected by FXMLLoader

        @FXML // fx:id="text01"
        private TextField text01; // Value injected by FXMLLoader

        @FXML // fx:id="text02"
        private TextField text02; // Value injected by FXMLLoader

        @FXML // fx:id="textArea"
        private TextArea textArea; // Value injected by FXMLLoader

        private phoneUser user = new phoneUser();

        public static boolean flag = false;

        @FXML
        void OnAdd(ActionEvent event) {
              String txt = "";
              txt = text01.getText().trim();
              String option = "";
              option = selectMenu02.getText().trim();
              if(option.equals("姓名")){
                  user.setUsername(txt);
              }else {
                  user.setUphone(txt);
              }
              if(user.getUsername()!=null && user.getUphone() !=null && !user.getUphone().equals("")){
                     serviceUser.addUser(user);
                     user.setUphone(null);
                     user.setUsername(null);
              }

        }

        @FXML
        void OnAlter(ActionEvent event) {
            selectMenu01.setText(itemalter.getText());
        }

        @FXML
        void OnItemOneThree(ActionEvent event) {
            selectMenu03.setText(itemone03.getText());
        }

        @FXML
        void OnItemThreeThree(ActionEvent event) {
            selectMenu03.setText(itemthrre03.getText());
        }

        @FXML
        void OnItemTwoThree(ActionEvent event) {
            selectMenu03.setText(itemtwo02.getText());
        }

        @FXML
        void Ondelete(ActionEvent event) {
            selectMenu01.setText(itemdelete.getText());
            String txt = text01.getText().trim();
            String select = selectMenu02.getText().trim();
            if(select.equals("电话号码")){
                serviceUser.deleteByPhone(txt);
                SetTextArea();
            }else {
                serviceUser.deleteByName(txt);
                SetTextArea();
            }
        }

        @FXML
        void Onseach(ActionEvent event) throws Exception{

            String txt = text01.getText().trim();
            String option = selectMenu02.getText().trim();
            selectMenu01.setText(itemsearch.getText());
            List<phoneUser> users = null;
            phoneUser user = null;

            if(txt.equals("") || txt.equals(null)){
                SetTextArea();
            }else {
                if(option.equals(new String("电话号码"))){
                    user = serviceUser.getUserByPhone(txt);
                    if(user==null){
                        win.addAeaechLog(new Stage());
                    }else {
                        this.SetSingalMassage(user);
                    }
                }else {
                     users = serviceUser.getUserByName(txt);
                     if(users==null){
                         win.addAeaechLog(new Stage());
                     }else {
                         SetSearchMassage(users);
                     }
                }
             }
        }

        @FXML
        void onAlter(ActionEvent event) throws Exception{
            String txt01="";
            String txt02="";
            String select01 ="";
            String select02 ="";
            String select03 = "";

            txt01 = text01.getText().trim();
            txt02 = text02.getText().trim();


            select01 = selectMenu01.getText().trim();
            select02 = selectMenu03.getText().trim();
            select03 = selectMenu02.getText().trim();

            phoneUser user = null;
             if((txt01.equals("") || txt02.equals("") ) && select01.equals("修改")){
                  new FxmlNewWin().addDialog(new Stage());
             }else {
                  if(select03.equals("电话号码")){
                      user = serviceUser.getUserByPhone(txt01);
                      if(user==null || txt02.equals("")){
                         win.alterLog(new Stage());
                      }else {
                       this.AlterThing(select02,txt02,user);
                          SetTextArea();
                      }
                  }else {
                      List<phoneUser> users = serviceUser.getUserByName(txt01);
                      if(users.get(1)!=null || txt02.equals("")){
                          win.alterLog(new Stage());
                      }else {
                          this.AlterThing(select02,txt02,users.get(0));
                          SetTextArea();
                      }
                  }
             }
        }

        public void AlterThing(String option,String alter,phoneUser user){
               if("姓名".equals(option)){
                   serviceUser.alterName(alter,user);
               }else if("电话号码".equals(option)){
                   serviceUser.alterPhone(alter,user);
               }else {
                   System.out.println(user.getUaddress());
               }
        }
        @FXML
        void onFirst(ActionEvent event) {
               selectMenu02.setText(itemtwo02.getText());
        }

        @FXML
        void onNo(ActionEvent event) {
              text01.setText("");
              text02.setText("");
              System.out.println("取消");
        }

        @FXML
        void onSelectMenuOne(ActionEvent event) {

        }

        @FXML
        void onSelectMenuThree(ActionEvent event) {

        }

        @FXML
        void onSelectMenuTwo(ActionEvent event) {

        }

        @FXML
        void onTextArea(MouseEvent event) {

        }

        @FXML
        void onTextOne(ActionEvent event) {

        }

        @FXML
        void onTextTwo(ActionEvent event) {

        }

        @FXML // This method is called by the FXMLLoader when initialization is complete
        void initialize() {
            assert btnAlter != null : "fx:id=\"btnAlter\" was not injected: check your FXML file 'hello-view.fxml'.";
            assert btnNo != null : "fx:id=\"btnNo\" was not injected: check your FXML file 'hello-view.fxml'.";
            assert itemadd != null : "fx:id=\"itemadd\" was not injected: check your FXML file 'hello-view.fxml'.";
            assert itemalter != null : "fx:id=\"itemalter\" was not injected: check your FXML file 'hello-view.fxml'.";
            assert itemdelete != null : "fx:id=\"itemdelete\" was not injected: check your FXML file 'hello-view.fxml'.";
            assert itemone03 != null : "fx:id=\"itemone03\" was not injected: check your FXML file 'hello-view.fxml'.";
            assert itemsearch != null : "fx:id=\"itemsearch\" was not injected: check your FXML file 'hello-view.fxml'.";
            assert itemthrre03 != null : "fx:id=\"itemthrre03\" was not injected: check your FXML file 'hello-view.fxml'.";
            assert itemtwo02 != null : "fx:id=\"itemtwo02\" was not injected: check your FXML file 'hello-view.fxml'.";
            assert itemtwo03 != null : "fx:id=\"itemtwo03\" was not injected: check your FXML file 'hello-view.fxml'.";
            assert selectMenu01 != null : "fx:id=\"selectMenu01\" was not injected: check your FXML file 'hello-view.fxml'.";
            assert selectMenu02 != null : "fx:id=\"selectMenu02\" was not injected: check your FXML file 'hello-view.fxml'.";
            assert selectMenu03 != null : "fx:id=\"selectMenu03\" was not injected: check your FXML file 'hello-view.fxml'.";
            assert text01 != null : "fx:id=\"text01\" was not injected: check your FXML file 'hello-view.fxml'.";
            assert text02 != null : "fx:id=\"text02\" was not injected: check your FXML file 'hello-view.fxml'.";
            assert textArea != null : "fx:id=\"textArea\" was not injected: check your FXML file 'hello-view.fxml'.";
            System.out.println("123");
        }

}
