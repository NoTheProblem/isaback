package ftn.isa.pharmacy.security.auth;


// DTO za login
public class JwtAuthenticationRequest {

    private String username;
    private String password;
    private String role;

    public JwtAuthenticationRequest() {
        super();
    }

    public JwtAuthenticationRequest(String username, String password, String role) {
        this.setUsername(username);
        this.setPassword(password);
        this.setPassword(role);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
