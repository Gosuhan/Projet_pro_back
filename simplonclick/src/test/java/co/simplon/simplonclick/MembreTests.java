package co.simplon.simplonclick;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import co.simplon.simplonclick.controller.MembreController;
import co.simplon.simplonclick.dao.MembreDAO;
import co.simplon.simplonclick.model.Membre;
import co.simplon.simplonclick.service.MembreService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=MembreController.class, secure=false)
public class MembreTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MembreService membreService;
	
	@MockBean
	private MembreDAO membreDAO;
	
	@Test
    public void getMembreByIdTest() throws Exception {
		
        Membre membre = new Membre();
        membre.setId_membre((long) 1);
        membre.setNom("Test");;
        
        Mockito.when(membreService.getMembre((long) 1)).thenReturn(membre);
       
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("http://localhost:8080/api/membre/1").accept(MediaType.APPLICATION_JSON);
       
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
       
        System.out.println(result.getResponse());
        String expected = "{id_membre:1, nom:Test}";
       
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
       
    }
	
}