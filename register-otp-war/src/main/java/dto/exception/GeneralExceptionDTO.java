package dto.exception;

import java.io.Serializable;

/**
 * Clase encapsuladora de una excepción no controlada en servicios REST.
 * 
 * @author POL TEAM
 */
public class GeneralExceptionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String type;
    private String message;

    public GeneralExceptionDTO() {
        /* constructor vacío para temas de serialización y deserialización con GSON */
    }

    public GeneralExceptionDTO(String type, String message) {
        super();
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
