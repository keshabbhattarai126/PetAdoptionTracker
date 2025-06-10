/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petadoptiontracker.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import petadoptiontracker.view.ImageUpload;

public class ImageUploadController {

    private ImageUpload imageUpload;

    public ImageUploadController(ImageUpload imageUpload) {
        this.imageUpload = imageUpload;
        this.imageUpload.ImageButton1Listener(new ImageButton1Listener());
        this.imageUpload.ImageButton2Listener(new ImageButton2Listener());
    }

    public void open() {
        imageUpload.setVisible(true);
    }

    class ImageButton1Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(imageUpload);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                imageUpload.setSelectedFile(file);
            }
        }
    }

    class ImageButton2Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(imageUpload);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                imageUpload.setSelectedFile(file);
            }
        }
    }
}
