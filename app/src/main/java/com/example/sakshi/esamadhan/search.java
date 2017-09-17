package com.example.sakshi.esamadhan;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by SHIVANI on 21-03-2017.
 */
public class search implements Serializable {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
    private String name;
    private ArrayList<String>proc;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<String> getProc() {
        return proc;
    }

    public void setProc(ArrayList<String> proc) {
        this.proc = proc;
    }

    public void setName(String name) {
        this.name = name;

    }

    private String url;
}
