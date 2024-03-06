package service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class Csv {

    private String task;

    public ArrayList<ArrayList<String>> CsvToString (String inputFile){

        ArrayList<ArrayList<String>> queryArray = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(inputFile))) {

            ArrayList<String> partQueryArray = new ArrayList<>();

            StringBuilder scoutput = new StringBuilder();
            String[] nextLine;
            int i = 1;
            while ((nextLine = reader.readNext()) != null) {
                System.out.println(this.task);

                if (this.task.equals("filter")) {

                    if (i % 900 == 0) {
                        queryArray.add(partQueryArray);
                        partQueryArray = new ArrayList<>();
                    }

                    scoutput.append("'");
                    scoutput.append(nextLine[0].toLowerCase(Locale.ROOT));
                    scoutput.append("' ");
                    partQueryArray.add(scoutput.toString());
                    scoutput = new StringBuilder();

                    i++;

                }

                if(this.task.equals("insert")){
                    partQueryArray = new ArrayList<>();
                    for(String cell : nextLine){
                        scoutput.append("'")
                                .append(cell)
                                .append("'");
                        partQueryArray.add(scoutput.toString());
                        scoutput = new StringBuilder();
                    }

                    queryArray.add(partQueryArray);

                    scoutput = new StringBuilder();
                }
            }

            queryArray.add(partQueryArray);
            System.out.println(queryArray);




        } catch (IOException | CsvValidationException e) {
            System.out.println(e.getMessage());
        }
        return queryArray;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public  String getTask() {
        return task;
    }
}
