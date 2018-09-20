package ru.itis.reflex.exceptions;

@SuppressWarnings("serial")
public class CompanyExistsException extends Throwable {

    public CompanyExistsException(final String message) {
        super(message);
    }

}
