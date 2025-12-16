public class User {
    private String name;
    private String phone;
    private String email;
    private String username;
    private String password;
    private boolean isStaff;

    public User(String name, String phone, String email,
                String username, String password, boolean isStaff) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.isStaff = isStaff;
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String attempt) {
        return password.equals(attempt);
    }

    public boolean isStaff() {
        return isStaff;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return (isStaff ? "[STAFF] " : "") + username + " (" + email + ")";
    }
}
