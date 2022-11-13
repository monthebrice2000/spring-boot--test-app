package none.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import none.constants.SecurityConstant;
import none.service.dto.HttpResponseDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint extends Http403ForbiddenEntryPoint {

    private static final Log logger = LogFactory.getLog(JwtAuthenticationEntryPoint.class);

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        logger.debug("Pre-authenticated entry point called. Rejecting access");

        HttpResponseDTO httpResponseDTO = new HttpResponseDTO(
            403,
            HttpStatus.FORBIDDEN,
            SecurityConstant.FORBIDDEN_MESSAGE,
            SecurityConstant.FORBIDDEN_MESSAGE
        );
        response.setContentType("application/json");
        response.setStatus(403);
        OutputStream outputStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(outputStream, httpResponseDTO);
        outputStream.flush();
    }
}
