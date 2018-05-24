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

import co.simplon.simplonclick.controller.RessourceController;
import co.simplon.simplonclick.dao.RessourceDAO;
import co.simplon.simplonclick.model.Ressource;
import co.simplon.simplonclick.service.RessourceService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=RessourceController.class, secure=false)
public class RessourceTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private RessourceService ressourceService;
	
	@MockBean
	private RessourceDAO ressourceDAO;
	
	@Test
    public void getMembreByIdTest() throws Exception {
		
		Ressource ressource = new Ressource();
		ressource.setId_ressource((long) 1);
        ressource.setNom_ressource("Test");;
        
        Mockito.when(ressourceService.getRessource((long) 1)).thenReturn(ressource);
       
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("http://localhost:8080/api/ressource/1").accept(MediaType.APPLICATION_JSON);
       
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
       
        System.out.println(result.getResponse());
        String expected = "{id_ressource:1, nom_ressource:Test}";
       
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
       
    }
	
}
