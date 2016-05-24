package com.fmt.cheaptrip.ws.request;

/**
 * Created by Miguel on 24/05/16.
 */

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * http://www.itworld.com/article/2702452/development/how-to-send-a-post-request-with-google-volley-on-android.html
 */
public class CustomStringRequest extends Request<String> {

    private Listener mListener = null;
    private Map<String, String> params = new HashMap<String, String>();;

    public CustomStringRequest(int method, String url, Listener mListener, ErrorListener mErrorListener) {
        super(method, url, mErrorListener);
        this.mListener = mListener;
    }

    public CustomStringRequest(int method, String url) {
        this(method, url, null, null);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(String response) {
        if(this.mListener != null) {
            mListener.onResponse(response);
        }
    }

    @Override
    protected void onFinish() {
        super.onFinish();
        mListener = null;
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

    //Util function
    public void addParam(String key, String value) {
        getParams().put(key, value);
    }
}