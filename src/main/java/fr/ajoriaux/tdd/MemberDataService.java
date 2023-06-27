package fr.ajoriaux.tdd;

public interface MemberDataService {
    void createMember(Member member);

    void updateMember(Member member);
    
    Member getMember(String code);
    
    void removeMember(String code);
}