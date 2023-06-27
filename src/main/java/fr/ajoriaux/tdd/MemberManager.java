package fr.ajoriaux.tdd;

public class MemberManager {
    private MemberDataService dbMemberDataService;

    public MemberDataService getDatabaseMemberDataService() {
        return dbMemberDataService;
    }

    public void setDatabaseMemberDataService(MemberDataService dbMemberDataService) {
        this.dbMemberDataService = dbMemberDataService;
    }
    
    public boolean addMember(Member member) {
    	dbMemberDataService.createMember(member);
    	return true;
    }

    public Member searchMemberByCode(String code) {
    	Member member = dbMemberDataService.getMember(code);
    	return member;
    }
}
