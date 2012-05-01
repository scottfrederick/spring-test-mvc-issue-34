package org.springframework.test.web.issue34;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.test.web.issue34.domain.SomeBean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/issue34")
public class Issue34 {

    public Issue34() {
    }
    
	@Transactional
	@RequestMapping(value = "/management/insert", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, ? extends Object> doInsert(@RequestBody final SomeBean someBean,
			final HttpServletRequest request) {

		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			// do something here
			//getRepository().addNew(someBean);
		} catch (Exception e) {
			String localizedMessage = "error";
			responseMap.put("error", new String[] { localizedMessage });
		}
		return responseMap;
	}
}
