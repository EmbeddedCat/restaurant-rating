package uni.exercise.servlets.users.email_user;


import java.io.*;
import java.nio.charset.StandardCharsets;

public class EmailUser {
    private String email;
    private String password;

    public EmailUser() {
        this.email = null;
        this.password = null;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public void retrieveCredentials() throws IOException {
        InputStream credFile = getClass().getClassLoader().getResourceAsStream("credentials.cred");
        InputStreamReader streamReader = new InputStreamReader(credFile, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);

        reader = new BufferedReader(streamReader);
        this.email = reader.readLine();
        this.password = reader.readLine();

        streamReader.close();
    }   
}