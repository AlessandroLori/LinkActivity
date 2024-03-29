package linkactivity.linkactivity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CouponPointsDAO {

    private static final File file= new File("src/main/CompanyCoupon-Filesystem.txt");
    private static final String IO_MESSAGE= "IOException error";
    public static void addPoints(CompanyModel company, String todo, int quantity) throws IOException, IOExceptionHandler {
        String nomeaz = company.getCompanyNomeaz();

        FileWriter writer = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file));){

            String line;
            StringBuilder result = new StringBuilder();
            int updatedNumber;

            while ((line = reader.readLine()) != null) {
                if (line.contains(nomeaz)) {
                    int index = line.indexOf("pts=");
                    if (index >= 0) {
                        int startIndex = index + 4;
                        int endIndex = line.indexOf(' ', startIndex);
                        if (endIndex < 0) {
                            endIndex = line.length();
                        }
                        String numberString = line.substring(startIndex, endIndex);
                        int number = Integer.parseInt(numberString);

                        updatedNumber = ausfunction(todo, number, quantity);

                        String updatedLine = line.substring(0, startIndex) + updatedNumber + line.substring(endIndex);
                        result.append(updatedLine);
                    } else {
                        result.append(line);
                    }
                } else {
                    result.append(line);
                }
                result.append("\n");
            }
            writer = new FileWriter(file);
            writer.write(result.toString());

        } catch (IOException e) {
            throw new IOExceptionHandler(IO_MESSAGE);
        } finally {
            assert writer != null;
            writer.close();
        }
    }

    private static int ausfunction(String todo, int number, int quantity){
        int updatedNumber;
        if(todo.equals("add")){
            updatedNumber = number + 100;
        } else if(todo.equals("")){
            updatedNumber= number-quantity;
        } else{
            updatedNumber= number-100;
        }
        return updatedNumber;
    }

    public static CouponModel getCurrentPoints(CompanyModel company) throws IOExceptionHandler, IOException {
        String nomeaz = company.getCompanyNomeaz();
        int points = 0;

        try(BufferedReader reader = new BufferedReader(new FileReader(file));) {

            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains(nomeaz)) {
                    int index = line.indexOf("pts=");
                    if (index >= 0) {
                        int startIndex = index + 4;
                        int endIndex = line.indexOf(' ', startIndex);
                        if (endIndex < 0) {
                            endIndex = line.length();
                        }
                        String numberString = line.substring(startIndex, endIndex);
                        points = Integer.parseInt(numberString);
                        break;
                    }
                }
            }

        } catch (IOException e) {
            throw new IOExceptionHandler(IO_MESSAGE);
        }

        return new CouponModel(points) {
            @Override
            public Double getPrice() {
                return null;
            }
        };
    }

    public static void redeemCoupon(CompanyModel company, int points) throws IOExceptionHandler, IOException {
        String nomeaz = company.getCompanyNomeaz();

        String s;
        if (points == 300) {
            s = "cp1=";
        } else if (points == 550) {
            s = "cp2=";
        } else {
            s = "cp3=";
        }

        BufferedWriter writer = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file));){

            String line;
            StringBuilder updatedContent = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                if (line.contains(nomeaz)) {
                    int index = line.indexOf(s);
                    if (index >= 0) {
                        int startIndex = index + 4;
                        int endIndex = line.indexOf(' ', startIndex);
                        if (endIndex < 0) {
                            endIndex = line.length();
                        }
                        String numberString = line.substring(startIndex, endIndex);
                        int number = Integer.parseInt(numberString);
                        number += 1;
                        line = line.substring(0, startIndex) + number + line.substring(endIndex);
                    }
                }
                updatedContent.append(line).append("\n");
            }
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(updatedContent.toString());

        } catch (IOException e) {
            throw new IOExceptionHandler(IO_MESSAGE);
        } finally {
            assert writer != null;
            writer.close();
        }
    }

    public static List<CouponModel> getAvailableCoupons(CompanyModel company) throws IOExceptionHandler, IOException {
        String nomeaz = company.getCompanyNomeaz();
        List<Integer> coupList = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(file));
        ) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains(nomeaz)) {
                    List<Integer> index = new ArrayList<>();
                    index.add(line.indexOf("cp1="));
                    index.add(line.indexOf("cp2="));
                    index.add(line.indexOf("cp3="));

                    for (int i = 0; i <= 2; i++) {
                        if (index.get(i) >= 0) {
                            int startIndex = index.get(i) + 4;
                            int endIndex = line.indexOf(' ', startIndex);

                            endIndex = endindex(endIndex, line);

                            String numberString = line.substring(startIndex, endIndex);
                            int number = Integer.parseInt(numberString);
                            coupList.add(number);
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new IOExceptionHandler(IO_MESSAGE);
        }
        List<CouponModel> couponModels = new ArrayList<>();
        while (3 > couponModels.size()) {
            CouponModel couponModel = new CouponModel(coupList.get(0), "") {
                @Override
                public Double getPrice() {
                    return null;
                }
            };
            couponModels.add(couponModel);
            coupList.remove(0);
        }
        return couponModels;
    }

    private static int endindex(int endIndex, String line){
        if (endIndex < 0) {
            endIndex = line.length();
        }
        return endIndex;
    }


    public static void removeCoupons(List<CouponModel> couponModels, CompanyModel y) throws IOExceptionHandler, IOException {
        String nomeaz = y.getCompanyNomeaz();
        List<Integer> numbers = new ArrayList<>(List.of(0, 0, 0)); // Esempio di lista di interi
        int i = 0;
        int j;

        while (i < couponModels.size()) {
            if (couponModels.get(i).getCouponDiscount().equals(5.0)) {
                j = numbers.get(0);
                j = j + 1;
                numbers.set(0, j);
            } else if (couponModels.get(i).getCouponDiscount().equals(10.0)) {
                j = numbers.get(1);
                j = j + 1;
                numbers.set(1, j);
            } else {
                j = numbers.get(2);
                j = j + 1;
                numbers.set(2, j);
            }
            i++;
        }
        File file2 = new File(String.valueOf(file));
        FileWriter writer = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file2));){

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains(nomeaz)) {
                    String[] tokens = line.split(" ");

                    tokensaid(tokens, numbers);

                    line = String.join(" ", tokens);
                }

                sb.append(line).append(System.lineSeparator());
            }
            writer = new FileWriter(file2);
            writer.write(sb.toString());
        } catch (IOException e) {
            throw new IOExceptionHandler(IO_MESSAGE);
        } finally {
            assert writer != null;
            writer.close();
        }
    }

    private static void tokensaid(String[] tokens, List<Integer> numbers){
        for (int g = 0; g < tokens.length; g++) {
            if (tokens[g].startsWith("cp1=")) {
                int number = Integer.parseInt(tokens[g].substring(4));
                number -= numbers.get(0);
                tokens[g] = "cp1=" + number;
            } else if (tokens[g].startsWith("cp2=")) {
                int number = Integer.parseInt(tokens[g].substring(4));
                number -= numbers.get(1);
                tokens[g] = "cp2=" + number;
            } else if (tokens[g].startsWith("cp3=")) {
                int number = Integer.parseInt(tokens[g].substring(4));
                number -= numbers.get(2);
                tokens[g] = "cp3=" + number;
            }
        }
    }

    public void insertNewAzienda(String nomeAzienda) throws IOExceptionHandler {
        String content= nomeAzienda +": pts=0 - cp1=0 - cp2=0 - cp3=0";
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.write(content);
            fileWriter.write(System.lineSeparator());
        } catch (IOException e) {
            throw new IOExceptionHandler(IO_MESSAGE);
        }
    }
}