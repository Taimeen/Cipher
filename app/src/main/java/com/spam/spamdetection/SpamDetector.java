package com.spam.spamdetection;

import android.content.Context;
import android.content.res.AssetFileDescriptor;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class SpamDetector {
    private static Interpreter tfliteInterpreter;
    private static TfidfVectorizer vectorizer;

    public static void initialize(Context context) {
        try {
            MappedByteBuffer tfliteModel = loadModelFile(context, "spam_detector.tflite");
            tfliteInterpreter = new Interpreter(tfliteModel);
            vectorizer = TfidfVectorizer.load(context, "vocabulary.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isSpam(Context context, String message) {
        if (tfliteInterpreter == null || vectorizer == null) {
            initialize(context);
        }
        float[][] inputData = vectorizer.transform(message);
        float[][] outputData = new float[1][1];
        tfliteInterpreter.run(inputData, outputData);
        return outputData[0][0] > 0.5;
    }

    private static MappedByteBuffer loadModelFile(Context context, String modelPath) throws IOException {
        AssetFileDescriptor fileDescriptor = context.getAssets().openFd(modelPath);
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }
}
