package none.exceptions;

import com.auth0.jwt.exceptions.TokenExpiredException;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.nio.file.AccessDeniedException;
import javax.persistence.NoResultException;
import none.service.dto.HttpResponseDTO;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.util.ErrorHandler;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ExceptionHandling implements ErrorController {

    //private final Logger logger = LoggerFactory.getLogger( getClass() );

    private static final String ACCOUNT_LOCKED = "Your account has been locked. Please contact administration";
    private static final String METHOD_IS_NOT_ALLOWED = "This request method is not allowed on this endpoint. Please send a request";
    private static final String INTERNAL_SERVER_ERROR_MSG = "An error occured while processing the request";
    private static final String INCORRECT_CREDENTIALS = "Username / password incorrect. Please try again";
    private static final String ACCOUNT_DISABLED = "Your account has been disabled. If this is an error, please contact administration";
    private static final String ERROR_PROCESSING_FILE = "Error occured while processing file";
    private static final String NOT_ENOUGH_PERMISSION = "You do not have enough permission";
    private static final String ERROR_PATH = "/error";

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<HttpResponseDTO> accountDisabledException() {
        return createHttpResponse(HttpStatus.BAD_REQUEST, ACCOUNT_DISABLED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<HttpResponseDTO> accessDeniedException() {
        return createHttpResponse(HttpStatus.FORBIDDEN, NOT_ENOUGH_PERMISSION);
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<HttpResponseDTO> lockedException() {
        return createHttpResponse(HttpStatus.UNAUTHORIZED, ACCOUNT_LOCKED);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<HttpResponseDTO> tokenExpiredException(TokenExpiredException exception) {
        return createHttpResponse(HttpStatus.UNAUTHORIZED, exception.getMessage().toUpperCase());
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<HttpResponseDTO> emailExistException(EmailAlreadyExistException exception) {
        return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<HttpResponseDTO> badCredentialsException() {
        return createHttpResponse(HttpStatus.BAD_REQUEST, INCORRECT_CREDENTIALS);
    }

    @ExceptionHandler(UsernameAlreadyExistException.class)
    public ResponseEntity<HttpResponseDTO> usernameAlreadyExistException(UsernameAlreadyExistException exception) {
        return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<HttpResponseDTO> emailExistException(EmailNotFoundException exception) {
        return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<HttpResponseDTO> usernameNotFoundException(UsernameNotFoundException exception) {
        return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<HttpResponseDTO> methodSupportedException(HttpRequestMethodNotSupportedException exception) {
        return createHttpResponse(HttpStatus.METHOD_NOT_ALLOWED, METHOD_IS_NOT_ALLOWED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpResponseDTO> internalServerErrorException(Exception exception) {
        return createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG);
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<HttpResponseDTO> notFoundException(NoResultException exception) {
        return createHttpResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<HttpResponseDTO> iOException(IOException exception) {
        return createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_PROCESSING_FILE);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<HttpResponseDTO> iOException(NoHandlerFoundException exception) {
        return createHttpResponse(HttpStatus.BAD_REQUEST, "This page was not found");
    }

    @RequestMapping(ERROR_PATH)
    public ResponseEntity<HttpResponseDTO> notFound404() {
        return createHttpResponse(HttpStatus.NOT_FOUND, "There is no mapping for this URL");
    }

    protected ResponseEntity<HttpResponseDTO> createHttpResponse(HttpStatus httpStatus, String message) {
        return new ResponseEntity<HttpResponseDTO>(
            new HttpResponseDTO(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase(), message),
            httpStatus
        );
    }
}
