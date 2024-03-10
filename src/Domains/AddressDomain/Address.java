package Domains.AddressDomain;

/**
 * "Entidade" de um endereço
 * Usando atributos em inglês para exemplificar
 * o consumo e uso do Record
 */
public class Address {

    String cep;
    String street;
    String additionalAddressDetails;
    String neighborhood;
    String city;
    String state;
    Integer areaCode;

    public Address(WebServiceAddressRecord addressRecord) {
        this.cep = addressRecord.cep();
        this.street = addressRecord.logradouro();
        this.additionalAddressDetails = addressRecord.complemento();
        this.neighborhood = addressRecord.bairro();
        this.city = addressRecord.localidade();
        this.state = addressRecord.uf();
        this.areaCode = addressRecord.ddd();
    }

}
