package none.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import none.constants.SecurityConstant;
import none.service.dto.HttpResponseDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.stereotype.Component;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private static final Log logger = LogFactory.getLog(JwtAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
        throws IOException, ServletException {
        logger.debug("Access Denied");

        HttpResponseDTO httpResponseDTO = new HttpResponseDTO(
            401,
            HttpStatus.UNAUTHORIZED,
            SecurityConstant.ACCESS_DENIED_MESSAGE,
            SecurityConstant.ACCESS_DENIED_MESSAGE
        );
        response.setContentType("application/json");
        response.setStatus(401);
        OutputStream outputStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(outputStream, httpResponseDTO);
        outputStream.flush();
    }
}
