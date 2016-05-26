package com.fmt.cheaptrip.webservices.response;

import com.fmt.cheaptrip.entities.User;

/**
 * Created by Miguel on 25/05/16.
 */

public class WSResponseObject {

    private String success;
    private String error;
    private User user;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
