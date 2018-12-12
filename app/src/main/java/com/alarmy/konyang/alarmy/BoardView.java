package com.alarmy.konyang.alarmy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static com.alarmy.konyang.alarmy.Constant.BOARD_VIEW_URL;


public class BoardView extends AppCompatActivity {

    String url = BOARD_VIEW_URL;
    String deleteUrl;
    String idx;
    TextView title;
    TextView name;
    TextView text;
    String vTitle;
    String vName;
    String vText;
    TextView vCat;
    String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_view);
        title = (TextView) findViewById(R.id.vtitle);
        name = (TextView) findViewById(R.id.vname);
        text = (TextView) findViewById(R.id.vtext);
        vCat = (TextView)findViewById(R.id.vcat);
        Intent i = getIntent();
        idx = i.getExtras().getString("idx");
        category = i.getExtras().getString("category");
        vCat.setText(category);
        sendRequest();
    }
    public void vedit(View view){
        Intent i = new Intent(BoardView.this, BoardEdit.class);
        i.putExtra("idx",idx);
        i.putExtra("category",category);
        startActivity(i);
    }
    public void vdelete(View view){
        sendDelete();
    }
    public  void sendDelete(){
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, deleteUrl+idx,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                    Dialog("글삭제 성공");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Dialog("글삭제 실패");
            }
        });
        queue.add(jsonObjectRequest);
    }

    public void sendRequest(){
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url+idx,null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            vTitle = response.getString("title");
                            vName = response.getString("owner");
                            vText = response.getString("content");
                            title.setText(vTitle);
                            name.setText(vName);
                            text.setText(vText);
                        } catch (JSONException e) {
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(jsonObjectRequest);
    }
    public void Dialog(String title){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(BoardView.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(BoardView.this);
        }
        builder.setTitle(title).setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // continue with delete
            }
        }).setIcon(android.R.drawable.ic_dialog_alert).show();
    }
}
