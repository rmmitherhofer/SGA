package sga.DAO;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import sga.Model.PerfilUserModel;
import sga.Model.UsuarioModel;

public class UsuarioDao {

    public List<UsuarioModel> listUser(UsuarioModel lu) {
        try {
            BancoDadosDao conn = new BancoDadosDao();
            conn.connect();

            String strQuery;
            strQuery = (" Select ");
            strQuery += (" cd_Usuario ");
            strQuery += (" ,nm_Usuario ");
            strQuery += (" ,ds_Login ");
            strQuery += (" ,ds_Senha ");
            strQuery += (" ,cd_Matricula ");
            strQuery += (" ,ic_Ativo ");
            strQuery += (" from Usuario ");
            strQuery += (" where 1 = 1 ");

            if (lu.getCdusuario() != 0) {
                strQuery += (" and cd_Usuario = " + lu.getCdusuario());
            }
            if (!"".equals(lu.getDslogin()) && lu.getDslogin() != null) {
                strQuery += (" and ds_Login like '%" + lu.getDslogin() + "%'");
            }
            if (!"".equals(lu.getNmusuario()) && lu.getNmusuario() != null) {
                strQuery += (" and nm_Usuario like '%" + lu.getNmusuario() + "%'");
            }
            if (lu.getOp() != null && "B".equals(lu.getOp())){
            strQuery += (" and ic_Ativo = " + lu.getIcativo());
            }

            PreparedStatement comando;
            comando = conn.connect().prepareStatement(strQuery);
            ResultSet result;
            result = comando.executeQuery();

            List<UsuarioModel> listUser = new ArrayList<UsuarioModel>();

            while (result.next()) {
                UsuarioModel um = new UsuarioModel();
                um.setCdusuario(result.getInt("cd_Usuario"));
                um.setNmusuario(result.getString("nm_Usuario"));
                um.setDslogin(result.getString("ds_Login"));
                um.setDssenha(result.getString("ds_Senha"));
                um.setCdmatricula(result.getInt("cd_Matricula"));
                um.setIcativo(result.getInt("ic_Ativo"));
                listUser.add(um);
            }
            result.close();
            comando.close();
            conn.connect().close();

            return listUser;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO ao carregar Conta: " + e.getMessage());
            return null;
        }
    }

    public int quantUser() {
        try {
            BancoDadosDao conn = new BancoDadosDao();
            conn.connect();

            String strQuery;
            strQuery = (" Select ");
            strQuery += (" Max(cd_Usuario) + 1 as cd_Usuario");
            strQuery += (" from Usuario ");

            PreparedStatement comando;
            comando = conn.connect().prepareStatement(strQuery);
            ResultSet result;
            result = comando.executeQuery();

            int cduser = 0;
            while (result.next()) {
                cduser = result.getInt("cd_Usuario");
            }
            result.close();
            comando.close();
            conn.connect().close();

            return cduser;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO ao carregar Codigo de Usuario Disponivel: " + e.getMessage());
            return 0;
        }
    }

    public String dmlUser(UsuarioModel um) {
        BancoDadosDao conn = new BancoDadosDao();

        try (CallableStatement cstm = conn.connect().prepareCall("{call sp_Usuario(?,?,?,?,?,?,?)}")) {
            cstm.setInt(1, um.getCdusuario());
            cstm.setString(2, um.getNmusuario());
            cstm.setString(3, um.getDslogin());
            cstm.setString(4, um.getDssenha());
            cstm.setInt(5, um.getCdmatricula());
            cstm.setInt(6, um.getIcativo());
            cstm.setString(7, um.getOp());

            String msg = null;
            if (cstm.executeUpdate() > 0) {
                if (um.getOp().equals("I")) {
                    msg = "Usuario Cadastrado com Sucesso";
                } else if (um.getOp().equals("U")) {
                    msg = "Usuario Alterado com Sucesso";
                }
            }
            cstm.close();
            conn.connect().close();
            return msg;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
            return null;
        }
    }
}
