package VO;

public class user {
    private String username;
    private String password;
    private String gender;
    private String telephone;
    private String address;
    private String email;

    public user(){

    }

    public user(String username,String password){
        this.username=username;
        this.password=password;
    }

    public user(String name,String password,String gender,String telephone,String address,String email){
        this.username=name;
        this.password=password;
        this.gender=gender;
        this.address=address;
        this.telephone=telephone;
        this.email=email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }
}
