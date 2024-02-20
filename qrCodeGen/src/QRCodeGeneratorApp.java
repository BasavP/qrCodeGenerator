import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class QRCodeGeneratorApp extends JFrame{


    private JTextField urlTextField;
    private JTextField nameTextField;
    private JTextField dobTextField;
    private JTextField emailTextField;
    private JButton generateButton;

    public QRCodeGeneratorApp() {
        setTitle("QR Code Generator");
        setSize(400, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 2));

        // Form header
        JLabel formHeader = new JLabel("QR Code Generator Form");
        formHeader.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(formHeader);
        mainPanel.add(new JLabel()); // Placeholder

        JLabel nameLabel = new JLabel("Name:");
        mainPanel.add(nameLabel);
        nameTextField = new JTextField();
        mainPanel.add(nameTextField);

        JLabel dobLabel = new JLabel("Date of Birth:");
        mainPanel.add(dobLabel);
        dobTextField = new JTextField();
        mainPanel.add(dobTextField);

        JLabel emailLabel = new JLabel("Email:");
        mainPanel.add(emailLabel);
        emailTextField = new JTextField();
        mainPanel.add(emailTextField);

        JLabel urlLabel = new JLabel("URL:");
        mainPanel.add(urlLabel);
        urlTextField = new JTextField();
        mainPanel.add(urlTextField);

        JButton generateButton = new JButton("Generate QR Code");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateQRCode();
            }
        });
        mainPanel.add(generateButton);

        JButton clearButton = new JButton("Clear Form");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        mainPanel.add(clearButton);

        getContentPane().add(mainPanel);
    }

    private void generateQRCode() {
        String url = urlTextField.getText();
        if (!url.isEmpty()) {
            // Call your method to generate QR code here
            String imagePath = generateQRCodeImage(url);
            if (imagePath != null) {
                // Show generated QR code in a new window
                showQRCode(imagePath);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to generate QR code", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a URL", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String generateQRCodeImage(String url) {
        String fileName = UUID.randomUUID().toString() + ".png";
        try {
            createQR(url, fileName, "UTF-8", new HashMap<>(), 300, 300);
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void showQRCode(String imagePath) {
        // Display the QR code image in a new window
        ImageIcon icon = new ImageIcon(imagePath);
        JLabel label = new JLabel(icon);
        JFrame qrCodeFrame = new JFrame("QR Code");
        qrCodeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        qrCodeFrame.getContentPane().add(label);
        qrCodeFrame.pack();
        qrCodeFrame.setVisible(true);
    }

    public  void createQR(String data, String path,
                                String charset, Map hashMap,
                                int height, int width)
            throws WriterException, IOException
    {

        BitMatrix matrix = new MultiFormatWriter().encode(
                new String(data.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, width, height);

        MatrixToImageWriter.writeToFile(
                matrix,
                path.substring(path.lastIndexOf('.') + 1),
                new File(path));
    }
    private void clearForm() {
        nameTextField.setText("");
        dobTextField.setText("");
        emailTextField.setText("");
        urlTextField.setText("");
    }
    // Driver code
    public static void main(String[] args)
            throws Exception
    {
//
//        // The data that the QR code will contain
//        String data = "www.geeksforgeeks.org";
//
//        // The path where the image will get saved
//        String path = "demo123.png";
//
//        // Encoding charset
//        String charset = "UTF-8";
//
//        Map<EncodeHintType, ErrorCorrectionLevel> hashMap
//                = new HashMap<EncodeHintType,
//                ErrorCorrectionLevel>();
//
//        hashMap.put(EncodeHintType.ERROR_CORRECTION,
//                ErrorCorrectionLevel.L);

        // Create the QR code and save
        // in the specified folder
        // as a jpg file
//        createQR(data, path, charset, hashMap, 200, 200);
//        System.out.println("QR Code Generated!!! ");

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QRCodeGeneratorApp().setVisible(true);
            }
        });
    }
}