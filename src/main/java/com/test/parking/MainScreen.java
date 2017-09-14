/*
 * Copyright (c) 2011, 2012 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.test.parking;

import com.test.parking.ui.admin.MainScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;

import java.io.IOException;

//@Lazy
@SpringBootApplication
@Configuration
@ComponentScan(basePackages="com.test.parking")
@EntityScan(basePackages = "com.test.parking")
public class MainScreen
        extends Application {

    public static void main(String[] args) {
        Application.launch(MainScreen.class, args);
//        launch(args);
//        launchApp(MainScreen.class, args);
    }
    private ConfigurableApplicationContext springContext;
    private Parent root;


    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(MainScreen.class);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main_screen.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        root = fxmlLoader.load();
    }

    @Override
    public void start(Stage stage) throws Exception {
//        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("main_screen.fxml"));
//        loader.setControllerFactory(applicationContext::getBean);
//        Parent mainFrame = loader.load();

//        Parent mainFrame = FXMLLoader.load(getClass().getClassLoader().getResource("main_screen.fxml"));

//        Object obj = loader.load("main_screen.fxml");
//        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main_screen.fxml"));

        stage.setTitle("Parking Lot MS");
        stage.setScene(new Scene(root, 300, 275));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
}
