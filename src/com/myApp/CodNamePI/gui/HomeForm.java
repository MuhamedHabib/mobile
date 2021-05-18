/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myApp.CodNamePI.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Lenovo
 */
public class HomeForm extends Form{
Form current;
    public HomeForm() {
        current=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        add(new Label("choose an option"));
        Button btnAddFomration = new Button("Ajouter Formation");
        Button btnListFormation = new Button("List Formation");
        Button btnAddFiles = new Button("Ajouter des fichiers");
        Button btnListFiles = new Button("List fichiers");
        
        
        
        btnAddFomration.addActionListener(e-> new AddFormationForm(current).show());
        btnListFormation.addActionListener(e-> new ListFormationForm(current).show());

  

      //  btnAddFiles.addActionListener(e-> new AddFileForm(current).show());
       // btnListFiles.addActionListener(e-> new ListFilesForm(current).show());
       // addAll(btnAddFomration,btnListFormation,btnAddFiles,btnListFiles);
        addAll(btnAddFomration,btnListFormation);
        
        
    }
    
    
}
