package ma.ensa.accountManagement_service.responce;





public class AuthenticationResponse {
    private String token;
    private String role;
    private String responseType;

    public AuthenticationResponse(String responseType,String token,String role){
        this.responseType=responseType;
        this.token=token;
        this.role=role;
    }

    public AuthenticationResponse() {
    }

    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }
}
