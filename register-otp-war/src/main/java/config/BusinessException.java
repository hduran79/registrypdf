package config;

import javax.ejb.ApplicationException;

@ApplicationException
public class BusinessException extends RuntimeException{

	private static final long serialVersionUID = 1L;

    /**
     * Código del error de la excepción de negocio.
     */
    private final String code;
    private final String[] parameters;

    /**
     * Crea un nuevo error de negocio.
     * 
     * @param code Código de la excepción de negocio.
     * @param message Mensaje técnico de la excepción.
     * @param parameter envio de parametros para el mensaje.
     * @param throwable Error a encapsular.
     */
    public BusinessException(String code, String[] parameter, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
        this.parameters = parameter;
    }

    /**
     * Crea un nuevo error de negocio con un código dado.
     * 
     * @param code Código del error de negocio.
     * @param throwable Error a encapsular.
     */
    public BusinessException(String code, Throwable throwable) {
        this(code, null, null, throwable);
    }

    /**
     * Crea un nuevo error de negocio con un código dado.
     * 
     * @param code Código del error de negocio.
     * @param parameter envio de parametros para el mensaje
     * 
     */
    public BusinessException(String code, String[] parameter) {
        this(code, parameter, null, null);
    }

    /**
     * Crea un nuevo error de negocio con un código dado
     * @param code Código del error de negocio.
     */
    public BusinessException(String code) {
        this(code, null, null, null);
    }

    /**
     * Obtiene el código del error de negocio.
     * 
     * @return Código del error de negocio.
     */
    public String getCode() {
        return code;
    }

    /**
     * funcion que retorna lo parametros que se van a generar en el mensaje.
     * @return Parametros que se van a generar en el mensaje de negocio.
     */
    public String[] getParameters() {
        return parameters;
    }

    /**
     * Valida que una expresión de negocio se cumpla.
     * 
     * @param expresion Regla de negocio a cumplir.
     * @param code Código de la excepción de negocio.
     * @param parameter Parámetros para el mensaje de negocio.
     */
    public static void check(boolean expresion, String code, String... parameter) {
        if(!expresion) {
            throw new BusinessException(code, parameter);
        }
    }

}
