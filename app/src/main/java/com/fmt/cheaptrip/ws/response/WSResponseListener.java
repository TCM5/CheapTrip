package com.fmt.cheaptrip.ws.response;

import com.android.volley.VolleyError;

/**
 * Created by Miguel on 25/05/16.
 */

public interface WSResponseListener {
    public void onResponse(WSResponseObject response);
    public void onError(VolleyError error);
}