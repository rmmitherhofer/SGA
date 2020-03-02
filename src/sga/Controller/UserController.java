package sga.Controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import sga.DAO.PerfilDao;
import sga.DAO.UsuarioDao;
import sga.Model.PerfilUserModel;
import sga.Model.UsuarioModel;

public class UserController {

    private int cduser;
    private String nmusuario;
    private String dslogin;
    private String senha;
    private String senhaconf;
    private int cdmatricula;
    private String icativo;
    private String op;

    public UserController(int cduser, String nmusuario, String dslogin, String senha, String senhaconf, int cdmatricula, String icativo, String op) {
        this.cduser = cduser;
        this.nmusuario = nmusuario;
        this.dslogin = dslogin;
        this.senha = senha;
        this.senhaconf = senhaconf;
        this.cdmatricula = cdmatricula;
        this.icativo = icativo;
        this.op = op;

    }

    //Metodo que Busca e carrega todas as informaçoes do usuario.
    public List listUser() {
        int status = 0;
        if ("Selecionar".equals(icativo)){
            JOptionPane.showMessageDialog(null, "Selecione o Status para realizar a consulta.");
        }
        if ("Ativo".equals(icativo)) {
            status = 1;
        }
        UsuarioModel um = new UsuarioModel();
        um.setDslogin(dslogin);
        um.setNmusuario(nmusuario);
        um.setIcativo(status);
        um.setCdusuario(cduser);
        um.setOp(op);
        UsuarioDao ud = new UsuarioDao();
        List<UsuarioModel> listUser = new ArrayList<UsuarioModel>();
        listUser = ud.listUser(um);
        return listUser;
    }

    //Metodo que realiza processos de Insert, Update e Delete
    public String dmlUser() {
        int status = 0;
        String msg = null;
        UsuarioModel um = new UsuarioModel();
        um.setCdusuario(cduser);
        if ("Selecionar".equals(icativo)) {
            msg = "Selecione o Status";
            return msg;
        }else if ("Ativo".equals(icativo)) {
            status = 1;
        }
        um.setIcativo(status);
        if ("".equals(nmusuario)) {
            msg = "Informe o Nome do Usuario";
            return msg;
        }
        um.setNmusuario(nmusuario);
        um.setCdmatricula(cdmatricula);
        
        if ("".equals(dslogin)) {
            msg = "Informe o Login/Conta do Usuario";
            return msg;
        }
        um.setDslogin(dslogin);
        
        if ("".equals(senha) || "".equals(senhaconf)) {
            msg = "Informe a senha do usuario";
            return msg;
        } else if (!senha.equals(senhaconf)) {
            msg = "As senhas digitadas não são iguais";
            return msg;
        }
        um.setDssenha(senha);     
        
        um.setOp(op);
        UsuarioDao ud = new UsuarioDao();
        msg = ud.dmlUser(um);
        return msg;
    }

    //Load do codigo de matricula disponivel na base para ser utilizado para cadastro
    public int countUser() {
        int quant = 0;
        UsuarioDao ud = new UsuarioDao();
        quant = ud.quantUser();
        return quant;
    }

    //Load dos perfis vinculados ao usuario
    public List<PerfilUserModel> groupPerfil() {
        PerfilUserModel cum = new PerfilUserModel();
        cum.setCduser(cduser);
        PerfilDao pd = new PerfilDao();
        List<PerfilUserModel> listPerfilUser = new ArrayList<PerfilUserModel>();
        listPerfilUser = pd.listPerfilUser(cum);
        return listPerfilUser;
    }
}
