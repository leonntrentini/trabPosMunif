package esfugiblog.esy.httpfugiblog.trabposmunif;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ProdutoService produtoService;

    private EditText edId;
    private EditText edValor;
    private EditText edNome;
    private TextView tvResultado;
    private List<Produto> produtos;
    private Produto produtoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        produtoService = new ProdutoService(getBaseContext());

        edId = (EditText) findViewById(R.id.editTextId);
        edValor = (EditText) findViewById(R.id.editValor);
        edNome = (EditText) findViewById(R.id.editTextNome);
        tvResultado = (TextView) findViewById(R.id.tvResultado);;
    }


    @Override
    public void onClick(View v) {

    }

    public void salvar(View view) {
        Toast.makeText(getApplicationContext(), "Salvar", Toast.LENGTH_SHORT).show();
        Produto pr = new Produto();
        if (!edId.getText().toString().isEmpty()) {
            pr.setId(Integer.parseInt(edId.getText().toString()));
        }
        pr.setNome(edNome.getText().toString());
        pr.setValor(edValor.getText().toString());
        produtoService.salvar(pr);
        edId.setText("");
        edNome.setText("");
        edValor.setText("");
    }

    public void listar(View view) {
        Toast.makeText(getApplicationContext(), "Listando", Toast.LENGTH_SHORT).show();
        List<Produto> produtos = produtoService.buscar();
        tvResultado.setText(produtos.toString().replace("[","").replace("]","").replace(",",""));
    }

    public void excluir(View view) {
        Toast.makeText(getApplicationContext(), "Excluindo", Toast.LENGTH_SHORT).show();

        produtoService.remover(Integer.parseInt(edId.getText().toString()));
    }

    public void carrega(View view) {
        Produto prod=produtoService.buscar(Integer.parseInt(edId.getText().toString()));
        if (prod==null){
            Toast.makeText(getApplicationContext(), "Não Existe", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Carregando", Toast.LENGTH_SHORT).show();
        }
        edNome.setText(prod.getNome());
        edValor.setText(prod.getValor());
    }
}
