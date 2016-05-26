package com.fmt.cheaptrip.webservices.response;

import com.android.volley.VolleyError;

/**
 * Created by Miguel on 25/05/16.
 */

public interface WSResponseListener {
    void onResponse(WSResponseObject response);
    void onError(VolleyError error);
}