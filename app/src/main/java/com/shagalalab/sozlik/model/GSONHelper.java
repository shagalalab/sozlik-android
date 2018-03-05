package com.shagalalab.sozlik.model;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by manas on 05.03.18.
 */

public class GSONHelper {

    private final Context context;

    public GSONHelper(Context context) {
        this.context = context;
    }

    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = context.getAssets().open("sozlik.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public List<SozlikDbModel> modelList() {

        Type listType = new TypeToken<List<SozlikDbModel>>() {
        }.getType();

        return new GsonBuilder().create().fromJson(loadJSONFromAsset(), listType);

    }


}
