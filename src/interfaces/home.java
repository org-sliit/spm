/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import org.jfree.data.general.DefaultPieDataset;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Character;

/**
 *
 * @author User
 */
public class home extends javax.swing.JFrame {

    /**
     * Creates new form home
     */
    public home() {
        initComponents();
    }

    public void caljava() {
        String x = "Helloworld";
        String javatext = tjava.getText().toString();

        int incr1 = 0;
        int incr2 = 0;
        int fincr = 0;
        int nincr = 0;
        int x11 = 0;
        //jcs.setText(javatext);

        String[] add1 = {" + ", " - ", "*", "/", "%", "++", "--", "==", "!=", " > ", " < ", ">=", "<=", "&&", "||", "!", "|", "^", "~", "<<", ">>", ">>>", "<<<",
            ",", "->", ".", "::", "+=", "-=", "*=", "/=", " = ", ">>>=", "|=", "&=", "%=", "<<=", ">>=", "^=",
            " void", "double ", "int ", "float ", "String ", "printf(", "println(", "cout", "cin", " if", " for", " while",
            "do-while", " switch", "case", "endl", "\n", "class ", "new "};

        String[] add2 = {"new", "delete", " throw", "and", " throws"};

        String lines[] = javatext.split("\\r?\\n");

        String[] splited = javatext.split("\\s+");

        ArrayList<Integer> cline = new ArrayList<Integer>();
        ArrayList<String> rspace = new ArrayList<String>();
        ArrayList<String> vari = new ArrayList<String>();

        ArrayList<String> rspace1 = new ArrayList<String>();

        for (String rs : splited) {

            rspace.add(rs);

        }

        for (int ii = 0; ii <= rspace.size() - 2; ii++) {

            if ((rspace.get(ii).equals("int") || rspace.get(ii).equals("float") || rspace.get(ii).equals("String") || rspace.get(ii).equals("double"))
                    && (rspace.get(ii + 1).indexOf("(") > 0)) {

                fincr = fincr + 1;
            }
            if ((rspace.get(ii).equals("int") || rspace.get(ii).equals("float") || rspace.get(ii).equals("String") || rspace.get(ii).equals("double"))
                    && (rspace.get(ii + 1).matches("[a-zA-Z$_][a-zA-Z0-9$_]*"))) {

                fincr = fincr + 1;
                vari.add(rspace.get(ii + 1));

            }

            String xx = "int1";
            if (rspace.get(ii).indexOf("int[]") >= 0 || rspace.get(ii).equals("float[]") || rspace.get(ii).equals("String[]") || rspace.get(ii).equals("double[]")) {

                fincr = fincr + 1;

            } else if ((rspace.get(ii).equals("int") || rspace.get(ii).equals("float") || rspace.get(ii).equals("String") || rspace.get(ii).equals("double")) && (rspace.get(ii + 1).equals("[]"))) {

                fincr = fincr + 1;
            } else if ((rspace.get(ii + 1).equals("[]"))
                    && (rspace.get(ii).matches("[a-zA-Z$_][a-zA-Z0-9$_]*"))) {

                fincr = fincr + 1;
            } else if ((rspace.get(ii).equals("[")) && (rspace.get(ii + 1).equals("]"))) {
                fincr = fincr + 1;

            }

        }

        for (String rs1 : splited) {

            rspace1.add(rs1);

        }
        for (int ii = 0; ii <= rspace1.size() - 1; ii++) {

            //String x1 = splited.toString();
            //char[] chars = x1.toCharArray();
            //StringBuilder sb = new StringBuilder();
            //for (String y : vari) {
            for (char c : rspace1.get(ii).toCharArray()) {

                if (Character.isDigit(c) && !(vari.contains(rspace.get(ii)))) {
                    nincr = nincr + 1;
                    break;

                }

                {

                }

            }

            Pattern p = Pattern.compile("\"");
            Matcher m = p.matcher(javatext.toString());

            /*while (m.find()) {
                System.out.println(m.group(1));
                if(m.group(1).length()>0){
                    
                    x11=x11+1;
                }
                
            }*/
            System.out.println(x11);
        }

        
        String javatext1=tjava.getText().toString();
        String q;
        int qi=0;
        
        for (int s = 0; s <= javatext1.length(); s++) {

                    if ((javatext1.indexOf("\"") > 0)) {

                        try {

                            qi = qi + 1;
                            

                            q = javatext1.substring(javatext1.indexOf("\"") + 1, javatext1.length());
                            //System.out.println(str);
                            //System.out.println(add1[i]);
                            javatext1 = q;
                        } catch (Exception ex) {
                            System.out.println(ex);

                        }
                    }

                }
        
        
        System.out.println((nincr));

        int l = 0;

        for (String slines : lines) {
            int i = 0;
            int s = 0;

            String str;
            String ostr = slines;

            str = slines;
            int tincr = 0;

            //slines.indexOf(add1[i]);
            for (i = 0; i <= 57; i++) {
                //slines = tjava.getText().toString();

                for (s = 0; s <= slines.length(); s++) {

                    if ((slines.indexOf(add1[i]) > 0)) {

                        try {

                            incr1 = incr1 + 1;
                            tincr = tincr + 1;

                            str = slines.substring(slines.indexOf(add1[i]) + add1[i].length(), slines.length());
                            //System.out.println(str);
                            //System.out.println(add1[i]);
                            slines = str;
                        } catch (Exception ex) {
                            System.out.println(ex);

                        }
                    }

                }

            }
            l = l + 1;
            cline.add(tincr);

        }

        int l3 = 0;
        int incr3 = 0;

        for (String slines : lines) {
            int i = 0;
            int s = 0;

            String str;
            String ostr = slines;

            str = slines;
            int tincr = 0;

            //slines.indexOf(add1[i]);
            //slines = tjava.getText().toString();
            for (s = 0; s <= slines.length(); s++) {

                if ((slines.indexOf(add1[i]) > 0)) {

                    try {

                        incr3 = incr3 + 1;
                        tincr = tincr + 1;

                        str = slines.substring(slines.indexOf(add1[i]) + add1[i].length(), slines.length());
                        //System.out.println(str);
                        //System.out.println(add1[i]);
                        slines = str;
                    } catch (Exception ex) {
                        System.out.println(ex);

                    }
                }

                //System.out.println(javatext.indexOf(add1[i]));
            }

            l3 = l3 + 1;
            cline.add(tincr);

        }

        String str1;
        int l2 = 0;

        //slines = tjava.getText().toString();
        for (String slines2 : lines) {

            int tincr = 0;

            String ostr = slines2;
            for (int i = 0; i <= 4; i++) {
                //javatext = tjava.getText().toString();

                for (int s = 0; s <= slines2.length(); s++) {

                    if ((slines2.indexOf(add2[i]) > 0)) {

                        try {
                            incr2 = incr2 + 2;
                            tincr = tincr + 2;
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

            System.out.println(ostr + "********" + ((cline.get(l2)) + (tincr)));
            l2 = l2 + 1;
            //System.out.println(cline.get(l));
            //System.out.println(cline.get(l)+tincr);

            tincr = 0;
        }

        jcs.setText(String.valueOf(incr1 + incr2 + fincr + nincr+((qi/2))));

        //return (incr1+incr2);
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tc = new javax.swing.JTextArea();
        gjava = new javax.swing.JButton();
        gc = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jcs = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ccs = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tjava.setColumns(20);
        tjava.setRows(5);
        jScrollPane1.setViewportView(tjava);

        tc.setColumns(20);
        tc.setRows(5);
        jScrollPane2.setViewportView(tc);

        gjava.setText("java");
        gjava.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gjavaActionPerformed(evt);
            }
        });

        gc.setText("c++");
        gc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gcActionPerformed(evt);
            }
        });

        jLabel1.setText("Cs");

        jLabel2.setText("jLabel2");

        jcs.setText("Cs");

        jLabel4.setText("jLabel4");

        jLabel5.setText("Cs");

        jLabel6.setText("jLabel6");

        ccs.setText("Cs");

        jLabel8.setText("jLabel8");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jcs)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(gjava)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(gc)
                                    .addComponent(jLabel6))
                                .addGap(128, 128, 128))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(ccs))
                                .addGap(34, 34, 34))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gjava)
                    .addComponent(gc))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jcs)
                    .addComponent(jLabel5)
                    .addComponent(ccs))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2))))
                .addContainerGap(92, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void gjavaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gjavaActionPerformed
        // TODO add your handling code here:

        caljava();
        //DefaultPieDataset barchartdata=new DefaultPieDataset();
        // barchartdata.setValue(20000,"yuyu","ioui");
        // barchartdata.setValue(15000,"yu yu","ioui");

        //JfreeChart barChartData=CharsFactory.createBarChart("uiui",monthly,yuy);

    }//GEN-LAST:event_gjavaActionPerformed

    private void gcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gcActionPerformed
        // TODO add your handling code here:
        String ctext = tc.getText().toString();
        ccs.setText(ctext);
    }//GEN-LAST:event_gcActionPerformed

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
    private javax.swing.JLabel ccs;
    private javax.swing.JButton gc;
    private javax.swing.JButton gjava;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jcs;
    private javax.swing.JTextArea tc;
    private javax.swing.JTextArea tjava;
    // End of variables declaration//GEN-END:variables
}
