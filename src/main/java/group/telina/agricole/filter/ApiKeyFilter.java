package group.telina.agricole.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ApiKeyFilter implements Filter {

    private static final String API_KEY = "agri-secure-key";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String key = httpRequest.getHeader("x-api-key");

        if (key == null || !key.equals(API_KEY)) {
            httpResponse.setStatus(401);
            httpResponse.setContentType("application/json");
            httpResponse.getWriter().write("{\"message\": \"Bad credentials\"}");
            return;
        }

        chain.doFilter(request, response);
    }
}