package com.sg.kata.accountmanagement.account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sg.kata.accountmanagement.account.operations.OperationDTO;

import javassist.tools.rmi.ObjectNotFoundException;




@ExtendWith(SpringExtension.class)
@WebMvcTest
public class AccountControllerTest {
	@Autowired
	MockMvc mockMvc;

	@MockBean
	AccountService accountServiceImpl;
	@Test
	public void saveMoneyException() throws ObjectNotFoundException  {
		// Given
		OperationDTO deposit = new OperationDTO() ;
		 // When
		Mockito.doThrow(ObjectNotFoundException.class)
	    .when(accountServiceImpl).saveMoney(Mockito.any(OperationDTO.class));
		 try {
			 //then
			mockMvc.perform(MockMvcRequestBuilders.post("/save")
			            .contentType(MediaType.APPLICATION_JSON)
			            .content(asJsonString(deposit))
			    ).andExpect(status().isInternalServerError());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void saveMoney() throws Exception {
		OperationDTO deposit = new OperationDTO() ;
		 mockMvc.perform(MockMvcRequestBuilders.post("/save")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(deposit))
	        ).andExpect(status().is2xxSuccessful());
	}
	@Test
	public void retrieveSaving() throws Exception {
		OperationDTO withdrawal = new OperationDTO();
		mockMvc.perform(MockMvcRequestBuilders.post("/retrieve")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(withdrawal))
        ).andExpect(status().is2xxSuccessful());
	}
	@Test
	public void retrieveSavingException() throws ObjectNotFoundException {
		// Given
		OperationDTO withdrawal = new OperationDTO();
		// when
		Mockito.doThrow(ObjectNotFoundException.class)
	    .when(accountServiceImpl).retrieveSaving(Mockito.any(OperationDTO.class));
		try {
			//then
			mockMvc.perform(MockMvcRequestBuilders.post("/retrieve")
			        .contentType(MediaType.APPLICATION_JSON)
			        .content(asJsonString(withdrawal))
			).andExpect(status().isInternalServerError());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String asJsonString(final Object obj) throws JsonProcessingException {
        
        return new ObjectMapper().writeValueAsString(obj);
   
}
}
