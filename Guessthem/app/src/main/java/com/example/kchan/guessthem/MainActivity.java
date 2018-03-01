package com.example.kchan.guessthem;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> celebURL = new ArrayList<String>();
    ArrayList<String> celebnames = new ArrayList<String>();
    int chosenceleb = 0;
    int correctbutton;
    int[] options= new int[4];
    String[] answers=new String[4];
    Button b0;
    Button b1;
    Button b2;
    Button b3;
    ImageView imageView;

        public void chooseBut(View view){
            if(view.getTag().toString().equals(Integer.toString(correctbutton))){
                Toast.makeText(MainActivity.this, "Correct.!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "No. It was "+ celebnames.get(chosenceleb), Toast.LENGTH_SHORT).show();
            }
            repeatmode();
        }
        public class Imagetask extends AsyncTask<String, Void, Bitmap> {

            @Override
            protected Bitmap doInBackground(String... urls) {
                try {
                    URL url=new URL(urls[0]);
                    HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                    connection.connect();
                    InputStream inputStream=connection.getInputStream();
                    Bitmap myBitmap= BitmapFactory.decodeStream(inputStream);
                    return myBitmap;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        public class Downloadtask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            String result="";
            URL url;
            HttpURLConnection urlconnect =null;
            try {
                url = new URL(params[0]);
                urlconnect=(HttpURLConnection)url.openConnection();
                InputStream in=urlconnect.getInputStream();
                InputStreamReader reader=new InputStreamReader(in);
                int data=reader.read();
                while ((data != -1)){

                    char current=(char) data;
                    result+=current;
                    data=reader.read();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {

            }return result;
        }
    }
    public void repeatmode(){
        Random random= new Random();
        chosenceleb = random.nextInt(celebURL.size());
        Imagetask imagetask=new Imagetask();
        Bitmap celebimage;
        try {
            celebimage=imagetask.execute(celebURL.get(chosenceleb)).get();
            imageView.setImageBitmap(celebimage);
            correctbutton=random.nextInt(4);
            int incorrectlocation;
            for(int i=0; i<4;i++){
                if(i==correctbutton){
                    answers[i]=celebnames.get(chosenceleb);

                }else{
                    incorrectlocation=random.nextInt(celebURL.size());
                    while(incorrectlocation==chosenceleb){
                        incorrectlocation=random.nextInt(celebURL.size());
                    }
                    answers[i]=celebnames.get(incorrectlocation);


                }
                b0.setText(answers[0]);
                b1.setText(answers[1]);
                b2.setText(answers[2]);
                b3.setText(answers[3]);
        } }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }





        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Downloadtask task=new Downloadtask();
        imageView=(ImageView)findViewById(R.id.imageView);
        b0=(Button)findViewById(R.id.button);
        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        String result= null;
        try {
            result=task.execute("http://www.posh24.se/kandisar").get();
            String[] splitresult=result.split("<div class=\"sidebarContainer\">");

            Pattern p=Pattern.compile("<img src=\"(.*?)\"");
            Matcher m=p.matcher(splitresult[0]);

            while(m.find()){
                celebURL.add(m.group(1));
                System.out.println(m.group(1));
            }
             p=Pattern.compile("alt=\"(.*?)\"");
             m=p.matcher(splitresult[0]);

            while(m.find()){
                celebnames.add(m.group(1));
                System.out.println(m.group(1));
            }

                Log.i("Download",result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        repeatmode();
    }
}
