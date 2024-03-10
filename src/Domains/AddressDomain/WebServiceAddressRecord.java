package Domains.AddressDomain;

public record WebServiceAddressRecord(
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf,
        Integer ddd
) { }
