package com.spam.spamdetection;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.HashMap;

public class TfidfVectorizer {
    private HashMap<String, Integer> vocabulary;

    public static TfidfVectorizer load(Context context, String filePath) {
        TfidfVectorizer vectorizer = new TfidfVectorizer();
        try {
            InputStream inputStream = context.getAssets().open(filePath);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();

            String json = new String(buffer);
            Type type = new TypeToken<HashMap<String, Integer>>() {}.getType();
            vectorizer.vocabulary = new Gson().fromJson(json, type);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return vectorizer;
    }

    public float[][] transform(String message) {
        float[][] vector = new float[1][vocabulary.size()];
        for (String word : message.split("\\s+")) {
            if (vocabulary.containsKey(word)) {
                vector[0][vocabulary.get(word)] += 1;
            }
        }
        return vector;
    }
}
