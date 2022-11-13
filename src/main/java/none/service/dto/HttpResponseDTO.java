package none.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.http.HttpStatus;

public class HttpResponseDTO {

    private int httpStatusCode;
    private HttpStatus httStatus;
    private String reason;
    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss")
    private Date date;

    public HttpResponseDTO(int httpStatusCode, HttpStatus httStatus, String reason, String message) {
        this.httpStatusCode = httpStatusCode;
        this.httStatus = httStatus;
        this.reason = reason;
        this.message = message;
        this.date = new Date();
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public HttpStatus getHttStatus() {
        return httStatus;
    }

    public void setHttStatus(HttpStatus httStatus) {
        this.httStatus = httStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return (
            "HttpResponseDTO{" +
            "httpStatusCode=" +
            httpStatusCode +
            ", httStatus=" +
            httStatus +
            ", reason='" +
            reason +
            '\'' +
            ", message='" +
            message +
            '\'' +
            '}'
        );
    }
}
