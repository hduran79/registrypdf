package util;

/**
 * Se utiliza para crear funciones genericas que utilizan en la aplicacion
 * 
 * @author POL TEAM
 */
public final class Util {

    private Util() {
    }

    /**
     * Expande una plantilla cuyos nombres de variables son el indice prefijado con "#" dentro 
     * el arreglo, ej: la plantilla "Hola #1, desde #2" con los parámetros ["mundo", "Java"] 
     * es expandido a "Hola mundo, desde Java".
     * 
     * @param message Plantilla a expandir
     * @param parameter Parámetros de la plantilla
     * @return Plantilla expandida.
     */
    public static String replaceString(String message, String[] parameter) {

        if (parameter == null) {
            return message;
        }
        String resultString = message;

        for (int x = 0; x < parameter.length; x++) {
            String flag = "#" + x;
            resultString = resultString.replace(flag, parameter[x]);
        }

        return resultString;

    }
}
