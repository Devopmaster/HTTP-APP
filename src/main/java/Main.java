import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Scanner;

import static java.net.http.HttpResponse.BodyHandlers.ofString;


public class Main{
    private static final String HTTP_URL =
            "https://corona-virus-stats.herokuapp.com/api/v1/cases/general-stats";

    public static void main(String[] args) {
        Covid covid = getCovidStatisticObcjetfromJson();
        WelceomeSoutWithCommand();
        apllication(covid);

    }

    private static String getCovidStatisticAsJson() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        URI uri = URI.create(HTTP_URL);
        builder.uri(uri);
        builder.GET();
        HttpRequest request = builder.build();

        String result = "";
        try {
            result = client.send(request, ofString()).body();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.getMessage();
        }

        return result;
    }

    private static Covid getCovidStatisticObcjetfromJson() {
        Gson gson = new Gson();

        String result = getCovidStatisticAsJson();
        return  gson.fromJson(result, Covid.class);

    }

    private static void apllication(Covid covid) {


        System.out.println("Please enter command");
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        switch (command) {
            case "total":
                System.out.println(covid.getData().getTotalCases());
                break;
            case "recovery":
                System.out.println(covid.getData().getRecoveryCases());
                break;
            case "death":
                System.out.println(covid.getData().getDeathCases());
                break;
            case "cinfected":
                System.out.println(covid.getData().getCurrentlyInfected());
                break;
            case "mcondition":
                System.out.println(covid.getData().getMildConditionActiveCases());
                break;
            case "ccondition":
                System.out.println(covid.getData().getCriticalConditionActiveCases());
                break;
            case "recovered":
                System.out.println(covid.getData().getRecoveredClosedCases());
                break;
            case "all":
                System.out.println(covid.getData().toString());
                break;
            case "exit":
                System.exit(0);
                break;

        }
        apllication(covid);
    }

    private static void WelceomeSoutWithCommand() {
        System.out.println("Welcome, this is an application for purpose of follow current statistic about Coronavirus,\n" +
                " bellow you can find list of possible command to use for get statistic which are  you intrest. \n" +
                "COMMANDS:\n" +
                "total - total cases\n" +
                "recovery - recovery cases\n" +
                "death - death cases\n" +
                "cinfected - currently infected\n" +
                "coutcome - cases outcome\n" +
                "mcondition - mild condition active cases\n" +
                "ccondition - critical condition active cases\n" +
                "recovered - recovered_closed_cases\n" +
                "all - all data\n" +
                "exit - stop application");
    }


}
