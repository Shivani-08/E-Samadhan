package com.example.sakshi.esamadhan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sakshi on 3/20/2017.
 */

public class userJsonparser {
    public static user parsefeed(String content) {
        user newUser = new user();
        try {
            JSONArray ar = new JSONArray(content);
            for (int i = 0; i < ar.length(); i++) {
                JSONObject obj = ar.getJSONObject(i);

                newUser.setName(obj.getString("name"));
                newUser.setAddress(obj.getString("address"));
                newUser.setEmail(obj.getString("email"));
                newUser.setPassword(obj.getString("password"));
                newUser.setPhone(obj.getString("phone"));
                newUser.setPincode(obj.getString("pincode"));
                newUser.setBhamashahid(obj.getString("bhamashahid"));
                newUser.setLocation(obj.getString("location"));
                return newUser;
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }


        return newUser;
    }
}
