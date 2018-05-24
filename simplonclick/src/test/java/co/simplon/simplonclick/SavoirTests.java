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

import co.simplon.simplonclick.controller.SavoirController;
import co.simplon.simplonclick.dao.SavoirDAO;
import co.simplon.simplonclick.model.Savoir;
import co.simplon.simplonclick.service.SavoirService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=SavoirController.class, secure=false)
public class SavoirTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private SavoirService savoirService;
	
	@MockBean
	private SavoirDAO savoirDAO;
	
	@Test
    public void getSavoirByIdTest() throws Exception {
		
		Savoir savoir = new Savoir();
		savoir.setId_savoir((long) 1);
		savoir.setNom_savoir("Test");;
        
        Mockito.when(savoirService.getSavoir((long) 1)).thenReturn(savoir);
       
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("http://localhost:8080/api/savoir/1").accept(MediaType.APPLICATION_JSON);
       
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
       
        System.out.println(result.getResponse());
        String expected = "{id_savoir:1, nom_savoir:Test}";
       
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
       
    }
	
}
