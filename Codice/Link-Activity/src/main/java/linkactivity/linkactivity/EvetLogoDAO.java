package linkactivity.linkactivity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EvetLogoDAO {

    public static EventModel getLogo(EventModel eventModel) throws IOException {
        String nomeaz= eventModel.getEventModelNomeAzienda();

        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Reliq\\Desktop\\ISPW\\1Progetto\\LinkActivity\\Codice\\Link-Activity\\src\\main\\CompanyLogo-Filesystem.txt"));

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
}
