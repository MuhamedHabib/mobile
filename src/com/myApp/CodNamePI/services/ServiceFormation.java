/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myApp.CodNamePI.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;

import com.codename1.ui.events.ActionListener;
import com.myApp.CodNamePI.entities.Myformation;
import com.myApp.CodNamePI.utils.Statics;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lenovo
 */
public class ServiceFormation {
    public ArrayList<Myformation> myformations;
    public static ServiceFormation instance;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceFormation() {
        req = new ConnectionRequest();
    }

    public static ServiceFormation getInstance(){
        if(instance == null)
            instance = new ServiceFormation();
        return instance;
    }


    public boolean addFormation(Myformation t)
    {
        String url =Statics.BASE_URL
                +"/addJSON?libelle="+t.getLibelle()
                + "&description="+t.getDiscription()
                +"&type="+t.getType()
                +"&image="+t.getImage();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK=req.getResponseCode()==200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Myformation> parseFormation(String jsonText){
        try {
            myformations = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> FormationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)FormationListJson.get("root");
            for(Map<String,Object> obj : list)
            {
                Myformation t = new Myformation();
                float id = Float.parseFloat(obj.get("id").toString());

                t.setId((int)id);
                t.setLibelle(obj.get("libelle").toString());
                t.setDiscription(obj.get("description").toString());
                t.setType(obj.get("type").toString());
                t.setImage(obj.get("image").toString());
               // t.setLibelle(obj.get("libelle").toString());
              //  t.setEtat((int)Float.parseFloat(obj.get("etat").toString()));
            //    t.setReponse(obj.get("reponse").toString());

                myformations.add(t);
            }

        } catch (IOException ex) {
        }
        return myformations;
    }

    public void ModifierFormation( int id , String libelle, String description, String type, String image ) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl( "http://127.0.0.1:8000/updateJSON?id="+id+"&libelle="+libelle+"&description="+description+
                "&type="+type+"&image="+image) ;
        NetworkManager.getInstance().addToQueue(con);
    }
    public void SupprimerFormation(int id_formation) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/deleteFormationJSON/"+id_formation);
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public ArrayList<Myformation> getAllformations(){
        String url = Statics.BASE_URL+"/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                myformations = parseFormation(new String (req.getResponseData()));
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return myformations;
    }
}
