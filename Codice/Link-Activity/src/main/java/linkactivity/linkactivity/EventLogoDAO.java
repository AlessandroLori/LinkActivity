package linkactivity.linkactivity;

import java.io.*;

public class EventLogoDAO {

    public static EventModel getLogo(EventModel eventModel) throws IOException {
        String nomeaz= eventModel.getEventModelNomeAzienda();
        File file= new File("C:\\Users\\micci\\Desktop\\LinkActivityDEMO\\Codice\\Link-Activity\\src\\main\\CompanyLogo-Filesystem.txt");

        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line;
        boolean foundCompany = false;
        String companyString = "";
        StringBuilder sb = new StringBuilder();

        // Lettura del file riga per riga
        while ((line = reader.readLine()) != null) {
            // Se la riga contiene "IBM", copia la stringa successiva
            if (!foundCompany && line.contains(nomeaz)) {
                companyString = line.substring(line.indexOf(nomeaz) + nomeaz.length()+1).trim();
                foundCompany = true;
            } else if (foundCompany) {
                // Scandisce la stringa per cercare il carattere "\" e aggiunge un secondo "\"

                for (char h : companyString.toCharArray()) {
                    sb.append(h);
                    if (h == '\\') {
                        sb.append('\\');
                    }
                }
                break;
            }
        }
        reader.close();

        eventModel= new EventModel(String.valueOf(sb)){};
        return eventModel;
    }

    public void insertLogo(String imagePath, CompanyBean companyName) {
        String content= companyName.getNomeAzienda() +" " + imagePath;
        try (FileWriter fileWriter = new FileWriter("C:\\Users\\micci\\Desktop\\LinkActivityDEMO\\Codice\\Link-Activity\\src\\main\\CompanyLogo-Filesystem.txt", true)) {
            fileWriter.write(content);
            fileWriter.write(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}