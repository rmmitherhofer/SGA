
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

public class PerfilDao {
   public List<PerfilUserModel> listPerfil(PerfilUserModel lpu) {
        try {
            BancoDadosDao conn = new BancoDadosDao();
            conn.connect();

            String strQuery;
            strQuery = (" Select ");
            strQuery += (" cd_Perfil ");
            strQuery += (" ,tp_Perfil ");
            strQuery += (" ,ds_Liberacao ");
            strQuery += (" ,ds_Sistema ");
            strQuery += (" ,ic_Ativo ");
            strQuery += (" from Perfil ");
            strQuery += (" where ic_Ativo = 1 ");

            PreparedStatement comando;
            comando = conn.connect().prepareStatement(strQuery);
            ResultSet result;
            result = comando.executeQuery();

            List<PerfilUserModel> listaPerfil = new ArrayList<PerfilUserModel>();

            while (result.next()) {
                PerfilUserModel pul = new PerfilUserModel();
                pul.setCdperfil(result.getInt("cd_Perfil"));
                pul.setTpperfil(result.getString("tp_Perfil"));
                pul.setDsliberacao(result.getString("ds_Liberacao"));
                pul.setDssistema(result.getString("ds_Sistema"));
                pul.setIcativoper(result.getInt("ic_Ativo"));
                listaPerfil.add(pul);
            }
            result.close();
            comando.close();
            conn.connect().close();

            return listaPerfil;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO ao carregar Perfil: " + e.getMessage());
            return null;
        }
    }
   
   public List<PerfilUserModel> listPerfilUser(PerfilUserModel lpu) {
        try {
            BancoDadosDao conn = new BancoDadosDao();
            conn.connect();

            String strQuery;
            strQuery = (" Select ");
            strQuery += (" pu.cd_Perfil ");
            strQuery += (" ,pu.cd_Usuario ");
            strQuery += (" ,p.ds_Liberacao ");
            strQuery += (" ,p.tp_Perfil ");
            strQuery += (" ,p.ds_Sistema ");
            strQuery += (" ,pu.ic_Ativo ");
            strQuery += (" from PerfilUsuario pu ");
            strQuery += (" inner join perfil p on pu.cd_Perfil = p.cd_Perfil and p.ic_Ativo = 1 ");
            strQuery += (" Where pu.cd_Usuario = " + lpu.getCduser());

            PreparedStatement comando;
            comando = conn.connect().prepareStatement(strQuery);
            ResultSet result;
            result = comando.executeQuery();

            List<PerfilUserModel> listaPerfil = new ArrayList<PerfilUserModel>();

            while (result.next()) {
                PerfilUserModel pul = new PerfilUserModel();
                pul.setCdperfil(result.getInt("cd_Perfil"));
                pul.setTpperfil(result.getString("tp_Perfil"));
                pul.setDsliberacao(result.getString("ds_Liberacao"));
                pul.setDssistema(result.getString("ds_Sistema"));
                pul.setIcativoper(result.getInt("ic_Ativo"));
                pul.setCduser(result.getInt("cd_Usuario"));
                pul.setIcativoper(result.getInt("ic_Ativo"));                
                listaPerfil.add(pul);
            }
            result.close();
            comando.close();
            conn.connect().close();

            return listaPerfil;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO ao carregar Perfil Parametrizado: " + e.getMessage());
            return null;
        }
    }
   
   public String dmlPerfilUser(PerfilUserModel pum) {
        BancoDadosDao conn = new BancoDadosDao();
        
        try (CallableStatement cstm = conn.connect().prepareCall("{call sp_PerfilUser(?,?,?)}")) {
            cstm.setInt(1, pum.getCdperfil());
            cstm.setInt(2, pum.getCduser());
            cstm.setString(3, pum.getOp());

            String msg = null;
            if (cstm.executeUpdate() > 0) {
                msg = "Perfil Vinculao com Sucesso";
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
