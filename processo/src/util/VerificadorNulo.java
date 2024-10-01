package util;


public class VerificadorNulo {

    public static <T> boolean isNull(T valor) {
    	/*
    	 * Essa função inicialmente inicialmente gerava um erro com a mensagem dizendo com campo estava vazio
    	 * Como eu já tinha feito para aprender o polimorfismo paramétrico , deixei mesmo sabendo que agora ela não era estritamente necessária
    	 * Pois agora ela só diz se algo é vazio  ou não ;
    	 * */
        if (valor == null) {
            return true;  
        }

        
        if (valor instanceof String && ((String) valor).trim().isEmpty()) {
            return true;  
        }

        return false;  
    }
}

