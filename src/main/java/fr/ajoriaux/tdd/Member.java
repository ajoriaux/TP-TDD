package fr.ajoriaux.tdd;

import java.sql.Date;

public class Member {

    private String code;
    private String lastname;
    private String firstname;
    private Date birthdate;
    private String civility;
    
	public Member(String code, String lastname, String firstname, Date birthdate, String civility) {
		this.code = code;
		this.lastname = lastname;
		this.firstname = firstname;
		this.birthdate = birthdate;
		this.civility = civility;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getCivility() {
		return civility;
	}

	public void setCivility(String civility) {
		this.civility = civility;
	}
}