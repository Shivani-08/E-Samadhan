package com.example.sakshi.esamadhan;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SHIVANI on 21-03-2017.
 */
public class searchJsonParser {

    public static List<search> parsefeed(String content) {
        try {
            JSONArray ar = new JSONArray(content);
            List<search> dataList = new ArrayList<>();

            for (int i = ar.length() - 1; i >= 0; i--) {
                JSONObject obj = ar.getJSONObject(i);

                search newSearch = new search();
                newSearch.setId(obj.getString("_id"));
                newSearch.setName(obj.getString("name"));
                JSONArray ar2 = obj.getJSONArray("proc");
                ArrayList<String> Proc = new ArrayList<String>();
                for (int j = 0; j < ar2.length() - 1; j++) {
                    Log.e("string",ar2.getString(j));
                    Proc.add(ar2.getString(j));
                }
                newSearch.setProc(Proc);
                newSearch.setUrl(obj.getString("url"));
                dataList.add(newSearch);

            }

            return dataList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}