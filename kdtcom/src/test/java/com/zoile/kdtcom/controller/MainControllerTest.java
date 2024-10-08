package com.zoile.kdtcom.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.zoile.kdtcom.controller.service.ClientIpAddress;
import com.zoile.kdtcom.controller.service.FileUploadService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
public class MainControllerTest {

	private MockMvc mockMvc;
	
	@Mock
	private FileUploadService fileUploadService;
	
	@Mock
	private ClientIpAddress clientIpAddress;
	
	@InjectMocks
	private MainController mainController;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
	}
	
	@Test
	public void testRegisterPost() throws Exception {
		//Mocking 의존성 객체 셋팅
		when(clientIpAddress.getClientIpAddress()).thenReturn("127.0.0.1");
		mockMvc.perform(post("/register")
				.param("userid", "testUser")
				.param("userpass", "testPass")
				.param("username", "testName")
				.param("useremail", "oqwo@oqwo.com")
				.param("usertel", "010-1234-1245")
				.param("postcode", "12345")
				.param("address", "경기도 구")
				.param("address_detail", "동")
				.param("extra_address", "집")
				.param("userprofile", "하이"))
				 .andExpect(status().isOk()) //상태가 200으로 떨어지면 성공
				 .andExpect(view().name("kdtcom.index")) //뷰이름
				 .andExpect(model().attributeDoesNotExist("error")); // 에러 메세지
	}
}
