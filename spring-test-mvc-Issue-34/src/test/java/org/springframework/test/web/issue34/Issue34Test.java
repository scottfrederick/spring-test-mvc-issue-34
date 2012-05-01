package org.springframework.test.web.issue34;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.web.issue34.context.WebContextLoader;
import org.springframework.test.web.issue34.domain.SomeBean;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		loader=WebContextLoader.class,
		locations={"classpath:spring/servlet-context.xml","classpath:spring/spring-test-infrastructure.xml"})
@Transactional
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class Issue34Test {
	
	private MockMvc mockMvc;	
    private ObjectMapper mapper;
    
	@Autowired
	private WebApplicationContext wac;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webApplicationContextSetup(this.wac).build();
        this.mapper = new ObjectMapper();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testMain() throws Exception {
		// create article
		SomeBean someBean = new SomeBean();
		someBean.setId(99L);
		someBean.setName("Test");
		String articleJson = this.mapper.writeValueAsString(someBean);
		byte[] body = articleJson.getBytes("UTF-8");
		this.mockMvc.perform(
				post("/issue34/management/insert.action")
				.body(body)
				.contentType(MediaType.valueOf("application/json;charset=UTF-8")))
			.andExpect(status().isOk())
			.andExpect(content().type(MediaType.valueOf("application/json;charset=UTF-8")));
	}

}
