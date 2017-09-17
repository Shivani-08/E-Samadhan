package com.example.sakshi.esamadhan;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sakshi on 3/20/2017.
 */

public class loginJsonParser {
    public  static login parsefeed(String content){
        login log =new login();
        try {

            JSONObject obje= new JSONObject(content);
            log.setSuccess(obje.getString("success"));
            log.setToken(obje.getString("token"));
            return log;

        }
        catch (JSONException e1) {
            e1.printStackTrace();
        }


        return log;
    }
}
