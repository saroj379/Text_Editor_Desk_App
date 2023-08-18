/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package text.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;

/**
 *
 * @author princ
 */
public class TextEditor implements ActionListener{
    // declaring of the textEditor properties
    JFrame frame;
    
    JMenuBar menu;
    
    JMenu file, edit, themes;
    
    // File menu items
    JMenuItem newFile, openFile, saveFile, exit;
    
    // Edit menu items
    JMenuItem cut, copy, paste, selectAll, fontSize;
    
    // themes menuitems
    JMenuItem dark, light, moonLight;
    
    // Text area
    JTextArea txtArea;
    
    //scroll bar
    JScrollPane scroll;
    
    // constructor where created all the required GUI
    TextEditor(){
        // initialize a frame
        frame = new JFrame("");
        frame.setTitle("Untitled_Document");
        Image img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("text-editor.png"));
        frame.setIconImage(img);
        
        // initialize a menubar
        menu = new JMenuBar();
        
        // initialize textarea
        txtArea = new JTextArea(33,87);
        
        // initialize menu items
        file = new JMenu("File");
        edit = new JMenu("Edit");
        themes = new JMenu("Themes");
        
        // initialize file menu items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");
        exit = new JMenuItem("Exit");
        
        // adding actionListener to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        
        //add to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        file.add(exit);
        
        // initialize items for edit menu
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        fontSize = new JMenuItem("Font Size");
        
        //adding actionListener to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        fontSize.addActionListener(this);
        
        // themes menuitems
        dark = new JMenuItem("Dark");
        light = new JMenuItem("Light");
        moonLight = new JMenuItem("Moonlight");
        
        // add to themes
        themes.add(dark);
        themes.add(light);
        themes.add(moonLight);
        
        // action perform
        dark.addActionListener(this);
        light.addActionListener(this);
        moonLight.addActionListener(this);
        
        // add to edit
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(fontSize);
        
        // menu add to menuBar
        menu.add(file);
        menu.add(edit);
        menu.add(themes);
        
        //set menuBar to frame
        frame.setJMenuBar(menu);
        // add textarea to frame
//        frame.add(txtArea);
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //added
        panel.add(txtArea, BorderLayout.CENTER);
        // scroll for textarea
        scroll = new JScrollPane(txtArea);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(scroll);
        frame.add(panel);
        frame.setBounds(350, 200, 900, 600);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setResizable(false);
        
        // when exit it will ask to confirm 
        frame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                String txtFieldStr = txtArea.getText();
                System.out.print(txtFieldStr);
                // if there is blank text so not need to save the file
                if(txtFieldStr.length() == 0) frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                // if there is atlist some text then ask for save or not
                else{
                    int opt = JOptionPane.showConfirmDialog(frame, "Do you want to Save?");
                    if(opt == JOptionPane.YES_OPTION) {
                        // initialize file picker
                        JFileChooser fileChooser = new JFileChooser("Desktop:");
                        // get choose option from file chooser
                        int chooseOption = fileChooser.showSaveDialog(null);
                        if(chooseOption == JFileChooser.APPROVE_OPTION){
                            //create a new file with chosen directory path and file name
                            File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                            try{
                                // initialize file writer
                                FileWriter fileWriter = new FileWriter(file);
                                //initialize buffer writer
                                BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
                                //Write contents of text area to file
                                txtArea.write(bufferWriter);
                            }
                            catch(IOException ioException){
                                ioException.printStackTrace();
                            }
                        }
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                    else if(opt == JOptionPane.NO_OPTION) {
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                    else frame.setVisible(true);
                }
            }
        });
    }
    
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        // Edit menu item functions to perform actions
        if(actionEvent.getSource() == cut){
            // perform cut operations
            txtArea.cut();
        }
        if(actionEvent.getSource() == copy){
            // perform cpoy operations
            txtArea.copy();
        }
        if(actionEvent.getSource() == paste){
            // perform paste operations
            txtArea.paste();
        }
        if(actionEvent.getSource() == selectAll){
            // perform select all operations
            txtArea.selectAll();
        }
        if(actionEvent.getSource() == fontSize){
            // to set font
            String sizeOfFont = JOptionPane.showInputDialog(frame,"Enter font size : ");
            if(sizeOfFont != null){
                try{
                    int convertedSize = Integer.parseInt(sizeOfFont);
                    Font font = new Font(Font.SANS_SERIF,Font.PLAIN,convertedSize);
                    txtArea.setFont(font);
                }catch(NumberFormatException el){
                    UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(Font.DIALOG, Font.BOLD, 16)));
                    JOptionPane.showMessageDialog(frame, "<html><div color = red>Invalid formate","",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if(actionEvent.getSource() == exit){
            System.exit(0);
        }
        
        // file menu items fuction for action perform
        if(actionEvent.getSource() == openFile){
            // for openfile
            JFileChooser fileChooser = new JFileChooser("Desktop");
            int chooserOption = fileChooser.showOpenDialog(null);
            if(chooserOption == JFileChooser.APPROVE_OPTION){
                // getting selected a particular file
                File file = fileChooser.getSelectedFile();
                // get the path of selected file
                String filePath = file.getPath();
                String toShowFileName = file.getName();
                frame.setTitle(toShowFileName);
                try{
                    //Initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    //Initialize buffer reader
                    BufferedReader bufferReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    // Rade contents of file line by line
                    while((intermediate = bufferReader.readLine()) != null){
                        output += intermediate+"\n";
                    }
                    txtArea.setText(output);
                }
                catch(IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
            
        }
        if(actionEvent.getSource() == saveFile){
            // initialize file picker
            JFileChooser fileChooser = new JFileChooser("Desktop:");
            // get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //create a new file with chosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    // initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    //initialize buffer writer
                    BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
                    //Write contents of text area to file
                    txtArea.write(bufferWriter);
                }
                catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == newFile){
            TextEditor newTextEditor = new TextEditor();
        }
        
        // for themes
        if(actionEvent.getSource() == dark){
            txtArea.setBackground(Color.black);
            txtArea.setForeground(Color.WHITE);
        }
        if(actionEvent.getSource() == light){
            txtArea.setBackground(Color.white);
            txtArea.setForeground(Color.black);
        }
        if(actionEvent.getSource() == moonLight){
            txtArea.setBackground(new Color(155,207,255));
            txtArea.setForeground(Color.black);
        }
        
    }
    
    public static void main(String[] args) {
        TextEditor te = new TextEditor();
    }
}
