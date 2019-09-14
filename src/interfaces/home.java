/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Character;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.TableModel;

/**
 *
 * @author User
 */
public class home extends javax.swing.JFrame {

    int jvalue, cvalue, lijavactc, licppctc, jvalue1;
    int x = 5;int rec=0;int crec=0;
    
    // int[] ctc;
    //ArrayList<String> wctc  = new ArrayList<String>();
    ArrayList<Integer> actc = new ArrayList<Integer>();

    ArrayList<Integer> inheri = new ArrayList<Integer>();

    ArrayList<Integer> inheric = new ArrayList<Integer>();

    ArrayList<Integer> recursiveArr = new ArrayList<Integer>();
    public HashMap<Integer,Integer> valueArray = new HashMap<Integer, Integer>();;
    DefaultTableModel model = new DefaultTableModel();

    int inheritancecount;
    int inheritancecountc;

    // List<> myList;
    // List<Integer> l2 = new ArrayList<Integer>(); 
//    DefaultTableModel model = new DefaultTableModel();
//        
//        JTable table = new JTable(model);
    /**
     *
     * Creates new form home
     */
    public home() {
        initComponents();
        setLocationRelativeTo(null);
        tableNew.setModel(model);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    
     public void countNested(String codeText){
     int lineNum = 0;
     int sum = 0;
     String[] lines = codeText.split("\\r?\\n");
     lines = Arrays.stream(lines).filter(x -> !x.isEmpty()).toArray(String[]::new);
     for(int m = 0; m<lines.length;m++){

     }
     final String regex = "((?:(?:for|if|while)))\\s*\\s*\\(.*?.*\\)";
     final Pattern pattern = Pattern.compile(regex, Pattern.COMMENTS | Pattern.MULTILINE);

     for(int i=0; i < lines.length;) {
         final Matcher matcher = pattern.matcher(lines[i]);
         int count = 0;
         int max = 0;
         int count2 =0;
         boolean flag = false;

         if(matcher.find()) {
             System.out.println("Full match: " + matcher.group(0));
             ArrayList<Integer> linesArray= new ArrayList<Integer>();
             String temp = lines[i];
             outerloop:
             for (int j = i; j < lines.length;  j++) {
                 linesArray.add(i+count2);
                 count2++;
                 for (char ch : lines[j].toCharArray()) {
                     if (ch == '{') {
                         flag = true;
                         count++;
                         max++;
                     } else if (ch == '}' && flag) {
                         count--;
                         max++;
                     }

                     if (count == 0 && flag) {
                         break outerloop;
                     }
                 }

             }

             i+=count2-1;
             String methodBody="";
             for (Integer s : linesArray)
             {
                 valueArray.put(s, valueArray.get(s)+1);
                 methodBody += lines[s] + "\n";
             }

             final String regex2 = "((?:(?:for|if|while)\\s+)*)\\s*(\\w+)\\s*\\(.*?\\)\\s*(\\{(?:\\{[^\\{}]*\\}|.)*?\\})";
             final Pattern pattern2 = Pattern.compile(regex2, Pattern.DOTALL);
             Matcher matcher2 = pattern2.matcher(methodBody);
             if (matcher2.find()) {
                 countNested(matcher2.group(3));
             }

         }else{
             i++;
         }
     }
 }

    public void calRecursive(String codeText) {
        int lineNum = 0;
        int sum = 0;
        String[] lines = codeText.split("\\r?\\n");
        lines = Arrays.stream(lines).filter(x -> !x.isEmpty()).toArray(String[]::new);
        Pattern p = Pattern.compile("(?:(?:public|private|protected|static|final|native|synchronized|abstract|transient)+\\s+)+[$_\\w<>\\[\\]\\s]*\\s+[\\$_\\w]+\\([^\\)]*\\)?\\s*\\{?[^\\}]*\\}?");
        for (int i = 0; i < lines.length; i++) {
            Matcher matcher = p.matcher(lines[i]);
            lineNum++;
            int count = 0;
            int max = 0;
            int count2 = 0;
            boolean flag = false;
            ArrayList<Integer> linesArray = new ArrayList<Integer>();
            if (matcher.find()) {
                String temp = lines[i];
                outerloop:
                for (int j = i; j < lines.length; j++) {
                    linesArray.add(lineNum + count2);
                    count2++;
                    for (char ch : lines[j].toCharArray()) {
                        if (ch == '{') {
                            flag = true;
                            count++;
                            max++;
                        } else if (ch == '}') {
                            count--;
                            max++;
                        }

                        if (count == 0 && flag) {
                            break outerloop;
                        }
                    }
                }
                String method = "";
                Pattern p2 = Pattern.compile("([a-zA-Z_{1}][a-zA-Z0-9_]+)(?=\\()");
                Matcher matcher2 = p2.matcher(lines[linesArray.get(0) - 1]);
                Pattern p3 = null;
                if (matcher2.find()) {
                    p3 = Pattern.compile(matcher2.group(1) + "(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                    for (int n = 1; n < linesArray.size(); n++) {
                        Matcher matcher3 = p3.matcher(lines[linesArray.get(n) - 1]);
                        int sumRecursive = 0;
                        while (matcher3.find()) {
                            System.out.println(lines[linesArray.get(n) - 1] + " " + (linesArray.get(n) - 1) + "\n");
                            String methodBody = "";
                            for (Integer s : linesArray) {
                                methodBody += lines[s - 1] + "\n";
                                recursiveArr.add(s - 1);
                            }

                        }
                    }

                }

            }
        }
    }
    
    

    
    public int calJavaCtc(String javatext) {

        int incr1 = 0;
        int incr2 = 0;

        String[] add1 = {"if", "&", " | ", "&&", "||", "catch {", "switch", "case"};

        String[] add2 = {"for (", "while (", "do {"};

        String lines[] = javatext.split(" \\r?\\n");

        String[] splited = javatext.split("\\s+");

        ArrayList<Integer> cline = new ArrayList<Integer>();
        ArrayList<String> rspace = new ArrayList<String>();

        ArrayList<String>[] wline = new ArrayList[lines.length];

        for (int i = 0; i < lines.length; i++) {
            wline[i] = new ArrayList<String>();
        }

        int l = 0;
        int zz = 0;

        for (String slines : lines) {

            zz = zz + 1;
            int i = 0;
            int s = 0;

            String str;
            String ostr = slines;

            str = slines;
            int tincr = 0;

            //slines.indexOf(add1[i]);
            for (i = 0; i <= add1.length - 1; i++) {
                //slines = tjava.getText().toString();

                for (s = 0; s <= slines.length(); s++) {

                    if ((slines.indexOf(add1[i]) >= 0)) {

                        try {

                            incr1 = incr1 + 1;
                            tincr = tincr + 1;
                            wline[zz - 1].add(add1[i]);

                            str = slines.substring((slines.indexOf(add1[i])) + 1, slines.length());
                            //System.out.println(str);
                            //System.out.println(add1[i]);
                            slines = str;
                        } catch (Exception ex) {
                            System.out.println(ex);

                        }
                    }

                }

                slines = ostr;

            }

            //incr1=incr1-1;
            l = l + 1;
            cline.add(tincr);

        }

        int l3 = 0;
        int incr3 = 0;

        String str1;
        int l2 = 0;
        int y = 0;

        //slines = tjava.getText().toString();
        for (String slines2 : lines) {

            y = y + 1;
            int tincr = 0;

            String ostr = slines2;
            for (int i = 0; i <= add2.length - 1; i++) {
                //javatext = tjava.getText().toString();

                for (int s = 0; s <= slines2.length(); s++) {

                    if ((slines2.indexOf(add2[i]) >= 0)) {

                        try {
                            incr2 = incr2 + 2;
                            tincr = tincr + 2;
                            wline[y - 1].add(add2[i]);
                            //System.out.println(add1[i]);

                            str1 = slines2.substring(slines2.indexOf(add2[i]) + add2[i].length(), slines2.length());

                            slines2 = str1;
                        } catch (Exception ex) {
                            System.out.println(ex);

                        }
                    }
                    //System.out.println(javatext.indexOf(add1[i]));

                }
            }

            //System.out.println(ostr + "********"+ wctc.add(wline[l2].toString())+"  " + ctc(l2).a((cline.get(l2)) + (tincr)));
            // wctc.add(wline[l2].toString());
            int a = cline.get(l2) + (tincr);

            actc.add(a);
            //System.out.println(ostr + "********"+(wctc.get(l2))+"   "+ctc[l2]);

            l2 = l2 + 1;
            //System.out.println(cline.get(l));
            //System.out.println(cline.get(l)+tincr);

            tincr = 0;
        }

        int totCtc = lijavactc = incr1 + incr2;
        jctc.setText(String.valueOf(incr1 + incr2));

        x = x + 1;
        System.out.println("aaaaaaaaaaaaaaaaaaaaa" + actc);

        return totCtc;

    }

    public int calCppCtc(String javatext) {
        int incr1 = 0;
        int incr2 = 0;

        String[] add1 = {"if", "&", " | ", "&&", "||", "catch {", "switch", "case"};

        String[] add2 = {"for (", "while (", "do {"};

        String lines[] = javatext.split(" \\r?\\n");

        String[] splited = javatext.split("\\s+");

        ArrayList<Integer> cline = new ArrayList<Integer>();
        ArrayList<String> rspace = new ArrayList<String>();

        ArrayList<String>[] wline = new ArrayList[lines.length];

        for (int i = 0; i < lines.length; i++) {
            wline[i] = new ArrayList<String>();
        }

        int l = 0;
        int zz = 0;

        for (String slines : lines) {

            zz = zz + 1;
            int i = 0;
            int s = 0;

            String str;
            String ostr = slines;

            str = slines;
            int tincr = 0;

            //slines.indexOf(add1[i]);
            for (i = 0; i <= add1.length - 1; i++) {
                //slines = tjava.getText().toString();

                for (s = 0; s <= slines.length(); s++) {

                    if ((slines.indexOf(add1[i]) >= 0)) {

                        try {

                            incr1 = incr1 + 1;
                            tincr = tincr + 1;
                            wline[zz - 1].add(add1[i]);

                            str = slines.substring((slines.indexOf(add1[i])) + 1, slines.length());
                            //System.out.println(str);
                            //System.out.println(add1[i]);
                            slines = str;
                        } catch (Exception ex) {
                            System.out.println(ex);

                        }
                    }

                }

                slines = ostr;

            }

            //incr1=incr1-1;
            l = l + 1;
            cline.add(tincr);

        }

        int l3 = 0;
        int incr3 = 0;

        String str1;
        int l2 = 0;
        int y = 0;

        //slines = tjava.getText().toString();
        for (String slines2 : lines) {
            y = y + 1;
            int tincr = 0;

            String ostr = slines2;
            for (int i = 0; i <= add2.length - 1; i++) {
                //javatext = tjava.getText().toString();

                for (int s = 0; s <= slines2.length(); s++) {

                    if ((slines2.indexOf(add2[i]) >= 0)) {

                        try {
                            incr2 = incr2 + 2;
                            tincr = tincr + 2;
                            wline[y - 1].add(add2[i]);
                            //System.out.println(add1[i]);

                            str1 = slines2.substring(slines2.indexOf(add2[i]) + add2[i].length(), slines2.length());

                            slines2 = str1;
                        } catch (Exception ex) {
                            System.out.println(ex);

                        }
                    }
                    //System.out.println(javatext.indexOf(add1[i]));

                }
            }
            int a = cline.get(l2) + (tincr);

            actc.add(a);

            System.out.println(ostr + "********   " + ((cline.get(l2)) + (tincr)));
            l2 = l2 + 1;
            //System.out.println(cline.get(l));
            //System.out.println(cline.get(l)+tincr);

            tincr = 0;
        }

        int totCtc = licppctc = incr1 + incr2;
        cctc.setText(String.valueOf(totCtc));
        System.out.println("esxdfcgvhbjnklsuhgsuidghudsg " + totCtc);
        return totCtc;
    }

    ///Inheritance
    public int calInheritance(String x) {

        String javatext = x;

        int increment1 = 0;
        int increment2 = 0;

        //String Array
        String[] inheritarray = {"extends", "implements"};

        String lines[] = javatext.split("\\r?\\n");

        ArrayList<Integer> linecount = new ArrayList<Integer>();

        int line = 0;
        int totincrement = 0;

        for (String stringlines : lines) {
            increment1 = 0;
            int i = 0;
            int s = 0;

            String text;

            text = stringlines;
            System.out.println(stringlines.length() + "abcd");
            for (i = 0; i <= inheritarray.length - 1; i++) {

                for (s = 0; s <= stringlines.length(); s++) {
                    if ((stringlines.indexOf(inheritarray[i]) > 0)) {
                        try {
                            increment1 = increment1 + 1;
                            totincrement = totincrement + 1;

                            text = stringlines.substring(stringlines.indexOf(inheritarray[i]) + inheritarray[i].length(), stringlines.length());

                            stringlines = text;

                            String childcount[] = text.split(",");
                            int ccount = childcount.length;
                            System.out.println("aaasasasas" + childcount.length);

                            totincrement = totincrement + ccount;
                            increment1 = increment1 + ccount;
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }

            }
            inheri.add(increment1);
            System.out.println(increment1 + "asdasdasdas");
            increment1 = 0;
            line = line + 1;
            linecount.add(totincrement);

        }

        jLabel3.setText(String.valueOf(totincrement));

        inheritancecount = totincrement;

        return inheritancecount;
    }

    //Inheritance 2
    public int calInheritancec(String x) {

        String javatext = x;

        int increment1 = 0;
        int increment2 = 0;
        int totincrement = 0;
        //String Array
        String[] inheritarray = {":"};

        String lines[] = javatext.split("\\r?\\n");

        ArrayList<Integer> linecount = new ArrayList<Integer>();

        int line = 0;

        for (String stringlines : lines) {
            int i = 0;
            int s = 0;

            String text;

            text = stringlines;
            for (i = 0; i <= inheritarray.length - 1; i++) {

                for (s = 0; s <= stringlines.length(); s++) {
                    if ((stringlines.indexOf(inheritarray[i]) > 0)) {
                        try {
                            increment1 = increment1 + 1;
                            totincrement = totincrement + 1;

                            text = stringlines.substring(stringlines.indexOf(inheritarray[i]) + inheritarray[i].length(), stringlines.length());

                            stringlines = text;

                            String childcount[] = text.split(",");
                            int ccount = childcount.length;
                            System.out.println("aaasasasas" + childcount.length);
                            System.out.println("adasdsad" + totincrement);
                            totincrement = totincrement + ccount;
                            increment1 = increment1 + ccount;
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }

            }
            //line = line +1;
            inheric.add(increment1);
            System.out.println(increment1 + "asdasdasdas");
            increment1 = 0;
            line = line + 1;
            linecount.add(totincrement);

        }

        jLabel7.setText(String.valueOf(totincrement));
        inheritancecountc = totincrement;

        return inheritancecountc;

    }

    public int caljava1(String javatext) {

        if (javatext.equals("")) {
            JOptionPane.showMessageDialog(null, "No Inserted Code !");

        } else {

            /*model.setRowCount(0);
     model.setColumnCount(0);
     
     
        model.addColumn("Program statements");
           model.addColumn("Tokens identified under the size factor");
         model.addColumn("Cs");
        model.addColumn("Ctc");
        model.addColumn("Ci");
        model.addColumn("Cnc");
        model.addColumn("Tw");
        model.addColumn("Cps");
         model.addColumn("Cr");*/
            //x = "Helloworld";
            //javatext = tjava.getText().toString();
            int incr1 = 0;
            int incr2 = 0;
            int fincr = 0;
            int nincr = 0;
            int x11 = 0;
            int oincr = 0;
            int oincr0 = 0;
            //jcs.setText(javatext);

            String[] add1 = {" + ", " - ", "*", "/", "%", "++", "--", "==", "!=", " > ", " < ", ">=", "<=", "&&", "||", "!", " | ", "^", "~", " << ", " >> ", ">>>", "<<<",
                ",", "->", ".", "::", "+=", "-=", "*=", "/=", " = ", ">>>=", "|=", "&=", "%=", "<<=", ">>=", "^=",
                " void", "double ", "int ", "float ", "String ", "long ", "printf(", ".println(", "cin", "if", "for", " while(",
                " do{", " switch", "case", "endl", "class ", "args[]", "System.", ".out", "FileNotFoundException", "accessFiles", "cout"};

            String[] add2 = {"new", "delete", "throw ", " and ", " throws "};

            String lines[] = javatext.split(" \\r?\\n");

            // String[] splited = javatext.split("\\s+");
            ArrayList<Integer> cline = new ArrayList<Integer>();
            // ArrayList<String> rspace = new ArrayList<String>();
            ArrayList<String> vari = new ArrayList<String>();
            ArrayList<String> func = new ArrayList<String>();
            ArrayList<String> obj = new ArrayList<String>();
            ArrayList<String> obj1 = new ArrayList<String>();

            ArrayList<String> lfunc = new ArrayList<String>();
            ArrayList<Integer> fline = new ArrayList<Integer>();
            ArrayList<Integer> nline = new ArrayList<Integer>();
            ArrayList<Integer> oline0 = new ArrayList<Integer>();
            ArrayList<Integer> oline = new ArrayList<Integer>();
            ArrayList<Integer> qline = new ArrayList<Integer>();

            ArrayList<String>[] wline = new ArrayList[lines.length];

            for (int i = 0; i < lines.length; i++) {
                wline[i] = new ArrayList<String>();
            }

            String q;
            int qi = 0;
            String[] qa;
            for (int i = 0; i < lines.length; i++) {

                int lqi = 0;

                String javatext1 = lines[i];
                String javatext2 = lines[i];

                Pattern p = Pattern.compile("\"([^\"]*)\"");
                Matcher m = p.matcher(javatext1);
                while (m.find()) {
                    System.out.println("QQQQQQQ  line of " + (i + 1) + "=" + m.group(1));
                    wline[i].add(m.group(1));

                }

                for (int i1 = 0; i1 <= javatext2.length(); i1++) {

                    if ((javatext1.indexOf("\"") > 0)) {

                        try {

                            qi = qi + 1;
                            lqi = lqi + 1;

                            q = javatext1.substring(javatext1.indexOf("\"") + 2, javatext1.length());

                            //qa.
                            javatext1 = q;
                        } catch (Exception ex) {
                            System.out.println(ex);

                        }
                    }

                }

                qline.add(lqi);

            }

            for (int i = 0; i < lines.length; i++) {

                ArrayList<String> frspace = new ArrayList<String>();
                String[] splited = (lines[i]).split("\\s+");
                //String[] splited = javatext.split("\\s+");

                int lfincr = 0;

                for (String rs : splited) {

                    frspace.add(rs);

                }

                System.out.println("$$$$$$$$$" + frspace);

                for (int ii = 0; ii <= frspace.size() - 2; ii++) {

                    if ((frspace.get(ii).equals("int") || frspace.get(ii).equals("float") || frspace.get(ii).equals("String") || frspace.get(ii).equals("double") || (frspace.get(ii).equals("long")) || (frspace.get(ii).equals("void")))
                            && (frspace.get(ii + 1).indexOf("(") > 0)) {

                        fincr = fincr + 1;
                        lfincr = lfincr + 1;
                        wline[i].add(frspace.get(ii + 1));
                        func.add(frspace.get(ii + 1));

                    }
                    if ((frspace.get(ii).equals("int") || frspace.get(ii).equals("float") || frspace.get(ii).equals("String") || frspace.get(ii).equals("double") || (frspace.get(ii).equals("long")))
                            && (frspace.get(ii + 1).matches("[a-zA-Z$_][a-zA-Z0-9$_]*"))) {

                        fincr = fincr + 1;
                        lfincr = lfincr + 1;
                        wline[i].add(frspace.get(ii + 1));

                        vari.add(frspace.get(ii + 1));

                    }

                    String xx = "int1";
                    if (frspace.get(ii).indexOf("int[]") >= 0 || frspace.get(ii).equals("float[]") || frspace.get(ii).equals("String[]") || frspace.get(ii).equals("double[]") || (frspace.get(ii).equals("long[]"))) {

                        fincr = fincr + 1;
                        lfincr = lfincr + 1;
                        wline[i].add(frspace.get(ii));

                    } else if ((frspace.get(ii).equals("int") || frspace.get(ii).equals("float") || frspace.get(ii).equals("String") || frspace.get(ii).equals("double") || (frspace.get(ii).equals("long"))) && (frspace.get(ii + 1).equals("[]"))) {

                        fincr = fincr + 1;
                        lfincr = lfincr + 1;
                        wline[i].add(frspace.get(ii + 1));

                    } else if ((frspace.get(ii + 1).equals("[]"))
                            && (frspace.get(ii).matches("[a-zA-Z$_][a-zA-Z0-9$_]*"))) {

                        fincr = fincr + 1;
                        lfincr = lfincr + 1;

                    } else if ((frspace.get(ii).equals("[")) && (frspace.get(ii + 1).equals("]"))) {
                        fincr = fincr + 1;
                        lfincr = lfincr + 1;

                    }

                    if (frspace.get(ii).equals("class")) {

                        obj.add(frspace.get(ii + 1));

                    }

                }

                fline.add(lfincr);
            }

            // *********for (String rs1 : splited) {
            //rspace1.add(rs1);
            // }
            for (int i = 0; i < lines.length; i++) {

                ArrayList<String> rspace1 = new ArrayList<String>();

                String[] splited = (lines[i]).split("\\s+");

                for (String rs : splited) {

                    rspace1.add(rs);

                }

                int lnincr = 0;
                for (int ii = 0; ii <= rspace1.size() - 1; ii++) {

                    //System.out.println("aaaaaaaaaaaa"+rspace1.get(ii));
                    for (char c : rspace1.get(ii).toCharArray()) {

                        if (Character.isDigit(c) && !(vari.contains(rspace1.get(ii)))) {
                            nincr = nincr + 1;
                            lnincr = lnincr + 1;
                            wline[i].add(rspace1.get(ii));

                            break;

                        }
                    }

                    if ((vari.contains(rspace1.get(ii))) && (rspace1.get(ii - 1).indexOf("long")) < 0 && (rspace1.get(ii - 1).indexOf("String")) < 0 && (rspace1.get(ii - 1).indexOf("int")) < 0 && (rspace1.get(ii - 1).indexOf("double")) < 0) {
                        nincr = nincr + 1;
                        lnincr = lnincr + 1;

                        wline[i].add(rspace1.get(ii));
                    }

                    if ((func.contains(rspace1.get(ii))) && (rspace1.get(ii - 1).indexOf("long")) < 0 && (rspace1.get(ii - 1).indexOf("void")) < 0 && (rspace1.get(ii - 1).indexOf("String")) < 0 && (rspace1.get(ii - 1).indexOf("int")) < 0) {
                        nincr = nincr + 1;
                        lnincr = lnincr + 1;
                        wline[i].add(rspace1.get(ii));

                    }

                }

                nline.add(lnincr);

            }

            for (int i = 0; i < lines.length; i++) {

                ArrayList<String> rspace1 = new ArrayList<String>();
                String[] splited = (lines[i]).split("\\s+");
                //String[] splited = javatext.split("\\s+");

                int loincr0 = 0;

                for (String rs : splited) {

                    rspace1.add(rs);

                }

                for (int ii = 0; ii <= rspace1.size() - 2; ii++) {

                    if ((obj.contains(rspace1.get(ii))) && (rspace1.get(ii + 1)).matches("[a-zA-Z$_][a-zA-Z0-9$_]*")) {

                        oincr0 = oincr0 + 1;
                        loincr0 = loincr0 + 1;
                        wline[i].add(rspace1.get(ii + 1));

                        obj1.add(rspace1.get(ii + 1));

                    }

                }

                oline0.add(loincr0);

            }

            for (int i = 0; i < lines.length; i++) {

                ArrayList<String> rspace1 = new ArrayList<String>();
                String[] splited = (lines[i]).split("\\s+");
                //String[] splited = javatext.split("\\s+");

                int loincr = 0;

                for (String rs : splited) {

                    rspace1.add(rs);

                }
                for (int ii = 0; ii <= rspace1.size() - 1; ii++) {

                    if ((obj1.contains(rspace1.get(ii))) && !(obj.contains(rspace1.get(ii - 1)))) {

                        oincr = oincr + 1;
                        loincr = loincr + 1;
                        wline[i].add(rspace1.get(ii));

                    }

                }
                oline.add(loincr);

            }

            //String javatext1 = javatext;
            Pattern p = Pattern.compile("\"([^\"]*)\"");
            Matcher m = p.matcher(javatext);
            while (m.find()) {
                //System.out.println(m.group(1));
            }

            // System.out.println((nincr));
            int l = 0;
            int y = 0;
            for (String slines : lines) {
                int i = 0;
                int s = 0;

                y = y + 1;
                System.out.println(y);

                String str;
                String ostr = slines;

                str = slines;
                int tincr = 0;

                //slines.indexOf(add1[i]);
                for (i = 0; i <= add1.length - 1; i++) {
                    //slines = tjava.getText().toString();

                    for (s = 0; s <= slines.length(); s++) {

                        if ((slines.indexOf(add1[i]) >= 0)) {

                            try {

                                incr1 = incr1 + 1;
                                tincr = tincr + 1;
                                System.out.println(add1[i]);
                                wline[y - 1].add(add1[i]);

                                str = slines.substring((slines.indexOf(add1[i])) + 1, slines.length());
                                //System.out.println(str);
                                //System.out.println(add1[i]);
                                slines = str;
                            } catch (Exception ex) {
                                System.out.println(ex);

                            }
                        }

                    }

                    slines = ostr;
                    //System.out.println("--------------"+tincr);
                    //System.out.println("----*****----------");

                }

                l = l + 1;
                cline.add(tincr);
                System.out.println("--------------" + tincr);
                System.out.println("----*****----------");

                //incr1=incr1-1;
            }

            int l3 = 0;
            int incr3 = 0;
            int zz = 0;

            String str1;
            int l2 = 0;

            //slines = tjava.getText().toString();
            for (String slines2 : lines) {

                zz = zz + 1;
                int tincr = 0;

                String ostr = slines2;
                for (int i = 0; i <= 4; i++) {
                    //javatext = tjava.getText().toString();

                    for (int s = 0; s <= slines2.length(); s++) {

                        if ((slines2.indexOf(add2[i]) >= 0)) {

                            try {
                                incr2 = incr2 + 2;
                                tincr = tincr + 2;
                                wline[zz - 1].add(add2[i]);
                                //System.out.println(add1[i]);

                                str1 = slines2.substring(slines2.indexOf(add2[i]) + add2[i].length(), slines2.length());

                                slines2 = str1;
                            } catch (Exception ex) {
                                System.out.println(ex);

                            }
                        }

                    }
                }

                // System.out.println(ostr + "********" + wline[l2] + "*******" + ((cline.get(l2)) + (tincr) + (fline.get(l2)) + (nline.get(l2)) + (oline0.get(l2) + (oline.get(l2)) + (qline.get(l2)) / 2)));
                //model.insertRow(l2, new Object[] {ostr,wline[l2],((cline.get(l2)) + (tincr) + (fline.get(l2)) + (nline.get(l2)) + (oline0.get(l2) + (oline.get(l2)) + (qline.get(l2)) / 2))});
                l2 = l2 + 1;

                tincr = 0;
            }

            //int value = incr1 + incr2 + fincr + nincr + oincr + ((qi / 2));
            jvalue1 = incr1 + incr2 + fincr + nincr + oincr + oincr0 + ((qi / 2) + lijavactc + inheritancecount);
            //jcs.setText(String.valueOf(jvalue));
            //System.out.println("bbbbbbbb"+actc);

            /* JFrame f = new JFrame();
    f.setSize(3000, 3000);
    f.add(new JScrollPane(table));
    f.setVisible(true);*/
        }
        // System.out.println(String.valueOf(value));
        return jvalue1;
        //jcs.setText(String.valueOf(fincr+nincr));

        //return (incr1+incr2);
    }

    public int caljava(String javatext) {

        if (javatext.equals("")) {
            JOptionPane.showMessageDialog(null, "No Inserted Code !");

        } else {

            model.setRowCount(0);
            model.setColumnCount(0);

            model.addColumn("Program statements");
            model.addColumn("Tokens identified under the size factor");
            model.addColumn("Cs");
            model.addColumn("Ctc");
            model.addColumn("Ci");
            model.addColumn("Cr");
            model.addColumn("Tw");
            model.addColumn("Cps");
            model.addColumn("Cnc");
            //x = "Helloworld";

            //javatext = tjava.getText().toString();
            int incr1 = 0;
            int incr2 = 0;
            int fincr = 0;
            int nincr = 0;
            int x11 = 0;
            int oincr = 0;
            int oincr0 = 0;
            //jcs.setText(javatext);

            String[] add1 = {" + ", " - ", "*", "/", "%", "++", "--", "==", "!=", " > ", " < ", ">=", "<=", "&&", "||", "!", " | ", "^", "~", " << ", " >> ", ">>>", "<<<",
                ",", "->", ".", "::", "+=", "-=", "*=", "/=", " = ", ">>>=", "|=", "&=", "%=", "<<=", ">>=", "^=",
                " void", "double ", "int ", "float ", "String ", "long ", "printf(", ".println(", "cin", "if", "for", " while(",
                " do{", " switch", "case", "endl", "class ", "args[]", "System.", ".out", "FileNotFoundException", "accessFiles", "cout"};

            String[] add2 = {"new", "delete", "throw ", " and ", " throws "};

            String lines[] = javatext.split(" \\r?\\n");

            // String[] splited = javatext.split("\\s+");
            ArrayList<Integer> cline = new ArrayList<Integer>();
            // ArrayList<String> rspace = new ArrayList<String>();
            ArrayList<String> vari = new ArrayList<String>();
            ArrayList<String> func = new ArrayList<String>();
            ArrayList<String> obj = new ArrayList<String>();
            ArrayList<String> obj1 = new ArrayList<String>();

            ArrayList<String> lfunc = new ArrayList<String>();
            ArrayList<Integer> fline = new ArrayList<Integer>();
            ArrayList<Integer> nline = new ArrayList<Integer>();
            ArrayList<Integer> oline0 = new ArrayList<Integer>();
            ArrayList<Integer> oline = new ArrayList<Integer>();
            ArrayList<Integer> qline = new ArrayList<Integer>();

            ArrayList<String>[] wline = new ArrayList[lines.length];

            for (int i = 0; i < lines.length; i++) {
                wline[i] = new ArrayList<String>();
            }

            String q;
            int qi = 0;
            String[] qa;
            for (int i = 0; i < lines.length; i++) {

                int lqi = 0;

                String javatext1 = lines[i];
                String javatext2 = lines[i];

                Pattern p = Pattern.compile("\"([^\"]*)\"");
                Matcher m = p.matcher(javatext1);
                while (m.find()) {
                    System.out.println("QQQQQQQ  line of " + (i + 1) + "=" + m.group(1));
                    wline[i].add(m.group(1));

                }

                for (int i1 = 0; i1 <= javatext2.length(); i1++) {

                    if ((javatext1.indexOf("\"") > 0)) {

                        try {

                            qi = qi + 1;
                            lqi = lqi + 1;

                            q = javatext1.substring(javatext1.indexOf("\"") + 2, javatext1.length());

                            //qa.
                            javatext1 = q;
                        } catch (Exception ex) {
                            System.out.println(ex);

                        }
                    }

                }

                qline.add(lqi);

            }

            for (int i = 0; i < lines.length; i++) {

                ArrayList<String> frspace = new ArrayList<String>();
                String[] splited = (lines[i]).split("\\s+");
                //String[] splited = javatext.split("\\s+");

                int lfincr = 0;

                for (String rs : splited) {

                    frspace.add(rs);

                }

                System.out.println("$$$$$$$$$" + frspace);

                for (int ii = 0; ii <= frspace.size() - 2; ii++) {

                    if ((frspace.get(ii).equals("int") || frspace.get(ii).equals("float") || frspace.get(ii).equals("String") || frspace.get(ii).equals("double") || (frspace.get(ii).equals("long")) || (frspace.get(ii).equals("void")))
                            && (frspace.get(ii + 1).indexOf("(") > 0)) {

                        fincr = fincr + 1;
                        lfincr = lfincr + 1;
                        wline[i].add(frspace.get(ii + 1));
                        func.add(frspace.get(ii + 1));

                    }
                    if ((frspace.get(ii).equals("int") || frspace.get(ii).equals("float") || frspace.get(ii).equals("String") || frspace.get(ii).equals("double") || (frspace.get(ii).equals("long")))
                            && (frspace.get(ii + 1).matches("[a-zA-Z$_][a-zA-Z0-9$_]*"))) {

                        fincr = fincr + 1;
                        lfincr = lfincr + 1;
                        wline[i].add(frspace.get(ii + 1));

                        vari.add(frspace.get(ii + 1));

                    }

                    String xx = "int1";
                    if (frspace.get(ii).indexOf("int[]") >= 0 || frspace.get(ii).equals("float[]") || frspace.get(ii).equals("String[]") || frspace.get(ii).equals("double[]") || (frspace.get(ii).equals("long[]"))) {

                        fincr = fincr + 1;
                        lfincr = lfincr + 1;
                        wline[i].add(frspace.get(ii));

                    } else if ((frspace.get(ii).equals("int") || frspace.get(ii).equals("float") || frspace.get(ii).equals("String") || frspace.get(ii).equals("double") || (frspace.get(ii).equals("long"))) && (frspace.get(ii + 1).equals("[]"))) {

                        fincr = fincr + 1;
                        lfincr = lfincr + 1;
                        wline[i].add(frspace.get(ii + 1));

                    } else if ((frspace.get(ii + 1).equals("[]"))
                            && (frspace.get(ii).matches("[a-zA-Z$_][a-zA-Z0-9$_]*"))) {

                        fincr = fincr + 1;
                        lfincr = lfincr + 1;

                    } else if ((frspace.get(ii).equals("[")) && (frspace.get(ii + 1).equals("]"))) {
                        fincr = fincr + 1;
                        lfincr = lfincr + 1;

                    }

                    if (frspace.get(ii).equals("class")) {

                        obj.add(frspace.get(ii + 1));

                    }

                }

                fline.add(lfincr);
            }

            // *********for (String rs1 : splited) {
            //rspace1.add(rs1);
            // }
            for (int i = 0; i < lines.length; i++) {

                ArrayList<String> rspace1 = new ArrayList<String>();

                String[] splited = (lines[i]).split("\\s+");

                for (String rs : splited) {

                    rspace1.add(rs);

                }

                int lnincr = 0;
                for (int ii = 0; ii <= rspace1.size() - 1; ii++) {

                    //System.out.println("aaaaaaaaaaaa"+rspace1.get(ii));
                    for (char c : rspace1.get(ii).toCharArray()) {

                        if (Character.isDigit(c) && !(vari.contains(rspace1.get(ii)))) {
                            nincr = nincr + 1;
                            lnincr = lnincr + 1;
                            wline[i].add(rspace1.get(ii));

                            break;

                        }
                    }

                    if ((vari.contains(rspace1.get(ii))) && (rspace1.get(ii - 1).indexOf("long")) < 0 && (rspace1.get(ii - 1).indexOf("String")) < 0 && (rspace1.get(ii - 1).indexOf("int")) < 0 && (rspace1.get(ii - 1).indexOf("double")) < 0) {
                        nincr = nincr + 1;
                        lnincr = lnincr + 1;

                        wline[i].add(rspace1.get(ii));
                    }

                    if ((func.contains(rspace1.get(ii))) && (rspace1.get(ii - 1).indexOf("long")) < 0 && (rspace1.get(ii - 1).indexOf("void")) < 0 && (rspace1.get(ii - 1).indexOf("String")) < 0 && (rspace1.get(ii - 1).indexOf("int")) < 0) {
                        nincr = nincr + 1;
                        lnincr = lnincr + 1;
                        wline[i].add(rspace1.get(ii));

                    }

                }

                nline.add(lnincr);

            }

            for (int i = 0; i < lines.length; i++) {

                ArrayList<String> rspace1 = new ArrayList<String>();
                String[] splited = (lines[i]).split("\\s+");
                //String[] splited = javatext.split("\\s+");

                int loincr0 = 0;

                for (String rs : splited) {

                    rspace1.add(rs);

                }

                for (int ii = 0; ii <= rspace1.size() - 2; ii++) {

                    if ((obj.contains(rspace1.get(ii))) && (rspace1.get(ii + 1)).matches("[a-zA-Z$_][a-zA-Z0-9$_]*")) {

                        oincr0 = oincr0 + 1;
                        loincr0 = loincr0 + 1;
                        wline[i].add(rspace1.get(ii + 1));

                        obj1.add(rspace1.get(ii + 1));

                    }

                }

                oline0.add(loincr0);

            }

            for (int i = 0; i < lines.length; i++) {

                ArrayList<String> rspace1 = new ArrayList<String>();
                String[] splited = (lines[i]).split("\\s+");
                //String[] splited = javatext.split("\\s+");

                int loincr = 0;

                for (String rs : splited) {

                    rspace1.add(rs);

                }
                for (int ii = 0; ii <= rspace1.size() - 1; ii++) {

                    if ((obj1.contains(rspace1.get(ii))) && !(obj.contains(rspace1.get(ii - 1)))) {

                        oincr = oincr + 1;
                        loincr = loincr + 1;
                        wline[i].add(rspace1.get(ii));

                    }

                }
                oline.add(loincr);

            }

            //String javatext1 = javatext;
            Pattern p = Pattern.compile("\"([^\"]*)\"");
            Matcher m = p.matcher(javatext);
            while (m.find()) {
                //System.out.println(m.group(1));
            }

            // System.out.println((nincr));
            int l = 0;
            int y = 0;
            for (String slines : lines) {
                int i = 0;
                int s = 0;

                y = y + 1;
                System.out.println(y);

                String str;
                String ostr = slines;

                str = slines;
                int tincr = 0;

                //slines.indexOf(add1[i]);
                for (i = 0; i <= add1.length - 1; i++) {
                    //slines = tjava.getText().toString();

                    for (s = 0; s <= slines.length(); s++) {

                        if ((slines.indexOf(add1[i]) >= 0)) {

                            try {

                                incr1 = incr1 + 1;
                                tincr = tincr + 1;
                                System.out.println(add1[i]);
                                wline[y - 1].add(add1[i]);

                                str = slines.substring((slines.indexOf(add1[i])) + 1, slines.length());
                                //System.out.println(str);
                                //System.out.println(add1[i]);
                                slines = str;
                            } catch (Exception ex) {
                                System.out.println(ex);

                            }
                        }

                    }

                    slines = ostr;
                    //System.out.println("--------------"+tincr);
                    //System.out.println("----*****----------");

                }

                l = l + 1;
                cline.add(tincr);
                System.out.println("--------------" + tincr);
                System.out.println("----*****----------");

                //incr1=incr1-1;
            }

            int l3 = 0;
            int incr3 = 0;
            int zz = 0;
            rec=0;

            String str1;
            int l2 = 0;
            

            //slines = tjava.getText().toString();
            for (String slines2 : lines) {

                zz = zz + 1;
                int tincr = 0;

                String ostr = slines2;
                for (int i = 0; i <= 4; i++) {
                    //javatext = tjava.getText().toString();

                    for (int s = 0; s <= slines2.length(); s++) {

                        if ((slines2.indexOf(add2[i]) >= 0)) {

                            try {
                                incr2 = incr2 + 2;
                                tincr = tincr + 2;
                                wline[zz - 1].add(add2[i]);
                                //System.out.println(add1[i]);

                                str1 = slines2.substring(slines2.indexOf(add2[i]) + add2[i].length(), slines2.length());

                                slines2 = str1;
                            } catch (Exception ex) {
                                System.out.println(ex);

                            }
                        }

                    }
                }

                System.out.println(ostr + "********" + wline[l2] + "*******" + ((cline.get(l2)) + (tincr) + (fline.get(l2)) + (nline.get(l2)) + (oline0.get(l2) + (oline.get(l2)) + (qline.get(l2)) / 2)));

                int temp = (cline.get(l2)) + (tincr) + (fline.get(l2)) + (nline.get(l2)) + (oline0.get(l2) + (oline.get(l2)) + (qline.get(l2)) / 2) + actc.get(l2) + inheri.get(l2);

                if (recursiveArr.contains(l2)) {
                    
                    rec=rec+temp*2;

                    model.insertRow(l2, new Object[]{ostr, wline[l2], ((cline.get(l2)) + (tincr) + (fline.get(l2)) + (nline.get(l2)) + (oline0.get(l2) + (oline.get(l2)) + (qline.get(l2)) / 2)), actc.get(l2), inheri.get(l2), temp * 2,valueArray.get(l2)});

                } else {
                    if (temp * 2 == 0) {

                        temp = 0;
                    }

                    model.insertRow(l2, new Object[]{ostr, wline[l2], ((cline.get(l2)) + (tincr) + (fline.get(l2)) + (nline.get(l2)) + (oline0.get(l2) + (oline.get(l2)) + (qline.get(l2)) / 2)), actc.get(l2), inheri.get(l2), "0",valueArray.get(l2)});
                }
                System.out.println("svhkzfbsz" + recursiveArr);
                l2 = l2 + 1;

                tincr = 0;
            }

            //int value = incr1 + incr2 + fincr + nincr + oincr + ((qi / 2));
            jvalue = incr1 + incr2 + fincr + nincr + oincr + oincr0 + ((qi / 2));
            cr.setText(String.valueOf(rec));
            
            
            jcs.setText(String.valueOf(jvalue));
            System.out.println("bbbbbbbb" + actc);

//    JFrame f = new JFrame();
//    f.setSize(3000, 3000);
//    f.add(new JScrollPane(tableNew));
            //f.setVisible(true);
        }
        // System.out.println(String.valueOf(value));
        return jvalue;
        //jcs.setText(String.valueOf(fincr+nincr));

        //return (incr1+incr2);
    }

    public int calcpp(String javatext) {
        
        //recursiveArr.removeAll(recursiveArr);

        if (javatext.equals("")) {
            JOptionPane.showMessageDialog(null, "No Inserted Code !");

        } else {
            // javatext = tjava.getText().toString();
            TableModel model1 = tableNew.getModel();
            model.setRowCount(0);
            model.setColumnCount(0);
            model.addColumn("Program statements");
            model.addColumn("Tokens identified under the size factor");
            model.addColumn("Cs");
            model.addColumn("Ctc");
            model.addColumn("Cnc");
            model.addColumn("Ci");
            model.addColumn("Tw");
            model.addColumn("Cps");
            model.addColumn("Cr");
            int incr1 = 0;
            int incr2 = 0;
            int fincr = 0;
            int nincr = 0;
            int x11 = 0;
            int oincr = 0;
            int oincr0 = 0;
            //jcs.setText(javatext);

            String[] add1 = {" + ", " - ", "*", "/", "%", "++", "--", "==", "!=", " > ", " < ", ">=", "<=", "&&", "||", "!", " | ", "^", "~", " << ", " >> ", ">>>", "<<<",
                ",", "->", ".", "::", "+=", "-=", "*=", "/=", " = ", ">>>=", "|=", "&=", "%=", "<<=", ">>=", "^=",
                " void", "double ", "int ", "float ", "String ", "long ", "printf(", ".println(", "cin", "if", "for", " while(",
                " do{", " switch", "case", "endl", "class ", "args[]", "System.", ".out", "FileNotFoundException", "accessFiles", "cout"};

            String[] add2 = {"new", "delete", "throw ", " and ", " throws "};

            String lines[] = javatext.split(" \\r?\\n");

            // String[] splited = javatext.split("\\s+");
            ArrayList<Integer> cline = new ArrayList<Integer>();
            // ArrayList<String> rspace = new ArrayList<String>();
            ArrayList<String> vari = new ArrayList<String>();
            ArrayList<String> func = new ArrayList<String>();
            ArrayList<String> obj = new ArrayList<String>();
            ArrayList<String> obj1 = new ArrayList<String>();

            ArrayList<String> lfunc = new ArrayList<String>();
            ArrayList<Integer> fline = new ArrayList<Integer>();
            ArrayList<Integer> nline = new ArrayList<Integer>();
            ArrayList<Integer> oline0 = new ArrayList<Integer>();
            ArrayList<Integer> oline = new ArrayList<Integer>();
            ArrayList<Integer> qline = new ArrayList<Integer>();

            ArrayList<String>[] wline = new ArrayList[lines.length];

            for (int i = 0; i < lines.length; i++) {
                wline[i] = new ArrayList<String>();
            }

            String q;
            int qi = 0;
            String[] qa;
            for (int i = 0; i < lines.length; i++) {

                int lqi = 0;

                String javatext1 = lines[i];
                String javatext2 = lines[i];

                Pattern p = Pattern.compile("\"([^\"]*)\"");
                Matcher m = p.matcher(javatext1);
                while (m.find()) {
                    System.out.println("QQQQQQQ  line of " + (i + 1) + "=" + m.group(1));
                    wline[i].add(m.group(1));

                }

                for (int i1 = 0; i1 <= javatext2.length(); i1++) {

                    if ((javatext1.indexOf("\"") > 0)) {

                        try {

                            qi = qi + 1;
                            lqi = lqi + 1;

                            q = javatext1.substring(javatext1.indexOf("\"") + 2, javatext1.length());

                            //qa.
                            javatext1 = q;
                        } catch (Exception ex) {
                            System.out.println(ex);

                        }
                    }

                }

                qline.add(lqi);

            }

            for (int i = 0; i < lines.length; i++) {

                ArrayList<String> frspace = new ArrayList<String>();
                String[] splited = (lines[i]).split("\\s+");
                //String[] splited = javatext.split("\\s+");

                int lfincr = 0;

                for (String rs : splited) {

                    frspace.add(rs);

                }

                System.out.println("$$$$$$$$$" + frspace);

                for (int ii = 0; ii <= frspace.size() - 2; ii++) {

                    if ((frspace.get(ii).equals("int") || frspace.get(ii).equals("float") || frspace.get(ii).equals("String") || frspace.get(ii).equals("double") || (frspace.get(ii).equals("long")) || (frspace.get(ii).equals("void")))
                            && (frspace.get(ii + 1).indexOf("(") > 0)) {

                        fincr = fincr + 1;
                        lfincr = lfincr + 1;
                        wline[i].add(frspace.get(ii + 1));
                        func.add(frspace.get(ii + 1));

                    }
                    if ((frspace.get(ii).equals("int") || frspace.get(ii).equals("float") || frspace.get(ii).equals("String") || frspace.get(ii).equals("double") || (frspace.get(ii).equals("long")))
                            && (frspace.get(ii + 1).matches("[a-zA-Z$_][a-zA-Z0-9$_]*"))) {

                        fincr = fincr + 1;
                        lfincr = lfincr + 1;
                        wline[i].add(frspace.get(ii + 1));

                        vari.add(frspace.get(ii + 1));

                    }

                    String xx = "int1";
                    if (frspace.get(ii).indexOf("int[]") >= 0 || frspace.get(ii).equals("float[]") || frspace.get(ii).equals("String[]") || frspace.get(ii).equals("double[]") || (frspace.get(ii).equals("long[]"))) {

                        fincr = fincr + 1;
                        lfincr = lfincr + 1;
                        wline[i].add(frspace.get(ii));

                    } else if ((frspace.get(ii).equals("int") || frspace.get(ii).equals("float") || frspace.get(ii).equals("String") || frspace.get(ii).equals("double") || (frspace.get(ii).equals("long"))) && (frspace.get(ii + 1).equals("[]"))) {

                        fincr = fincr + 1;
                        lfincr = lfincr + 1;
                        wline[i].add(frspace.get(ii + 1));

                    } else if ((frspace.get(ii + 1).equals("[]"))
                            && (frspace.get(ii).matches("[a-zA-Z$_][a-zA-Z0-9$_]*"))) {

                        fincr = fincr + 1;
                        lfincr = lfincr + 1;

                    } else if ((frspace.get(ii).equals("[")) && (frspace.get(ii + 1).equals("]"))) {
                        fincr = fincr + 1;
                        lfincr = lfincr + 1;

                    }

                    if (frspace.get(ii).equals("class")) {

                        obj.add(frspace.get(ii + 1));

                    }

                }

                fline.add(lfincr);
            }

            // *********for (String rs1 : splited) {
            //rspace1.add(rs1);
            // }
            for (int i = 0; i < lines.length; i++) {

                ArrayList<String> rspace1 = new ArrayList<String>();

                String[] splited = (lines[i]).split("\\s+");

                for (String rs : splited) {

                    rspace1.add(rs);

                }

                int lnincr = 0;
                for (int ii = 0; ii <= rspace1.size() - 1; ii++) {

                    //System.out.println("aaaaaaaaaaaa"+rspace1.get(ii));
                    for (char c : rspace1.get(ii).toCharArray()) {

                        if (Character.isDigit(c) && !(vari.contains(rspace1.get(ii)))) {
                            nincr = nincr + 1;
                            lnincr = lnincr + 1;
                            wline[i].add(rspace1.get(ii));

                            break;

                        }
                    }

                    if ((vari.contains(rspace1.get(ii))) && (rspace1.get(ii - 1).indexOf("long")) < 0 && (rspace1.get(ii - 1).indexOf("String")) < 0 && (rspace1.get(ii - 1).indexOf("int")) < 0 && (rspace1.get(ii - 1).indexOf("double")) < 0) {
                        nincr = nincr + 1;
                        lnincr = lnincr + 1;

                        wline[i].add(rspace1.get(ii));
                    }

                    if ((func.contains(rspace1.get(ii))) && (rspace1.get(ii - 1).indexOf("long")) < 0 && (rspace1.get(ii - 1).indexOf("void")) < 0 && (rspace1.get(ii - 1).indexOf("String")) < 0 && (rspace1.get(ii - 1).indexOf("int")) < 0) {
                        nincr = nincr + 1;
                        lnincr = lnincr + 1;
                        wline[i].add(rspace1.get(ii));

                    }

                }

                nline.add(lnincr);

            }

            for (int i = 0; i < lines.length; i++) {

                ArrayList<String> rspace1 = new ArrayList<String>();
                String[] splited = (lines[i]).split("\\s+");
                //String[] splited = javatext.split("\\s+");

                int loincr0 = 0;

                for (String rs : splited) {

                    rspace1.add(rs);

                }

                for (int ii = 0; ii <= rspace1.size() - 2; ii++) {

                    if ((obj.contains(rspace1.get(ii))) && (rspace1.get(ii + 1)).matches("[a-zA-Z$_][a-zA-Z0-9$_]*")) {

                        oincr0 = oincr0 + 1;
                        loincr0 = loincr0 + 1;
                        wline[i].add(rspace1.get(ii + 1));

                        obj1.add(rspace1.get(ii + 1));

                    }

                }

                oline0.add(loincr0);

            }

            for (int i = 0; i < lines.length; i++) {

                ArrayList<String> rspace1 = new ArrayList<String>();
                String[] splited = (lines[i]).split("\\s+");
                //String[] splited = javatext.split("\\s+");

                int loincr = 0;

                for (String rs : splited) {

                    rspace1.add(rs);

                }
                for (int ii = 0; ii <= rspace1.size() - 1; ii++) {

                    if ((obj1.contains(rspace1.get(ii))) && !(obj.contains(rspace1.get(ii - 1)))) {

                        oincr = oincr + 1;
                        loincr = loincr + 1;
                        wline[i].add(rspace1.get(ii));

                    }

                }
                oline.add(loincr);

            }

            //String javatext1 = javatext;
            Pattern p = Pattern.compile("\"([^\"]*)\"");
            Matcher m = p.matcher(javatext);
            while (m.find()) {
                //System.out.println(m.group(1));
            }

            // System.out.println((nincr));
            int l = 0;
            int y = 0;
            for (String slines : lines) {
                int i = 0;
                int s = 0;

                y = y + 1;
                System.out.println(y);

                String str;
                String ostr = slines;

                str = slines;
                int tincr = 0;

                //slines.indexOf(add1[i]);
                for (i = 0; i <= add1.length - 1; i++) {
                    //slines = tjava.getText().toString();

                    for (s = 0; s <= slines.length(); s++) {

                        if ((slines.indexOf(add1[i]) >= 0)) {

                            try {

                                incr1 = incr1 + 1;
                                tincr = tincr + 1;
                                System.out.println(add1[i]);
                                wline[y - 1].add(add1[i]);

                                str = slines.substring((slines.indexOf(add1[i])) + 1, slines.length());
                                //System.out.println(str);
                                //System.out.println(add1[i]);
                                slines = str;
                            } catch (Exception ex) {
                                System.out.println(ex);

                            }
                        }

                    }

                    slines = ostr;
                    //System.out.println("--------------"+tincr);
                    //System.out.println("----*****----------");

                }

                l = l + 1;
                cline.add(tincr);
                System.out.println("--------------" + tincr);
                System.out.println("----*****----------");

                //incr1=incr1-1;
            }

            int l3 = 0;
            int incr3 = 0;
            int zz = 0;
            rec=0;

            String str1;
            int l2 = 0;

            //slines = tjava.getText().toString();
            for (String slines2 : lines) {

                zz = zz + 1;
                int tincr = 0;

                String ostr = slines2;
                for (int i = 0; i <= 4; i++) {
                    //javatext = tjava.getText().toString();

                    for (int s = 0; s <= slines2.length(); s++) {

                        if ((slines2.indexOf(add2[i]) >= 0)) {

                            try {
                                incr2 = incr2 + 2;
                                tincr = tincr + 2;
                                wline[zz - 1].add(add2[i]);
                                //System.out.println(add1[i]);

                                str1 = slines2.substring(slines2.indexOf(add2[i]) + add2[i].length(), slines2.length());

                                slines2 = str1;
                            } catch (Exception ex) {
                                System.out.println(ex);

                            }
                        }
                        //System.out.println(javatext.indexOf(add1[i]));

                    }
                }
                int temp = (cline.get(l2)) + (tincr) + (fline.get(l2)) + (nline.get(l2)) + (oline0.get(l2) + (oline.get(l2)) + (qline.get(l2)) / 2) + actc.get(l2) + inheric.get(l2);

                if (recursiveArr.contains(l2)) {
                        crec=crec+temp*2;
                   

                    model.insertRow(l2, new Object[]{ostr, wline[l2], ((cline.get(l2)) + (tincr) + (fline.get(l2)) + (nline.get(l2)) + (oline0.get(l2) + (oline.get(l2)) + (qline.get(l2)) / 2)), actc.get(l2), inheric.get(l2), temp * 2,"0"});

                } else {
                    if (temp * 2 == 0) {

                        temp = 0;
                    }

                    model.insertRow(l2, new Object[]{ostr, wline[l2], ((cline.get(l2)) + (tincr) + (fline.get(l2)) + (nline.get(l2)) + (oline0.get(l2) + (oline.get(l2)) + (qline.get(l2)) / 2)), actc.get(l2), inheric.get(l2), "0","0"});
                }
                System.out.println("svhkzfbsz" + recursiveArr);
               

                //System.out.println(ostr + "********" + wline[l2] + "*******" + ((cline.get(l2)) + (tincr) + (fline.get(l2)) + (nline.get(l2)) + (oline0.get(l2) + (oline.get(l2)) + (qline.get(l2)) / 2)));
                //model.insertRow(l2, new Object[]{ostr, wline[l2], ((cline.get(l2)) + (tincr) + (fline.get(l2)) + (nline.get(l2)) + (oline0.get(l2) + (oline.get(l2)) + (qline.get(l2)) / 2)), actc.get(l2), inheric.get(l2)});
                l2 = l2 + 1;
                
                

                tincr = 0;
            }

            //int value = incr1 + incr2 + fincr + nincr + oincr + ((qi / 2));
            cvalue = incr1 + incr2 + fincr + nincr + oincr + oincr0 + ((qi / 2));
            lcpp.setText(String.valueOf(cvalue));
            ccr.setText(String.valueOf(crec));

        }
        return cvalue;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tjava = new javax.swing.JTextArea();
        gjava = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jcs = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lcpp = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jctc = new javax.swing.JLabel();
        cctc = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableNew = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        cr = new javax.swing.JLabel();
        ccr = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tjava.setColumns(20);
        tjava.setRows(5);
        jScrollPane1.setViewportView(tjava);

        gjava.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        gjava.setText("Calculate Complexity");
        gjava.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gjavaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel1.setText("Size Complexity (Cs)            :");

        jcs.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jcs.setText("0");

        jLabel31.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel31.setText("Type Complexity (Ctc)          :");

        jButton1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton1.setText("GET REPORT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel4.setText("Size Complexity (Cs)            :");

        lcpp.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lcpp.setText("0");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "C++", "Java" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel5.setText("Type Complexity (Ctc)          :");

        jctc.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jctc.setText("0");

        cctc.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        cctc.setText("0");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel2.setText("Inheritance Complexity (Ci)  :");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel3.setText("0");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel6.setText("Inheritance Complexity (Ci)  :");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel7.setText("0");

        tableNew.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Cs", "Ctc", "Cnc", "Ci", "TW", "Cps", "Cr"
            }
        ));
        jScrollPane2.setViewportView(tableNew);

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel8.setText("Complexity Calculater");

        jButton2.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton2.setText("Open File");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Fira Sans Semi-Light", 1, 14)); // NOI18N
        jLabel9.setText("Select Language :");

        jLabel10.setFont(new java.awt.Font("Fira Sans Semi-Light", 1, 18)); // NOI18N
        jLabel10.setText("Select Your Code");

        jLabel11.setFont(new java.awt.Font("Fira Sans Semi-Light", 1, 18)); // NOI18N
        jLabel11.setText("Complexity Details");

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel13.setText("Recursion Complexity (Cr)    :");

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel14.setText("Nesting Complexity (Cnc)     :");

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel15.setText("Program Complexity (Cnc)   :");

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel16.setText("Recursion Complexity (Cr)    :");

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel17.setText("Nesting Complexity (Cnc)     :");

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel18.setText("Program Complexity (Cnc)    :");

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel19.setText("Total Complexity              :");

        jLabel20.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel20.setText("Total Complexity               :");

        jLabel21.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel21.setText("JAVA");

        jLabel22.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel22.setText("C++");

        cr.setText("0");

        ccr.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(jLabel10)
                        .addGap(208, 208, 208)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(279, 279, 279)
                        .addComponent(jLabel11))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(255, 255, 255)
                        .addComponent(gjava, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(390, 390, 390)
                        .addComponent(jLabel19)
                        .addGap(178, 178, 178)
                        .addComponent(jLabel20))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(156, 156, 156)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(782, 782, 782)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(120, 120, 120)
                                        .addComponent(jLabel21)
                                        .addGap(418, 418, 418)
                                        .addComponent(jLabel22))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel1)
                                        .addGap(47, 47, 47)
                                        .addComponent(jcs)
                                        .addGap(119, 119, 119)
                                        .addComponent(jLabel4)
                                        .addGap(57, 57, 57)
                                        .addComponent(lcpp))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel31)
                                        .addGap(46, 46, 46)
                                        .addComponent(jctc)
                                        .addGap(119, 119, 119)
                                        .addComponent(jLabel5)
                                        .addGap(56, 56, 56)
                                        .addComponent(cctc))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel14)
                                        .addGap(177, 177, 177)
                                        .addComponent(jLabel17))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel15)
                                        .addGap(180, 180, 180)
                                        .addComponent(jLabel18))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel13))
                                        .addGap(2, 2, 2)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel3)
                                            .addComponent(cr))
                                        .addGap(119, 119, 119)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel16)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addGap(57, 57, 57)
                                                .addComponent(jLabel7))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(332, 332, 332)
                                                .addComponent(ccr, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel22))
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jcs)
                                    .addComponent(jLabel4)
                                    .addComponent(lcpp))
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel31)
                                    .addComponent(jctc)
                                    .addComponent(jLabel5)
                                    .addComponent(cctc))
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel13)
                                        .addComponent(cr))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel16)
                                        .addComponent(ccr)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel17))
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel18))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(gjava, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void gjavaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gjavaActionPerformed
//jTable1.getModel().setValueAt(5, 5, 5);

        if (jComboBox1.getSelectedItem().equals("Java")) {

            System.out.println("java");

// TODO add your handling code here:
String[] lines = tjava.getText().split("\\r?\\n");
            lines = Arrays.stream(lines).filter(x -> !x.isEmpty()).toArray(String[]::new);
            for(int m=0;m<lines.length;m++){
            valueArray.put(m,0);
            }
        
            countNested(tjava.getText());
            calRecursive(tjava.getText());
            calInheritance(tjava.getText());
            calJavaCtc(tjava.getText());
            caljava(tjava.getText());

        } else {
            calRecursive(tjava.getText());
            calInheritancec(tjava.getText());
            calCppCtc(tjava.getText());
            calcpp(tjava.getText());

        }
        // calJavaCtc(tjava.getText());

        //DefaultPieDataset barchartdata=new DefaultPieDataset();
        // barchartdata.setValue(20000,"yuyu","ioui");
        // barchartdata.setValue(15000,"yu yu","ioui");
        //JfreeChart barChartData=CharsFactory.createBarChart("uiui",monthly,yuy);

    }//GEN-LAST:event_gjavaActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
        //jTextField1.setText(filename);

        try {
            FileReader reader = new FileReader(filename);
            BufferedReader br = new BufferedReader(reader);
            tjava.read(br, null);
            br.close();
            tjava.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {

        // TODO add your handling code here:
        String value2 = "opopop";
        //        String value2=surnamebox.getText();
        //        String value3=idbox.getText();
        //        String value4=designationbox.getText();
        //        String value5=dobbox.getText();
        //        String value6=hiredbox.getText();

        JFileChooser dialog = new JFileChooser();
        dialog.setSelectedFile(new File("Code Complexity" + ".pdf"));
        int dialogresult = dialog.showSaveDialog(null);
        if (dialogresult == JFileChooser.APPROVE_OPTION) {

            String filepath = dialog.getSelectedFile().getPath();

            try {

                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(filepath));

                document.open();
                document.add(new Paragraph("Code Complexity", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD)));
                document.add(new Paragraph(new Date().toString()));
                document.add(new Paragraph("---------------------------------------------------", FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, Font.PLAIN)));
                //                document.add(new Paragraph("EMPLOYEE DETAILS",FontFactory.getFont(FontFactory.TIMES_BOLD,15,Font.BOLD)));
                document.add(new Paragraph("Code complexity OF Size(Cs) for JAVA code: " + cvalue, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN)));
                document.add(new Paragraph("Code complexity for Size(Cs) C++ code: " + jvalue, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN)));
                document.add(new Paragraph("Code complexity OF Size(Ctc) for JAVA code: " + lijavactc, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN)));
                document.add(new Paragraph("Code complexity for Size(Ctc) C++ code: " + licppctc, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN)));
                document.add(new Paragraph("Code complexity for Size(Ci) java code: " + inheritancecount, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN)));
                document.add(new Paragraph("Code complexity for recursive(Cr) java code: " + rec, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN)));
                                document.add(new Paragraph("Code complexity recursive(Cr)  code c++: " + crec, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN)));

                //                document.add(new Paragraph("Date of Birth: "+value5,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN)));
                //                document.add(new Paragraph("Date Hired: "+value6,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN)));
                document.add(new Paragraph("---------------------------------------------------", FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, Font.PLAIN)));
                //document.add(new Paragraph("EMPLOYEE DETAILS",FontFactory.getFont(FontFactory.TIMES_BOLD,15,Font.BOLD)));

                document.close();
                JOptionPane.showMessageDialog(null, "Report was successfully generated");

            } catch (DocumentException | HeadlessException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null, e);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "sssssssssssssss");
                }
            }

        }

        // TODO add your handling code here:
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new home().setVisible(true);
            }
        });

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ccr;
    private javax.swing.JLabel cctc;
    private javax.swing.JLabel cr;
    private javax.swing.JButton gjava;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jcs;
    private javax.swing.JLabel jctc;
    private javax.swing.JLabel lcpp;
    private javax.swing.JTable tableNew;
    private javax.swing.JTextArea tjava;
    // End of variables declaration//GEN-END:variables
}
