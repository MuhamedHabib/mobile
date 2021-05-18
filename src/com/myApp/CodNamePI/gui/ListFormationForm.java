/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myApp.CodNamePI.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.myApp.CodNamePI.entities.Myformation;
import com.myApp.CodNamePI.services.ServiceFormation;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author Lenovo
 */
public class ListFormationForm extends Form{

    static void add(Label label) {
    }
    private Resources theme;
    Button bt_supprimer , bt_modifier , bt_rec;

    public ListFormationForm(Form previous) {
        Form current;
        setTitle("List Formation");
        SpanLabel lb = new SpanLabel();
        ServiceFormation serviceReclamation=new ServiceFormation();
        //       Container c1 = new Container(BoxLayout.y());
        TextField et = new TextField(""," type");
        bt_rec=new Button("Recherche");
        bt_rec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String s =(et.getText());
                if ((et.getText().length()==0))
                { Dialog.show("Alert", "Please fill all the fields", new Command("OK"));  }
                else     {         if (!s.equals("video") || !s.equals("livre"))
                {
                    Dialog.show("Alert", "Invalid data", new Command("OK"));
                }

                else {
                //    Myformation t = new Myformation(Integer.parseInt(et.getText()));
                    //                ServiceReclamation ser = new ServiceReclamation();
                    ServiceFormation.getInstance().getAllformations();
                    Dialog.show("Success","Recherche Terminer",new Command("OK"));
                    /////////////////////////////////////////////////////////////
                    Form   Affichee = new Form("Les etats ="+s);
                    Container cn = new Container(new BoxLayout(BoxLayout.Y_AXIS));

                    for(Myformation c:serviceReclamation.getAllformations()){
                        //Container t = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                      //  int etat = c.getEtat();
                     //   String ch = Integer.toString(etat) ;
                        int id = c.getId();
                        String Libelle=c.getLibelle();
                        String description=c.getDiscription();
                        String type=c.getType();
                        String img =c.getImage();
                        String date =c.getDate();
                        Label l44 = new Label(Libelle);
                        Label l55 = new Label(description);
                        Label l11 = new Label(type);
                        Label l22 = new Label(img);
                        Label l33 = new Label(date);
                        bt_modifier=new Button("Repondre");
                        bt_supprimer=new Button("Supprimer");
                      //  Picker date = new Picker();

                        bt_supprimer.addActionListener((e)->{
                            ServiceFormation ser = new ServiceFormation();
                            Dialog.show("Success","Supprimer Reclamation",new Command("OK"));
                            ser.SupprimerFormation(id);
                            new ListFormationForm(previous).show();
                        });
                        bt_modifier.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                Form   AjouterReclamation = new Form("Ajouter une formation"+ id);

                                Button btnValider = new Button("modifier");
                                TextField reponsee = new TextField(""," reponse");
                                // Container re=new Container(new GridLayout(1,2));
                                // Container re = new Container(BoxLayout.x());
                                Container re= new Container(new BoxLayout(BoxLayout.X_AXIS));
                                re.add(reponsee);
                                re.add(btnValider);
                                AjouterReclamation.add(re);

                                btnValider.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {
                                        if ((reponsee.getText().length()==0))
                                            Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                                        else
                                        {
                                 //           Myformation t = new Myformation(id,reponsee.getText());
                                            ServiceFormation ser = new ServiceFormation();
                                       //     ServiceFormation.getInstance().ModifierFormation(id,reponsee.getText());
                                            Dialog.show("Success","Reponse accepted",new Command("OK"));
                                            new ListFormationForm(previous).show();
                                        }
                                    };
                                });
                                AjouterReclamation.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                                AjouterReclamation.show();
                            }
                        });
                        /////
                        Container tr=new Container(new GridLayout(1,6));


                            tr.add(date);
                            tr.add(l22);
                            tr.add(l11);
                            tr.add(l33);
                            tr.add(l44);
                            tr.add(l55);
                            tr.add(bt_modifier);
                            tr.add(bt_supprimer);
                            cn.add(tr);}

                    Affichee.add(cn);
                    /////////////////////////////////////////////////////////////
                    Affichee.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                    Affichee.show();
                }

                }}
        });





        bt_modifier=new Button("Modifier");
        bt_supprimer=new Button("Supprimer");


       // Label l1 = new Label("ID Reclamation");
        Label l1 = new Label("ID Formation");

        l1.getAllStyles().setFgColor(0x0E36D7);
        Label l2 = new Label("libelle");
        l2.getAllStyles().setFgColor(0x0E36D7);
        Label l3 = new Label("description");
        l3.getAllStyles().setFgColor(0x0E36D7);
        Label l4 = new Label("type");
        l4.getAllStyles().setFgColor(0x0E36D7);
        Label l5 = new Label("image");
        l4.getAllStyles().setFgColor(0x0E36D7);
        Label ld = new Label("Date");
        ld.getAllStyles().setFgColor(0x0E36D7);
        Label ls = new Label("     Repondre");
        ls.getAllStyles().setFgColor(0x0E36D7);
        Label lss = new Label("     Supprimer");
        lss.getAllStyles().setFgColor(0x0E36D7);
        Container h=new Container(new GridLayout(1,6));
        h.add(ld);
        h.add(l2);
        h.add(l4);
        h.add(l3);
        h.add(l5);
        h.add(ls);
        h.add(lss);
        Container cn = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        cn.add(h);
        for(Myformation c:serviceReclamation.getAllformations()){
            Container t = new Container(new BoxLayout(BoxLayout.Y_AXIS));
          //  int etat = c.getEtat();
        //    String ch = Integer.toString(etat) ;
            int id = c.getId();
            String contenu=c.getLibelle();
            String reponse=c.getDiscription();
            String daterec=c.getType();
            String imaget=c.getImage();
            String daterecs=c.getDate();



            Label l11 = new Label(daterecs);
            Label l22 = new Label(contenu);
            Label l33 = new Label(reponse);
            Label l55 = new Label(daterec);
            Label l44 = new Label(imaget);

            bt_modifier=new Button("Repondre");
            bt_supprimer=new Button("Supprimer");
            Picker date = new Picker();
            //date.setDate(daterec);
            bt_supprimer.addActionListener((e)->{
                ServiceFormation ser = new ServiceFormation();
                Dialog.show("Success","Supprimer Reclamation",new Command("OK"));
                ser.SupprimerFormation(id);
                new ListFormationForm(previous).show();
            });
            bt_modifier.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Form   AjouterFormation = new Form("Ajouter une Formation"+ id);

                    Button btnValider = new Button("modifier");
                    TextField libelle = new TextField("","libelle");
                    TextField description = new TextField(""," description");
                    TextField type = new TextField("","type");
                    TextField image = new TextField(""," image");

                    AjouterFormation.add(libelle);
                    AjouterFormation.add(description);
                    AjouterFormation.add(type);
                    AjouterFormation.add(image);
                    AjouterFormation.add(btnValider);
                    btnValider.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            if ((libelle.getText().length()==0)||(description.getText().length()==0)
                            ||(type.getText().length()==0)||(image.getText().length()==0)
                            )
                                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                            else
                            {
                                Myformation t = new Myformation(id,
                                        libelle.getText(),
                                        description.getText(),
                                        type.getText(),
                                        image.getText());
                                ServiceFormation ser = new ServiceFormation();
                                ServiceFormation.getInstance().ModifierFormation(id,libelle.getText(),
                                        description.getText(),
                                        type.getText(),
                                        image.getText());
                                Dialog.show("Success","Reponse accepted",new Command("OK"));
                                new ListFormationForm(previous).show();
                            }
                        };
                    });
                    AjouterFormation.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                    AjouterFormation.show();
                }
            });

            Container tr=new Container(new GridLayout(1,6));
            tr.add(date);
            tr.add(l22);
            tr.add(l11);
            tr.add(l33);
            tr.add(l44);
            tr.add(l55);

            tr.add(bt_modifier);
            tr.add(bt_supprimer);

            cn.add(tr);
        }
        cn.add(et);
        cn.add(bt_rec);
        addAll(cn);
        //lb.setText(ServiceReclamation.getInstance().getAllreclamations().toString());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
}

   