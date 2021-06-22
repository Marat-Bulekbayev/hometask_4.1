package businessobject;

public class Email {

    private String addressee;
    private String emailSubject;
    private String emailText;

    public Email(String addressee, String emailSubject, String emailText) {
        this.addressee = addressee;
        this.emailSubject = emailSubject;
        this.emailText = emailText;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailText() {
        return emailText;
    }

    public void setEmailText(String emailText) {
        this.emailText = emailText;
    }
}
