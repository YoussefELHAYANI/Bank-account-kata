package com.sg.kata.accountmanagement.account.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTestUtils {
	public static String asJsonString(final Object obj) throws JsonProcessingException {
        
            return new ObjectMapper().writeValueAsString(obj);
       
    }
}
