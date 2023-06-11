import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    // Declaring properties of TextEditor
    JFrame frame;            // for creating a frame of our application window
    JMenuBar menuBar;        // for creating menu bar of our application
    JMenu file, edit;        // menus in menubar
    JTextArea textArea;      // textArea

    // Menus >> MenuItem ( Creating fileMenu items and editMenu Items

    // 1. File Menu items
    JMenuItem newFile, openFile, saveFile;
    // 2. Edit Menu items
    JMenuItem cut, copy, paste, selectAll, close;

    // Creating constructor
    TextEditor(){
        // Initialize a frame
        frame = new JFrame();

        // Initialize a menubar
        menuBar = new JMenuBar();

        // Initialize menus
        file = new JMenu("File");
        edit = new JMenu("Edit");

        // Initialize file menu items
        newFile = new JMenuItem("New File");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save FIle");

        // Adding action Listener to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        // Adding menu items into file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        // Initialize edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        // Adding action listener to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        // Adding menu items into edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // Add menus into menubar
        menuBar.add(file);
        menuBar.add(edit);

        // Initialize textArea
        textArea = new JTextArea();

        // Set Menubar to frame
        frame.setJMenuBar(menuBar);
        // Add textArea to frame
//        frame.add(textArea);

        // Create content Pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        // Add text area to panel
        panel.add(textArea,BorderLayout.CENTER);
        // Create Scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // Add scroll pane to panel
        panel.add(scrollPane);
        //Add panel to frame
        frame.add(panel);

        // Set Dimensions of frame
        frame.setBounds(400,250,400,400);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }

    // ActionListener
    @Override
    public void actionPerformed(ActionEvent actionEvent){

        if(actionEvent.getSource()==cut){
            //Perform cut operation
            textArea.cut();
        }
        if(actionEvent.getSource()==copy){
            //Perform copy operation
            textArea.copy();
        }
        if(actionEvent.getSource()==paste){
            //Perform paste operation
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll){
            //Perform selectAll operation
            textArea.selectAll();
        }
        if(actionEvent.getSource()==close){
            //Perform close operation
            System.exit(0);
        }

        if(actionEvent.getSource()==openFile){
            // Open file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            // If we have clicked on open button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                // Getting selected file
                File file = fileChooser.getSelectedFile();
                // Get the path of selected files
                String filePath = file.getPath();
                try{
                    // Initialize file reader
                    FileReader fileReader= new FileReader(filePath);
                    // Initialized the buffered reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    // Read contents of file line by line
                    while((intermediate = bufferedReader.readLine())!=null) {
                        output+=intermediate+"\n";
                    }

                    // Set the output string to text area
                    textArea.setText(output);
                }
                catch (IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }

        if(actionEvent.getSource()==saveFile){
            // Initialize the file picker
            JFileChooser fileChooser= new JFileChooser("C:");
            // Get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            // Check if we clicked on save button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                // Create s new file with choosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try {
                    FileWriter fileWriter = new FileWriter(file);
                    // Initialize Buffered writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    // Write contets of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

        if(actionEvent.getSource()==newFile){
            TextEditor newTextEditor = new TextEditor();
        }
    }
    public static void main(String[] args) {
        TextEditor textEditor= new TextEditor();
    }
}