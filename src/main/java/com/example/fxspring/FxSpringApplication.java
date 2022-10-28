package com.example.fxspring;

import com.example.fxspring.Controller.fxmlController;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.fxspring.fxmlView.myView;
import org.springframework.context.ApplicationContext;

@MapperScan("com.example.fxspring.Dao")
@SpringBootApplication
public class FxSpringApplication extends Application {
    private static ApplicationContext applicationContext;

    private static FXMLLoader loaderFxml(String fxmlPath){
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(FxSpringApplication.class.getResource(fxmlPath));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        return fxmlLoader;
    }

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(FxSpringApplication.class, args);
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("个人通讯录");
        primaryStage.setScene(new Scene(loaderFxml("/hello-view.fxml").load()));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
