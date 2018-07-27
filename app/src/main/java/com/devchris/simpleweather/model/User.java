package com.devchris.simpleweather.model;

public class User {

    private long id;
    private String email;
    private String password;
    private String authenticationToken;

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getAuthenticationToken() {
        return authenticationToken;
    }
    
    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null)
            return false;
        return authenticationToken != null ? authenticationToken.equals(user.authenticationToken) : user.authenticationToken == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (authenticationToken != null ? authenticationToken.hashCode() : 0);
        return result;
    }
}
