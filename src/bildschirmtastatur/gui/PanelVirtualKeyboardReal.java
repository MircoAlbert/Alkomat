
package bildschirmtastatur.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.text.JTextComponent;
import bildschirmtastatur.lang.KeyEnums;

public class PanelVirtualKeyboardReal extends javax.swing.JPanel {
    ResourceBundle keyBundle = null;
    KeyEnums keyEnums = null;
    JTextComponent textComponent;
    Window window = null;
    Locale localeL;
    ActionListener al;

    boolean poitToUp = false;
    boolean shiftBs = false;
    int layer=1;
    int gap = 2;
    int tab = 4;

    /** Creates new form PanelVirtualKeyboard */
    public PanelVirtualKeyboardReal(ActionListener l) {
    	this.al =l;
        initComponents();
        localeL= Locale.getDefault(); //Set locale to default

//        localeL= new Locale("en", "GB");
        try {
            keyBundle = ResourceBundle.getBundle("bildschirmtastatur.lang.Keyboard", localeL);
        } catch (MissingResourceException e) {
            System.err.println(e);
        }        
        updateGUI();
        layer=0;
    }

    /**
     * Set focus to ENTER button
     */
    public void setFocusToEXE(){
        jBExe.requestFocus();
    }

    public final void updateGUI() {
        switch (layer) {
            case 0:
                //ROW1*********************************************************************
                jBR1B1.setText(keyBundle.getString(KeyEnums.jBR1B1.toString()));
                jBR1B2.setText(keyBundle.getString(KeyEnums.jBR1B2.toString()));
                jBR1B3.setText(keyBundle.getString(KeyEnums.jBR1B3.toString()));
                jBR1B4.setText(keyBundle.getString(KeyEnums.jBR1B4.toString()));
                jBR1B5.setText(keyBundle.getString(KeyEnums.jBR1B5.toString()));
                jBR1B6.setText(keyBundle.getString(KeyEnums.jBR1B6.toString()));
                jBR1B7.setText(keyBundle.getString(KeyEnums.jBR1B7.toString()));
                jBR1B8.setText(keyBundle.getString(KeyEnums.jBR1B8.toString()));
                jBR1B9.setText(keyBundle.getString(KeyEnums.jBR1B9.toString()));
                jBR1B10.setText(keyBundle.getString(KeyEnums.jBR1B10.toString()));
                jBR1B11.setText(keyBundle.getString(KeyEnums.jBR1B11.toString()));
                jBR1B12.setText(keyBundle.getString(KeyEnums.jBR1B12.toString()));
                jBR1B13.setText(keyBundle.getString(KeyEnums.jBR1B13.toString()));
//              jBBackspace.setText(keyBundle.getString(KeyEnums.jBR1BACKSPACE.toString()));
                //ROW2*********************************************************************
//                jBR2B1.setText(keyBundle.getString(KeyEnums.jBR2B1.toString())); //Tab Key
                jBR2B2.setText(keyBundle.getString(KeyEnums.jBR2B2.toString()));
                jBR2B3.setText(keyBundle.getString(KeyEnums.jBR2B3.toString()));
                jBR2B4.setText(keyBundle.getString(KeyEnums.jBR2B4.toString()));
                jBR2B5.setText(keyBundle.getString(KeyEnums.jBR2B5.toString()));
                jBR2B6.setText(keyBundle.getString(KeyEnums.jBR2B6.toString()));
                jBR2B7.setText(keyBundle.getString(KeyEnums.jBR2B7.toString()));
                jBR2B8.setText(keyBundle.getString(KeyEnums.jBR2B8.toString()));
                jBR2B9.setText(keyBundle.getString(KeyEnums.jBR2B9.toString()));
                jBR2B10.setText(keyBundle.getString(KeyEnums.jBR2B10.toString()));
                jBR2B11.setText(keyBundle.getString(KeyEnums.jBR2B11.toString()));
                jBR2B12.setText(keyBundle.getString(KeyEnums.jBR2B12.toString()));
                jBR2B13.setText(keyBundle.getString(KeyEnums.jBR2B13.toString()));
//              jBEnter.setText(keyBundle.getString(KeyEnums.jBENTER.toString()));
                //ROW3*********************************************************************
//              jBR3B1.setText(keyBundle.getString(KeyEnums.jBR3B1.toString())); //Cap Key
                jBR3B2.setText(keyBundle.getString(KeyEnums.jBR3B2.toString()));
                jBR3B3.setText(keyBundle.getString(KeyEnums.jBR3B3.toString()));
                jBR3B4.setText(keyBundle.getString(KeyEnums.jBR3B4.toString()));
                jBR3B5.setText(keyBundle.getString(KeyEnums.jBR3B5.toString()));
                jBR3B6.setText(keyBundle.getString(KeyEnums.jBR3B6.toString()));
                jBR3B7.setText(keyBundle.getString(KeyEnums.jBR3B7.toString()));
                jBR3B8.setText(keyBundle.getString(KeyEnums.jBR3B8.toString()));
                jBR3B9.setText(keyBundle.getString(KeyEnums.jBR3B9.toString()));
                jBR3B10.setText(keyBundle.getString(KeyEnums.jBR3B10.toString()));
                jBR3B11.setText(keyBundle.getString(KeyEnums.jBR3B11.toString()));
                jBR3B12.setText(keyBundle.getString(KeyEnums.jBR3B12.toString()));
                jBR3B13.setText(keyBundle.getString(KeyEnums.jBR3B13.toString()));
                //ROW4*********************************************************************
//              jBR4B1.setText(keyBundle.getString(KeyEnums.jBR4B1.toString())); //Shift Key
                jBR4B2.setText(keyBundle.getString(KeyEnums.jBR4B2.toString()));
                jBR4B3.setText(keyBundle.getString(KeyEnums.jBR4B3.toString()));
                jBR4B4.setText(keyBundle.getString(KeyEnums.jBR4B4.toString()));
                jBR4B5.setText(keyBundle.getString(KeyEnums.jBR4B5.toString()));
                jBR4B6.setText(keyBundle.getString(KeyEnums.jBR4B6.toString()));
                jBR4B7.setText(keyBundle.getString(KeyEnums.jBR4B7.toString()));
                jBR4B8.setText(keyBundle.getString(KeyEnums.jBR4B8.toString()));
                jBR4B9.setText(keyBundle.getString(KeyEnums.jBR4B9.toString()));
                jBR4B10.setText(keyBundle.getString(KeyEnums.jBR4B10.toString()));
                jBR4B11.setText(keyBundle.getString(KeyEnums.jBR4B11.toString()));
                jBR4B12.setText(keyBundle.getString(KeyEnums.jBR4B12.toString()));
//              jBR4B13.setText(keyBundle.getString(KeyEnums.jBR4B13.toString())); //Shift Key
                //ROW5*********************************************************************
//              jBCtrl.setText(keyBundle.getString(KeyEnums.jBCTRL.toString()));  //Ctrl Key
//              jBSpace.setText(keyBundle.getString(KeyEnums.jBSPACE.toString()));  //Space Key
              jTBAlt_Gr.setText(keyBundle.getString(KeyEnums.jTBALT_GR.toString()));  //Alt-Gr Key
                break;
            case 1:
                //ROW1*********************************************************************
                jBR1B1.setText(keyBundle.getString(KeyEnums.jBR1B1_0.toString()));
                jBR1B2.setText(keyBundle.getString(KeyEnums.jBR1B2_0.toString()));
                jBR1B3.setText(keyBundle.getString(KeyEnums.jBR1B3_0.toString()));
                jBR1B4.setText(keyBundle.getString(KeyEnums.jBR1B4_0.toString()));
                jBR1B5.setText(keyBundle.getString(KeyEnums.jBR1B5_0.toString()));
                jBR1B6.setText(keyBundle.getString(KeyEnums.jBR1B6_0.toString()));
                jBR1B7.setText(keyBundle.getString(KeyEnums.jBR1B7_0.toString()));
                jBR1B8.setText(keyBundle.getString(KeyEnums.jBR1B8_0.toString()));
                jBR1B9.setText(keyBundle.getString(KeyEnums.jBR1B9_0.toString()));
                jBR1B10.setText(keyBundle.getString(KeyEnums.jBR1B10_0.toString()));
                jBR1B11.setText(keyBundle.getString(KeyEnums.jBR1B11_0.toString()));
                jBR1B12.setText(keyBundle.getString(KeyEnums.jBR1B12_0.toString()));
                jBR1B13.setText(keyBundle.getString(KeyEnums.jBR1B13_0.toString()));
//              jBBackspace.setText(keyBundle.getString(KeyEnums.jBR1BACKSPACE_0.toString()));
                //ROW2*********************************************************************
//              jBR2B1.setText(keyBundle.getString(KeyEnums.jBR2B1_0.toString())); //Tab Key
                jBR2B2.setText(keyBundle.getString(KeyEnums.jBR2B2_0.toString()));
                jBR2B3.setText(keyBundle.getString(KeyEnums.jBR2B3_0.toString()));
                jBR2B4.setText(keyBundle.getString(KeyEnums.jBR2B4_0.toString()));
                jBR2B5.setText(keyBundle.getString(KeyEnums.jBR2B5_0.toString()));
                jBR2B6.setText(keyBundle.getString(KeyEnums.jBR2B6_0.toString()));
                jBR2B7.setText(keyBundle.getString(KeyEnums.jBR2B7_0.toString()));
                jBR2B8.setText(keyBundle.getString(KeyEnums.jBR2B8_0.toString()));
                jBR2B9.setText(keyBundle.getString(KeyEnums.jBR2B9_0.toString()));
                jBR2B10.setText(keyBundle.getString(KeyEnums.jBR2B10_0.toString()));
                jBR2B11.setText(keyBundle.getString(KeyEnums.jBR2B11_0.toString()));
                jBR2B12.setText(keyBundle.getString(KeyEnums.jBR2B12_0.toString()));
                jBR2B13.setText(keyBundle.getString(KeyEnums.jBR2B13_0.toString()));
//              jBEnter.setText(keyBundle.getString(KeyEnums.jBENTER_0.toString()));
                //ROW3*********************************************************************
//              jBR3B1.setText(keyBundle.getString(KeyEnums.jBR3B1_0.toString())); //Cap Key
                jBR3B2.setText(keyBundle.getString(KeyEnums.jBR3B2_0.toString()));
                jBR3B3.setText(keyBundle.getString(KeyEnums.jBR3B3_0.toString()));
                jBR3B4.setText(keyBundle.getString(KeyEnums.jBR3B4_0.toString()));
                jBR3B5.setText(keyBundle.getString(KeyEnums.jBR3B5_0.toString()));
                jBR3B6.setText(keyBundle.getString(KeyEnums.jBR3B6_0.toString()));
                jBR3B7.setText(keyBundle.getString(KeyEnums.jBR3B7_0.toString()));
                jBR3B8.setText(keyBundle.getString(KeyEnums.jBR3B8_0.toString()));
                jBR3B9.setText(keyBundle.getString(KeyEnums.jBR3B9_0.toString()));
                jBR3B10.setText(keyBundle.getString(KeyEnums.jBR3B10_0.toString()));
                jBR3B11.setText(keyBundle.getString(KeyEnums.jBR3B11_0.toString()));
                jBR3B12.setText(keyBundle.getString(KeyEnums.jBR3B12_0.toString()));
                jBR3B13.setText(keyBundle.getString(KeyEnums.jBR3B13_0.toString()));
                //ROW4*********************************************************************
//              jBR4B1.setText(keyBundle.getString(KeyEnums.jBR4B1_0.toString())); //Shift Key
                jBR4B2.setText(keyBundle.getString(KeyEnums.jBR4B2_0.toString()));
                jBR4B3.setText(keyBundle.getString(KeyEnums.jBR4B3_0.toString()));
                jBR4B4.setText(keyBundle.getString(KeyEnums.jBR4B4_0.toString()));
                jBR4B5.setText(keyBundle.getString(KeyEnums.jBR4B5_0.toString()));
                jBR4B6.setText(keyBundle.getString(KeyEnums.jBR4B6_0.toString()));
                jBR4B7.setText(keyBundle.getString(KeyEnums.jBR4B7_0.toString()));
                jBR4B8.setText(keyBundle.getString(KeyEnums.jBR4B8_0.toString()));
                jBR4B9.setText(keyBundle.getString(KeyEnums.jBR4B9_0.toString()));
                jBR4B10.setText(keyBundle.getString(KeyEnums.jBR4B10_0.toString()));
                jBR4B11.setText(keyBundle.getString(KeyEnums.jBR4B11_0.toString()));
                jBR4B12.setText(keyBundle.getString(KeyEnums.jBR4B12_0.toString()));
//              jBR4B13.setText(keyBundle.getString(KeyEnums.jBR4B13_0.toString())); //Shift Key
                //ROW5*********************************************************************
//              jBCtrl.setText(keyBundle.getString(KeyEnums.jBCTRL_0.toString()));  //Ctrl Key
//              jBSpace.setText(keyBundle.getString(KeyEnums.jBSPACE_0.toString()));  //Space Key
              jTBAlt_Gr.setText(keyBundle.getString(KeyEnums.jTBALT_GR_0.toString()));  //Alt-Gr Key
                break;
            case 2:
                //ROW1*********************************************************************
                jBR1B1.setText(keyBundle.getString(KeyEnums.jBR1B1_1.toString()));
                jBR1B2.setText(keyBundle.getString(KeyEnums.jBR1B2_1.toString()));
                jBR1B3.setText(keyBundle.getString(KeyEnums.jBR1B3_1.toString()));
                jBR1B4.setText(keyBundle.getString(KeyEnums.jBR1B4_1.toString()));
                jBR1B5.setText(keyBundle.getString(KeyEnums.jBR1B5_1.toString()));
                jBR1B6.setText(keyBundle.getString(KeyEnums.jBR1B6_1.toString()));
                jBR1B7.setText(keyBundle.getString(KeyEnums.jBR1B7_1.toString()));
                jBR1B8.setText(keyBundle.getString(KeyEnums.jBR1B8_1.toString()));
                jBR1B9.setText(keyBundle.getString(KeyEnums.jBR1B9_1.toString()));
                jBR1B10.setText(keyBundle.getString(KeyEnums.jBR1B10_1.toString()));
                jBR1B11.setText(keyBundle.getString(KeyEnums.jBR1B11_1.toString()));
                jBR1B12.setText(keyBundle.getString(KeyEnums.jBR1B12_1.toString()));
                jBR1B13.setText(keyBundle.getString(KeyEnums.jBR1B13_1.toString()));
//              jBBackspace.setText(keyBundle.getString(KeyEnums.jBR1BACKSPACE_1.toString()));
                //ROW2*********************************************************************
//                jBR2B1.setText(keyBundle.getString(KeyEnums.jBR2B1_1.toString())); //Tab Key
                jBR2B2.setText(keyBundle.getString(KeyEnums.jBR2B2_1.toString()));
                jBR2B3.setText(keyBundle.getString(KeyEnums.jBR2B3_1.toString()));
                jBR2B4.setText(keyBundle.getString(KeyEnums.jBR2B4_1.toString()));
                jBR2B5.setText(keyBundle.getString(KeyEnums.jBR2B5_1.toString()));
                jBR2B6.setText(keyBundle.getString(KeyEnums.jBR2B6_1.toString()));
                jBR2B7.setText(keyBundle.getString(KeyEnums.jBR2B7_1.toString()));
                jBR2B8.setText(keyBundle.getString(KeyEnums.jBR2B8_1.toString()));
                jBR2B9.setText(keyBundle.getString(KeyEnums.jBR2B9_1.toString()));
                jBR2B10.setText(keyBundle.getString(KeyEnums.jBR2B10_1.toString()));
                jBR2B11.setText(keyBundle.getString(KeyEnums.jBR2B11_1.toString()));
                jBR2B12.setText(keyBundle.getString(KeyEnums.jBR2B12_1.toString()));
                jBR2B13.setText(keyBundle.getString(KeyEnums.jBR2B13_1.toString()));
//              jBEnter.setText(keyBundle.getString(KeyEnums.jBENTER_1.toString()));
                //ROW3*********************************************************************
//              jBR3B1.setText(keyBundle.getString(KeyEnums.jBR3B1_1.toString())); //Cap Key
                jBR3B2.setText(keyBundle.getString(KeyEnums.jBR3B2_1.toString()));
                jBR3B3.setText(keyBundle.getString(KeyEnums.jBR3B3_1.toString()));
                jBR3B4.setText(keyBundle.getString(KeyEnums.jBR3B4_1.toString()));
                jBR3B5.setText(keyBundle.getString(KeyEnums.jBR3B5_1.toString()));
                jBR3B6.setText(keyBundle.getString(KeyEnums.jBR3B6_1.toString()));
                jBR3B7.setText(keyBundle.getString(KeyEnums.jBR3B7_1.toString()));
                jBR3B8.setText(keyBundle.getString(KeyEnums.jBR3B8_1.toString()));
                jBR3B9.setText(keyBundle.getString(KeyEnums.jBR3B9_1.toString()));
                jBR3B10.setText(keyBundle.getString(KeyEnums.jBR3B10_1.toString()));
                jBR3B11.setText(keyBundle.getString(KeyEnums.jBR3B11_1.toString()));
                jBR3B12.setText(keyBundle.getString(KeyEnums.jBR3B12_1.toString()));
                jBR3B13.setText(keyBundle.getString(KeyEnums.jBR3B13_1.toString()));
                //ROW4*********************************************************************
//              jBR4B1.setText(keyBundle.getString(KeyEnums.jBR4B1_1.toString())); //Shift Key
                jBR4B2.setText(keyBundle.getString(KeyEnums.jBR4B2_1.toString()));
                jBR4B3.setText(keyBundle.getString(KeyEnums.jBR4B3_1.toString()));
                jBR4B4.setText(keyBundle.getString(KeyEnums.jBR4B4_1.toString()));
                jBR4B5.setText(keyBundle.getString(KeyEnums.jBR4B5_1.toString()));
                jBR4B6.setText(keyBundle.getString(KeyEnums.jBR4B6_1.toString()));
                jBR4B7.setText(keyBundle.getString(KeyEnums.jBR4B7_1.toString()));
                jBR4B8.setText(keyBundle.getString(KeyEnums.jBR4B8_1.toString()));
                jBR4B9.setText(keyBundle.getString(KeyEnums.jBR4B9_1.toString()));
                jBR4B10.setText(keyBundle.getString(KeyEnums.jBR4B10_1.toString()));
                jBR4B11.setText(keyBundle.getString(KeyEnums.jBR4B11_1.toString()));
                jBR4B12.setText(keyBundle.getString(KeyEnums.jBR4B12_1.toString()));
//              jBR4B13.setText(keyBundle.getString(KeyEnums.jBR4B13_1.toString())); //Shift Key
                //ROW5*********************************************************************
//              jBCtrl.setText(keyBundle.getString(KeyEnums.jBCTRL_1.toString()));  //Ctrl Key
//              jBSpace.setText(keyBundle.getString(KeyEnums.jBSPACE_1.toString()));  //Space Key
              jTBAlt_Gr.setText(keyBundle.getString(KeyEnums.jTBALT_GR_1.toString()));  //Alt-Gr Key
                break;
        }
    }

     @Override
  protected void paintComponent( Graphics g )
  {
         //2D graphic Component loading
        Graphics2D g2D = (Graphics2D) g;
        super.paintComponent( g2D );

        //Height and width of Panel
        int lHeight=this.getHeight();
        int lWidth =this.getWidth();
        
        int width = lWidth/15-gap;
        int height = lHeight/5-gap;

        int x = gap;
        int y = gap;

        //Row 1
        jBR1B1.setBounds(x, y, width, height);
            x += width+gap;
        jBR1B2.setBounds(x, y, width, height);
            x += width+gap;
        jBR1B3.setBounds(x, y, width, height);
            x += width+gap;
        jBR1B4.setBounds(x, y, width, height);
            x += width+gap;
        jBR1B5.setBounds(x, y, width, height);
            x += width+gap;
        jBR1B6.setBounds(x, y, width, height);
            x += width+gap;
        jBR1B7.setBounds(x, y, width, height);
            x += width+gap;
        jBR1B8.setBounds(x, y, width, height);
            x += width+gap;
        jBR1B9.setBounds(x, y, width, height);
            x += width+gap;
        jBR1B10.setBounds(x, y, width, height);
            x += width+gap;
        jBR1B11.setBounds(x, y, width, height);
            x += width+gap;
        jBR1B12.setBounds(x, y, width, height);
            x += width+gap;
        jBR1B13.setBounds(x, y, width, height);
            x += width+gap;
        jBBackspace.setBounds(x, y, width*2, height);

        //Row 2
            x = gap;
            y += height+gap;

        jBR2B1.setBounds(x, y, width+gap+width/2, height);
            x += width+gap+width/2+gap;
        jBR2B2.setBounds(x, y, width, height);
            x += width+gap;
        jBR2B3.setBounds(x, y, width, height);
            x += width+gap;
        jBR2B4.setBounds(x, y, width, height);
            x += width+gap;
        jBR2B5.setBounds(x, y, width, height);
            x += width+gap;
        jBR2B6.setBounds(x, y, width, height);
            x += width+gap;
        jBR2B7.setBounds(x, y, width, height);
            x += width+gap;
        jBR2B8.setBounds(x, y, width, height);
            x += width+gap;
        jBR2B9.setBounds(x, y, width, height);
            x += width+gap;
        jBR2B10.setBounds(x, y, width, height);
            x += width+gap;
        jBR2B11.setBounds(x, y, width, height);
            x += width+gap;
        jBR2B12.setBounds(x, y, width, height);
            x += width+gap;
        jBR2B13.setBounds(x, y, width, height);
            x += width+gap;
        jBEnter.setBounds(x, y, width+width/2-gap, height+gap+height);

        //Row 3
            x = gap;
            y += height+gap;

        jTBR3B1.setBounds(x, y, width+gap+width/2, height);
            x += width+gap+width/2+gap;
        jBR3B2.setBounds(x, y, width, height);
            x += width+gap;
        jBR3B3.setBounds(x, y, width, height);
            x += width+gap;
        jBR3B4.setBounds(x, y, width, height);
            x += width+gap;
        jBR3B5.setBounds(x, y, width, height);
            x += width+gap;
        jBR3B6.setBounds(x, y, width, height);
            x += width+gap;
        jBR3B7.setBounds(x, y, width, height);
            x += width+gap;
        jBR3B8.setBounds(x, y, width, height);
            x += width+gap;
        jBR3B9.setBounds(x, y, width, height);
            x += width+gap;
        jBR3B10.setBounds(x, y, width, height);
            x += width+gap;
        jBR3B11.setBounds(x, y, width, height);
            x += width+gap;
        jBR3B12.setBounds(x, y, width, height);
            x += width+gap;
        jBR3B13.setBounds(x, y, width, height);

        //Row 4
            x = gap;
            y += height+gap;

        jTBR4B1.setBounds(x, y, width*2+gap, height);
            x += width*2+gap+gap;
        jBR4B2.setBounds(x, y, width, height);
            x += width+gap;
        jBR4B3.setBounds(x, y, width, height);
            x += width+gap;
        jBR4B4.setBounds(x, y, width, height);
            x += width+gap;
        jBR4B5.setBounds(x, y, width, height);
            x += width+gap;
        jBR4B6.setBounds(x, y, width, height);
            x += width+gap;
        jBR4B7.setBounds(x, y, width, height);
            x += width+gap;
        jBR4B8.setBounds(x, y, width, height);
            x += width+gap;
        jBR4B9.setBounds(x, y, width, height);
            x += width+gap;
        jBR4B10.setBounds(x, y, width, height);
            x += width+gap;
        jBR4B11.setBounds(x, y, width, height);
            x += width+gap;
        jBR4B12.setBounds(x, y, width, height);
            x += width+gap;
        jTBR4B13.setBounds(x, y, width*2, height);

        //Row 5
            x = gap;
            y += height+gap;
            
        jBCtrl.setBounds(x, y, width*2+width/2+gap*2, height);
            x += width*5+gap*5;
        jBSpace.setBounds(x, y, width*5+gap*4, height);
            x += width*5+gap*5;
        jTBAlt_Gr.setBounds(x, y, width+width/2+gap, height);
            x += width*2+width/2+gap*2;
        jBExe.setBounds(x, y, width*2+width/2+gap, height);
     }

    /**
     * Get the value of locale
     *
     * @return the value of locale
     */
    public Locale getLocaleL() {
        return localeL;
    }

    /**
     * Set the value of locale
     *
     * @param locale new value of locale
     */
    public void setLocaleL(Locale localeL) {
        this.localeL = localeL;
        try {
            keyBundle = ResourceBundle.getBundle("bildschirmtastatur.lang.Keyboard", this.localeL);
        } catch (MissingResourceException e) {
            System.err.println(e);
        }
        updateGUI();
    }

    public JTextComponent getTextComponent() {
        return textComponent;
    }

    public void setTextComponent(JTextComponent textComponent) {
        this.textComponent = textComponent;
    }

    public Window getWindow() {
        return window;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public boolean isPoitToUp() {
        return poitToUp;
    }

    public void setPoitToUp(boolean poitToUp) {
        this.poitToUp = poitToUp;
    }

    public boolean isShiftBs() {
        return shiftBs;
    }

    public void setShiftBs(boolean shiftBs) {
        this.shiftBs = shiftBs;
    }

    public int getGap() {
        return gap;
    }

    public void setGap(int gap) {
        this.gap = gap;
    }

    public int getTab() {
        return tab;
    }

    public void setTab(int tab) {
        this.tab = tab;
    }
    
     /**
     * Write value to the JTextComponent to the right Caret position
     *
     * @param evt
     */
    private void writeValue(java.awt.event.ActionEvent evt){
        if(textComponent==null) return;
        if(((((JButton)evt.getSource()).getText())).equals("")) return;
        String text = textComponent.getText();        
        int index = textComponent.getCaretPosition();
        text = new StringBuffer(text).insert(index,
            ((JButton)evt.getSource()).getText()).toString();
        textComponent.setText(text);
        textComponent.setCaretPosition(index+(((JButton)evt.getSource()).getText()).toString().length());

        if(jTBR4B13.isSelected() && !jTBAlt_Gr.isSelected()){
            if(layer==0)layer =1;
            else layer=0;
            jTBR4B13.setSelected(false);
            jTBR4B1.setSelected(false);
        }

        if(poitToUp){
            if(((JButton)evt.getSource()).getText().equals(".") && layer==0){
                layer = 1;
                jTBR4B1.setSelected(true);
                jTBR4B13.setSelected(true);
                updateGUI();
            }
        }
        updateGUI();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBR1B1 = new javax.swing.JButton();
        jBR1B2 = new javax.swing.JButton();
        jBR1B3 = new javax.swing.JButton();
        jBR1B4 = new javax.swing.JButton();
        jBR1B5 = new javax.swing.JButton();
        jBR1B6 = new javax.swing.JButton();
        jBR1B7 = new javax.swing.JButton();
        jBR1B8 = new javax.swing.JButton();
        jBR1B9 = new javax.swing.JButton();
        jBR1B10 = new javax.swing.JButton();
        jBR1B11 = new javax.swing.JButton();
        jBR1B12 = new javax.swing.JButton();
        jBR1B13 = new javax.swing.JButton();
        jBBackspace = new javax.swing.JButton();
        jBR2B1 = new javax.swing.JButton();
        jBR2B2 = new javax.swing.JButton();
        jBR2B3 = new javax.swing.JButton();
        jBR2B4 = new javax.swing.JButton();
        jBR2B5 = new javax.swing.JButton();
        jBR2B6 = new javax.swing.JButton();
        jBR2B7 = new javax.swing.JButton();
        jBR2B8 = new javax.swing.JButton();
        jBR2B9 = new javax.swing.JButton();
        jBR2B10 = new javax.swing.JButton();
        jBR2B11 = new javax.swing.JButton();
        jBR2B12 = new javax.swing.JButton();
        jBR2B13 = new javax.swing.JButton();
        jBEnter = new javax.swing.JButton();
        jTBR3B1 = new javax.swing.JToggleButton();
        jBR3B2 = new javax.swing.JButton();
        jBR3B3 = new javax.swing.JButton();
        jBR3B4 = new javax.swing.JButton();
        jBR3B5 = new javax.swing.JButton();
        jBR3B6 = new javax.swing.JButton();
        jBR3B7 = new javax.swing.JButton();
        jBR3B8 = new javax.swing.JButton();
        jBR3B9 = new javax.swing.JButton();
        jBR3B10 = new javax.swing.JButton();
        jBR3B11 = new javax.swing.JButton();
        jBR3B12 = new javax.swing.JButton();
        jBR3B13 = new javax.swing.JButton();
        jTBR4B1 = new javax.swing.JToggleButton();
        jBR4B2 = new javax.swing.JButton();
        jBR4B3 = new javax.swing.JButton();
        jBR4B4 = new javax.swing.JButton();
        jBR4B5 = new javax.swing.JButton();
        jBR4B6 = new javax.swing.JButton();
        jBR4B7 = new javax.swing.JButton();
        jBR4B8 = new javax.swing.JButton();
        jBR4B9 = new javax.swing.JButton();
        jBR4B10 = new javax.swing.JButton();
        jBR4B11 = new javax.swing.JButton();
        jBR4B12 = new javax.swing.JButton();
        jTBR4B13 = new javax.swing.JToggleButton();
        jBCtrl = new javax.swing.JButton();
        jBSpace = new javax.swing.JButton();
        jTBAlt_Gr = new javax.swing.JToggleButton();
        jBExe = new javax.swing.JButton();

        jBR1B1.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR1B1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR1B1ActionPerformed(evt);
            }
        });

        jBR1B2.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR1B2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR1B2ActionPerformed(evt);
            }
        });

        jBR1B3.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR1B3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR1B3ActionPerformed(evt);
            }
        });

        jBR1B4.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR1B4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR1B4ActionPerformed(evt);
            }
        });

        jBR1B5.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR1B5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR1B5ActionPerformed(evt);
            }
        });

        jBR1B6.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR1B6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR1B6ActionPerformed(evt);
            }
        });

        jBR1B7.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR1B7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR1B7ActionPerformed(evt);
            }
        });

        jBR1B8.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR1B8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR1B8ActionPerformed(evt);
            }
        });

        jBR1B9.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR1B9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR1B9ActionPerformed(evt);
            }
        });

        jBR1B10.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR1B10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR1B10ActionPerformed(evt);
            }
        });

        jBR1B11.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR1B11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR1B11ActionPerformed(evt);
            }
        });

        jBR1B12.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR1B12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR1B12ActionPerformed(evt);
            }
        });

        jBR1B13.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR1B13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR1B13ActionPerformed(evt);
            }
        });

        jBBackspace.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBBackspace.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bildschirmtastatur/images/backspace.gif"))); // NOI18N
        jBBackspace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBackspaceActionPerformed(evt);
            }
        });

        jBR2B1.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR2B1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bildschirmtastatur/images/tab.gif"))); // NOI18N
        jBR2B1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR2B1ActionPerformed(evt);
            }
        });

        jBR2B2.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR2B2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR2B2ActionPerformed(evt);
            }
        });

        jBR2B3.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR2B3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR2B3ActionPerformed(evt);
            }
        });

        jBR2B4.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR2B4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR2B4ActionPerformed(evt);
            }
        });

        jBR2B5.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR2B5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR2B5ActionPerformed(evt);
            }
        });

        jBR2B6.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR2B6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR2B6ActionPerformed(evt);
            }
        });

        jBR2B7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBR2B7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR2B7ActionPerformed(evt);
            }
        });

        jBR2B8.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR2B8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR2B8ActionPerformed(evt);
            }
        });

        jBR2B9.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR2B9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR2B9ActionPerformed(evt);
            }
        });

        jBR2B10.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR2B10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR2B10ActionPerformed(evt);
            }
        });

        jBR2B11.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR2B11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR2B11ActionPerformed(evt);
            }
        });

        jBR2B12.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR2B12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR2B12ActionPerformed(evt);
            }
        });

        jBR2B13.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR2B13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR2B13ActionPerformed(evt);
            }
        });

        jBEnter.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBEnter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bildschirmtastatur/images/enter.gif"))); // NOI18N
        jBEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEnterActionPerformed(evt);
            }
        });

        jTBR3B1.setFont(new java.awt.Font("Tahoma", 0, 18));
        jTBR3B1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bildschirmtastatur/images/shiftInvert.gif"))); // NOI18N
        jTBR3B1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTBR3B1ActionPerformed(evt);
            }
        });

        jBR3B2.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR3B2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR3B2ActionPerformed(evt);
            }
        });

        jBR3B3.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR3B3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR3B3ActionPerformed(evt);
            }
        });

        jBR3B4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBR3B4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR3B4ActionPerformed(evt);
            }
        });

        jBR3B5.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR3B5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR3B5ActionPerformed(evt);
            }
        });

        jBR3B6.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR3B6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR3B6ActionPerformed(evt);
            }
        });

        jBR3B7.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR3B7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR3B7ActionPerformed(evt);
            }
        });

        jBR3B8.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR3B8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR3B8ActionPerformed(evt);
            }
        });

        jBR3B9.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR3B9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR3B9ActionPerformed(evt);
            }
        });

        jBR3B10.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR3B10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR3B10ActionPerformed(evt);
            }
        });

        jBR3B11.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR3B11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR3B11ActionPerformed(evt);
            }
        });

        jBR3B12.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR3B12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR3B12ActionPerformed(evt);
            }
        });

        jBR3B13.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR3B13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR3B13ActionPerformed(evt);
            }
        });

        jTBR4B1.setFont(new java.awt.Font("Tahoma", 0, 18));
        jTBR4B1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bildschirmtastatur/images/shift.gif"))); // NOI18N
        jTBR4B1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBR4B1MouseClicked(evt);
            }
        });
        jTBR4B1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTBR4B1ActionPerformed(evt);
            }
        });

        jBR4B2.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR4B2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR4B2ActionPerformed(evt);
            }
        });

        jBR4B3.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR4B3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR4B3ActionPerformed(evt);
            }
        });

        jBR4B4.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR4B4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR4B4ActionPerformed(evt);
            }
        });

        jBR4B5.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR4B5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR4B5ActionPerformed(evt);
            }
        });

        jBR4B6.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR4B6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR4B6ActionPerformed(evt);
            }
        });

        jBR4B7.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR4B7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR4B7ActionPerformed(evt);
            }
        });

        jBR4B8.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR4B8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR4B8ActionPerformed(evt);
            }
        });

        jBR4B9.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR4B9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR4B9ActionPerformed(evt);
            }
        });

        jBR4B10.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR4B10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR4B10ActionPerformed(evt);
            }
        });

        jBR4B11.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR4B11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR4B11ActionPerformed(evt);
            }
        });

        jBR4B12.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBR4B12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBR4B12ActionPerformed(evt);
            }
        });

        jTBR4B13.setFont(new java.awt.Font("Tahoma", 0, 18));
        jTBR4B13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bildschirmtastatur/images/shift.gif"))); // NOI18N
        jTBR4B13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBR4B13MouseClicked(evt);
            }
        });
        jTBR4B13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTBR4B13ActionPerformed(evt);
            }
        });

        jBCtrl.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBCtrl.addActionListener(al);

        jBSpace.setFont(new java.awt.Font("Tahoma", 0, 18));
        jBSpace.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bildschirmtastatur/images/space.gif"))); // NOI18N
        jBSpace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSpaceActionPerformed(evt);
            }
        });

        jTBAlt_Gr.setFont(new java.awt.Font("Tahoma", 0, 18));
        jTBAlt_Gr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBAlt_GrMouseClicked(evt);
            }
        });
        jTBAlt_Gr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTBAlt_GrActionPerformed(evt);
            }
        });

        jBExe.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBExe.setText("Speichern");
        jBExe.addActionListener(al);
            
        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jBR2B5)
                        .addGap(5, 5, 5)
                        .addComponent(jBR2B6)
                        .addGap(5, 5, 5)
                        .addComponent(jBR2B7)
                        .addGap(5, 5, 5)
                        .addComponent(jBR2B8)
                        .addGap(5, 5, 5)
                        .addComponent(jBR2B9)
                        .addGap(5, 5, 5)
                        .addComponent(jBR2B10)
                        .addGap(5, 5, 5)
                        .addComponent(jBR2B11)
                        .addGap(5, 5, 5)
                        .addComponent(jBR2B12)
                        .addGap(5, 5, 5)
                        .addComponent(jBR2B13))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jBEnter)
                        .addGap(5, 5, 5)
                        .addComponent(jTBR3B1)
                        .addGap(5, 5, 5)
                        .addComponent(jBR3B2)
                        .addGap(5, 5, 5)
                        .addComponent(jBR3B3)
                        .addGap(5, 5, 5)
                        .addComponent(jBR3B4)
                        .addGap(5, 5, 5)
                        .addComponent(jBR3B5)
                        .addGap(5, 5, 5)
                        .addComponent(jBR3B6)
                        .addGap(5, 5, 5)
                        .addComponent(jBR3B7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jBR3B8)
                        .addGap(5, 5, 5)
                        .addComponent(jBR3B9)
                        .addGap(5, 5, 5)
                        .addComponent(jBR3B10)
                        .addGap(5, 5, 5)
                        .addComponent(jBR3B11)
                        .addGap(5, 5, 5)
                        .addComponent(jBR3B12)
                        .addGap(5, 5, 5)
                        .addComponent(jBR3B13)
                        .addGap(5, 5, 5)
                        .addComponent(jTBR4B1)
                        .addGap(5, 5, 5)
                        .addComponent(jBR4B2)
                        .addGap(5, 5, 5)
                        .addComponent(jBR4B3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jBR4B4)
                        .addGap(5, 5, 5)
                        .addComponent(jBR4B5)
                        .addGap(5, 5, 5)
                        .addComponent(jBR4B6)
                        .addGap(5, 5, 5)
                        .addComponent(jBR4B7)
                        .addGap(5, 5, 5)
                        .addComponent(jBR4B8)
                        .addGap(5, 5, 5)
                        .addComponent(jBR4B9)
                        .addGap(5, 5, 5)
                        .addComponent(jBR4B10)
                        .addGap(5, 5, 5)
                        .addComponent(jBR4B11)
                        .addGap(5, 5, 5)
                        .addComponent(jBR4B12))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jBR1B1)
                                .addGap(5, 5, 5)
                                .addComponent(jBR1B2)
                                .addGap(5, 5, 5)
                                .addComponent(jBR1B3)
                                .addGap(5, 5, 5)
                                .addComponent(jBR1B4)
                                .addGap(5, 5, 5)
                                .addComponent(jBR1B5)
                                .addGap(5, 5, 5)
                                .addComponent(jBR1B6)
                                .addGap(5, 5, 5)
                                .addComponent(jBR1B7)
                                .addGap(5, 5, 5)
                                .addComponent(jBR1B8)
                                .addGap(5, 5, 5)
                                .addComponent(jBR1B9)
                                .addGap(5, 5, 5)
                                .addComponent(jBR1B10))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jBR1B11)
                                .addGap(5, 5, 5)
                                .addComponent(jBR1B12)
                                .addGap(5, 5, 5)
                                .addComponent(jBR1B13)
                                .addGap(5, 5, 5)
                                .addComponent(jBBackspace)
                                .addGap(5, 5, 5)
                                .addComponent(jBR2B1)
                                .addGap(5, 5, 5)
                                .addComponent(jBR2B2)
                                .addGap(5, 5, 5)
                                .addComponent(jBR2B3)
                                .addGap(5, 5, 5)
                                .addComponent(jBR2B4))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(102, 102, 102)
                                .addComponent(jTBR4B13)
                                .addGap(5, 5, 5)
                                .addComponent(jBCtrl)
                                .addGap(5, 5, 5)
                                .addComponent(jBSpace)
                                .addGap(5, 5, 5)
                                .addComponent(jTBAlt_Gr)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBExe)))
                .addContainerGap(245, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBR1B1)
                    .addComponent(jBR1B2)
                    .addComponent(jBR1B3)
                    .addComponent(jBR1B4)
                    .addComponent(jBR1B5)
                    .addComponent(jBR1B6)
                    .addComponent(jBR1B7)
                    .addComponent(jBR1B8)
                    .addComponent(jBR1B9)
                    .addComponent(jBR1B10))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jBR1B11))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jBR1B12))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jBR1B13))
                    .addComponent(jBBackspace)
                    .addComponent(jBR2B1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jBR2B2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jBR2B3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jBR2B4)))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBR2B5)
                    .addComponent(jBR2B6)
                    .addComponent(jBR2B7)
                    .addComponent(jBR2B8)
                    .addComponent(jBR2B9)
                    .addComponent(jBR2B10)
                    .addComponent(jBR2B11)
                    .addComponent(jBR2B12)
                    .addComponent(jBR2B13))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBEnter)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTBR3B1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jBR3B2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jBR3B3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jBR3B4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jBR3B5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jBR3B6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jBR3B7)))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jBR3B8))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jBR3B9))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jBR3B10))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jBR3B11))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jBR3B12))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jBR3B13))
                    .addComponent(jTBR4B1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jBR4B2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jBR4B3)))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBR4B4)
                    .addComponent(jBR4B5)
                    .addComponent(jBR4B6)
                    .addComponent(jBR4B7)
                    .addComponent(jBR4B8)
                    .addComponent(jBR4B9)
                    .addComponent(jBR4B10)
                    .addComponent(jBR4B11)
                    .addComponent(jBR4B12))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTBR4B13))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jBCtrl))
                    .addComponent(jBSpace)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jBExe)
                        .addComponent(jTBAlt_Gr)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTBR4B13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTBR4B13ActionPerformed
        if(jTBR3B1.isSelected()){
            if(jTBR4B13.isSelected())
                layer=0;
            else layer=1;            
        }else{
            if(jTBR4B13.isSelected())
                layer=1;
            else layer=0;
        }
        
        jTBAlt_Gr.setSelected(false);
        jTBR4B1.setSelected(jTBR4B13.isSelected());
        updateGUI();
    }//GEN-LAST:event_jTBR4B13ActionPerformed

    private void jTBR4B13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBR4B13MouseClicked

    }//GEN-LAST:event_jTBR4B13MouseClicked

    private void jTBAlt_GrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTBAlt_GrActionPerformed
        if(jTBAlt_Gr.isSelected()){
            layer=2;
        }else if(jTBR3B1.isSelected()){
            if(jTBR4B13.isSelected())
                layer=0;
            else layer=1;
        }else{
            if(jTBR4B13.isSelected())
                layer=1;
            else layer=0;
        }

        jTBR4B1.setSelected(jTBR4B13.isSelected());
        updateGUI();
    }//GEN-LAST:event_jTBAlt_GrActionPerformed

    private void jTBAlt_GrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBAlt_GrMouseClicked
    }//GEN-LAST:event_jTBAlt_GrMouseClicked

    private void jTBR4B1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTBR4B1ActionPerformed
            jTBR4B13.setSelected(jTBR4B1.isSelected());
            jTBR4B13ActionPerformed(evt);
    }//GEN-LAST:event_jTBR4B1ActionPerformed

    private void jTBR4B1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBR4B1MouseClicked
        jTBR4B13.setSelected(jTBR4B1.isSelected());
        jTBR4B13MouseClicked(evt);
    }//GEN-LAST:event_jTBR4B1MouseClicked

    private void jBEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEnterActionPerformed
        if(textComponent==null) return;
        //Delete char on caret position
        String text = textComponent.getText();
        int index = textComponent.getCaretPosition();
        text = new StringBuffer(text).insert(index, "\n").toString();
        textComponent.setText(text);
        textComponent.setCaretPosition(index+1);
    }//GEN-LAST:event_jBEnterActionPerformed

    private void jBR1B1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR1B1ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR1B1ActionPerformed

    private void jBR1B2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR1B2ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR1B2ActionPerformed

    private void jBR1B3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR1B3ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR1B3ActionPerformed

    private void jBR1B4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR1B4ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR1B4ActionPerformed

    private void jBR1B5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR1B5ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR1B5ActionPerformed

    private void jBR1B6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR1B6ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR1B6ActionPerformed

    private void jBR1B7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR1B7ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR1B7ActionPerformed

    private void jBR1B8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR1B8ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR1B8ActionPerformed

    private void jBR1B9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR1B9ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR1B9ActionPerformed

    private void jBR1B10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR1B10ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR1B10ActionPerformed

    private void jBR1B11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR1B11ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR1B11ActionPerformed

    private void jBR1B12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR1B12ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR1B12ActionPerformed

    private void jBR1B13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR1B13ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR1B13ActionPerformed

    private void jBBackspaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBackspaceActionPerformed
        if(textComponent==null) return;
        //Delete char on caret position
        String text = textComponent.getText();
        int index = textComponent.getCaretPosition();
        if(index>0){
            text = new StringBuffer(text).delete(index-1,index).toString();
            textComponent.setText(text);
            textComponent.setCaretPosition(index-1);
        }
    }//GEN-LAST:event_jBBackspaceActionPerformed

    private void jBR2B2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR2B2ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR2B2ActionPerformed

    private void jBR2B3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR2B3ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR2B3ActionPerformed

    private void jBR2B4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR2B4ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR2B4ActionPerformed

    private void jBR2B5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR2B5ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR2B5ActionPerformed

    private void jBR2B6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR2B6ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR2B6ActionPerformed

    private void jBR2B7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR2B7ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR2B7ActionPerformed

    private void jBR2B8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR2B8ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR2B8ActionPerformed

    private void jBR2B9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR2B9ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR2B9ActionPerformed

    private void jBR2B10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR2B10ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR2B10ActionPerformed

    private void jBR2B11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR2B11ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR2B11ActionPerformed

    private void jBR2B12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR2B12ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR2B12ActionPerformed

    private void jBR2B13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR2B13ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR2B13ActionPerformed

    private void jBR3B2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR3B2ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR3B2ActionPerformed

    private void jBR3B3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR3B3ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR3B3ActionPerformed

    private void jBR3B4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR3B4ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR3B4ActionPerformed

    private void jBR3B5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR3B5ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR3B5ActionPerformed

    private void jBR3B6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR3B6ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR3B6ActionPerformed

    private void jBR3B7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR3B7ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR3B7ActionPerformed

    private void jBR3B8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR3B8ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR3B8ActionPerformed

    private void jBR3B9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR3B9ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR3B9ActionPerformed

    private void jBR3B10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR3B10ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR3B10ActionPerformed

    private void jBR3B11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR3B11ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR3B11ActionPerformed

    private void jBR3B12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR3B12ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR3B12ActionPerformed

    private void jBR3B13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR3B13ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR3B13ActionPerformed

    private void jBR4B2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR4B2ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR4B2ActionPerformed

    private void jBR4B3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR4B3ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR4B3ActionPerformed

    private void jBR4B4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR4B4ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR4B4ActionPerformed

    private void jBR4B5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR4B5ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR4B5ActionPerformed

    private void jBR4B6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR4B6ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR4B6ActionPerformed

    private void jBR4B7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR4B7ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR4B7ActionPerformed

    private void jBR4B8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR4B8ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR4B8ActionPerformed

    private void jBR4B9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR4B9ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR4B9ActionPerformed

    private void jBR4B10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR4B10ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR4B10ActionPerformed

    private void jBR4B11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR4B11ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR4B11ActionPerformed

    private void jBR4B12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR4B12ActionPerformed
        writeValue(evt);
    }//GEN-LAST:event_jBR4B12ActionPerformed

    private void jBSpaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSpaceActionPerformed
        if(textComponent==null) return;
        String text = textComponent.getText();
        int index = textComponent.getCaretPosition();
        text = new StringBuffer(text).insert(index,
            " ").toString();
        textComponent.setText(text);
        textComponent.setCaretPosition(index+1);
    }//GEN-LAST:event_jBSpaceActionPerformed

    private void jTBR3B1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTBR3B1ActionPerformed
        if(jTBR3B1.isSelected()){
            if(jTBR4B13.isSelected())
                layer=0;
            else layer=1;
        }else{
            if(jTBR4B13.isSelected())
                layer=1;
            else layer=0;
        }
        jTBAlt_Gr.setSelected(false);
        updateGUI();
    }//GEN-LAST:event_jTBR3B1ActionPerformed

    private void jBR2B1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBR2B1ActionPerformed
        if(textComponent==null) return;
        //Delete char on caret position
        String text = textComponent.getText();
        int index = textComponent.getCaretPosition();
        String tabT="";
        for(int i=0; i<tab;i++)
            tabT+=" ";
        text = new StringBuffer(text).insert(index, tabT).toString();
        textComponent.setText(text);
        textComponent.setCaretPosition(index+tabT.length());
    }//GEN-LAST:event_jBR2B1ActionPerformed

    private void jBExeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBExeActionPerformed
        if(window!=null){
            window.setVisible(false);
            window.dispose();
        }
    }//GEN-LAST:event_jBExeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBBackspace;
    private javax.swing.JButton jBCtrl;
    private javax.swing.JButton jBEnter;
    private javax.swing.JButton jBExe;
    private javax.swing.JButton jBR1B1;
    private javax.swing.JButton jBR1B10;
    private javax.swing.JButton jBR1B11;
    private javax.swing.JButton jBR1B12;
    private javax.swing.JButton jBR1B13;
    private javax.swing.JButton jBR1B2;
    private javax.swing.JButton jBR1B3;
    private javax.swing.JButton jBR1B4;
    private javax.swing.JButton jBR1B5;
    private javax.swing.JButton jBR1B6;
    private javax.swing.JButton jBR1B7;
    private javax.swing.JButton jBR1B8;
    private javax.swing.JButton jBR1B9;
    private javax.swing.JButton jBR2B1;
    private javax.swing.JButton jBR2B10;
    private javax.swing.JButton jBR2B11;
    private javax.swing.JButton jBR2B12;
    private javax.swing.JButton jBR2B13;
    private javax.swing.JButton jBR2B2;
    private javax.swing.JButton jBR2B3;
    private javax.swing.JButton jBR2B4;
    private javax.swing.JButton jBR2B5;
    private javax.swing.JButton jBR2B6;
    private javax.swing.JButton jBR2B7;
    private javax.swing.JButton jBR2B8;
    private javax.swing.JButton jBR2B9;
    private javax.swing.JButton jBR3B10;
    private javax.swing.JButton jBR3B11;
    private javax.swing.JButton jBR3B12;
    private javax.swing.JButton jBR3B13;
    private javax.swing.JButton jBR3B2;
    private javax.swing.JButton jBR3B3;
    private javax.swing.JButton jBR3B4;
    private javax.swing.JButton jBR3B5;
    private javax.swing.JButton jBR3B6;
    private javax.swing.JButton jBR3B7;
    private javax.swing.JButton jBR3B8;
    private javax.swing.JButton jBR3B9;
    private javax.swing.JButton jBR4B10;
    private javax.swing.JButton jBR4B11;
    private javax.swing.JButton jBR4B12;
    private javax.swing.JButton jBR4B2;
    private javax.swing.JButton jBR4B3;
    private javax.swing.JButton jBR4B4;
    private javax.swing.JButton jBR4B5;
    private javax.swing.JButton jBR4B6;
    private javax.swing.JButton jBR4B7;
    private javax.swing.JButton jBR4B8;
    private javax.swing.JButton jBR4B9;
    private javax.swing.JButton jBSpace;
    private javax.swing.JToggleButton jTBAlt_Gr;
    private javax.swing.JToggleButton jTBR3B1;
    private javax.swing.JToggleButton jTBR4B1;
    private javax.swing.JToggleButton jTBR4B13;
    // End of variables declaration//GEN-END:variables
}
