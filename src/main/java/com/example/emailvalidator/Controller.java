package com.example.emailvalidator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class Controller {
    @FXML
    private Label emailLable;
    @FXML
    private TextField emailField;
    @FXML
    private Button sendButton;
    private Scene scene;
    private Stage stage;
    public static String passwordGenerator(int length){
        int[][] randomRanges = {{48,57},{65,90},{97,122}};
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i=0;i<length;i++){
            int dice = random.nextInt(0,randomRanges.length);
            otp.append((char)random.nextInt(randomRanges[dice][0],randomRanges[dice][1]));
        }
        return otp.toString();
    }
    public void send(ActionEvent event){
        String emailid = emailField.getText();
        if(emailid.contains("@")&&emailid.contains(".")){
            String host = "smtp.gmail.com";
            Properties properties = System.getProperties();
            System.out.println("PROPERTIES: "+properties);

            properties.put("mail.smtp.host",host);
            properties.put("mail.smtp.port","465");
            properties.put("mail.smtp.ssl.enable","true");
            properties.put("mail.smtp.auth","true");

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("2022btcse002@curaj.ac.in","#$1000000");
                }
            });

            session.setDebug(true);
            String from = "2022btcse002@curaj.ac.in";
            String subject = "Email Authentication";
            String message = passwordGenerator(6);
            String to = emailid;
            MimeMessage m = new MimeMessage(session);
            try {
                m.setFrom(from);
                m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                m.setSubject(subject);
                m.setText(message);
                Transport.send(m);
                System.out.println("Sent!!");
                Parent root = FXMLLoader.load(getClass().getResource("scene2.fxml"));
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else{
            emailLable.setText("Please Enter a valid email id!");
        }
    }

}