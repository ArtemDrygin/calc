package calculator.service;

public interface Converter {
    Integer convert(String number);
    String convert(Integer number);
    boolean isValid(String number);
    boolean isValid(Integer number);
}
