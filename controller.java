package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {

    @FXML private TextField textField;
    @FXML private ListView<String> textList;

    private String filePath = "data.txt";
    private File data = new File(filePath);

    public void addTask(ActionEvent event){
        String task = textField.getText();

        if(!task.equals("")){
            textList.getItems().add(task);
            textField.setText("");
        }
        else{
            System.out.println("Error!");
        }

    }

    public void deleteTask(ActionEvent event){
        String selectedTask = textList.getSelectionModel().getSelectedItem();
        if(selectedTask != null){
          textList.getItems().remove(selectedTask);
        }
        else{
            System.out.println("Please select a Task!");
        }



    }

    public void exitApplication(ActionEvent event){

        List<String> currentTask = textList.getItems();

        FileWriter write = null;
        try {
            write = new FileWriter(filePath);

            for (String task : currentTask){
                task += "\n";
                write.write(task);

            }

            write.close();

        } catch (IOException e) {
            e.printStackTrace();
        }



        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Scanner readData = new Scanner(data);

            while (readData.hasNextLine()){
                String text = readData.nextLine();
                textList.getItems().add(text);
            }

            readData.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
