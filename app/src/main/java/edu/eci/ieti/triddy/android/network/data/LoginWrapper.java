package edu.eci.ieti.triddy.android.network.data;

public class LoginWrapper {

    public final String email;

    public String password;

    public LoginWrapper( String email, String password )
    {
        this.email = email;
        this.password = password;
    }
}
