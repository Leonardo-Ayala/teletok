package sw2.lab6.teletok.entity;

import org.aspectj.bridge.IMessage;

import javax.persistence.Entity;

public class PostClassTemp {
    private String token;
    private int postId;
    private String message;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
