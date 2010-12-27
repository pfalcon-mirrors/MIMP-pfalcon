package org.mimp.dom.gpx;

public class PersonType {

    protected String name = "";
    protected EmailType email;
    protected LinkType link;

    public PersonType() {
    }

    public PersonType(String name, EmailType email, LinkType link) {
        this.name = name;
        this.email = email;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public EmailType getEmail() {
        if (email == null) {
            email = new EmailType();
        }
        return this.email;
    }

    public void setEmail(EmailType value) {
        this.email = value;
    }

    public LinkType getLink() {
        if (link == null) {
            link = new LinkType();
        }
        return this.link;
    }

    public void setLink(LinkType value) {
        this.link = value;
    }
}
