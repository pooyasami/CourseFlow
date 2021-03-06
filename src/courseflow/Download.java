/*
 * Download.java
 *
 * Copyright(c) 2011 Pooya Samizadeh-Yazd and Yuan Zhou All Rights Reserved.
 *
 */
package courseflow;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import org.pdfbox.cos.COSDocument;
import org.pdfbox.pdfparser.PDFParser;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;

/**
 * This class is for starting the downloads and also converting the PDF file to text.
 * @author Daphne Z & Pooya Samizadeh
 */
public class Download {

    private URL urlESEv1 = null;
    private URL urlEVSEv1 = null;
    private URL urlISEv1 = null;
    private URL urlPSEv1 = null;
    private URL urlSSEv1 = null;
    private URL urlESEv2 = null;
    private URL urlEVSEv2 = null;
    private URL urlISEv2 = null;
    private URL urlPSEv2 = null;
    private URL urlSSEv2 = null;
    private URL urlCatelogv1 = null;
    private URL urlCatelogv2 = null;

    /**
     * The initializer function that loads all the URLs
     * @throws MalformedURLException
     * @throws IOException
     */
    public Download() throws MalformedURLException, IOException {
        urlESEv1 = new URL("http://www.uregina.ca/gencal/ugcal201011/facultyofEngineering/ugcal_285.shtml");
        urlEVSEv1 = new URL("http://www.uregina.ca/gencal/ugcal201011/facultyofEngineering/ugcal_285.shtml#bascEVSE");
        urlISEv1 = new URL("http://www.uregina.ca/gencal/ugcal201011/facultyofEngineering/ugcal_286.shtml");
        urlPSEv1 = new URL("http://www.uregina.ca/gencal/ugcal201011/facultyofEngineering/ugcal_286.shtml#bascPSE");
        urlSSEv1 = new URL("http://www.uregina.ca/gencal/ugcal201011/facultyofEngineering/ugcal_287.shtml");

        urlESEv2 = new URL("http://www.uregina.ca/gencal/ugcal200910/facultyofEngineering/ugcal_285.shtml");
        urlEVSEv2 = new URL("http://www.uregina.ca/gencal/ugcal200910/facultyofEngineering/ugcal_285.shtml#bascEVSE");
        urlISEv2 = new URL("http://www.uregina.ca/gencal/ugcal200910/facultyofEngineering/ugcal_286.shtml");
        urlPSEv2 = new URL("http://www.uregina.ca/gencal/ugcal200910/facultyofEngineering/ugcal_286.shtml#bascPSE");
        urlSSEv2 = new URL("http://www.uregina.ca/gencal/ugcal200910/facultyofEngineering/ugcal_287.shtml");

        urlCatelogv1 = new URL("http://www.uregina.ca/gencal/catalog/1011UGCourseCatalog.pdf");
        urlCatelogv2 = new URL("http://www.uregina.ca/gencal/catalog/0910UGCourseCatalog.pdf");

        boolean makeDirecotry = new File("Downloads").mkdir();
    }

    /**
     * It calls the download functions
     * @return true if it is all done
     * @throws IOException
     */
    public boolean convert() throws IOException {

        /*
         * =========================  Version 1 Converting Start ==========================================
         */
        if (isInternetReachable()) {
            System.out.println("Course Catalogue and Course Calendar are being downloaded. Please wait...");
            this.convertESEv1URL();
            this.convertEVSEv1URL();
            this.convertISEv1URL();
            this.convertPSEv1URL();
            this.convertSSEv1URL();

            this.convertCatelogv1URL();
            this.convertPDFtoText("Downloads/Catelogv1.pdf", "Downloads/1011UGCourseCatalog.txt");
            /*
             * =========================  Version 2 Converting Start ==========================================
             */
            this.convertESEv2URL();
            this.convertEVSEv2URL();
            this.convertISEv2URL();
            this.convertPSEv2URL();
            this.convertSSEv2URL();

            this.convertCatelogv2URL();
            this.convertPDFtoText("Downloads/Catelogv2.pdf", "Downloads/0910UGCourseCatalog.txt");
            return true;
        } else {
            System.out.println("There is no internet connection. Please connect to internet and try again.");
            return false;
        }

    }

    /**
     * taken from http://stackoverflow.com/questions/1139547/detect-internet-connection-using-java and is for
     * checking if there is an Internet connection
     * @return true if there is an Internet connection
     */
    public boolean isInternetReachable() {
        try {
            //make a URL to a known source
            URL url = new URL("http://www.uregina.ca/");

            //open a connection to that source
            HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();

            //trying to retrieve data from the source. If there
            //is no connection, this line will fail
            Object objData = urlConnect.getContent();

        } catch (UnknownHostException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * It downloads the Course Catalog Ver. 1
     * @throws IOException
     */
    private void convertCatelogv1URL() throws IOException {

        java.io.BufferedInputStream in = new java.io.BufferedInputStream(urlCatelogv1.openStream());
        java.io.FileOutputStream fos = new java.io.FileOutputStream("Downloads/Catelogv1.pdf");
        java.io.BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
        byte[] data = new byte[1024];
        int x = 0;
        while ((x = in.read(data, 0, 1024)) >= 0) {
            bout.write(data, 0, x);
        }
        bout.close();
        in.close();
    }

    /**
     * It downloads ESE calendar page
     * @throws IOException
     */
    private void convertESEv1URL() throws IOException {

        BufferedReader rESEv1 = new BufferedReader(new InputStreamReader(urlESEv1.openStream()));
        BufferedWriter wESEv1 = new BufferedWriter(new FileWriter("Downloads/esev1.htm"));

        String line;
        while ((line = rESEv1.readLine()) != null) {
            wESEv1.write(line);
            wESEv1.newLine();
        }
        rESEv1.close();
        wESEv1.close();

    }

    /**
     * It downloads EVSE calendar page
     * @throws IOException
     */
    private void convertEVSEv1URL() throws IOException {

        BufferedReader rEVSEv1 = new BufferedReader(new InputStreamReader(urlEVSEv1.openStream()));
        BufferedWriter wEVSEv1 = new BufferedWriter(new FileWriter("Downloads/evsev1.htm"));

        String line;
        while ((line = rEVSEv1.readLine()) != null) {
            wEVSEv1.write(line);
            wEVSEv1.newLine();
        }
        rEVSEv1.close();
        wEVSEv1.close();

    }

    /**
     * It downloads ISE calendar page
     * @throws IOException
     */
    private void convertISEv1URL() throws IOException {

        BufferedReader rISEv1 = new BufferedReader(new InputStreamReader(urlISEv1.openStream()));
        BufferedWriter wISEv1 = new BufferedWriter(new FileWriter("Downloads/isev1.htm"));

        String line;
        while ((line = rISEv1.readLine()) != null) {
            wISEv1.write(line);
            wISEv1.newLine();
        }
        rISEv1.close();
        wISEv1.close();

    }

    /**
     * It downloads PSE calendar page
     * @throws IOException
     */
    private void convertPSEv1URL() throws IOException {

        BufferedReader rPSEv1 = new BufferedReader(new InputStreamReader(urlPSEv1.openStream()));
        BufferedWriter wPSEv1 = new BufferedWriter(new FileWriter("Downloads/psev1.htm"));

        String line;
        while ((line = rPSEv1.readLine()) != null) {
            wPSEv1.write(line);
            wPSEv1.newLine();
        }
        rPSEv1.close();
        wPSEv1.close();

    }

    /**
     * It downloads SSE calendar page
     * @throws IOException
     */
    private void convertSSEv1URL() throws IOException {

        BufferedReader rSSEv1 = new BufferedReader(new InputStreamReader(urlSSEv1.openStream()));
        BufferedWriter wSSEv1 = new BufferedWriter(new FileWriter("Downloads/ssev1.htm"));

        String line;
        while ((line = rSSEv1.readLine()) != null) {
            wSSEv1.write(line);
            wSSEv1.newLine();
        }
        rSSEv1.close();
        wSSEv1.close();

    }

    /**
     * It downloads the Catalog for Ver. 2
     * @throws IOException
     */
    private void convertCatelogv2URL() throws IOException {

        java.io.BufferedInputStream in = new java.io.BufferedInputStream(urlCatelogv2.openStream());
        java.io.FileOutputStream fos = new java.io.FileOutputStream("Downloads/Catelogv2.pdf");
        java.io.BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
        byte[] data = new byte[1024];
        int x = 0;
        while ((x = in.read(data, 0, 1024)) >= 0) {
            bout.write(data, 0, x);
        }
        bout.close();
        in.close();

    }

    /**
     * It downloads ESE calendar page
     * @throws IOException
     */
    private void convertESEv2URL() throws IOException {

        BufferedReader rESEv2 = new BufferedReader(new InputStreamReader(urlESEv2.openStream()));
        BufferedWriter wESEv2 = new BufferedWriter(new FileWriter("Downloads/esev2.htm"));

        String line;
        while ((line = rESEv2.readLine()) != null) {
            wESEv2.write(line);
            wESEv2.newLine();
        }
        rESEv2.close();
        wESEv2.close();

    }

    /**
     * It downloads EVSE calendar page
     * @throws IOException
     */
    private void convertEVSEv2URL() throws IOException {

        BufferedReader rEVSEv2 = new BufferedReader(new InputStreamReader(urlEVSEv2.openStream()));
        BufferedWriter wEVSEv2 = new BufferedWriter(new FileWriter("Downloads/evsev2.htm"));

        String line;
        while ((line = rEVSEv2.readLine()) != null) {
            wEVSEv2.write(line);
            wEVSEv2.newLine();
        }
        rEVSEv2.close();
        wEVSEv2.close();

    }

    /**
     * It downloads ISE calendar page
     * @throws IOException
     */
    private void convertISEv2URL() throws IOException {

        BufferedReader rISEv2 = new BufferedReader(new InputStreamReader(urlISEv2.openStream()));
        BufferedWriter wISEv2 = new BufferedWriter(new FileWriter("Downloads/isev2.htm"));

        String line;
        while ((line = rISEv2.readLine()) != null) {
            wISEv2.write(line);
            wISEv2.newLine();
        }
        rISEv2.close();
        wISEv2.close();

    }

    /**
     * It downloads PSE calendar page
     * @throws IOException
     */
    private void convertPSEv2URL() throws IOException {

        BufferedReader rPSEv2 = new BufferedReader(new InputStreamReader(urlPSEv2.openStream()));
        BufferedWriter wPSEv2 = new BufferedWriter(new FileWriter("Downloads/psev2.htm"));

        String line;
        while ((line = rPSEv2.readLine()) != null) {
            wPSEv2.write(line);
            wPSEv2.newLine();
        }
        rPSEv2.close();
        wPSEv2.close();

    }

    /**
     * It downloads SSE calendar page
     * @throws IOException
     */
    private void convertSSEv2URL() throws IOException {

        BufferedReader rSSEv2 = new BufferedReader(new InputStreamReader(urlSSEv2.openStream()));
        BufferedWriter wSSEv2 = new BufferedWriter(new FileWriter("Downloads/ssev2.htm"));

        String line;
        while ((line = rSSEv2.readLine()) != null) {
            wSSEv2.write(line);
            wSSEv2.newLine();
        }
        rSSEv2.close();
        wSSEv2.close();

    }

    /**
     * Converts the pdf to text
     * @param pdfFile the pdf filename
     * @param txtFile the txt output
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void convertPDFtoText(String pdfFile, String txtFile) throws FileNotFoundException, IOException {
        PDFParser parser;
        PDFTextStripper pdfStripper;
        PDDocument pdDoc;
        COSDocument cosDoc;

        File f = new File(pdfFile);
        parser = new PDFParser(new FileInputStream(f));
        parser.parse();
        cosDoc = parser.getDocument();
        pdfStripper = new PDFTextStripper();
        pdDoc = new PDDocument(cosDoc);

        BufferedWriter bw = new BufferedWriter(new FileWriter(txtFile));
        bw.write(pdfStripper.getText(pdDoc));

        pdDoc.close();

    }
}
