import Domains.AddressDomain.Address;
import Domains.AddressDomain.AddressRequestValidator;
import Domains.AddressDomain.WebServiceAddressRecord;
import Http.WebServiceClient;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final String END_APPLICATION_COMMAND = "sair";


    public static void main(String[] args) throws IOException, InterruptedException {

        try {
            System.out.println("Bem-vindo(a)!");

            String response = "";

            List<Address> addresses = new ArrayList<>();

            Gson jsonHandler = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .setPrettyPrinting()
                    .create();

            while (!response.equalsIgnoreCase(END_APPLICATION_COMMAND)) {
                Scanner reader = new Scanner(System.in);
                System.out.println("Digite o CEP que deseja buscar ou [sair] para encerrar a execução: ");
                String input = reader.nextLine();

                boolean isExitCommand = input.equalsIgnoreCase(END_APPLICATION_COMMAND);
                if (isExitCommand) {
                    response = END_APPLICATION_COMMAND;
                    continue;
                }

                if (!AddressRequestValidator.isValid(input)) {
                    System.out.println("O formato do CEP está inválido." +
                            " Revise os dados e tente novamente.");
                    continue;
                }

                WebServiceClient webServiceClient = new WebServiceClient();
                String webserviceJsonResponse = webServiceClient.sendRequest(input);

                WebServiceAddressRecord rawApiAddress = jsonHandler.fromJson(
                        webserviceJsonResponse,
                        WebServiceAddressRecord.class
                );

                Address address = new Address(rawApiAddress);
                addresses.add(address);
            }

            if (addresses.isEmpty()) {
                System.out.println("Nenhum endereço buscado para colocar no arquivo.");
                return;
            }

            FileWriter writer = new FileWriter("addresses.json");
            writer.write(jsonHandler.toJson(addresses));
            writer.close();

            System.out.println("Endereços resultantes escritos com sucesso!");
        } catch (JsonSyntaxException exception) {
            System.out.println("Não foi possível recuperar a resposta da API!" +
                    "\nVerifique os parâmetros e tente novamente");
        } catch (Exception exception) {
            System.out.println("Houve algum problema ao executar! \n"
                    + exception
            );
        }

        System.out.println("Encerrando execução.");

    }
}