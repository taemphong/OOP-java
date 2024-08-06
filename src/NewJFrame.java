
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class NewJFrame extends javax.swing.JFrame {

    Connection conn = null;

    void connectDB() {
        try {
            String url = "jdbc:sqlite:oopproject.db"; //กำหนด url ของฐานข้อมูล
            conn = DriverManager.getConnection(url);
            JOptionPane.showMessageDialog(null, "Connection Sucess");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
        }
    }
    
        public void  insertDataIntoComboBox(){
            //jComboBox1.addItem(null);
       try { 
           String sql = "SELECT * FROM Categories ";
           PreparedStatement stmt = conn.prepareStatement(sql);
           ResultSet rs = stmt.executeQuery(); 
           while(rs.next()){
              
              int c1 = rs.getInt("categoryID");
           String c2 = rs.getString("categoryName");
           String c3 = rs.getString("description");
              //สร้างออบเจค ของ class Product
          //  Product p = new Product(c1,c2,c3,c4,c5,c6);
          Category cat = new Category(c1,c2,c3);
             //ใส่ออบเจคเข้าไปใน ComboBox
            jComboBox1.addItem(cat);
           }
           
           
           System.out.println("select ข้อมูลสำเร็จ");
        }catch (SQLException e) {
           System.out.println("Error " + e.getMessage());
        } 
        
    }


    Product getProductFromUI() {
        
        if(!ProjectUtil.verifyInteger(jTextField1.getText())){
            JOptionPane.showMessageDialog(null, "กรุณากรอก ProductID เป็นจำนวนเต็ม");
            return null;
        }
        
        if(!ProjectUtil.verifyInteger(jTextField3.getText())){
            JOptionPane.showMessageDialog(null, "กรุณากรอก CategoryID เป็นจำนวนเต็ม");
            return null;
        }
        
        if(!ProjectUtil.verifyDouble(jTextField4.getText())){
            JOptionPane.showMessageDialog(null, "กรุณากรอก Price เป็นจำนวนทศนิยม");
            return null;
        }
        
        int productID = Integer.parseInt(jTextField1.getText());
        String productName = jTextField2.getText();
        Category category = (Category)jComboBox1.getSelectedItem();
        double price = Double.parseDouble(jTextField4.getText());
        ImageIcon icon = (ImageIcon) jLabel9.getIcon();
        BufferedImage picture = ProjectUtil.getBufferedImageFromIcon(icon);
        Product p = new Product(productID, productName, category, price, picture);

        return p;
    }

    void setTableFromResultSet(ResultSet rs) {
        String col[] = {"รหัสสินค้า", "ชื่อสินค้า", "รหัสประเภทสินค้า", "ราคา", "รูปภาพ", "ชื่อประเภทสินค้า", "รายละเอียดประเภทสินค้า", "รายละเอียด Product"};
        Class[] column_types = {Integer.class, String.class, Integer.class, Double.class, ImageIcon.class, String.class, String.class, Product.class};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                try {
                    return column_types[column];
                } catch (Exception e) {
                    e.printStackTrace();
                    return Object.class;
                }
            }
        };

        //ลิ้ง tableModel กับ jTable1
        jTable1.setModel(tableModel);
        jTable1.setRowHeight(80);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(80);

        try {
            while (rs.next()) {
                //อ่านข้อมูลแถวปัจจุบัน
                int c1 = rs.getInt("ProductID");
                String c2 = rs.getString("ProductName");
                int c3 = rs.getInt("CategoryID");
                double c4 = rs.getDouble("Price");
                InputStream c5 = rs.getBinaryStream("Picture");
                String c6 = rs.getString("CategoryName");
                String c7 = rs.getString("Description");        
                
                Category cat = new Category(c3,c6,c7);
                BufferedImage image = null;
                ImageIcon icon = null;
                if (c5 != null) {
                    image = ImageIO.read(c5);
                    Image dimg = image.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                    icon = new ImageIcon(dimg);
                }
                Product p = new Product(c1, c2, cat, c4, image);

                //สร้าง row
                Object[] row = {c1, c2, c3, c4, icon, c6, c7, p};
                //เพิ่มเข้าไป TableModel
                tableModel.addRow(row);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
        }
    }

    
    
    public NewJFrame() {
        initComponents();
        connectDB();
        insertDataIntoComboBox();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        picturePanel1 = new org.netbeans.modules.form.InvalidComponent();
        jTextField8 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton4.setText("browse");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton5.setText("ค้นหา");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton6.setText("Reset");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("INSERT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 0, 51));
        jLabel5.setText("Picture");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("DELETE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setText("UPDATE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 51, 0));
        jLabel6.setText("ค้นหาจากทุกคอลัม");

        jTextField6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField6.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField6CaretUpdate(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 51, 0));
        jLabel7.setText("ค้นหาจากราคา");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 51));
        jLabel1.setText("ProductID");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 51));
        jLabel2.setText("ProductName");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 51));
        jLabel3.setText("Price");

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTextField3.setEditable(false);
        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 0, 51));
        jLabel4.setText("Category");

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTextField7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 51, 0));
        jLabel8.setText("ถึง");

        jLabel10.setForeground(new java.awt.Color(255, 51, 51));
        jLabel10.setText("CategoryID");

        jLabel11.setForeground(new java.awt.Color(255, 51, 0));
        jLabel11.setText("CategoryName");

        jTextField9.setEditable(false);

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel12.setForeground(new java.awt.Color(255, 51, 51));
        jLabel12.setText("Description");

        jTextField10.setEditable(false);

        javax.swing.GroupLayout picturePanel1Layout = new javax.swing.GroupLayout(picturePanel1);
        picturePanel1.setLayout(picturePanel1Layout);
        picturePanel1Layout.setHorizontalGroup(
            picturePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(picturePanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, picturePanel1Layout.createSequentialGroup()
                .addContainerGap(222, Short.MAX_VALUE)
                .addGroup(picturePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, picturePanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, picturePanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(picturePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(picturePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(picturePanel1Layout.createSequentialGroup()
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(145, 145, 145))))
            .addGroup(picturePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(picturePanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(picturePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(picturePanel1Layout.createSequentialGroup()
                            .addGroup(picturePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(picturePanel1Layout.createSequentialGroup()
                                    .addComponent(jButton1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButton2))
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(picturePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(picturePanel1Layout.createSequentialGroup()
                                    .addGroup(picturePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField1)
                                        .addComponent(jTextField2)
                                        .addComponent(jTextField4)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton4))
                                .addGroup(picturePanel1Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(jButton3)
                                    .addGap(26, 26, 26)
                                    .addComponent(jButton6)))))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                    .addGroup(picturePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(picturePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(picturePanel1Layout.createSequentialGroup()
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton5))
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(52, 52, 52)))
        );
        picturePanel1Layout.setVerticalGroup(
            picturePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(picturePanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(picturePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField3)
                    .addComponent(jLabel11)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(picturePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(picturePanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(picturePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(picturePanel1Layout.createSequentialGroup()
                    .addGroup(picturePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, picturePanel1Layout.createSequentialGroup()
                            .addGap(48, 48, 48)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                        .addGroup(picturePanel1Layout.createSequentialGroup()
                            .addGap(8, 8, 8)
                            .addGroup(picturePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(picturePanel1Layout.createSequentialGroup()
                                    .addGroup(picturePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(picturePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel8)
                                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton5)))
                                .addGroup(picturePanel1Layout.createSequentialGroup()
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(68, 68, 68)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(picturePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton4))))
                            .addGap(7, 7, 7)))
                    .addGroup(picturePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton2)
                        .addGroup(picturePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton3))
                        .addComponent(jButton6))
                    .addGap(18, 18, 18)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(17, 17, 17)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(picturePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(picturePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        Category cat = (Category)jComboBox1.getSelectedItem();
        if(cat!=null){
            jTextField3.setText(cat.getCategoryID()+"");
            jTextField9.setText(cat.getCategoryName()+"");
            jTextField10.setText(cat.getDescription());
        }else{
            jTextField3.setText("");
            jTextField9.setText("");
            jTextField10.setText("");
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField6CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField6CaretUpdate
        try {
            String url = "jdbc:sqlite:oopproject.db"; //กำหนด url ของฐานข้อมูล
            conn = DriverManager.getConnection(url);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
        }

        try {
            //รับ  input จาก user สร้าง คิวรี และส่งไปที่ฐานข้อมูล
            String search_string = jTextField6.getText();

            String sql = "SELECT Products.*,Categories.categoryName, Categories.Description "
            + "  FROM Products "
            + " INNER JOIN Categories "
            + " ON Products.CategoryID = Categories.CategoryID "
            + " WHERE  ProductID LIKE ? "
            + "    OR  ProductName LIKE ? "
            + "    OR  Categories.CategoryID LIKE ? "
            + "    OR  Price LIKE ? ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + search_string + "%");
            stmt.setString(2, "%" + search_string + "%");
            stmt.setString(3, "%" + search_string + "%");
            stmt.setString(4, "%" + search_string + "%");

            ResultSet rs = stmt.executeQuery();
            setTableFromResultSet(rs);

            //JOptionPane.showMessageDialog(null,"Select Sucess");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
        }
    }//GEN-LAST:event_jTextField6CaretUpdate

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        Product p = getProductFromUI();
        if(p==null)return;
        try {
            //สร้าง sql
            String sql = "UPDATE Products "
            + " SET ProductName = ?, "
            + "     CategoryID = ?, "
            + "          Price = ?, "
            + "        Picture = ? "
            + " WHERE ProductID = ? ";
            //สร้างตัวส่ง sql
            PreparedStatement stmt = conn.prepareStatement(sql);
            //เติมค่า ? ใน sql
            stmt.setInt(5, p.getProductID()); // เติม ? ตัวที่ 1 ด้วยค่า id
            stmt.setString(1, p.getProductName()); // เติม ? ตัวที่ 2 ด้วยค่า name_english
            stmt.setInt(2, p.getCategory().getCategoryID()); // เติม ? ตัวที่ 3 ด้วยค่า attack
            stmt.setDouble(3, p.getPrice());
            byte[] bytes = ProjectUtil.getByteArrayFromBufferedImage(p.getPicture());

            stmt.setBytes(4, bytes);

            stmt.executeUpdate(); //ส่ง SQL ไปยังฐานข้อมูล
            JOptionPane.showMessageDialog(null, "แก้ไขข้อมูลสำเร็จ");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Product p = getProductFromUI();
        if(p==null)return;
        try {
            //สร้าง sql
            String sql = "DELETE FROM Products WHERE ProductID = ?";
            //สร้างตัวส่ง sql
            PreparedStatement stmt = conn.prepareStatement(sql);
            //เติมค่า ? ใน sql
            stmt.setInt(1, p.getProductID()); // เติม ? ตัวที่ 1 ด้วยค่า id
            stmt.executeUpdate(); //ส่ง SQL ไปยังฐานข้อมูล
            JOptionPane.showMessageDialog(null, "ลบข้อมูลสำเร็จ");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int row = jTable1.rowAtPoint(evt.getPoint());
        int column = 7; //แก้
        Product p = (Product) jTable1.getModel().getValueAt(row, column);
        jTextField1.setText(p.getProductID() + "");
        jTextField2.setText(p.getProductName() + "");
        //jTextField3.setText(p.getCategory().getCategoryID() + "");
        jComboBox1.setSelectedItem(p.getCategory());

        jTextField4.setText(p.getPrice() + "");

        ImageIcon icon =  ProjectUtil.getIconFromBufferedImage(p.getPicture()
            ,jLabel9.getWidth(), jLabel9.getHeight());
        jLabel9.setIcon(icon);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Product p = getProductFromUI();
        if(p==null)return;

        try {
            //สร้าง sql
            String sql = "INSERT INTO "
            + " Products "
            + " VALUES (?, ?, ?, ?, ? ) ";
            //สร้างตัวส่ง sql
            PreparedStatement stmt = conn.prepareStatement(sql);
            //เติมค่า ? ใน sql
            stmt.setInt(1, p.getProductID()); // เติม ? ตัวที่ 1 ด้วยค่า id
            stmt.setString(2, p.getProductName()); // เติม ? ตัวที่ 2 ด้วยค่า name_english
            stmt.setInt(3, p.getCategory().getCategoryID()); // เติม ? ตัวที่ 3 ด้วยค่า attack
            stmt.setDouble(4, p.getPrice());

            byte[] bytes = ProjectUtil.getByteArrayFromBufferedImage(p.getPicture());
            stmt.setBytes(5, bytes);

            stmt.executeUpdate(); //ส่ง SQL ไปยังฐานข้อมูล
            JOptionPane.showMessageDialog(null, "เพิ่มข้อมูลสำเร็จ");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jTextField10.setText("");
        jLabel9.setText("");
        jLabel9.setIcon(null);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(!ProjectUtil.verifyInteger(jTextField7.getText())){
            JOptionPane.showMessageDialog(null, "กรุณากรอกช่วงราคาในการค้นหาเป็นจำนวนเลข");
            return;
        }

        if(!ProjectUtil.verifyInteger(jTextField8.getText())){
            JOptionPane.showMessageDialog(null, "กรุณากรอกช่วงราคาในการค้นหาเป็นจำนวนเลข");
            return;
        }

        try {
            //รับ  input จาก user สร้าง คิวรี และส่งไปที่ฐานข้อมูล
            double from_price = Double.parseDouble(jTextField7.getText());
            double to_price = Double.parseDouble(jTextField8.getText());

            String sql = "SELECT Products.*,Categories.categoryName, Categories.Description "
            + "  FROM Products "
            + " INNER JOIN Categories "
            + " ON Products.CategoryID = Categories.CategoryID "
            + "WHERE price >= ? AND price <= ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, from_price);
            stmt.setDouble(2, to_price);

            ResultSet rs = stmt.executeQuery();
            setTableFromResultSet(rs);

            JOptionPane.showMessageDialog(null, "Select Sucess");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        final int option = fileChooser.showOpenDialog(this);
        if (option != JFileChooser.APPROVE_OPTION) {
            return;
        }

        final File selectedFile = fileChooser.getSelectedFile();

        URL url;
        try {
            url = selectedFile.toURI().toURL();
        } catch (final MalformedURLException e1) {
            throw new RuntimeException(e1);
        }
        //jTextField4 : เก็บตำแหน่งไฟล์รูปที่อยู่ในเครื่อง
        jTextField5.setText(selectedFile.getAbsolutePath());
        try {
            BufferedImage img = ImageIO.read(new File(selectedFile.getAbsolutePath()));

            ImageIcon icon = ProjectUtil.getIconFromBufferedImage(img
                ,jLabel9.getWidth(),jLabel9.getHeight());
            //jLabel9 : ที่แสดงรูป
            jLabel9.setText("");
            jLabel9.setIcon(icon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<Category> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private org.netbeans.modules.form.InvalidComponent picturePanel1;
    // End of variables declaration//GEN-END:variables
}
