package com.example.helloworld;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Prateek on 23-Oct-15.
 */
public class FileRead {
    public static void main(String[] args) throws IOException {
        readData();

    }

    public static void readData() throws IOException {
        /*BufferedReader dataBR = new BufferedReader(new FileReader(new File("C:\\Users\\Prateek\\AndroidStudioProjects\\TheAnswer\\household_power_consumption\\household_power_consumption.txt")));
        String line = "";

        ArrayList<String[]> dataArr = new ArrayList<String[]>(); //An ArrayList is used because I don't know how many records are in the file.

        while ((line = dataBR.readLine()) != null) { // Read a single line from the file until there are no more lines to read

            String[] club = new String[9]; // Each club has 3 fields, so we need room for the 3 tokens.

            for (int i = 0; i < 9; i++) { // For each token in the line that we've read:
                String[] value = line.split();
                club[i] = value[i]; // Place the token into the 'i'th "column"
            }

            dataArr.add(club); // Add the "club" info to the list of clubs.
        }

        for (int i = 0; i < dataArr.size(); i++) {
            for (int x = 0; x < dataArr.get(i).length; x++) {
                System.out.printf("dataArr[%d][%d]: ", i, x);
                System.out.println(dataArr.get(i)[x]);
            }
        }*/
        //*{
        // -define .csv file in app
        String fileNameDefined = "C:\\Users\\Prateek\\AndroidStudioProjects\\TheAnswer\\household_power_consumption\\household_power_consumption.txt";
        // -File class needed to turn stringName to actual file
        File file = new File(fileNameDefined);
        int ctr = 0;
        int i=0,j=0;
        try {
            // -read from filePooped with Scanner class
            Scanner inputStream = new Scanner(file);
            String dataArr[][] = new String[600000][9];
            // hashNext() loops line-by-line
            while (inputStream.hasNext()) {
                //read single line, put in string
                String data = inputStream.next();
                // For each token in the line that we've read:
                i++;j++;
                if(j>=9)
                    j=j%9;
                dataArr[i][j]=data;
            }
            for ( i = 0; i < 10; i++) {
                for ( j = 0; j < 9; j++)
                    System.out.print(dataArr);
                System.out.println();
            }

            // after loop, close scanner
            inputStream.close();


        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

    }//*/
}

