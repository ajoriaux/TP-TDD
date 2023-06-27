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
    
    public boolean updateMember(Member member) {
    	Member oldMember = dbMemberDataService.getMember(member.getCode());
    	
    	if (member.getLastname() != oldMember.getLastname()) {
    		oldMember.setLastname(member.getLastname());
    	}
    	if (member.getFirstname() != oldMember.getFirstname()) {
    		oldMember.setFirstname(member.getFirstname());
    	}
    	if (member.getBirthdate() != oldMember.getBirthdate()) {
    		oldMember.setBirthdate(member.getBirthdate());
    	}
    	if (member.getCivility() != oldMember.getCivility()) {
    		oldMember.setCivility(member.getCivility());
    	}
    	
    	dbMemberDataService.updateMember(oldMember);
    	return true;
    }

    public Member searchMemberByCode(String code) {
    	Member member = dbMemberDataService.getMember(code);
    	return member;
    }
}
