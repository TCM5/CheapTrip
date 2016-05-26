package com.fmt.cheaptrip.webservices.request;

/**
 * Created by Miguel on 24/05/16.
 */

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

/**
 *
 */
public class CustomStringRequest extends Request<String> {


    private Listener mListener = null;
    private Map<String, String> params = new HashMap<String, String>();

    /**
     * R
     *
     * @param method
     * @param url
     * @param mListener
     * @param mErrorListener
     */
    public CustomStringRequest(int method, String url, Listener mListener, ErrorListener mErrorListener) {
        super(method, url, mErrorListener);
        this.mListener = mListener;
    }

    /**
     * @param method
     * @param url
     */
    public CustomStringRequest(int method, String url) {
        this(method, url, null, null);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed = "";
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(String response) {
        if (this.mListener != null) {
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

    /**
     * Util function
     */
    public void addParam(String key, String value) {
        getParams().put(key, value);
    }
}