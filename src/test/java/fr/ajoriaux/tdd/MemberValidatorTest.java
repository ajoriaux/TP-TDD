package fr.ajoriaux.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberValidatorTest {
	MemberDataService dbService;
	MemberManager manager;
	
	@BeforeEach
	public void initMocks() {
        dbService = mock(MemberDataService.class);
        
        manager = new MemberManager();
        manager.setDatabaseMemberDataService(dbService);
	}
	
	/// Recherche adhérent par code
	@Test
	public void searchMemberByCode() {
		String code = "MEM1";
		Member member = new Member(code, "Henry", "Thierry", new Date(1984, 4, 8), "M");
		when(dbService.getMember(code)).thenReturn(member);
		manager.searchMemberByCode(code);
		assertEquals(member, dbService.getMember(code));
	}
	
	/// Ajout d'un adhérent 
	@Test
	public void addMemberToDatabase() {
		Member member = new Member("MEM1", "Henry", "Thierry", new Date(1984, 4, 8), "M");
		manager.addMember(member);
		verify(dbService).createMember(member);
		assertTrue(manager.addMember(member));
	}
	
	/// modification d'un adhérent
	@Test 
	public void updateMemberInDatabase() {
		String code = "MEM1";
		Member oldMember = new Member(code, "Henry", "Thierry", new Date(1984, 4, 8), "M");
		Member member = new Member(code, "Henry", "Pauline", new Date(1984, 4, 8), "Mme");
		when(dbService.getMember(code)).thenReturn(member);
		manager.updateMember(member);
		verify(dbService).updateMember(member);
		assertTrue(manager.updateMember(member));
	}
	
	/// Suppression d'un adhérent 
	@Test
	public void removeMemberFromDatabase() {
		String code = "MEM1";
		manager.removeMember(code);
		verify(dbService).removeMember(code);
		assertTrue(manager.removeMember(code));
	}	 
}
