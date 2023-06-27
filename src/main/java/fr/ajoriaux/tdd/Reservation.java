package fr.ajoriaux.tdd;

import java.sql.Date;

public class Reservation {

    private String id;
    private Member member;
    private Book book;
    private Date reservedAt;
    private Date expireAt;
    
	public Reservation(String id, Member member, Book book, Date reservedAt, Date expireAt) {
		this.id = id;
		this.member = member;
		this.book = book;
		this.reservedAt = reservedAt;
		this.expireAt = expireAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Date getReservedAt() {
		return reservedAt;
	}

	public void setReservedAt(Date reservedAt) {
		this.reservedAt = reservedAt;
	}

	public Date getExpireAt() {
		return expireAt;
	}

	public void setExpireAt(Date expireAt) {
		this.expireAt = expireAt;
	}
}