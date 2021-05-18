/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myApp.CodNamePI.gui;



import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.myApp.CodNamePI.entities.Myformation;
import com.myApp.CodNamePI.services.*;

/**
 *
 * @author Lenovo
 */
public class AddFormationForm extends Form{

    public AddFormationForm(Form previous) {
        setTitle("Add a new Reclamation");
        setLayout(BoxLayout.y());

        TextField tfContenu = new TextField("", "Libelle");
        TextField tfEtat = new TextField("", "Description");
        TextField tfReponse = new TextField("", "Type");
        TextField tfImage = new TextField("", "Image");



        Button btnValider = new Button("Add Formation");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfContenu.getText().length()==0) || tfEtat.getText().length()==0
                        ||tfReponse.getText().length()==0 ||tfImage.getText().length()==0)
                    Dialog.show("Alert", "Pleas fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Myformation t = new Myformation( tfContenu.getText(),tfEtat.getText(),tfReponse.getText(),tfImage.getText());
                        if(new ServiceFormation().addFormation(t))
                        {Dialog.show("Success", "Connection accepted", new Command("OK"));
                            previous.showBack();
                        }
                        else
                            Dialog.show("ERROR", "Server error", new Command("Ok"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }


            }
        });
        addAll(tfContenu,tfEtat,tfReponse,tfImage,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }


}
