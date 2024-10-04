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
		//Mocking ������ ��ü ����
		when(clientIpAddress.getClientIpAddress()).thenReturn("127.0.0.1");
		mockMvc.perform(post("/register")
				.param("userid", "testUser")
				.param("userpass", "testPass")
				.param("username", "testName")
				.param("useremail", "oqwo@oqwo.com")
				.param("usertel", "010-1234-1245")
				.param("postcode", "12345")
				.param("address", "��⵵ ��")
				.param("address_detail", "��")
				.param("extra_address", "��")
				.param("userprofile", "����"))
				 .andExpect(status().isOk()) //���°� 200���� �������� ����
				 .andExpect(view().name("kdtcom.index")) //���̸�
				 .andExpect(model().attributeDoesNotExist("error")); // ���� �޼���
	}
}