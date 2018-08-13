import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;
import java.io.File;
import java.io.FileFilter;

public class UIFrame {
    private DefaultListModel<String> stringImageList;
    private File[] imageList;
    private FileFilter filter;
    private JFrame jframe;
    private JFileChooser loadChooser;
    private JFileChooser saveChooser;
    private JTextField heightInput;
    private JTextField widthInput;
    private JTextField saveFilePath;
    private JTextField openPath;
    private JButton openBrowse;
    private JButton saveBrowse;
    private JButton saveButton;
    private JProgressBar progressBar;
    private JLabel label;
    private JLabel label_1;
    private JLabel label_2;
    private JLabel label_3;
    private JLabel label_4;
    private JScrollPane scrollPane;
    private JList<String> list;
    private JLabel lblImageFolderLocation;
    private JTextField rowInput;
    private JTextField columnInput;

    public UIFrame() {
        jframe = new JFrame();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable(false);
        jframe.setPreferredSize(new Dimension(495, 389));
        jframe.setMinimumSize(new Dimension(495, 389));
        jframe.setMaximumSize(new Dimension(495, 389));
        jframe.pack();

        loadChooser = new JFileChooser();
        saveChooser = new JFileChooser();

        heightInput = new JTextField();
        heightInput.setColumns(1);

        widthInput = new JTextField();
        widthInput.setColumns(1);

        openPath = new JTextField();
        saveFilePath = new JTextField();

        openBrowse = new JButton("Browse");
        saveBrowse = new JButton("Browse");
        saveButton = new JButton("Save");

        stringImageList = new DefaultListModel<>();
        scrollPane = new JScrollPane();

        progressBar = new JProgressBar();

        label = new JLabel("");
        label_1 = new JLabel("");
        label_2 = new JLabel("");
        label_3 = new JLabel("");
        label_4 = new JLabel("");
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        filter = (file) -> file.isFile() && file.getName().endsWith("png");
        progressBar.setStringPainted(true);
        progressBar.setForeground(Color.GREEN);
        progressBar.setString("Choose a folder which includes png files.");

        openBrowse.addActionListener(e -> {
            SwingUtilities.invokeLater(new Thread(() ->
            {
                loadChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                loadChooser.setDialogTitle("Select photo folder...");
                loadChooser.setAcceptAllFileFilterUsed(false);
                loadChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                loadChooser.showOpenDialog(loadChooser);

                try {
                    imageList = loadChooser.getSelectedFile().listFiles(filter);
                    progressBar.setString("Set the width and height of the animation then Rows and Columns");

                    openPath.setText(loadChooser.getSelectedFile().getAbsolutePath());
                    getUpdateListView();
                } catch (Exception e1) {
                    imageList = null;
                    openPath.setText("");
                    getUpdateListView();
                }

            }));
        });

        saveBrowse.addActionListener(e -> {
            new Thread(() -> {
                try {
                    saveChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                    saveChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                    saveChooser.showSaveDialog(saveChooser);
                    saveFilePath.setText(saveChooser.getSelectedFile().getAbsolutePath());
                }catch(Exception e2)
                {
                    openPath.setText("");
                    getUpdateListView();
                }

                if (!saveChooser.getSelectedFile().getAbsolutePath().equals("") && !loadChooser.getSelectedFile().getAbsolutePath().equals(""))
                    if (!widthInput.getText().equals("") && !heightInput.getText().equals(""))
                        if (!rowInput.getText().equals("") && !columnInput.getText().equals(""))
                            progressBar.setString("Click Save!");
            }).run();
        });

        openPath.setColumns(1);
        saveFilePath.setColumns(1);

        saveButton.addActionListener(e -> new Thread(() -> Main.drawImage()).run());

        JLabel lblWidth = new JLabel("Width:");
        JLabel lblHeight = new JLabel("Height:");

        JLabel lblSaveLocation = new JLabel("Save Location:");
        lblImageFolderLocation = new JLabel("Image Folder Location:");

        rowInput = new JTextField();
        rowInput.setColumns(1);

        columnInput = new JTextField();
        columnInput.setColumns(1);

        JLabel lblColumns = new JLabel("Columns:");
        JLabel lblRows = new JLabel("Rows:");

        GroupLayout groupLayout = new GroupLayout(jframe.getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                                        .addComponent(label_1)
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                        .addComponent(label)
                                                                        .addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                                .addComponent(saveFilePath, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                                .addComponent(saveBrowse)
                                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                                .addComponent(saveButton, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
                                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                                        .addComponent(lblSaveLocation)
                                                                                        .addComponent(widthInput, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                                                                        .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                                                                                .addGroup(groupLayout.createSequentialGroup()
                                                                                                        .addComponent(lblHeight)
                                                                                                        .addGap(24))
                                                                                                .addGroup(groupLayout.createSequentialGroup()
                                                                                                        .addComponent(heightInput, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                                                                                                        .addGap(1)))
                                                                                        .addComponent(lblWidth)
                                                                                        .addComponent(openBrowse, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                                                .addGap(35)
                                                                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                                                        .addComponent(columnInput, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                                                                                                        .addComponent(lblColumns)
                                                                                                        .addComponent(lblRows)
                                                                                                        .addComponent(rowInput, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(117)
                                                                                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
                                                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                                                .addComponent(openPath, GroupLayout.PREFERRED_SIZE, 370, GroupLayout.PREFERRED_SIZE)))))
                                                                .addGap(223)))
                                                .addGap(77)
                                                .addComponent(label_2)
                                                .addGap(65)
                                                .addComponent(label_3)
                                                .addGap(25))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(lblImageFolderLocation)
                                                .addPreferredGap(ComponentPlacement.RELATED)))
                                .addComponent(label_4, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(6)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(label_2)
                                        .addComponent(label_3)
                                        .addComponent(label_4, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(label_1)
                                                        .addComponent(label))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(lblImageFolderLocation)
                                                .addGap(7)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addGap(60)
                                                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                                        .addComponent(lblWidth)
                                                                        .addComponent(lblRows))
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                                        .addComponent(widthInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(rowInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                                        .addComponent(lblHeight)
                                                                        .addComponent(lblColumns))
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                                        .addComponent(heightInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(columnInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                                                                .addComponent(lblSaveLocation)
                                                                .addGap(7))
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                                        .addComponent(openPath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(openBrowse))
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                                                                .addGap(26)))
                                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(saveFilePath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(saveBrowse)
                                                        .addComponent(saveButton))
                                                .addGap(20)
                                                .addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(11)))
                                .addContainerGap())
        );
        list = new JList<>(stringImageList);
        list.setVisibleRowCount(13);

        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setRowHeaderView(list);

        jframe.getContentPane().setLayout(groupLayout);
        jframe.setVisible(true);

    }

    private void getUpdateListView() {

        if (imageList == null) return;

        stringImageList.removeAllElements();

        for (File file : imageList) {
            stringImageList.addElement(file.getName());
        }
    }

    JProgressBar getProgressBar() {
        return progressBar;
    }

    String getSavePathString() {
        return saveFilePath.getText();
    }

    File[] getImageList() {
        return imageList;
    }

    String getHeightString() {
        return heightInput.getText();
    }

    String getWidthString() {
        return widthInput.getText();
    }

    String getColumnString() {
        return columnInput.getText();
    }

    String getRowString() {
        return rowInput.getText();
    }
}
