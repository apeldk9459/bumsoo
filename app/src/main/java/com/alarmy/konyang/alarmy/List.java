package com.alarmy.konyang.alarmy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.alarmy.konyang.alarmy.Constant.LIST_CREATE_URL;
import static com.alarmy.konyang.alarmy.Constant.LIST_VIEW_URL;

public class List extends AppCompatActivity {
    String eNum;
    private ListView boardList;
    private ListAdapter listadapter;
    private ArrayList<ListItem> bList;
    EditText listInseart;
    String insterturl=LIST_CREATE_URL;
    String loadurl=LIST_VIEW_URL;
    String cat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Intent i = getIntent();
        listInseart=(EditText)findViewById(R.id.board_list);
        eNum = i.getExtras().getString("eNum");
        boardList = (ListView) findViewById(R.id.blist);
        bList=new ArrayList<>();
        listadapter = new ListAdapter(List.this, bList);
        boardList.setAdapter(listadapter);
        BoardList();
        boardList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(List.this, NoticeBoard.class);
                i.putExtra("eNum",eNum);
                i.putExtra("category",bList.get(position).getCategory());
                startActivity(i);
            }
        });
    }
    public void bcreate(View view){
        JSONObject js = new JSONObject();
        try {
            js.accumulate("boardlist", listInseart.getText().toString());
            VolleyPost(js.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void bdelete(View view){

    }
    public void VolleyPost(final String requestBody){
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest JOPR = new JsonObjectRequest(Request.Method.POST, insterturl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    Toast.makeText(List.this,"게시판 생성완료",Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            public byte[] getBody() {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }
        };
        queue.add(JOPR);
    }
    public void BoardList(){
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, loadurl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        cat = object.getString("category");
                        bList.add(new ListItem(cat));
                        listadapter.notifyDataSetChanged();
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(jsonArrayRequest);
    }
}
