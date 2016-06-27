package br.projetojpa.exceptions;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class JPAException extends Exception{
    private static final long serialVersionUID = 1L;

    public JPAException() {
    }

    public JPAException(String message) {
        super(message);
    }

    public JPAException(Throwable cause) {
        super(cause);
    }

    public JPAException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage(); 
    }

    @Override
    public synchronized Throwable getCause() {
        return super.getCause(); 
    }
    
    
    
}
