
package sga.Model;

public class UsuarioModel {
    private int cdusuario;
    private String nmusuario;
    private String dslogin;
    private String dssenha;
    private int cdmatricula;
    private int icativo;
    private String op;

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public int getCdusuario() {
        return cdusuario;
    }

    public void setCdusuario(int cdusuario) {
        this.cdusuario = cdusuario;
    }

    public String getNmusuario() {
        return nmusuario;
    }

    public void setNmusuario(String nmusuario) {
        this.nmusuario = nmusuario;
    }

    public String getDslogin() {
        return dslogin;
    }

    public void setDslogin(String dslogin) {
        this.dslogin = dslogin;
    }

    public String getDssenha() {
        return dssenha;
    }

    public void setDssenha(String dssenha) {
        this.dssenha = dssenha;
    }

    public int getCdmatricula() {
        return cdmatricula;
    }

    public void setCdmatricula(int cdmatricula) {
        this.cdmatricula = cdmatricula;
    }

    public int getIcativo() {
        return icativo;
    }

    public void setIcativo(int icativo) {
        this.icativo = icativo;
    }
}
