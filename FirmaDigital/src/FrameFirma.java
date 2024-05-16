
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FrameFirma extends javax.swing.JFrame {

    PrivateKey keyPrivada;
    PublicKey keyPublica;

    Cipher cipher;

    public FrameFirma() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEncriptar = new javax.swing.JButton();
        btnDesencriptar = new javax.swing.JButton();
        btnVerificar = new javax.swing.JButton();
        btnHash = new javax.swing.JButton();
        btnLlave = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnEncriptar.setText("Encriptar");
        btnEncriptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEncriptarActionPerformed(evt);
            }
        });

        btnDesencriptar.setText("Desencriptar");
        btnDesencriptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesencriptarActionPerformed(evt);
            }
        });

        btnVerificar.setText("Verificar");
        btnVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarActionPerformed(evt);
            }
        });

        btnHash.setText("Hash");
        btnHash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHashActionPerformed(evt);
            }
        });

        btnLlave.setText("Generar llave");
        btnLlave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLlaveActionPerformed(evt);
            }
        });

        jLabel1.setText("Hash");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(btnEncriptar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDesencriptar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(btnVerificar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHash, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnLlave, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(222, 222, 222))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(258, 258, 258))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHash)
                    .addComponent(btnVerificar)
                    .addComponent(btnEncriptar)
                    .addComponent(btnDesencriptar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(btnLlave)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLlaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLlaveActionPerformed

        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(1024);
            KeyPair pair = keyGen.generateKeyPair();
            PrivateKey priv = pair.getPrivate();
            PublicKey pub = pair.getPublic();

            byte[] encPriv = priv.getEncoded();
            JFileChooser guardarPriv = new JFileChooser();
            guardarPriv.setDialogTitle("Seleccione donde se va guardar la llave privada.");
            int saveReturnValuePriv = guardarPriv.showSaveDialog(null);
            if (saveReturnValuePriv == JFileChooser.APPROVE_OPTION) {
                File encryptedFile = guardarPriv.getSelectedFile();
                FileOutputStream outputStream = new FileOutputStream(encryptedFile);
                outputStream.write(encPriv);
                outputStream.close();
            }

            byte[] encPub = pub.getEncoded();
            guardarPriv.setDialogTitle("Seleccione donde se va guardar la llave publica.");
            JFileChooser guardarPub = new JFileChooser();
            int saveReturnValuePub = guardarPub.showSaveDialog(null);
            if (saveReturnValuePub == JFileChooser.APPROVE_OPTION) {
                File encryptedFile = guardarPub.getSelectedFile();
                FileOutputStream outputStream = new FileOutputStream(encryptedFile);
                outputStream.write(encPub);
                outputStream.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnLlaveActionPerformed

    private void btnVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarActionPerformed
        try {
            JFileChooser fcPub = new JFileChooser();
            fcPub.setDialogTitle("Seleccione la clave pública.");
            int userSelectionPub = fcPub.showOpenDialog(null);

            if (userSelectionPub == JFileChooser.APPROVE_OPTION) {
                File publicKeyFile = fcPub.getSelectedFile();
                FileInputStream inputStreamPub = new FileInputStream(publicKeyFile);
                byte[] keyBytesPub = new byte[(int) publicKeyFile.length()];
                inputStreamPub.read(keyBytesPub);
                X509EncodedKeySpec specPub = new X509EncodedKeySpec(keyBytesPub);
                KeyFactory keyFactoryPub = KeyFactory.getInstance("RSA");
                keyPublica = keyFactoryPub.generatePublic(specPub);

            }

            JFileChooser fcPriv = new JFileChooser();
            fcPriv.setDialogTitle("Seleccione la llave privada.");
            int userSelectionPriv = fcPriv.showOpenDialog(null);

            if (userSelectionPriv == JFileChooser.APPROVE_OPTION) {
                File privateKeyFile = fcPriv.getSelectedFile();
                FileInputStream inputStreamPriv = new FileInputStream(privateKeyFile);
                byte[] keyBytesPriv = new byte[(int) privateKeyFile.length()];
                inputStreamPriv.read(keyBytesPriv);
                KeyFactory keyFactoryPriv = KeyFactory.getInstance("RSA");
                PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytesPriv);
                keyPrivada = keyFactoryPriv.generatePrivate(spec);

            }

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Seleccione el archivo a verificar.");
            int userSelection = fileChooser.showOpenDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File publicKeyFile = fileChooser.getSelectedFile();
                FileInputStream inputStream = new FileInputStream(publicKeyFile);
                byte[] keyBytes = new byte[(int) publicKeyFile.length()];
                inputStream.read(keyBytes);
                Signature sign = Signature.getInstance("SHA256withRSA");
                sign.initSign(keyPrivada);
                byte[] bytes = keyBytes;

                sign.update(bytes);
                byte[] signature = sign.sign();

                sign.initVerify(keyPublica);
                sign.update(bytes);

                boolean bool = sign.verify(signature);
                if (bool) {
                    JOptionPane.showMessageDialog(null, "La firma si es verdadera.");
                } else {
                    JOptionPane.showMessageDialog(null, "La firma es falsa.");
                }

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrameFirma.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrameFirma.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FrameFirma.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(FrameFirma.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(FrameFirma.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SignatureException ex) {
            Logger.getLogger(FrameFirma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnVerificarActionPerformed

    private void btnHashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHashActionPerformed

        JFileChooser fc = new JFileChooser();
        int seleccion = fc.showOpenDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = fc.getSelectedFile();
            try {
                FileInputStream inputStream = new FileInputStream(archivo);
                byte[] inputBytes = new byte[(int) archivo.length()];
                inputStream.read(inputBytes);

                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hash = md.digest(inputBytes);

                String stringHash = bytesToHex(hash);
                
                JFileChooser guardar = new JFileChooser();
                int saveReturnValue = guardar.showSaveDialog(null);
                if (saveReturnValue == JFileChooser.APPROVE_OPTION) {
                    File encryptedFile = guardar.getSelectedFile();
                    FileOutputStream outputStream = new FileOutputStream(encryptedFile);
                    outputStream.write(stringHash.getBytes());
                    outputStream.close();
                }
                inputStream.close();
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FrameFirma.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FrameFirma.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(FrameFirma.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btnHashActionPerformed

    private void btnEncriptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncriptarActionPerformed

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccione la clave pública.");
        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File publicKeyFile = fileChooser.getSelectedFile();
            try {
                FileInputStream inputStreamPub = new FileInputStream(publicKeyFile);
                byte[] keyBytes = new byte[(int) publicKeyFile.length()];
                inputStreamPub.read(keyBytes);
                X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                keyPublica = keyFactory.generatePublic(spec);

                cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.ENCRYPT_MODE, keyPublica);

                JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("Seleccione el archivo que va encriptado.");
                int seleccion = fc.showOpenDialog(null);

                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    File archivo = fc.getSelectedFile();
                    try {
                        FileInputStream inputStream = new FileInputStream(archivo);
                        byte[] inputBytes = new byte[(int) archivo.length()];
                        inputStream.read(inputBytes);
                        byte[] outputBytes = cipher.doFinal(inputBytes);
                        JFileChooser guardar = new JFileChooser();
                        guardar.setDialogTitle("Seleccione donde se va guardar el archivo encriptado.");
                        int saveReturnValue = guardar.showSaveDialog(null);
                        if (saveReturnValue == JFileChooser.APPROVE_OPTION) {
                            File encryptedFile = guardar.getSelectedFile();
                            FileOutputStream outputStream = new FileOutputStream(encryptedFile);
                            outputStream.write(outputBytes);
                            outputStream.close();
                        }
                        inputStream.close();

                    } catch (IllegalBlockSizeException ex) {
                        Logger.getLogger(FrameFirma.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (BadPaddingException ex) {
                        Logger.getLogger(FrameFirma.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FrameFirma.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FrameFirma.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(FrameFirma.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeySpecException ex) {
                Logger.getLogger(FrameFirma.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchPaddingException ex) {
                Logger.getLogger(FrameFirma.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeyException ex) {
                Logger.getLogger(FrameFirma.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btnEncriptarActionPerformed

    private void btnDesencriptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesencriptarActionPerformed

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccione la clave privada.");
        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File publicKeyFile = fileChooser.getSelectedFile();
            try {
                FileInputStream inputStreamPub = new FileInputStream(publicKeyFile);
                byte[] keyBytes = new byte[(int) publicKeyFile.length()];
                inputStreamPub.read(keyBytes);
                PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                keyPrivada = keyFactory.generatePrivate(spec);

                cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.DECRYPT_MODE, keyPrivada);

                JFileChooser fc = new JFileChooser();
                
                int seleccion = fc.showOpenDialog(null);
                fc.setDialogTitle("Seleccione el archivo que va desencriptado.");
                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    File archivo = fc.getSelectedFile();
                    try {
                        FileInputStream inputStream = new FileInputStream(archivo);
                        byte[] inputBytes = new byte[(int) archivo.length()];
                        inputStream.read(inputBytes);
                        byte[] outputBytes = cipher.doFinal(inputBytes);
                        JFileChooser guardar = new JFileChooser();
                        guardar.setDialogTitle("Seleccione donde se va guardar el archivo desencriptado.");
                        int saveReturnValue = guardar.showSaveDialog(null);
                        if (saveReturnValue == JFileChooser.APPROVE_OPTION) {
                            File encryptedFile = guardar.getSelectedFile();
                            FileOutputStream outputStream = new FileOutputStream(encryptedFile);
                            outputStream.write(outputBytes);
                            outputStream.close();

                        }
                        inputStream.close();

                    } catch (IllegalBlockSizeException ex) {
                        Logger.getLogger(FrameFirma.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (BadPaddingException ex) {
                        Logger.getLogger(FrameFirma.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FrameFirma.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FrameFirma.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(FrameFirma.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeySpecException ex) {
                Logger.getLogger(FrameFirma.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchPaddingException ex) {
                Logger.getLogger(FrameFirma.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeyException ex) {
                Logger.getLogger(FrameFirma.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btnDesencriptarActionPerformed

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameFirma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameFirma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameFirma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameFirma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameFirma().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDesencriptar;
    private javax.swing.JButton btnEncriptar;
    private javax.swing.JButton btnHash;
    private javax.swing.JButton btnLlave;
    private javax.swing.JButton btnVerificar;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
