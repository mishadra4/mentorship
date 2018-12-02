package filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.time.LocalDateTime;

import static constant.MentorshipConstants.ALL_URI;

@WebFilter(ALL_URI)
public class LoggingFilter implements Filter {
    Logger LOG = Logger.getLogger(LoggingFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOG.info("The request was handled at " + LocalDateTime.now() + " by awesome filter ;)");
        LOG.info("Request character encoding = *" + servletRequest.getCharacterEncoding() + "*");
        LOG.info("Response character encoding = *" + servletResponse.getCharacterEncoding() + "*");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
