package sga.Controller;

import sga.DAO.PerfilDao;
import sga.Model.PerfilUserModel;

public class PerfilController {
    private int cduser;
    private int cdperfil;
    private String op;

    public PerfilController(int cduser, int cdperfil, String op) {
        this.cduser = cduser;
        this.cdperfil = cdperfil;
        this.op = op;
    }
    
    public String dmlPerfil() {
        PerfilUserModel pum = new PerfilUserModel();
        pum.setCduser(cduser);
        pum.setCdperfil(cdperfil);
        pum.setOp(op);
        PerfilDao pd = new PerfilDao();
        String msg = null;
        msg = pd.dmlPerfilUser(pum);
        return msg;
    }
}
