package fr.ajoriaux.tdd;

public interface MemberDataService {
    void createMember(Member member);
    
    Member getMember(String code);
}