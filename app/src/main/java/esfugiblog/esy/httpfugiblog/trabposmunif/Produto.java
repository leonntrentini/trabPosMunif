package esfugiblog.esy.httpfugiblog.trabposmunif;

/**
 * Created by Igor on 14/02/2017.
 */
public class Produto {
    private Integer id;
    private String nome;
    private String valor;

    public Produto(Integer id, String nome, String valor){
        this.id=id;
        this.nome = nome;
        this.valor = valor;
    }

    public Produto(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return  "\n" + this.id + " - " + this.nome + " : " + this.valor;
    }
}
