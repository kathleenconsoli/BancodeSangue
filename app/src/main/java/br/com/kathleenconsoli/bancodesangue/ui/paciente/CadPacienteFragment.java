package br.com.kathleenconsoli.bancodesangue.ui.paciente;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.kathleenconsoli.bancodesangue.R;
import br.com.kathleenconsoli.bancodesangue.model.Paciente;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CadPacienteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CadPacienteFragment extends Fragment implements View.OnClickListener, Response.ErrorListener, Response.Listener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;

    //Objetos de tela
    private EditText etnome;
    private EditText etdatadenascimento;
    private RadioGroup rgSexo;
    private RadioGroup rgjadoou;
    private EditText etDoacaoData;
    private Spinner sptipos_sanguineo;
    private Button btsalvar;

    //volley
    private RequestQueue requestQueue;
    private JsonObjectRequest jsonObjectReq;

    public CadPacienteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CadPacienteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CadPacienteFragment newInstance(String param1, String param2) {
        CadPacienteFragment fragment = new CadPacienteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view =  inflater.inflate(R.layout.fragment_cad_paciente, container, false);
//instanciando a fila de requests - caso o objeto seja o view
        this.requestQueue = Volley.newRequestQueue(view.getContext());
//inicializando a fila de requests do SO
        this.requestQueue.start();
        //Binding (vinculo entre a tela e o objeto
        this.etnome = view.findViewById(R.id.etnome);
        this.etdatadenascimento = view.findViewById(R.id.etdatadenascimento);
        this.rgSexo = view.findViewById(R.id.rgSexo);
        this.rgjadoou = view.findViewById(R.id.rgjadoou);
        this.etDoacaoData = view.findViewById(R.id.etDoacaoData);
        this.sptipos_sanguineo = view.findViewById(R.id.sptipos_sanguineo);
        this.btsalvar = view.findViewById(R.id.btsalvar);

        //definindo o listener do botão
        this.btsalvar.setOnClickListener(this);

        //retorno da função
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btsalvar) {

            try {
                //instanciar meu objeto de negócio
                Paciente paciente = new Paciente();
                //Pegar os dados da tela e por no objeto
                paciente.setNomePaciente(this.etnome.getText().toString());
                paciente.setDatadenascimento(this.etdatadenascimento.getText().toString());
                int radioButtonSexo =
                        rgSexo.getCheckedRadioButtonId();
                View radioButtonsexo = rgSexo.findViewById(radioButtonSexo);
                paciente.setSexo(String.valueOf(rgSexo.indexOfChild(radioButtonsexo)));
                int radioButtonJadoou =
                        rgjadoou.getCheckedRadioButtonId();
                View radioButtonjadoou = rgjadoou.findViewById(radioButtonJadoou);
                paciente.setJadoouantes(String.valueOf(rgjadoou.indexOfChild(radioButtonjadoou)));
                paciente.setUltimadoacao(this.etDoacaoData.getText().toString());
                paciente.setSexo(String.valueOf(this.sptipos_sanguineo.getSelectedItemPosition()));
                jsonObjectReq = new JsonObjectRequest(
                        Request.Method.POST,
                        "http://10.0.2.2:8080/seg/cadusuario.php",
                        paciente.toJsonObject(), this, this);
                requestQueue.add(jsonObjectReq);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Snackbar mensagem = Snackbar.make(view,
                "Ops! Houve um problema ao realizar o cadastro: " +
                        error.toString(),Snackbar.LENGTH_LONG);
        mensagem.show();
    }

    @Override
    public void onResponse(Object response) {
        try {
//instanciando objeto para manejar o JSON que recebemos
            JSONObject jason = new JSONObject(response.toString());
//context e text são para a mensagem na tela o Toast
            Context context = view.getContext();
//pegando mensagem que veio do json
            CharSequence mensagem = jason.getString("message");
//duração da mensagem na tela
            int duration = Toast.LENGTH_SHORT;
//verificando se salvou sem erro para limpar campos da tela
            if (jason.getBoolean("success")){
//limpar campos da tela
                this.etdatadenascimento.setText("");
                this.etDoacaoData.setText("");
                this.etnome.setText("");
//selecionando primeiro item dos spinners
                this.sptipos_sanguineo.setSelection(0);
                //radio group
                this.rgSexo.clearCheck();
                this.rgjadoou.clearCheck();
            }
//mostrando a mensagem que veio do JSON
            Toast toast = Toast.makeText (context, mensagem, duration);
            toast.show();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}

