package com.example.jing.pricebeater;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.jing.myapplication.backend.myApi.MyApi;
import com.example.jing.myapplication.backend.myApi.model.JsonObject;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by Jing on 4/8/2017.
 */

class EndpointsAsyncTask extends AsyncTask<JsonObject, Void, String> {
    private static MyApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(JsonObject... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://pivotal-realm-162221.appspot.com/_ah/api/");
            // end options for devappserver

            myApiService = builder.build();
        }

        //context = params[0].first;
        //String name = params[0].second;

        try {
            //return myApiService.sayHi(name).execute().getData();
            return myApiService.shopRequest((JsonObject) params[0]).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }



    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}