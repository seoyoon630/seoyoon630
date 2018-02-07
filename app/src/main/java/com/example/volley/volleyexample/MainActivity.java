package com.example.volley.volleyexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    RequestQueue queue = null;
    public static final String TAG = "myTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView mTextView = (TextView) findViewById(R.id.text);

        // volley
        queue = Volley.newRequestQueue(this);

 /*
        final String url = "http://www.google.com";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mTextView.setText("Response is : " + response.substring(0, 500));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        });

        stringRequest.setTag(TAG);
        queue.add(stringRequest);
*/

        final String jsonUrl = "http://my-json-feed";
        String url = "http://my-json-feed";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        mTextView.setText("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });
        jsonObjectRequest.setTag(TAG);
        queue.add(jsonObjectRequest);


        // json
        final TextView mTextView2 = (TextView) findViewById(R.id.text2);
        Person person = new Person();
        person.setName("kim");
        person.setAge(20);
        person.setGender("M");
        Gson gson = new Gson();
        String json = gson.toJson(person);
        mTextView2.setText(json);

        final TextView mTextView3 = (TextView) findViewById(R.id.text3);
        Gson gson2 = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", "정서윤");
        jsonObject.addProperty("age", "26");
        jsonObject.addProperty("gender", "F");
        String json2 = gson2.toJson(jsonObject);
        mTextView3.setText(json);


        final TextView nameText = (TextView) findViewById(R.id.name);
        final TextView ageText = (TextView) findViewById(R.id.age);
        final TextView genderText = (TextView) findViewById(R.id.gender);
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(json2);
        String name = element.getAsJsonObject().get("name").getAsString();
        nameText.setText(name);
        String age = element.getAsJsonObject().get("age").getAsString();
        ageText.setText(age);
        String gender = element.getAsJsonObject().get("gender").getAsString();
        genderText.setText(gender);
    }

   @Override
    protected void onStop() {
        super.onStop();
        if(queue != null){
            queue.cancelAll(TAG);
        }
    }
}
