package com.example.sakshi.esamadhan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailedDoc extends AppCompatActivity {

    TextView title, content, link;
    search obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_doc);
        obj = (search) getIntent().getSerializableExtra("object");
        Log.e("object name", obj.getName());

        title=(TextView)findViewById(R.id.docTitle);
        content=(TextView)findViewById(R.id.docContent);
        link=(TextView)findViewById(R.id.docLink);

        title.setText(obj.getName());
        String cont=" ";
        ArrayList<String>pro=new ArrayList<String>();
        pro=obj.getProc();
        Log.e("Element",pro.get(0)+"");
        for(int i=0;i<pro.size();i++){
            Log.e("cont",cont);

            cont=cont+pro.get(i)+"\n";


        }
        Log.e("cont",cont);
        content.setText(cont);
        link.setText(obj.getUrl().toString());

    }
}
