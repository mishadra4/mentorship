package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

import static constant.MentorshipConstants.ALL_URI;

@WebFilter(ALL_URI)
public class RequestEncodingFilter implements Filter {

    private static final String ENCODING = "UTF-8";
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    public void init(FilterConfig filterConfig) {
    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(ENCODING);

        response.setContentType(CONTENT_TYPE);

        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}
