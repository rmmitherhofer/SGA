
package sga.Pacotes;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class TrataCaracteres extends PlainDocument{
    int tam = 0;
    String r = "";
    
    public TrataCaracteres (int a, String r){
        this.tam = a;
        this.r = r;
    }
       @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        int tamanho = (this.getLength() + str.length());
        if (tamanho <= tam){
            super.insertString(offs, str.replaceAll(r, ""), a);
        }else{
            super.insertString(offs, str.replaceAll("[aA0-zZ9]", ""), a);
        }
    }
}
