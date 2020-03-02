package sga.View;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sga.Controller.PerfilController;
import sga.Controller.UserController;
import sga.DAO.PerfilDao;
import sga.Model.PerfilUserModel;
import sga.Model.UsuarioModel;
import sga.Pacotes.TrataCaracteres;

public class SGAView extends javax.swing.JFrame {

    public SGAView() {
        initComponents();
        setLocationRelativeTo(null);
        txtCodUser.setDocument(new TrataCaracteres(4, "[aA-zZ]"));
        txtNome.setDocument(new TrataCaracteres(80, "[0-9]"));
        txtMatricula.setDocument(new TrataCaracteres(4, "[aA-zZ]"));
        txtConta.setDocument(new TrataCaracteres(20, "[0-9]"));
        pswSenha.setDocument(new TrataCaracteres(12, "aA0-zZ9"));
        pswSenhaConfirma.setDocument(new TrataCaracteres(12, "aA0-zZ9"));
        txtNomeBusca.setDocument(new TrataCaracteres(80, "[0-9]"));
        txtContaBusca.setDocument(new TrataCaracteres(20, "[0-9]"));
    }

    public void load() {
        UserController uc = new UserController(
                0,
                null,
                null,
                null,
                null,
                0,
                null,
                null
        );
        txtCodUser.setText(String.valueOf(uc.countUser()));
        btnAlterar.setEnabled(false);
        btnNovo.setEnabled(false);
        btnSalvar.setEnabled(true);
        txtCodUser.setEnabled(false);
        loadPerfil();
        tpAcesso.remove(pnConsulta);
        tpAcesso.remove(pnParametrizacao);
        tpAcesso.add(pnCadConta, "Cadastrar Conta", 0);
        tpAcesso.setSelectedIndex(0);
    }
    
    private void emptyAll(){
        DefaultTableModel dtmUser = (DefaultTableModel) tblUsuario.getModel();
        dtmUser.setNumRows(0);
        cmbStatus.setSelectedItem("Selecionar");
        txtNome.setText("");
        txtMatricula.setText("");
        txtConta.setText("");
        pswSenha.setText("");
        pswSenhaConfirma.setText("");                
        txtNomeBusca.setText("");
        txtContaBusca.setText("");
        btnAlterar.setEnabled(false);
        btnSalvar.setEnabled(true);
        btnNovo.setEnabled(false);
        cmbStatusBusca.setSelectedItem("Selecionar");
        tpAcesso.remove(pnConsulta);
        tpAcesso.remove(pnParametrizacao);
        tpAcesso.add(pnCadConta, "Cadastrar Conta", 0);
        tpAcesso.setSelectedIndex(0);
    }

    private void loadUser() {
        UserController uc = new UserController(
                0,
                txtNomeBusca.getText().trim(),
                txtContaBusca.getText().trim(),
                null,
                null,
                0,
                cmbStatusBusca.getSelectedItem().toString(),
                "B"
        );

        DefaultTableModel dtmUser = (DefaultTableModel) tblUsuario.getModel();
        dtmUser.setNumRows(0);

        List<UsuarioModel> listUser = new ArrayList<UsuarioModel>();
        listUser = uc.listUser();
        if (listUser.size() > 0) {
            for (UsuarioModel gUser : listUser) {
                dtmUser.addRow(new Object[]{
                    gUser.getCdusuario(),
                    gUser.getNmusuario(),
                    gUser.getDslogin(),
                    gUser.getCdmatricula(),
                    gUser.getIcativo()
                });
            }
        }
    }

    private void loadPerfil() {
        try {
            DefaultTableModel dtmPerfil = (DefaultTableModel) tblPerfil.getModel();
            dtmPerfil.setNumRows(0);
            PerfilDao pd = new PerfilDao();
            PerfilUserModel pum = new PerfilUserModel();
            List<PerfilUserModel> listPerfil = new ArrayList<PerfilUserModel>();
            listPerfil = pd.listPerfil(pum);
            try {
                for (PerfilUserModel gPerf : listPerfil) {
                    dtmPerfil.addRow(new Object[]{
                        gPerf.getCdperfil(),
                        gPerf.getDsliberacao(),
                        gPerf.getDssistema()
                    });
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getStackTrace());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.fillInStackTrace());
        }
    }

    private void loadPerfilUser() {
        loadPerfil();
        UserController cpc = new UserController(
                Integer.parseInt(txtCodUser.getText()),
                null,
                null,
                null,
                null,
                0,
                null,
                "B"
        );
        DefaultTableModel dtmPerfilUser = (DefaultTableModel) tblParametroPerfil.getModel();
        dtmPerfilUser.setNumRows(0);
        DefaultTableModel dtmPerfil = (DefaultTableModel) tblPerfil.getModel();
        List<PerfilUserModel> gPerfil = new ArrayList<PerfilUserModel>();
        try {
            gPerfil = cpc.groupPerfil();

            if (gPerfil != null) {
                for (PerfilUserModel gpum : gPerfil) {
                    dtmPerfilUser.addRow(new Object[]{
                        gpum.getCdperfil(),
                        gpum.getDsliberacao(),
                        gpum.getDssistema()

                    });

                    int i = 0;
                    int l = tblPerfil.getRowCount();
                    for (i = 0; i <= l; i++) {
                        int cdPerfil = Integer.parseInt(tblPerfil.getValueAt(i, 0).toString());
                        if (cdPerfil == gpum.getCdperfil()) {
                            dtmPerfil.removeRow(i);
                            break;
                        }
                    }

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.fillInStackTrace());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpAcesso = new javax.swing.JTabbedPane();
        pnCadConta = new javax.swing.JPanel();
        lblLogin = new javax.swing.JLabel();
        lblMatricula = new javax.swing.JLabel();
        lblCodigo = new javax.swing.JLabel();
        lblSenha = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        lblNmUser = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtConta = new javax.swing.JTextField();
        txtCodUser = new javax.swing.JTextField();
        pswSenha = new javax.swing.JPasswordField();
        txtMatricula = new javax.swing.JTextField();
        cmbStatus = new javax.swing.JComboBox<>();
        btnSalvar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnBusca = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        pswSenhaConfirma = new javax.swing.JPasswordField();
        lblSenhaConfirma = new javax.swing.JLabel();
        btnNovo = new javax.swing.JButton();
        pnConsulta = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuario = new javax.swing.JTable();
        lblNmUserBusca = new javax.swing.JLabel();
        txtNomeBusca = new javax.swing.JTextField();
        lblLoginBusca = new javax.swing.JLabel();
        txtContaBusca = new javax.swing.JTextField();
        lblStatusBusca = new javax.swing.JLabel();
        cmbStatusBusca = new javax.swing.JComboBox<>();
        btnVoltarBusca = new javax.swing.JButton();
        pnParametrizacao = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblParametroPerfil = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPerfil = new javax.swing.JTable();
        btnInclui = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        lblAcesso = new javax.swing.JLabel();
        lblAcessoParametrizado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("SGA Parametro");
        setResizable(false);

        lblLogin.setText("Conta *");

        lblMatricula.setText("Matricula");

        lblCodigo.setText("Codigo *");

        lblSenha.setText("Senha *");

        lblStatus.setText("Status *");

        lblNmUser.setText("Nome *");

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionar", "Ativo", "Inativo" }));

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sga/Imagens/Salvar.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sga/Imagens/Alterar.png"))); // NOI18N
        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnBusca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sga/Imagens/Buscar.png"))); // NOI18N
        btnBusca.setText("Consultar");
        btnBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaActionPerformed(evt);
            }
        });

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sga/Imagens/voltar2.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        pswSenhaConfirma.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                pswSenhaConfirmaFocusLost(evt);
            }
        });
        pswSenhaConfirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pswSenhaConfirmaActionPerformed(evt);
            }
        });

        lblSenhaConfirma.setText("Confirme a Senha *");

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnCadContaLayout = new javax.swing.GroupLayout(pnCadConta);
        pnCadConta.setLayout(pnCadContaLayout);
        pnCadContaLayout.setHorizontalGroup(
            pnCadContaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCadContaLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(pnCadContaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnCadContaLayout.createSequentialGroup()
                        .addComponent(btnSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAlterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBusca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSair))
                    .addComponent(lblNmUser)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnCadContaLayout.createSequentialGroup()
                        .addGroup(pnCadContaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMatricula)
                            .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSenha))
                        .addGroup(pnCadContaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnCadContaLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(pnCadContaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblLogin)
                                    .addComponent(txtConta, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnCadContaLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(lblSenhaConfirma))))
                    .addGroup(pnCadContaLayout.createSequentialGroup()
                        .addComponent(pswSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pswSenhaConfirma, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnCadContaLayout.createSequentialGroup()
                        .addGroup(pnCadContaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCodigo)
                            .addComponent(txtCodUser, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(65, 65, 65)
                        .addGroup(pnCadContaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblStatus)
                            .addGroup(pnCadContaLayout.createSequentialGroup()
                                .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(btnNovo)))))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        pnCadContaLayout.setVerticalGroup(
            pnCadContaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCadContaLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(pnCadContaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnCadContaLayout.createSequentialGroup()
                        .addGroup(pnCadContaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnCadContaLayout.createSequentialGroup()
                                .addComponent(lblCodigo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52))
                            .addGroup(pnCadContaLayout.createSequentialGroup()
                                .addGroup(pnCadContaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnCadContaLayout.createSequentialGroup()
                                        .addComponent(lblNmUser)
                                        .addGap(7, 7, 7))
                                    .addGroup(pnCadContaLayout.createSequentialGroup()
                                        .addComponent(lblStatus)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnCadContaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnNovo))
                                        .addGap(30, 30, 30)))
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblMatricula)
                        .addGap(7, 7, 7)
                        .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnCadContaLayout.createSequentialGroup()
                        .addComponent(lblLogin)
                        .addGap(7, 7, 7)
                        .addComponent(txtConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnCadContaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSenha)
                    .addComponent(lblSenhaConfirma))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnCadContaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pswSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pswSenhaConfirma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(pnCadContaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnAlterar)
                    .addComponent(btnBusca)
                    .addComponent(btnSair))
                .addContainerGap(117, Short.MAX_VALUE))
        );

        tpAcesso.addTab("Cadastrar Conta", pnCadConta);

        tblUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Coigo", "Nome", "Login", "Matricula", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsuario);

        lblNmUserBusca.setText("Nome");

        txtNomeBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeBuscaActionPerformed(evt);
            }
        });

        lblLoginBusca.setText("Conta");

        txtContaBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContaBuscaActionPerformed(evt);
            }
        });

        lblStatusBusca.setText("Status");

        cmbStatusBusca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionar", "Ativo", "Inativo" }));
        cmbStatusBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStatusBuscaActionPerformed(evt);
            }
        });

        btnVoltarBusca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sga/Imagens/voltar2.png"))); // NOI18N
        btnVoltarBusca.setText("Voltar");
        btnVoltarBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarBuscaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnConsultaLayout = new javax.swing.GroupLayout(pnConsulta);
        pnConsulta.setLayout(pnConsultaLayout);
        pnConsultaLayout.setHorizontalGroup(
            pnConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnConsultaLayout.createSequentialGroup()
                        .addGroup(pnConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnConsultaLayout.createSequentialGroup()
                                .addComponent(lblNmUserBusca)
                                .addGap(0, 317, Short.MAX_VALUE))
                            .addComponent(txtNomeBusca))
                        .addGap(18, 18, 18)
                        .addGroup(pnConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLoginBusca)
                            .addComponent(txtContaBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnConsultaLayout.createSequentialGroup()
                                .addComponent(lblStatusBusca)
                                .addGap(223, 223, 223))
                            .addGroup(pnConsultaLayout.createSequentialGroup()
                                .addComponent(cmbStatusBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                                .addComponent(btnVoltarBusca)
                                .addGap(33, 33, 33))))
                    .addGroup(pnConsultaLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        pnConsultaLayout.setVerticalGroup(
            pnConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnConsultaLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(pnConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnConsultaLayout.createSequentialGroup()
                        .addComponent(lblLoginBusca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtContaBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbStatusBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnConsultaLayout.createSequentialGroup()
                        .addComponent(lblNmUserBusca)
                        .addGap(7, 7, 7)
                        .addComponent(txtNomeBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnConsultaLayout.createSequentialGroup()
                        .addComponent(lblStatusBusca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVoltarBusca)))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );

        tpAcesso.addTab("Consultar Conta", pnConsulta);

        tblParametroPerfil.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descricao", "Sistema"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblParametroPerfil);

        tblPerfil.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descricao", "Sistema"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblPerfil);

        btnInclui.setText(">>");
        btnInclui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluiActionPerformed(evt);
            }
        });

        btnRemove.setText("<<");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        lblAcesso.setText("Acessos Disponiveis");

        lblAcessoParametrizado.setText("Acessos Parametrizado");

        javax.swing.GroupLayout pnParametrizacaoLayout = new javax.swing.GroupLayout(pnParametrizacao);
        pnParametrizacao.setLayout(pnParametrizacaoLayout);
        pnParametrizacaoLayout.setHorizontalGroup(
            pnParametrizacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnParametrizacaoLayout.createSequentialGroup()
                .addGroup(pnParametrizacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnParametrizacaoLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnParametrizacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnInclui, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnRemove, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(pnParametrizacaoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblAcesso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 371, Short.MAX_VALUE)))
                .addGroup(pnParametrizacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAcessoParametrizado)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(pnParametrizacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnParametrizacaoLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(472, Short.MAX_VALUE)))
        );
        pnParametrizacaoLayout.setVerticalGroup(
            pnParametrizacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnParametrizacaoLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(lblAcesso)
                .addGap(92, 92, 92)
                .addComponent(btnInclui)
                .addGap(42, 42, 42)
                .addComponent(btnRemove)
                .addContainerGap(219, Short.MAX_VALUE))
            .addGroup(pnParametrizacaoLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(lblAcessoParametrizado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pnParametrizacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnParametrizacaoLayout.createSequentialGroup()
                    .addGap(62, 62, 62)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        tpAcesso.addTab("Parametrização de Acesso", pnParametrizacao);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tpAcesso)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tpAcesso)
                .addContainerGap())
        );

        tpAcesso.getAccessibleContext().setAccessibleName("Parametrizaçao de Acesso");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIncluiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluiActionPerformed
        int x = 0;
        String msg = null;
        int i[] = tblPerfil.getSelectedRows();
        int l = tblPerfil.getSelectedRowCount();
        for (x = 0; x < l; x++) {
            if (i[x] > -1) {
                PerfilController pc = new PerfilController(
                        Integer.parseInt(txtCodUser.getText().trim()),
                        Integer.parseInt(tblPerfil.getValueAt(i[x], 0).toString().trim()),
                        "I"
                );
                msg = pc.dmlPerfil();
            } else {
                JOptionPane.showMessageDialog(null, "Não Foi Selecionado nenhum Registro");
                break;
            }
        }
        if (msg != null) {
            loadPerfilUser();
        }
    }//GEN-LAST:event_btnIncluiActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        int x = 0;
        String msg = null;
        int i[] = tblParametroPerfil.getSelectedRows();
        int l = tblParametroPerfil.getSelectedRowCount();
        for (x = 0; x < l; x++) {
            if (i[x] > -1) {
                PerfilController pc = new PerfilController(
                        Integer.parseInt(txtCodUser.getText().trim()),
                        Integer.parseInt(tblParametroPerfil.getValueAt(i[x], 0).toString().trim()),
                        "D"
                );
                msg = pc.dmlPerfil();
            } else {
                JOptionPane.showMessageDialog(null, "Não Foi Selecionado nenhum Registro");
                break;
            }
        }
        if (msg != null) {
            loadPerfilUser();
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void txtNomeBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeBuscaActionPerformed
        loadUser();
    }//GEN-LAST:event_txtNomeBuscaActionPerformed

    private void txtContaBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContaBuscaActionPerformed
        loadUser();
    }//GEN-LAST:event_txtContaBuscaActionPerformed

    private void cmbStatusBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStatusBuscaActionPerformed
    }//GEN-LAST:event_cmbStatusBuscaActionPerformed

    private void tblUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuarioMouseClicked
        int i = tblUsuario.getSelectedRow();
        UserController uc = new UserController(
                Integer.parseInt(tblUsuario.getValueAt(i, 0).toString()),
                null,
                null,
                null,
                null,
                0,
                null,
                "L"
        );
        List<UsuarioModel> listUser = new ArrayList<UsuarioModel>();
        listUser = uc.listUser();
        if (listUser.size() > 0) {
            for (UsuarioModel gUser : listUser) {
                txtCodUser.setText(String.valueOf(gUser.getCdusuario()));
                String status = null;
                if (gUser.getIcativo() == 1) {
                    status = "Ativo";
                } else {
                    status = "Inativo";
                }
                cmbStatus.setSelectedItem(status);
                txtNome.setText(String.valueOf(gUser.getNmusuario()));
                txtMatricula.setText(String.valueOf(gUser.getCdmatricula()));
                txtConta.setText(String.valueOf(gUser.getDslogin()));
                pswSenha.setText(String.valueOf(gUser.getDssenha()));
                pswSenhaConfirma.setText(String.valueOf(gUser.getDssenha()));
                loadPerfilUser();
            }
        }
        DefaultTableModel dtmUser = (DefaultTableModel) tblUsuario.getModel();
        dtmUser.setNumRows(0);
        txtNomeBusca.setText("");
        txtContaBusca.setText("");
        btnAlterar.setEnabled(true);
        btnSalvar.setEnabled(false);
        btnNovo.setEnabled(true);
        cmbStatusBusca.setSelectedItem("Selecionar");
        tpAcesso.remove(pnConsulta);
        tpAcesso.add(pnCadConta, "Alterar Conta", 0);
        tpAcesso.add(pnParametrizacao, "Parametrizar Acesso", 1);
        tpAcesso.setSelectedIndex(0);
    }//GEN-LAST:event_tblUsuarioMouseClicked

    private void btnBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaActionPerformed
        tpAcesso.remove(pnCadConta);
        tpAcesso.remove(pnParametrizacao);
        tpAcesso.add(pnConsulta, "Consultar Usuario", 0);
        tpAcesso.setSelectedIndex(0);
    }//GEN-LAST:event_btnBuscaActionPerformed

    private void btnVoltarBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarBuscaActionPerformed
        emptyAll();        
    }//GEN-LAST:event_btnVoltarBuscaActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        int matricula = 0;
        if (!"".equals(txtMatricula.getText())) {
            matricula = Integer.parseInt(txtMatricula.getText());
        }
        UserController uc = new UserController(
                Integer.parseInt(txtCodUser.getText().trim()),
                txtNome.getText().trim(),
                txtConta.getText().trim(),
                pswSenha.getText().trim(),
                pswSenhaConfirma.getText().trim(),
                matricula,
                cmbStatus.getSelectedItem().toString(),
                "I"
        );
        String msg = uc.dmlUser();
        JOptionPane.showMessageDialog(null, msg);
        if (msg.equals("Usuario Cadastrado com Sucesso")) {

            DefaultTableModel dtmUser = (DefaultTableModel) tblUsuario.getModel();
            dtmUser.setNumRows(0);
            loadPerfilUser();
            tpAcesso.add(pnCadConta, "Conta", 0);
            tpAcesso.add(pnParametrizacao, "Parametrizar Acesso", 1);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        int matricula = 0;
        if (!"".equals(txtMatricula.getText())) {
            matricula = Integer.parseInt(txtMatricula.getText());
        }
        UserController uc = new UserController(
                Integer.parseInt(txtCodUser.getText().trim()),
                txtNome.getText().trim(),
                txtConta.getText().trim(),
                pswSenha.getText().trim(),
                pswSenhaConfirma.getText().trim(),
                matricula,
                cmbStatus.getSelectedItem().toString(),
                "U"
        );
        String msg = uc.dmlUser();
        JOptionPane.showMessageDialog(null, msg);
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void pswSenhaConfirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pswSenhaConfirmaActionPerformed
    }//GEN-LAST:event_pswSenhaConfirmaActionPerformed

    private void pswSenhaConfirmaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pswSenhaConfirmaFocusLost
    }//GEN-LAST:event_pswSenhaConfirmaFocusLost

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        emptyAll();
    }//GEN-LAST:event_btnNovoActionPerformed

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
            java.util.logging.Logger.getLogger(SGAView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SGAView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SGAView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SGAView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SGAView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnBusca;
    private javax.swing.JButton btnInclui;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnVoltarBusca;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JComboBox<String> cmbStatusBusca;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAcesso;
    private javax.swing.JLabel lblAcessoParametrizado;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblLoginBusca;
    private javax.swing.JLabel lblMatricula;
    private javax.swing.JLabel lblNmUser;
    private javax.swing.JLabel lblNmUserBusca;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblSenhaConfirma;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblStatusBusca;
    private javax.swing.JPanel pnCadConta;
    private javax.swing.JPanel pnConsulta;
    private javax.swing.JPanel pnParametrizacao;
    private javax.swing.JPasswordField pswSenha;
    private javax.swing.JPasswordField pswSenhaConfirma;
    private javax.swing.JTable tblParametroPerfil;
    private javax.swing.JTable tblPerfil;
    private javax.swing.JTable tblUsuario;
    private javax.swing.JTabbedPane tpAcesso;
    private javax.swing.JTextField txtCodUser;
    private javax.swing.JTextField txtConta;
    private javax.swing.JTextField txtContaBusca;
    private javax.swing.JTextField txtMatricula;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNomeBusca;
    // End of variables declaration//GEN-END:variables
}
