package esfugiblog.esy.httpfugiblog.trabposmunif;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor on 14/02/2017.
 */
public class ProdutoService {
    private SQLiteDatabase db;
    private BancoDados banco;

    public ProdutoService(Context context) {
        banco=new BancoDados(context);
    }

    public boolean salvar(Produto produto) {
        ContentValues valores;
        long resultado = -1;

        db = banco.getWritableDatabase();
        valores = new ContentValues();


        valores.put(BancoDados.NOME, produto.getNome());
        valores.put(BancoDados.VALOR, produto.getValor());



        if (produto.getId() != null && produto.getId() != 0){
            String where = BancoDados.ID + " = " + produto.getId();
            resultado = db.update(BancoDados.TABELA, valores, where, null);
        } else {
            resultado = db.insert(BancoDados.TABELA, null, valores);
        }

        db.close();
        return resultado != -1;
    }
    public boolean remover(Integer id){
        String where = BancoDados.ID + " = " + id;
        db = banco.getReadableDatabase();
        int resultado = db.delete(BancoDados.TABELA, where, null);
        db.close();
        return resultado != -1;
    }

    public List<Produto> buscar(){
        Cursor dados;
        List<Produto> produtos = new ArrayList<>();
        String[] campos =  {BancoDados.ID, BancoDados.NOME,BancoDados.VALOR};

        db = banco.getReadableDatabase();
        dados = db.query(banco.TABELA, campos, null, null, null, null, null, null);

        if(dados!=null && dados.moveToFirst()){
            do {
                produtos.add(new Produto(dados.getInt(0), dados.getString(1),  dados.getString(2)));
            } while (dados.moveToNext());
        }

        db.close();
        return produtos;
    }

    public Produto buscar(Integer id){
        Cursor dados;
        Produto prod=null;
        String[] campos =  {BancoDados.ID,BancoDados.NOME, BancoDados.VALOR};

        db = banco.getReadableDatabase();
        dados = db.query(banco.TABELA, campos, "id="+id, null, null, null, null, null);

        if(dados!=null && dados.moveToFirst()){
            prod=new Produto(dados.getInt(0), dados.getString(1), dados.getString(2));
        }
        db.close();
        return prod;
    }

}
