package com.example.kchan.weatherapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText cityname;
    public void findweather(View view){
        Log.i("Cityname", cityname.getText().toString());

        Downloadtasks tasks = new Downloadtasks();
        tasks.execute("http://api.openweathermap.org/data/2.5/weather?q=" + cityname.getText().toString());

    }

    public class Downloadtasks extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                int data = reader.read();
                while (data != -1) {

                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(String result) {
             super.onPostExecute(result);
            try {
                JSONObject jsonObject=new JSONObject(result);
                String weather= jsonObject.getString("weather");
                Log.i("Weather content", weather);
                JSONArray array= new JSONArray(weather);
                for (int i=0;i< array.length();i++){
                    JSONObject jsonpart= array.getJSONObject(i);
                    Log.i("Name",jsonpart.getString("main"));
                    Log.i("Description",jsonpart.getString("description"));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityname=(EditText)findViewById(R.id.cityname);
    }
}
