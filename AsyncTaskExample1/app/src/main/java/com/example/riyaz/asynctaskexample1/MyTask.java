package com.example.riyaz.asynctaskexample1;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.ContextMenu;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Riyaz on 6/22/2017.
 */

public class MyTask extends AsyncTask<Void,Integer,String> {
    Button button;
    Context context;
    TextView textView;
    ProgressDialog progressDialog;

    MyTask(Context context,TextView textView, Button button){
        this.context = context;
        this.textView = textView;
        this.button = button;
    }

    @Override
    protected String doInBackground(Void... params) {
        int i = 0;
        synchronized (this){
            while(i <10){
                try {
                    wait(1500);
                    i++;
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
        return "Download complete";
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Download in progress");
        progressDialog.setMax(10);
        progressDialog.setProgress(0);
        progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
        progressDialog.show();

    }

    @Override
    protected void onPostExecute(String result) {
      textView.setText(result);
        progressDialog.hide();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
       int progress = values[0];
        progressDialog.setProgress(progress);
        textView.setText("Downloading in Progress");
    }
}
