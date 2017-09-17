package com.example.sakshi.esamadhan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sakshi on 3/20/2017.
 */

public class lawyerJsonParser {
    public static lawyer parsefeed(String content) {
        lawyer newLawyer = new lawyer();
        try {
            JSONArray ar = new JSONArray(content);
            for (int i = 0; i < ar.length(); i++) {
                JSONObject obj = ar.getJSONObject(i);

                newLawyer.setName(obj.getString("name"));
                newLawyer.setAddress(obj.getString("address"));
                newLawyer.setEmail(obj.getString("email"));
                newLawyer.setPassword(obj.getString("password"));
                newLawyer.setPhone(obj.getString("phone"));
                newLawyer.setPincode(obj.getString("pincode"));
                newLawyer.setDob(obj.getString("dob"));



                return newLawyer;
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }


        return newLawyer;
    }
}
