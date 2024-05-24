package com.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class GameController {
    @FXML
    ImageView Hex;
    @FXML
    Label lbl_Code;
    @FXML
    Label lbl_Welcome;

    @FXML
    Button btn_Hex;
    private GameApplication gameApp;

    @FXML
    protected void onClicked(ActionEvent event) throws IOException {
        GameApplication startHex= new GameApplication();
        startHex.startGame();


        }


    }

