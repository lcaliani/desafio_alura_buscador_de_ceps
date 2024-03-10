package Domains.AddressDomain;

public class AddressRequestValidator {

    /**
     * Validação simples para garantir que somente
     * seja informados números e letra
     * @param cep
     * @return
     */
    public static boolean isValid(String cep) {
        boolean hasValidHyphenCount = cep.chars()
                .filter(character -> character == '-')
                .count() <= 1;
        boolean hasValidLenght = cep.length() == 8 ||
                (cep.length() == 9 && cep.contains("-"));
        boolean isCepFormat = cep.matches("^[\\-0-9]*$");

        return hasValidLenght && hasValidHyphenCount && isCepFormat ;
    }
}
