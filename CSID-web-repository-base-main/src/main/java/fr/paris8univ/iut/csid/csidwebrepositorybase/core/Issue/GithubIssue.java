package fr.paris8univ.iut.csid.csidwebrepositorybase.core.Issue;

public class GithubIssue {
    private int id;
    private String html_url;
    private int number;
    private String state;
    private String body;

    public GithubIssue(int id, String html_url, int number, String state, String body) {
        super();
        this.id = id;
        this.html_url = html_url;
        this.number = number;
        this.state = state;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
