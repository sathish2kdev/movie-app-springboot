package com.app.MovieApp.security.common.config.viewresolver;

/**
 * @author Nandhu
 */
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public class JsonViewResolver implements ViewResolver {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public View resolveViewName(String viewName, Locale locale) throws Exception {

		logger.info("viewname - " + viewName);

		MappingJackson2JsonView view = new MappingJackson2JsonView();
		view.setPrettyPrint(true);
		return view;
	}
}