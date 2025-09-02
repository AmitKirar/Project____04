package in.co.rays.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.rays.util.ServletUtility;

@WebFilter(urlPatterns = { "/doc/*", "/ctl/*" })
public class FrontController implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;

		HttpServletResponse response = (HttpServletResponse) resp;

		HttpSession session = request.getSession();
		String uri = request.getRequestURI();
		request.setAttribute("uri", uri);

		if (session.getAttribute("user") == null) {
			request.setAttribute("error", "Your session has been expired. Please Login again!");
			ServletUtility.forward(ORSView.LOGIN_VIEW, request, response);
			return;
		} else {
			chain.doFilter(request, response);

		}

	}

	@Override
	public void destroy() {

	}

}
