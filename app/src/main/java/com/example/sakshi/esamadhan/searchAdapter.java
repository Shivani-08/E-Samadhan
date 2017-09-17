package com.example.sakshi.esamadhan;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.sakshi.esamadhan.R;
import com.example.sakshi.esamadhan.search;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import static java.lang.System.load;


/**
 * Created by ravi joshi on 10/23/2016.
 */
public class searchAdapter  extends ArrayAdapter<search> {
    private Context context;
    private List<search> dataList;




    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    public searchAdapter(Context context, int resource, List<search>objects){
        super(context,resource, objects);
        this.context=context;
        this.dataList=objects;



    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_layout,parent,false);
        final search model = dataList.get(position);
        TextView tv= (TextView) view.findViewById(R.id.doc_text);
        tv.setText(model.getName());
        return view;
    }

}
