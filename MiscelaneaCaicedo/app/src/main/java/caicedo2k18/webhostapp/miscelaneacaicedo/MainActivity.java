package caicedo2k18.webhostapp.miscelaneacaicedo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private Activity mActivity;
    private Button mButtonDo;
    private Button botonGuardar;
    private TextView mTextView;
    private EditText agno, mes;
    // private String JSONURLpost = "http://52.168.76.48/estudianteapi/read.php";

    private String JSONURLget = "http://caicedo2k18.000webhostapp.com/miscelaneaAPI/read.php";
    private String JSONURLpost = "http://caicedo2k18.000webhostapp.com/miscelaneaAPI/insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the application context
        mContext = getApplicationContext();
        mActivity = MainActivity.this;

        // Get the widget reference from XML layout
        mButtonDo = (Button) findViewById(R.id.btn_do);
        botonGuardar = (Button) findViewById(R.id.btnGuardar);
        mTextView = (TextView) findViewById(R.id.tv);

        agno = (EditText)findViewById(R.id.agno);

        mes = (EditText)findViewById(R.id.mes);




        // Set a click listener for button widget
        mButtonDo.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {
                // Empty the TextView
                mTextView.setText("");
                String mes_ = mes.getText().toString();
                String agno_ = agno.getText().toString();

                String mes_venta = agno_+"-"+mes_;

                // Initialize a new RequestQueue instance
                RequestQueue requestQueue = Volley.newRequestQueue(mContext);
                Toast.makeText(MainActivity.this,mes_venta, Toast.LENGTH_SHORT).show();
                try{
                    JSONObject jsonBod = new JSONObject();
                    jsonBod.put("mes_venta", mes_venta);
                    final String requestBody = jsonBod.toString();

                    JsonArrayRequest request = new JsonArrayRequest(JSONURLget, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray jsonArray) {

                            for (int i = 0; i < jsonArray.length(); i++) {
                                try {
                                    JSONObject objestudent = jsonArray.getJSONObject(i);
                                    String fecha_venta = objestudent.getString("fecha_venta");
                                    String venta_golosinas = objestudent.getString("venta_golosinas");
                                    String venta_aseo = objestudent.getString("venta_aseo");
                                    String venta_escolares = objestudent.getString("venta_escolares");
                                    mTextView.append(fecha_venta + " " + venta_golosinas + " " + venta_aseo
                                            + " " + venta_escolares + "\n");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                        }
                    });
                    requestQueue.add(request);
                } catch (JSONException e) {
                e.printStackTrace();
            }


            }
        });

    }

}